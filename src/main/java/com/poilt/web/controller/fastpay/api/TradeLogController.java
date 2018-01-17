package com.poilt.web.controller.fastpay.api;

import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.poilt.enums.StatusCode;
import com.poilt.exception.JsonException;
import com.poilt.exception.Result;
import com.poilt.model.PayMessage;
import com.poilt.model.PayMoney;
import com.poilt.model.fastpay.Card;
import com.poilt.model.fastpay.Merch;
import com.poilt.model.fastpay.TradeLog;
import com.poilt.service.fastpay.CardService;
import com.poilt.service.fastpay.MerchService;
import com.poilt.service.fastpay.TiedCardService;
import com.poilt.service.fastpay.TradeLogService;
import com.poilt.utils.Serialnumber;
import com.poilt.utils.TradeHttpRequestExecute;
import com.poilt.utils.WebUtils;

@Controller
public class TradeLogController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MerchService merchService;

	@Autowired
	private CardService cardService;

	@Autowired
	private TradeLogService tradeLogService;

	@Autowired
	private TradeHttpRequestExecute tradeExecute;
	
	@Autowired
	private TiedCardService tiedCardService;
	
	@Value("${fastpay.rateCode}")
	private String rateCode;
	
	@Value("${fastpay.payNotifyUrl}")
	private String payNotifyUrl;

	@RequestMapping("/fastpay_start_pay")
	public String startPay(TradeLog trade, HttpSession httpSession, HttpServletResponse response, Model model) throws Exception {
		String openId = WebUtils.getSessionValue("openId");
		logger.info("Session openId：" + openId);
		/*是否已绑结算卡*/
		Merch merch = merchService.findByOpenId(openId);
		String tiedCard = merch.getTiedCard();
		if(tiedCard == null || "N".equals(tiedCard) || "".equals(tiedCard)){
			return "/tiedCard";
		}
		String[] bankCodes = trade.getBankCode().split(",");
		trade.setOpenId(openId);
		String tradeNo = Serialnumber.getSerial();
		model.addAttribute("orderNo", tradeNo);
		/*记录交易流水号*/
		httpSession.setAttribute("tradeNo", tradeNo);
		trade.setTradeNo(tradeNo);
		trade.setOrderNo(tradeNo);
		trade.setUserName(merch.getName());
		trade.setIdCard(merch.getIdCard());
		trade.setBankCode(bankCodes[0]);
		trade.setBankAbbr(bankCodes[1]);
		trade.setStatus("0");
		tradeLogService.insert(trade);
		/*
		 * 是否绑卡 如果有绑卡记录，看下卡状态是否开通成功
		 */
		Card card = cardService.findByCardNo(openId, trade.getPayCardNo());
		if (card == null) {
			card = new Card();
			card.setOpenId(openId);
			card.setIdCard(merch.getIdCard());
			card.setPhone(merch.getPhone());
			card.setCardNo(trade.getPayCardNo());
			card.setCardType("2");// 信用卡
			card.setCardName(merch.getName());
			card.setBankCode(bankCodes[0]);
			card.setBankAbbr(bankCodes[1]);
			card.setBankName(trade.getBankName());
			cardService.insert(card);
		}
		JSONObject result = tiedCardService.tiedCreditCard(openId, trade.getPayCardNo());
		String respCode = result.getString("respCode");
		if("000000".equals(respCode)){
			String status = result.getJSONObject("result").getString("activateStatus");
			if("2".equals(status)){
				//String openId = "o1ZZ61qoovpSAhCjrk144BUc6NLY";
				/*logger.info("Session openId：" + openId);
				Merch merch = merchService.findByOpenId(openId);
				if(null == orderNo || "".equals(orderNo)){
					orderNo = httpSession.getAttribute("tradeNo").toString();
				}*/
				logger.info("支付订单号：" + tradeNo);
				TradeLog tradeLog = tradeLogService.findByTradeNo(openId, tradeNo);
				PayMessage message = new PayMessage();
				message.setTranType("PAYMSG");// 交易码
				message.setMerNo(merch.getMerNo());// 合作商户编号
				message.setMerTrace(tradeNo);// 商户流水
				message.setOrderId(tradeNo);// 支付订单号
				String tradeAmt = tradeLog.getTradeAmt()*100 + "";
				logger.info("支付金额：" + tradeAmt);
				message.setOrderAmount(tradeAmt);// 订单金额(分)
				message.setRateCode(rateCode);// 费率编号
				message.setCardNo(tradeLog.getPayCardNo());// 银行卡卡号
				message.setAccountName(tradeLog.getUserName());// 银行卡姓名
				message.setCardType("2");// 银行卡类型(1、借记卡，2、信用卡)
				message.setBankCode(tradeLog.getBankCode());// 银行代码
				message.setBankAbbr(tradeLog.getBankAbbr());// 银行代号
				message.setPhoneno(merch.getPhone());// 预留手机
				message.setCertType("01");// 01、身份证
				message.setCertNo(merch.getIdCard());// 证件号码
				JSONObject msgResult = tradeExecute.tradeHttpReq(JSONObject.toJSONString(message));
				if(!"000000".equals(msgResult.getString("respCode"))){
					throw new Exception(StatusCode.SYS_SEND_MSG_ERR.toString());
				}else{
					/*记录支付流水号(短信接口返回)*/
					String orderId = msgResult.getJSONObject("result").getString("orderId");
					String payNo = msgResult.getJSONObject("result").getString("payNo");
					TradeLog msgTrade = new TradeLog();
					msgTrade.setOpenId(openId);
					msgTrade.setOrderNo(orderId);
					msgTrade.setPayNo(payNo);
					tradeLogService.update(msgTrade);
				}
			} else if("1".equals(status)){
				//如果没有开通
				String html = result.getJSONObject("result").getString("html");
				logger.info("html页面代码" + html);
				response.reset();
				response.setContentType("text/html; charset=UTF-8");
				ServletOutputStream out = response.getOutputStream();
				out.println(html);
				return null;
			} else {
				throw new Exception(StatusCode.SYS_MSG_TIED_CARD.toString());
			}
		}else{
			throw new Exception(StatusCode.SYS_PAY_ERR.toString());
		}
		return "/paysms";
	}

	/**
	 * 支付短信
	 * @throws JsonException 
	 */
	@ResponseBody
	@RequestMapping("/fastpay_pay_sms")
	public Result<String> paySms(String orderNo, HttpSession httpSession) throws JsonException {
		try {
			String openId = WebUtils.getSessionValue("openId");
			//String openId = "o1ZZ61qoovpSAhCjrk144BUc6NLY";
			logger.info("Session openId：" + openId);
			Merch merch = merchService.findByOpenId(openId);
			if(null == orderNo || "".equals(orderNo)){
				orderNo = httpSession.getAttribute("tradeNo").toString();
			}
			logger.info("支付订单号：" + orderNo);
			TradeLog tradeLog = tradeLogService.findByTradeNo(openId, orderNo);
			PayMessage message = new PayMessage();
			message.setTranType("PAYMSG");// 交易码
			message.setMerNo(merch.getMerNo());// 合作商户编号
			message.setMerTrace(orderNo);// 商户流水
			message.setOrderId(orderNo);// 支付订单号
			String tradeAmt = tradeLog.getTradeAmt()*100 + "";
			logger.info("支付金额：" + tradeAmt);
			message.setOrderAmount(tradeAmt);// 订单金额(分)
			message.setRateCode(rateCode);// 费率编号
			message.setCardNo(tradeLog.getPayCardNo());// 银行卡卡号
			message.setAccountName(tradeLog.getUserName());// 银行卡姓名
			message.setCardType("2");// 银行卡类型(1、借记卡，2、信用卡)
			message.setBankCode(tradeLog.getBankCode());// 银行代码
			message.setBankAbbr(tradeLog.getBankAbbr());// 银行代号
			message.setPhoneno(merch.getPhone());// 预留手机
			message.setCertType("01");// 01、身份证
			message.setCertNo(merch.getIdCard());// 证件号码
			JSONObject result = tradeExecute.tradeHttpReq(JSONObject.toJSONString(message));
			if(!"000000".equals(result.getString("respCode"))){
				throw new JsonException(StatusCode.SYS_SEND_MSG_ERR);
			}else{
				/*记录支付流水号(短信接口返回)*/
				String orderId = result.getJSONObject("result").getString("orderId");
				String payNo = result.getJSONObject("result").getString("payNo");
				TradeLog trade = new TradeLog();
				trade.setOpenId(openId);
				trade.setOrderNo(orderId);
				trade.setPayNo(payNo);
				tradeLogService.update(trade);
			}
			return new Result<String>(result.toJSONString());
		} catch (JsonException e) {
			logger.error("[发送短信异常]", e);
			throw e;
		} catch (Exception e) {
			throw new JsonException(StatusCode.SYS_ERR);
		}
	}
	
	@ResponseBody
	@RequestMapping("/fastpay_pay")
	public Result<String> pay(String orderNo, String smsCode, HttpSession httpSession) throws JsonException {
		try {
			String openId = WebUtils.getSessionValue("openId");
			Merch merch = merchService.findByOpenId(openId);
			if(null == orderNo || "".equals(orderNo)){
				orderNo = httpSession.getAttribute("tradeNo").toString();
			}
			logger.info("支付订单号：" + orderNo);
			TradeLog tradeLog = tradeLogService.findByTradeNo(openId, orderNo);
			PayMoney payMoney = new PayMoney();
			payMoney.setTranType("CONPAY");
			payMoney.setMerNo(merch.getMerNo());// 合作商户编号
			payMoney.setMerTrace("a"+orderNo);//商户流水号
			payMoney.setOrderId(orderNo);// 支付订单号 
			payMoney.setPayNo(tradeLog.getPayNo());//支付流水号
			payMoney.setPayAmount(tradeLog.getTradeAmt()*100+"");// 支付金额(分)
			payMoney.setRateCode(rateCode);// 合作商户费率编号 
			payMoney.setCardNo(tradeLog.getPayCardNo());//银行卡卡号
			payMoney.setAccountName(tradeLog.getUserName());// 银行卡姓名 
			payMoney.setCardType("2");//银行卡类型
			payMoney.setBankCode(tradeLog.getBankCode());// 银行代码 
			payMoney.setBankAbbr(tradeLog.getBankAbbr());//银行代号
			payMoney.setPhoneno(merch.getPhone());// 预留手机
			payMoney.setCertType("01");// 证件类型(01:身份证)
			payMoney.setCertNo(merch.getIdCard());// 证件号码
			payMoney.setSmsCode(smsCode);//雉验证码
			payMoney.setProductName("上海嘎吱");// 购买商品名称
			payMoney.setProductDesc("上海嘎吱");// 购买商品描述
			payMoney.setNotifyUrl(payNotifyUrl);// 后台通知URL
			JSONObject result = tradeExecute.tradeHttpReq(JSONObject.toJSONString(payMoney));
			if(!"000000".equals(result.getString("respCode"))){
				String orderId = result.getJSONObject("result").getString("orderId");
				TradeLog trade = new TradeLog();
				trade.setOpenId(openId);
				trade.setOrderNo(orderId);
				trade.setStatus("1");
				tradeLogService.update(trade);
				throw new JsonException(StatusCode.SYS_PAY_ERR);
			}else{
				if("000000".equals(result.getJSONObject("result").getString("resultCode"))){
					/*成功则修改支付状态*/
					String orderId = result.getJSONObject("result").getString("orderId");
					TradeLog trade = new TradeLog();
					trade.setOpenId(openId);
					trade.setOrderNo(orderId);
					trade.setStatus("2");
					tradeLogService.update(trade);
				}else{
					String orderId = result.getJSONObject("result").getString("orderId");
					TradeLog trade = new TradeLog();
					trade.setOpenId(openId);
					trade.setOrderNo(orderId);
					trade.setStatus("1");
					tradeLogService.update(trade);
					throw new JsonException(StatusCode.SYS_PAY_ERR);
				}
			}
			httpSession.setAttribute("tradeNo", "");
			return new Result<String>(result.toJSONString());
		} catch (JsonException e) {
			logger.error("[支付异常]", e);
			throw e;
		} catch (Exception e) {
			throw new JsonException(StatusCode.SYS_ERR);
		}
	}

	@RequestMapping("/fastpay_trade_list")
	public String tradeList(HttpSession httpSession, Model model) {
		String openId = WebUtils.getSessionValue("openId");
		//String openId = "o1ZZ61qoovpSAhCjrk144BUc6NLY";
		logger.info("Session openId：" + openId);
		List<TradeLog> list = tradeLogService.select(1, 20, openId);
		model.addAttribute("tradeList", list);
		return "/tradeList";
	}

	@ResponseBody
	@RequestMapping("/fastpay_json_list")
	public List<TradeLog> jsonList(int pageNum, int pageSize, HttpSession httpSession, Model model) {
		logger.info(pageNum + "========" + pageSize);
		String openId = WebUtils.getSessionValue("openId");
		//String openId = "o1ZZ61qoovpSAhCjrk144BUc6NLY";
		logger.info("Session openId：" + openId);
		List<TradeLog> list = tradeLogService.select(pageNum, pageSize, openId);
		return list;
	}

}

package com.poilt.weixin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poilt.model.fastpay.Merch;
import com.poilt.service.fastpay.MerchRegisterService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

@Controller
@RequestMapping("/wechat/url")
public class WechatUrlController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WxMpService wxMpService;
	
	@Autowired
	MerchRegisterService merchRegisterService;
	
	@PostMapping
	public String post(@RequestParam(name = "code", required = true) String code) {
		logger.info("wechat post : " + code);
		return "/index";
	}
	
	/**
SNSAPI_BASE URL:[https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx45da44ed1dfa99c5&redirect_uri=https%3A%2F%2Fpay.masduo.com%2Fwechat%2Furl&response_type=code&scope=snsapi_base&state=#wechat_redirect]
SNSAPI_BASE URL:[https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx45da44ed1dfa99c5&redirect_uri=https%3A%2F%2Fpay.masduo.com%2Fwechat%2Furl&response_type=code&scope=snsapi_userinfo&state=#wechat_redirect]
 	 String url = "https://pay.masduo.com/wechat/url";
		String baseUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, null);
		logger.info("SNSAPI_BASE URL:[" + baseUrl + "]");
		String userInfoUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
		logger.info("SNSAPI_BASE URL:[" + userInfoUrl + "]");
		try {
			logger.info("wechat redirect code : " + code);
			WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
			logger.info("wechat access token's openId:" + wxMpOAuth2AccessToken.getOpenId());
			//WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
			//logger.info(wxMpUser.toString());
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	 * @return
	 * @throws Exception 
	 */
	@GetMapping
	public String get(@RequestParam(name = "code", required = true) String code) throws Exception {
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
		String openId = wxMpOAuth2AccessToken.getOpenId();
		Merch merch = merchRegisterService.queryByOpenId(openId);
		String idCardNo = merch.getIdCardNo();
		if(idCardNo != null && !"".equals(idCardNo)){
			return "/index";
		} else {
			return "/register";
		}
	}
	
	
}

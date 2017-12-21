package com.poilt.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andOptionIsNull() {
            addCriterion("option is null");
            return (Criteria) this;
        }

        public Criteria andOptionIsNotNull() {
            addCriterion("option is not null");
            return (Criteria) this;
        }

        public Criteria andOptionEqualTo(String value) {
            addCriterion("option =", value, "option");
            return (Criteria) this;
        }

        public Criteria andOptionNotEqualTo(String value) {
            addCriterion("option <>", value, "option");
            return (Criteria) this;
        }

        public Criteria andOptionGreaterThan(String value) {
            addCriterion("option >", value, "option");
            return (Criteria) this;
        }

        public Criteria andOptionGreaterThanOrEqualTo(String value) {
            addCriterion("option >=", value, "option");
            return (Criteria) this;
        }

        public Criteria andOptionLessThan(String value) {
            addCriterion("option <", value, "option");
            return (Criteria) this;
        }

        public Criteria andOptionLessThanOrEqualTo(String value) {
            addCriterion("option <=", value, "option");
            return (Criteria) this;
        }

        public Criteria andOptionLike(String value) {
            addCriterion("option like", value, "option");
            return (Criteria) this;
        }

        public Criteria andOptionNotLike(String value) {
            addCriterion("option not like", value, "option");
            return (Criteria) this;
        }

        public Criteria andOptionIn(List<String> values) {
            addCriterion("option in", values, "option");
            return (Criteria) this;
        }

        public Criteria andOptionNotIn(List<String> values) {
            addCriterion("option not in", values, "option");
            return (Criteria) this;
        }

        public Criteria andOptionBetween(String value1, String value2) {
            addCriterion("option between", value1, value2, "option");
            return (Criteria) this;
        }

        public Criteria andOptionNotBetween(String value1, String value2) {
            addCriterion("option not between", value1, value2, "option");
            return (Criteria) this;
        }

        public Criteria andReqParamIsNull() {
            addCriterion("req_param is null");
            return (Criteria) this;
        }

        public Criteria andReqParamIsNotNull() {
            addCriterion("req_param is not null");
            return (Criteria) this;
        }

        public Criteria andReqParamEqualTo(String value) {
            addCriterion("req_param =", value, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamNotEqualTo(String value) {
            addCriterion("req_param <>", value, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamGreaterThan(String value) {
            addCriterion("req_param >", value, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamGreaterThanOrEqualTo(String value) {
            addCriterion("req_param >=", value, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamLessThan(String value) {
            addCriterion("req_param <", value, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamLessThanOrEqualTo(String value) {
            addCriterion("req_param <=", value, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamLike(String value) {
            addCriterion("req_param like", value, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamNotLike(String value) {
            addCriterion("req_param not like", value, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamIn(List<String> values) {
            addCriterion("req_param in", values, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamNotIn(List<String> values) {
            addCriterion("req_param not in", values, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamBetween(String value1, String value2) {
            addCriterion("req_param between", value1, value2, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamNotBetween(String value1, String value2) {
            addCriterion("req_param not between", value1, value2, "reqParam");
            return (Criteria) this;
        }

        public Criteria andRespResultIsNull() {
            addCriterion("resp_result is null");
            return (Criteria) this;
        }

        public Criteria andRespResultIsNotNull() {
            addCriterion("resp_result is not null");
            return (Criteria) this;
        }

        public Criteria andRespResultEqualTo(String value) {
            addCriterion("resp_result =", value, "respResult");
            return (Criteria) this;
        }

        public Criteria andRespResultNotEqualTo(String value) {
            addCriterion("resp_result <>", value, "respResult");
            return (Criteria) this;
        }

        public Criteria andRespResultGreaterThan(String value) {
            addCriterion("resp_result >", value, "respResult");
            return (Criteria) this;
        }

        public Criteria andRespResultGreaterThanOrEqualTo(String value) {
            addCriterion("resp_result >=", value, "respResult");
            return (Criteria) this;
        }

        public Criteria andRespResultLessThan(String value) {
            addCriterion("resp_result <", value, "respResult");
            return (Criteria) this;
        }

        public Criteria andRespResultLessThanOrEqualTo(String value) {
            addCriterion("resp_result <=", value, "respResult");
            return (Criteria) this;
        }

        public Criteria andRespResultLike(String value) {
            addCriterion("resp_result like", value, "respResult");
            return (Criteria) this;
        }

        public Criteria andRespResultNotLike(String value) {
            addCriterion("resp_result not like", value, "respResult");
            return (Criteria) this;
        }

        public Criteria andRespResultIn(List<String> values) {
            addCriterion("resp_result in", values, "respResult");
            return (Criteria) this;
        }

        public Criteria andRespResultNotIn(List<String> values) {
            addCriterion("resp_result not in", values, "respResult");
            return (Criteria) this;
        }

        public Criteria andRespResultBetween(String value1, String value2) {
            addCriterion("resp_result between", value1, value2, "respResult");
            return (Criteria) this;
        }

        public Criteria andRespResultNotBetween(String value1, String value2) {
            addCriterion("resp_result not between", value1, value2, "respResult");
            return (Criteria) this;
        }

        public Criteria andCreateTimIsNull() {
            addCriterion("create_tim is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimIsNotNull() {
            addCriterion("create_tim is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimEqualTo(Date value) {
            addCriterion("create_tim =", value, "createTim");
            return (Criteria) this;
        }

        public Criteria andCreateTimNotEqualTo(Date value) {
            addCriterion("create_tim <>", value, "createTim");
            return (Criteria) this;
        }

        public Criteria andCreateTimGreaterThan(Date value) {
            addCriterion("create_tim >", value, "createTim");
            return (Criteria) this;
        }

        public Criteria andCreateTimGreaterThanOrEqualTo(Date value) {
            addCriterion("create_tim >=", value, "createTim");
            return (Criteria) this;
        }

        public Criteria andCreateTimLessThan(Date value) {
            addCriterion("create_tim <", value, "createTim");
            return (Criteria) this;
        }

        public Criteria andCreateTimLessThanOrEqualTo(Date value) {
            addCriterion("create_tim <=", value, "createTim");
            return (Criteria) this;
        }

        public Criteria andCreateTimIn(List<Date> values) {
            addCriterion("create_tim in", values, "createTim");
            return (Criteria) this;
        }

        public Criteria andCreateTimNotIn(List<Date> values) {
            addCriterion("create_tim not in", values, "createTim");
            return (Criteria) this;
        }

        public Criteria andCreateTimBetween(Date value1, Date value2) {
            addCriterion("create_tim between", value1, value2, "createTim");
            return (Criteria) this;
        }

        public Criteria andCreateTimNotBetween(Date value1, Date value2) {
            addCriterion("create_tim not between", value1, value2, "createTim");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
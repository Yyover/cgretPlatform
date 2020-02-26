package com.javaee6.cgret.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ClientExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClientExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andClientIdIsNull() {
            addCriterion("client_id is null");
            return (Criteria) this;
        }

        public Criteria andClientIdIsNotNull() {
            addCriterion("client_id is not null");
            return (Criteria) this;
        }

        public Criteria andClientIdEqualTo(Long value) {
            addCriterion("client_id =", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotEqualTo(Long value) {
            addCriterion("client_id <>", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThan(Long value) {
            addCriterion("client_id >", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThanOrEqualTo(Long value) {
            addCriterion("client_id >=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThan(Long value) {
            addCriterion("client_id <", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThanOrEqualTo(Long value) {
            addCriterion("client_id <=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdIn(List<Long> values) {
            addCriterion("client_id in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotIn(List<Long> values) {
            addCriterion("client_id not in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdBetween(Long value1, Long value2) {
            addCriterion("client_id between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotBetween(Long value1, Long value2) {
            addCriterion("client_id not between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientNameIsNull() {
            addCriterion("client_name is null");
            return (Criteria) this;
        }

        public Criteria andClientNameIsNotNull() {
            addCriterion("client_name is not null");
            return (Criteria) this;
        }

        public Criteria andClientNameEqualTo(String value) {
            addCriterion("client_name =", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameNotEqualTo(String value) {
            addCriterion("client_name <>", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameGreaterThan(String value) {
            addCriterion("client_name >", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameGreaterThanOrEqualTo(String value) {
            addCriterion("client_name >=", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameLessThan(String value) {
            addCriterion("client_name <", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameLessThanOrEqualTo(String value) {
            addCriterion("client_name <=", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameLike(String value) {
            addCriterion("client_name like", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameNotLike(String value) {
            addCriterion("client_name not like", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameIn(List<String> values) {
            addCriterion("client_name in", values, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameNotIn(List<String> values) {
            addCriterion("client_name not in", values, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameBetween(String value1, String value2) {
            addCriterion("client_name between", value1, value2, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameNotBetween(String value1, String value2) {
            addCriterion("client_name not between", value1, value2, "clientName");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("telephone is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("telephone is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(Long value) {
            addCriterion("telephone =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(Long value) {
            addCriterion("telephone <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(Long value) {
            addCriterion("telephone >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(Long value) {
            addCriterion("telephone >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(Long value) {
            addCriterion("telephone <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(Long value) {
            addCriterion("telephone <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<Long> values) {
            addCriterion("telephone in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<Long> values) {
            addCriterion("telephone not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(Long value1, Long value2) {
            addCriterion("telephone between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(Long value1, Long value2) {
            addCriterion("telephone not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andArticleCodeIsNull() {
            addCriterion("article_code is null");
            return (Criteria) this;
        }

        public Criteria andArticleCodeIsNotNull() {
            addCriterion("article_code is not null");
            return (Criteria) this;
        }

        public Criteria andArticleCodeEqualTo(String value) {
            addCriterion("article_code =", value, "articleCode");
            return (Criteria) this;
        }

        public Criteria andArticleCodeNotEqualTo(String value) {
            addCriterion("article_code <>", value, "articleCode");
            return (Criteria) this;
        }

        public Criteria andArticleCodeGreaterThan(String value) {
            addCriterion("article_code >", value, "articleCode");
            return (Criteria) this;
        }

        public Criteria andArticleCodeGreaterThanOrEqualTo(String value) {
            addCriterion("article_code >=", value, "articleCode");
            return (Criteria) this;
        }

        public Criteria andArticleCodeLessThan(String value) {
            addCriterion("article_code <", value, "articleCode");
            return (Criteria) this;
        }

        public Criteria andArticleCodeLessThanOrEqualTo(String value) {
            addCriterion("article_code <=", value, "articleCode");
            return (Criteria) this;
        }

        public Criteria andArticleCodeLike(String value) {
            addCriterion("article_code like", value, "articleCode");
            return (Criteria) this;
        }

        public Criteria andArticleCodeNotLike(String value) {
            addCriterion("article_code not like", value, "articleCode");
            return (Criteria) this;
        }

        public Criteria andArticleCodeIn(List<String> values) {
            addCriterion("article_code in", values, "articleCode");
            return (Criteria) this;
        }

        public Criteria andArticleCodeNotIn(List<String> values) {
            addCriterion("article_code not in", values, "articleCode");
            return (Criteria) this;
        }

        public Criteria andArticleCodeBetween(String value1, String value2) {
            addCriterion("article_code between", value1, value2, "articleCode");
            return (Criteria) this;
        }

        public Criteria andArticleCodeNotBetween(String value1, String value2) {
            addCriterion("article_code not between", value1, value2, "articleCode");
            return (Criteria) this;
        }

        public Criteria andHaedUrlIsNull() {
            addCriterion("haed_url is null");
            return (Criteria) this;
        }

        public Criteria andHaedUrlIsNotNull() {
            addCriterion("haed_url is not null");
            return (Criteria) this;
        }

        public Criteria andHaedUrlEqualTo(String value) {
            addCriterion("haed_url =", value, "haedUrl");
            return (Criteria) this;
        }

        public Criteria andHaedUrlNotEqualTo(String value) {
            addCriterion("haed_url <>", value, "haedUrl");
            return (Criteria) this;
        }

        public Criteria andHaedUrlGreaterThan(String value) {
            addCriterion("haed_url >", value, "haedUrl");
            return (Criteria) this;
        }

        public Criteria andHaedUrlGreaterThanOrEqualTo(String value) {
            addCriterion("haed_url >=", value, "haedUrl");
            return (Criteria) this;
        }

        public Criteria andHaedUrlLessThan(String value) {
            addCriterion("haed_url <", value, "haedUrl");
            return (Criteria) this;
        }

        public Criteria andHaedUrlLessThanOrEqualTo(String value) {
            addCriterion("haed_url <=", value, "haedUrl");
            return (Criteria) this;
        }

        public Criteria andHaedUrlLike(String value) {
            addCriterion("haed_url like", value, "haedUrl");
            return (Criteria) this;
        }

        public Criteria andHaedUrlNotLike(String value) {
            addCriterion("haed_url not like", value, "haedUrl");
            return (Criteria) this;
        }

        public Criteria andHaedUrlIn(List<String> values) {
            addCriterion("haed_url in", values, "haedUrl");
            return (Criteria) this;
        }

        public Criteria andHaedUrlNotIn(List<String> values) {
            addCriterion("haed_url not in", values, "haedUrl");
            return (Criteria) this;
        }

        public Criteria andHaedUrlBetween(String value1, String value2) {
            addCriterion("haed_url between", value1, value2, "haedUrl");
            return (Criteria) this;
        }

        public Criteria andHaedUrlNotBetween(String value1, String value2) {
            addCriterion("haed_url not between", value1, value2, "haedUrl");
            return (Criteria) this;
        }

        public Criteria andDefAddressIdIsNull() {
            addCriterion("def_address_id is null");
            return (Criteria) this;
        }

        public Criteria andDefAddressIdIsNotNull() {
            addCriterion("def_address_id is not null");
            return (Criteria) this;
        }

        public Criteria andDefAddressIdEqualTo(Long value) {
            addCriterion("def_address_id =", value, "defAddressId");
            return (Criteria) this;
        }

        public Criteria andDefAddressIdNotEqualTo(Long value) {
            addCriterion("def_address_id <>", value, "defAddressId");
            return (Criteria) this;
        }

        public Criteria andDefAddressIdGreaterThan(Long value) {
            addCriterion("def_address_id >", value, "defAddressId");
            return (Criteria) this;
        }

        public Criteria andDefAddressIdGreaterThanOrEqualTo(Long value) {
            addCriterion("def_address_id >=", value, "defAddressId");
            return (Criteria) this;
        }

        public Criteria andDefAddressIdLessThan(Long value) {
            addCriterion("def_address_id <", value, "defAddressId");
            return (Criteria) this;
        }

        public Criteria andDefAddressIdLessThanOrEqualTo(Long value) {
            addCriterion("def_address_id <=", value, "defAddressId");
            return (Criteria) this;
        }

        public Criteria andDefAddressIdIn(List<Long> values) {
            addCriterion("def_address_id in", values, "defAddressId");
            return (Criteria) this;
        }

        public Criteria andDefAddressIdNotIn(List<Long> values) {
            addCriterion("def_address_id not in", values, "defAddressId");
            return (Criteria) this;
        }

        public Criteria andDefAddressIdBetween(Long value1, Long value2) {
            addCriterion("def_address_id between", value1, value2, "defAddressId");
            return (Criteria) this;
        }

        public Criteria andDefAddressIdNotBetween(Long value1, Long value2) {
            addCriterion("def_address_id not between", value1, value2, "defAddressId");
            return (Criteria) this;
        }

        public Criteria andDefAddressIsNull() {
            addCriterion("def_address is null");
            return (Criteria) this;
        }

        public Criteria andDefAddressIsNotNull() {
            addCriterion("def_address is not null");
            return (Criteria) this;
        }

        public Criteria andDefAddressEqualTo(String value) {
            addCriterion("def_address =", value, "defAddress");
            return (Criteria) this;
        }

        public Criteria andDefAddressNotEqualTo(String value) {
            addCriterion("def_address <>", value, "defAddress");
            return (Criteria) this;
        }

        public Criteria andDefAddressGreaterThan(String value) {
            addCriterion("def_address >", value, "defAddress");
            return (Criteria) this;
        }

        public Criteria andDefAddressGreaterThanOrEqualTo(String value) {
            addCriterion("def_address >=", value, "defAddress");
            return (Criteria) this;
        }

        public Criteria andDefAddressLessThan(String value) {
            addCriterion("def_address <", value, "defAddress");
            return (Criteria) this;
        }

        public Criteria andDefAddressLessThanOrEqualTo(String value) {
            addCriterion("def_address <=", value, "defAddress");
            return (Criteria) this;
        }

        public Criteria andDefAddressLike(String value) {
            addCriterion("def_address like", value, "defAddress");
            return (Criteria) this;
        }

        public Criteria andDefAddressNotLike(String value) {
            addCriterion("def_address not like", value, "defAddress");
            return (Criteria) this;
        }

        public Criteria andDefAddressIn(List<String> values) {
            addCriterion("def_address in", values, "defAddress");
            return (Criteria) this;
        }

        public Criteria andDefAddressNotIn(List<String> values) {
            addCriterion("def_address not in", values, "defAddress");
            return (Criteria) this;
        }

        public Criteria andDefAddressBetween(String value1, String value2) {
            addCriterion("def_address between", value1, value2, "defAddress");
            return (Criteria) this;
        }

        public Criteria andDefAddressNotBetween(String value1, String value2) {
            addCriterion("def_address not between", value1, value2, "defAddress");
            return (Criteria) this;
        }

        public Criteria andIdentitytypeIsNull() {
            addCriterion("identityType is null");
            return (Criteria) this;
        }

        public Criteria andIdentitytypeIsNotNull() {
            addCriterion("identityType is not null");
            return (Criteria) this;
        }

        public Criteria andIdentitytypeEqualTo(String value) {
            addCriterion("identityType =", value, "identitytype");
            return (Criteria) this;
        }

        public Criteria andIdentitytypeNotEqualTo(String value) {
            addCriterion("identityType <>", value, "identitytype");
            return (Criteria) this;
        }

        public Criteria andIdentitytypeGreaterThan(String value) {
            addCriterion("identityType >", value, "identitytype");
            return (Criteria) this;
        }

        public Criteria andIdentitytypeGreaterThanOrEqualTo(String value) {
            addCriterion("identityType >=", value, "identitytype");
            return (Criteria) this;
        }

        public Criteria andIdentitytypeLessThan(String value) {
            addCriterion("identityType <", value, "identitytype");
            return (Criteria) this;
        }

        public Criteria andIdentitytypeLessThanOrEqualTo(String value) {
            addCriterion("identityType <=", value, "identitytype");
            return (Criteria) this;
        }

        public Criteria andIdentitytypeLike(String value) {
            addCriterion("identityType like", value, "identitytype");
            return (Criteria) this;
        }

        public Criteria andIdentitytypeNotLike(String value) {
            addCriterion("identityType not like", value, "identitytype");
            return (Criteria) this;
        }

        public Criteria andIdentitytypeIn(List<String> values) {
            addCriterion("identityType in", values, "identitytype");
            return (Criteria) this;
        }

        public Criteria andIdentitytypeNotIn(List<String> values) {
            addCriterion("identityType not in", values, "identitytype");
            return (Criteria) this;
        }

        public Criteria andIdentitytypeBetween(String value1, String value2) {
            addCriterion("identityType between", value1, value2, "identitytype");
            return (Criteria) this;
        }

        public Criteria andIdentitytypeNotBetween(String value1, String value2) {
            addCriterion("identityType not between", value1, value2, "identitytype");
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
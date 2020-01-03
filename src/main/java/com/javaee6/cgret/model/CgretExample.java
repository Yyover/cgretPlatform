package com.javaee6.cgret.model;

import java.util.ArrayList;
import java.util.List;

public class CgretExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CgretExample() {
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

        public Criteria andCgretIdIsNull() {
            addCriterion("cgret_id is null");
            return (Criteria) this;
        }

        public Criteria andCgretIdIsNotNull() {
            addCriterion("cgret_id is not null");
            return (Criteria) this;
        }

        public Criteria andCgretIdEqualTo(Integer value) {
            addCriterion("cgret_id =", value, "cgretId");
            return (Criteria) this;
        }

        public Criteria andCgretIdNotEqualTo(Integer value) {
            addCriterion("cgret_id <>", value, "cgretId");
            return (Criteria) this;
        }

        public Criteria andCgretIdGreaterThan(Integer value) {
            addCriterion("cgret_id >", value, "cgretId");
            return (Criteria) this;
        }

        public Criteria andCgretIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cgret_id >=", value, "cgretId");
            return (Criteria) this;
        }

        public Criteria andCgretIdLessThan(Integer value) {
            addCriterion("cgret_id <", value, "cgretId");
            return (Criteria) this;
        }

        public Criteria andCgretIdLessThanOrEqualTo(Integer value) {
            addCriterion("cgret_id <=", value, "cgretId");
            return (Criteria) this;
        }

        public Criteria andCgretIdIn(List<Integer> values) {
            addCriterion("cgret_id in", values, "cgretId");
            return (Criteria) this;
        }

        public Criteria andCgretIdNotIn(List<Integer> values) {
            addCriterion("cgret_id not in", values, "cgretId");
            return (Criteria) this;
        }

        public Criteria andCgretIdBetween(Integer value1, Integer value2) {
            addCriterion("cgret_id between", value1, value2, "cgretId");
            return (Criteria) this;
        }

        public Criteria andCgretIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cgret_id not between", value1, value2, "cgretId");
            return (Criteria) this;
        }

        public Criteria andCgretNameIsNull() {
            addCriterion("cgret_name is null");
            return (Criteria) this;
        }

        public Criteria andCgretNameIsNotNull() {
            addCriterion("cgret_name is not null");
            return (Criteria) this;
        }

        public Criteria andCgretNameEqualTo(String value) {
            addCriterion("cgret_name =", value, "cgretName");
            return (Criteria) this;
        }

        public Criteria andCgretNameNotEqualTo(String value) {
            addCriterion("cgret_name <>", value, "cgretName");
            return (Criteria) this;
        }

        public Criteria andCgretNameGreaterThan(String value) {
            addCriterion("cgret_name >", value, "cgretName");
            return (Criteria) this;
        }

        public Criteria andCgretNameGreaterThanOrEqualTo(String value) {
            addCriterion("cgret_name >=", value, "cgretName");
            return (Criteria) this;
        }

        public Criteria andCgretNameLessThan(String value) {
            addCriterion("cgret_name <", value, "cgretName");
            return (Criteria) this;
        }

        public Criteria andCgretNameLessThanOrEqualTo(String value) {
            addCriterion("cgret_name <=", value, "cgretName");
            return (Criteria) this;
        }

        public Criteria andCgretNameLike(String value) {
            addCriterion("cgret_name like", value, "cgretName");
            return (Criteria) this;
        }

        public Criteria andCgretNameNotLike(String value) {
            addCriterion("cgret_name not like", value, "cgretName");
            return (Criteria) this;
        }

        public Criteria andCgretNameIn(List<String> values) {
            addCriterion("cgret_name in", values, "cgretName");
            return (Criteria) this;
        }

        public Criteria andCgretNameNotIn(List<String> values) {
            addCriterion("cgret_name not in", values, "cgretName");
            return (Criteria) this;
        }

        public Criteria andCgretNameBetween(String value1, String value2) {
            addCriterion("cgret_name between", value1, value2, "cgretName");
            return (Criteria) this;
        }

        public Criteria andCgretNameNotBetween(String value1, String value2) {
            addCriterion("cgret_name not between", value1, value2, "cgretName");
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
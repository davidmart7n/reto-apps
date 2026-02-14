package io.github.davidmart7n.domain;

public class SearchCriteria {
    
    
    String key;
    FilterOperation operation;
    Object value;

    public SearchCriteria(String key, FilterOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    @Override
    public String toString() {
        return "SearchCriteria [key=" + key + ", operation=" + operation + ", value=" + value + "]";
    }
    
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public FilterOperation getOperation() {
        return operation;
    }
    public void setOperation(FilterOperation operation) {
        this.operation = operation;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }


    
    
}

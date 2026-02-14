package io.github.davidmart7n.domain;

public class SearchCriteria {
    
    @Override
    public String toString() {
        return "SearchCriteria [key=" + key + ", operation=" + operation + ", value=" + value + "]";
    }
    String key;
    FilterOperation operation;
    Object value;
    
}

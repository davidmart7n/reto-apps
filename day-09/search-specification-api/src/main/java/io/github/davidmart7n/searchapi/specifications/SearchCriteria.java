package io.github.davidmart7n.searchapi.specifications;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class SearchCriteria {

    String key;
    FilterOperation operation;
    Object value;
}

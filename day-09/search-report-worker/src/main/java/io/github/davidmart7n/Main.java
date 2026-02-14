package io.github.davidmart7n;

import java.util.List;

import io.github.davidmart7n.api.DataClient;
import io.github.davidmart7n.domain.FilterOperation;
import io.github.davidmart7n.domain.SearchCriteria;

public class Main {
    public static void main(String[] args) {
        DataClient client= new DataClient();

        SearchCriteria criteria= new SearchCriteria("annualRevenue", FilterOperation.GREATER_THAN, Integer.valueOf(9400000));

        client.getDataAndReportByCriteria(List.of(criteria));
    }
}
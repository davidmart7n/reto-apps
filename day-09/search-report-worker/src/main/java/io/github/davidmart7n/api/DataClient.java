package io.github.davidmart7n.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.github.davidmart7n.domain.CorporateClient;
import io.github.davidmart7n.domain.SearchCriteria;
import io.github.davidmart7n.reporter.DataReporter;

public class DataClient {
    
    private HttpClient client= HttpClient.newHttpClient();

    ObjectMapper mapper = new ObjectMapper();
    

    private String apiUrl="http://localhost:8085/";

    public List<CorporateClient> getDataAndReportByCriteria(List<SearchCriteria> criteria){
        try{
        mapper.registerModule(new JavaTimeModule());
        String jsonCriteria = mapper.writeValueAsString(criteria);
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create(apiUrl))
                                         .POST(BodyPublishers.ofString(jsonCriteria))
                                         .header("Content-Type", "application/json")
                                         .build();

        HttpResponse<String> response= client.send(request,BodyHandlers.ofString());

        if(response.statusCode()== 204) return List.of();

        List<CorporateClient> listClients= mapper.readValue(response.body(), new TypeReference<List<CorporateClient>>(){});
        DataReporter.generateReport(criteria, listClients);

        return listClients;

        }catch(Exception e){
            System.err.println("Error en llamada a la api");
            e.printStackTrace();
            throw new RuntimeException("Error en llamada a la api");            
        }
    }

}

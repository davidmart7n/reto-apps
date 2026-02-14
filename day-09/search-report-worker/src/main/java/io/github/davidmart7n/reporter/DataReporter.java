package io.github.davidmart7n.reporter;

import io.github.davidmart7n.domain.CorporateClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;

import io.github.davidmart7n.domain.SearchCriteria;

public class DataReporter {
    
    public static boolean generateReport(List<SearchCriteria> criteria, List<CorporateClient> clients){
        StringBuilder criteriaSummary = new StringBuilder();
        criteria.forEach((c)->criteriaSummary.append(" -").append(c.toString()).append("\n"));

        StringBuilder clientRows=new StringBuilder();
        clients.forEach((c)->clientRows.append(String.format("%d,%s,%s,%s \n",c.getId(),
                        c.getName(),c.getRiskLevel(),c.getActive())));

        String reportString= """
                ================================G
                REPORTE DE BUSQUEDA DE CLIENTES
                ================================
                CRITERIOS APLICADOS:
                %s
                RESULTADOS (Encontrados %s):
                ID,NOMBRE,RIESGO,ACTIVO
                %s
                """.formatted(criteriaSummary.toString(), clients.size(), clientRows.toString());

        try{
            String now = Instant.now().toString().replace(":", "-");
            Files.writeString(Paths.get(now.toString()+"txt"), reportString);
            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
}

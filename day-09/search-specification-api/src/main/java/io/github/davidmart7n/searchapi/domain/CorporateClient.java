package io.github.davidmart7n.searchapi.domain;

import java.time.LocalDate;

import jakarta.persistence.*; // Asegúrate de que sea jakarta y no javax
import lombok.*;

@Entity
@Getter
@Setter // O genera los getters/setters
public class CorporateClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double annualRevenue;
    private Double annualExpenses;

    @Enumerated(EnumType.STRING) 
    private RiskLevel riskLevel;

    private LocalDate lastAuditDate;
    private boolean isActive;


    public RiskLevel calculateRiskLevel() {
        if (annualRevenue <= 0)
            return RiskLevel.CRITICAL;

        double earnings = annualRevenue - annualExpenses;
        double percentage = (earnings / annualRevenue) * 100;

        return riskFromPercentage(percentage);
    }

    private RiskLevel riskFromPercentage(Double earningsPercentage) {
        if (earningsPercentage >= 20)
            return RiskLevel.LOW;
        if (earningsPercentage >= 10)
            return RiskLevel.MEDIUM;
        if (earningsPercentage > 0)
            return RiskLevel.HIGH;

        return RiskLevel.CRITICAL;

    }

}
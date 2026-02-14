package io.github.davidmart7n.domain;

import java.time.LocalDate;

public class CorporateClient {

    Long id;
    String name;
    Double annualRevenue;
    Double annualExpenses;
    RiskLevel riskLevel;
    LocalDate lastAuditDate;
    boolean isActive;

    public CorporateClient(){};

    public CorporateClient(Long id, String name, Double annualRevenue, Double annualExpenses, RiskLevel riskLevel,
            LocalDate lastAuditDate, boolean isActive) {
        this.id = id;
        this.name = name;
        this.annualRevenue = annualRevenue;
        this.annualExpenses = annualExpenses;
        this.riskLevel = riskLevel;
        this.lastAuditDate = lastAuditDate;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(){
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAnnualRevenue() {
        return annualRevenue;
    }

    public void setAnnualRevenue(Double annualRevenue) {
        this.annualRevenue = annualRevenue;
    }

    public Double getAnnualExpenses() {
        return annualExpenses;
    }

    public void setAnnualExpenses(Double annualExpenses) {
        this.annualExpenses = annualExpenses;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public LocalDate getLastAuditDate() {
        return lastAuditDate;
    }

    public void setLastAuditDate(LocalDate lastAuditDate) {
        this.lastAuditDate = lastAuditDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getActive(){
        return this.isActive;
    }

    @Override
    public String toString() {
        return "CorporateClient [id=" + id + ", name=" + name + ", annualRevenue=" + annualRevenue + ", annualExpenses="
                + annualExpenses + ", riskLevel=" + riskLevel + ", lastAuditDate=" + lastAuditDate + ", isActive="
                + isActive + "]";
    }

}

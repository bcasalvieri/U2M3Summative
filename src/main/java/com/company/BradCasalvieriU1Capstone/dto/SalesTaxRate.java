package com.company.BradCasalvieriU1Capstone.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "sales_tax_rate",
        indexes = {@Index(name = "ix_state_rate", columnList = "state, rate")})
public class SalesTaxRate {
    @Column(name = "state", unique = true, length = 2, nullable = false)
    private String state;
    @Column(name = "rate", precision = 3, scale = 2, nullable = false)
    private BigDecimal rate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesTaxRate that = (SalesTaxRate) o;
        return Objects.equals(state, that.state) &&
                Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, rate);
    }
}

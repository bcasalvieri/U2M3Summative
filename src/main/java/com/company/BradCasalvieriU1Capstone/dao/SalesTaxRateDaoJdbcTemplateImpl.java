package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.model.SalesTaxRate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SalesTaxRateDaoJdbcTemplateImpl implements SalesTaxRateDao {
    private static final String INSERT_SALES_TAX_RATE_SQL =
            "INSERT INTO sales_tax_rate (state, rate) VALUES (?, ?)";

    private static final String SELECT_SALES_TAX_RATE_SQL =
            "SELECT * FROM sales_tax_rate WHERE state = ?";

    private static final String SELECT_ALL_SALES_TAX_RATES_SQL =
            "SELECT * FROM sales_tax_rate";

    private static final String UPDATE_SALES_TAX_RATE_SQL =
            "UPDATE sales_tax_rate SET rate = ? WHERE state = ?";

    private static final String DELETE_SALES_TAX_RATE_SQL =
            "DELETE FROM sales_tax_rate WHERE state = ?";

    private JdbcTemplate jdbcTemplate;

    public SalesTaxRateDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public SalesTaxRate addSalesTaxRate(SalesTaxRate salesTaxRate) {
        jdbcTemplate.update(INSERT_SALES_TAX_RATE_SQL,
                salesTaxRate.getState(),
                salesTaxRate.getRate());
        return salesTaxRate;
    }

    @Override
    public SalesTaxRate getSalesTaxRateByState(String state) {
        try {
            return jdbcTemplate.queryForObject(SELECT_SALES_TAX_RATE_SQL, this::mapRowToSalesTaxRate, state);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<SalesTaxRate> getAllSalesTaxRates() {
        return jdbcTemplate.query(SELECT_ALL_SALES_TAX_RATES_SQL, this::mapRowToSalesTaxRate);
    }

    @Override
    public void updateSalesTaxRate(SalesTaxRate salesTaxRate) {
        jdbcTemplate.update(UPDATE_SALES_TAX_RATE_SQL,
                salesTaxRate.getRate(),
                salesTaxRate.getState());
    }

    @Override
    public void deleteSalesTaxRate(String state) {
        jdbcTemplate.update(DELETE_SALES_TAX_RATE_SQL, state);
    }

    private SalesTaxRate mapRowToSalesTaxRate(ResultSet rs, int rowNum) throws SQLException {
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState(rs.getString("state"));
        salesTaxRate.setRate(rs.getBigDecimal("rate"));
        return salesTaxRate;
    }
}

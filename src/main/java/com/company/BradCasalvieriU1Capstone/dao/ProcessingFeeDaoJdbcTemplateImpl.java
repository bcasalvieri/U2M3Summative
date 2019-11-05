package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.dto.ProcessingFee;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProcessingFeeDaoJdbcTemplateImpl implements ProcessingFeeDao {
    private static final String INSERT_FEE_SQL =
            "INSERT INTO processing_fee (product_type, fee) VALUES (?, ?)";

    private static final String SELECT_FEE_SQL =
            "SELECT * FROM processing_fee WHERE product_type = ?";

    private static final String SELECT_ALL_FEES_SQL =
            "SELECT * FROM processing_fee";

    private static final String UPDATE_FEE_SQL =
            "UPDATE processing_fee SET fee = ? WHERE product_type = ?";

    private static final String DELETE_FEE_SQL =
            "DELETE FROM processing_fee WHERE product_type = ?";

    private JdbcTemplate jdbcTemplate;

    public ProcessingFeeDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public ProcessingFee addProcessingFee(ProcessingFee processingFee) {
        jdbcTemplate.update(INSERT_FEE_SQL,
                processingFee.getProductType(),
                processingFee.getFee());
        return processingFee;
    }

    @Override
    public ProcessingFee getProcessingFeeByType(String type) {
        try {
            return jdbcTemplate.queryForObject(SELECT_FEE_SQL, this::mapRowToProcessingFee, type);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<ProcessingFee> getAllProcessingFees() {
        return jdbcTemplate.query(SELECT_ALL_FEES_SQL, this::mapRowToProcessingFee);
    }

    @Override
    public void updateProcessingFee(ProcessingFee processingFee) {
        jdbcTemplate.update(UPDATE_FEE_SQL,
                processingFee.getFee(),
                processingFee.getProductType());
    }

    @Override
    public void deleteProcessingFee(String type) {
        jdbcTemplate.update(DELETE_FEE_SQL, type);
    }

    private ProcessingFee mapRowToProcessingFee(ResultSet rs, int rowNum) throws SQLException {
        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProductType(rs.getString("product_type"));
        processingFee.setFee(rs.getBigDecimal("fee"));
        return processingFee;
    }
}

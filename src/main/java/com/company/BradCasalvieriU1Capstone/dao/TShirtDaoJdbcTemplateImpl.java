package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.model.TShirt;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TShirtDaoJdbcTemplateImpl implements TShirtDao {
    private static final String INSERT_TSHIRT_SQL =
            "INSERT INTO t_shirt (size, color, description, price, quantity) VALUES (?, ?, ?, ?, ?)";

    private static final String SELECT_TSHIRT_SQL =
            "SELECT * FROM t_shirt WHERE t_shirt_id = ?";

    private static final String SELECT_ALL_TSHIRTS_SQL =
            "SELECT * FROM t_shirt";

    private static final String UPDATE_TSHIRT_SQL =
            "UPDATE t_shirt SET size = ?, color = ?, description = ?, price = ?, quantity = ? WHERE t_shirt_id = ?";

    private static final String DELETE_TSHIRT_SQL =
            "DELETE FROM t_shirt WHERE t_shirt_id = ?";

    private static final String SELECT_TSHIRTS_BY_COLOR_SQL =
            "SELECT * FROM t_shirt WHERE color = ?";

    private static final String SELECT_TSHIRTS_BY_SIZE_SQL =
            "SELECT * FROM t_shirt WHERE size = ?";

    private JdbcTemplate jdbcTemplate;

    public TShirtDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public TShirt addTShirt(TShirt tShirt) {
        jdbcTemplate.update(INSERT_TSHIRT_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity());
        int id = jdbcTemplate.queryForObject("SELECT last_insert_id()", Integer.class);
        tShirt.setId(id);
        return tShirt;
    }

    @Override
    public TShirt getTShirtById(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_TSHIRT_SQL, this::mapRowToTShirt, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<TShirt> getAllTShirts() {
        return jdbcTemplate.query(SELECT_ALL_TSHIRTS_SQL, this::mapRowToTShirt);
    }

    @Override
    public void updateTShirt(TShirt tShirt) {
        jdbcTemplate.update(UPDATE_TSHIRT_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity(),
                tShirt.getId());
    }

    @Override
    public void deleteTShirt(int id) {
        jdbcTemplate.update(DELETE_TSHIRT_SQL, id);
    }

    @Override
    public List<TShirt> getTShirtsByColor(String color) {
        return jdbcTemplate.query(SELECT_TSHIRTS_BY_COLOR_SQL, this::mapRowToTShirt, color);
    }

    @Override
    public List<TShirt> getTShirtsBySize(String size) {
        return jdbcTemplate.query(SELECT_TSHIRTS_BY_SIZE_SQL, this::mapRowToTShirt, size);
    }

    private TShirt mapRowToTShirt(ResultSet rs, int rowNum) throws SQLException {
        TShirt tShirt = new TShirt();
        tShirt.setId(rs.getInt("t_shirt_id"));
        tShirt.setSize(rs.getString("size"));
        tShirt.setColor(rs.getString("color"));
        tShirt.setDescription(rs.getString("description"));
        tShirt.setPrice(rs.getBigDecimal("price"));
        tShirt.setQuantity(rs.getInt("quantity"));
        return tShirt;
    }
}

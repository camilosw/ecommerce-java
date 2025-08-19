package ecommerce.api.repository;

import ecommerce.api.domain.Product;
import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbc;

    public ProductRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM product";

        RowMapper<Product> productRowMapper = rowMapper();

        return jdbc.query(sql, productRowMapper);
    }

    public Optional<Product> findById(int id) {
        String sql = "SELECT * FROM product WHERE product.id = ?";
        
        RowMapper<Product> productRowMapper = rowMapper();
        
        List<Product> results = jdbc.query(sql, productRowMapper, id);

        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public Integer add(@NonNull Product product) {
        String sql = "INSERT INTO product (name, sku, price, stockQuantity) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setString(2, product.getSku());
            ps.setBigDecimal(3, product.getPrice());
            ps.setInt(4, product.getStockQuantity());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public boolean update(int id, @NonNull Product product) {
        String sql = "UPDATE product SET name = ?, sku = ?, price = ?, stockQuantity = ? WHERE product.id = ?";

        int rowsAffected = jdbc.update(
            sql,
            product.getName(),
            product.getSku(),
            product.getPrice(),
            product.getStockQuantity(),
            id
        );

        return rowsAffected > 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM product WHERE product.id = ?";

        int rowsAffected = jdbc.update(sql, id);

        return rowsAffected > 0;
    }

    private RowMapper<Product> rowMapper() {
        return (r, i) -> {
            return Product.builder()
                .id(r.getInt("id"))
                .name(r.getString("name"))
                .sku(r.getString("sku"))
                .price(r.getBigDecimal("price"))
                .stockQuantity(r.getInt("stockQuantity"))
                .build();
        };
    }
}

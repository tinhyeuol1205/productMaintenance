package com.example.productmaintain.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.productmaintain.business.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcProductRepository implements ProductRepository {
    private JdbcTemplate jdbc;
    @Autowired
    public JdbcProductRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
    private Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException {
		return new Product(
            rs.getString("Code"), 
            rs.getString("Description"),
            rs.getDouble("Price"));
	}
    @Override
    public List<Product> getProducts(){
        return jdbc.query("select Code, Description, Price from Product", this::mapRowToProduct);
    }
    @Override
    public Product getProduct(String pCode){
        return jdbc.queryForObject("select Code, Description, Price from Product where Code=?", this::mapRowToProduct,pCode);
    }
    public Product save(Product p){
        jdbc.update(
            "insert into Product (Code, Description, Price) values(?, ?, ?)",
		    p.getCode(),
            p.getDescription(),
            p.getPrice());
        return p;
    }
    @Override
    public void deleteProduct(String productCode){
        jdbc.update("delete from Product where Code=?",productCode);
    }
    @Override
    public Product update(Product p){
        jdbc.update(
            "update Product set Description=?,Price = ? where Code = ?",
            p.getDescription(),
            p.getPrice(),
            p.getCode());
        return p;
    }
}

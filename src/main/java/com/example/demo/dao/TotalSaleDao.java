package com.example.demo.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.TotalSalesDto;
import com.example.demo.model.Product;
import com.example.demo.model.Sale;
import com.example.demo.model.Store;
import com.example.demo.rowMapper.ProductMapper;

@Repository
public class TotalSaleDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	


	public List<TotalSalesDto> getTotalSalesSuperBazar(int id) {
	    String sql = "SELECT p.pname, " +
	                 "COALESCE(SUM(s.sale_quantity), 0) AS sale, " +
	                 "p.mrp, " +
	                 "COALESCE(SUM(s.sale_quantity) * p.mrp, 0) AS amount " +
	                 "FROM product_table p " +
	                 "LEFT JOIN sale s ON p.id = s.product_id " +
	                 "AND s.store_id = ? " +
	                 "GROUP BY p.pname";

	    return jdbcTemplate.query(sql, (rs, rowNum) -> {
	        TotalSalesDto dto = new TotalSalesDto();
	        dto.setPname(rs.getString("pname"));
	        dto.setTotalSales(rs.getInt("sale"));
	        dto.setMrp(rs.getDouble("mrp"));
	        dto.setAmount(rs.getDouble("amount"));
	        return dto;
	    }, id);
	}
	
	
	
	public int addStore(Store store) {
		String sql = "INSERT INTO store_table(sname) values (?)";
		return jdbcTemplate.update(sql,store.getSname());
	}



	public List<Store> getAllStores() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM store_table";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Store.class)) ;
	}
	
	public int addSale(Sale sale) {
		String sql = "INSERT INTO sale(product_id,sale_quantity,store_id,date,mrp) values (?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql,sale.getProduct_id(), sale.getSale_quantity(), sale.getStore_id(),sale.getDate(),sale.getMrp());
	}



	public int updateMrp(int id, double newMrp) {
		// TODO Auto-generated method stub
		String sql = "UPDATE product_table SET mrp = ? WHERE id = ?";
		return jdbcTemplate.update(sql,newMrp,id);
		
	}



	public List<TotalSalesDto> getAllSales(Long id, LocalDate date) {
		// TODO Auto-generated method stub
		
		
		String sql = "SELECT p.pname, " +
                "COALESCE(SUM(s.sale_quantity), 0) AS sale, " +
                "s.mrp, " +
                "COALESCE(SUM(s.sale_quantity) * s.mrp, 0) AS amount " +
                "FROM product_table p " +
                "LEFT JOIN sale s ON p.id = s.product_id " +
                "AND s.store_id = ? " +
                "AND s.date = ? " +  
                "GROUP BY p.pname " +
                "HAVING sale > 0";


		return jdbcTemplate.query(sql, (rs, rowNum) -> {
	        TotalSalesDto dto = new TotalSalesDto();
	        dto.setPname(rs.getString("pname"));
	        dto.setTotalSales(rs.getInt("sale"));
	        dto.setMrp(rs.getDouble("mrp"));
	        dto.setAmount(rs.getDouble("amount"));
	        return dto;
	    }, id,date);
	}
	
	public int searchDate(LocalDate date) {
		String sqlCheckDate = "SELECT COUNT(*) FROM sale s WHERE s.date = ?";
	    
	    int count = jdbcTemplate.queryForObject(sqlCheckDate,Integer.class,date);
	    
	    return count;
	}
	
	public int searchProduct(int id) {
		String sql = "SELECT COUNT(*) FROM product_table p WHERE p.id = ?";
	    
	    int count = jdbcTemplate.queryForObject(sql,Integer.class,id);
	    
	    return count;
	}
	
	public List<Store> getAllStoreId(){
		String sql = "SELECT * FROM store_table";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Store.class)) ;
	}



	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM product_table";
		return jdbcTemplate.query(sql, new  ProductMapper());
	}
    
   

}

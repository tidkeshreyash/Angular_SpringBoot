package com.example.demo.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TotalSaleDao;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.SaleDto;
import com.example.demo.dto.TotalSalesDto;
import com.example.demo.dto.UpdateMrp;
import com.example.demo.exception.StoreNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.Sale;
import com.example.demo.model.Store;

@Service
public class SaleService {
	
	@Autowired
	private TotalSaleDao totalSaleDao;

	public List<TotalSalesDto> getSales(int id)  {
		// TODO Auto-generated method stub
		
		
		 boolean storeFound = totalSaleDao.getAllStores().stream()
                 .anyMatch(store -> store.getId() == id);

        if (!storeFound) {
           throw new StoreNotFoundException("Store with id " + id + " is not available.");
        }
		
		return totalSaleDao.getTotalSalesSuperBazar(id);
	}

	public int addStore(Store store) {
		// TODO Auto-generated method stub
		return totalSaleDao.addStore(store);
	}

	public void addSale(SaleDto saleDto) {
		// TODO Auto-generated method stub
		Date date = saleDto.getDate();
	     List<ProductDto> products = saleDto.getProduct();    		 
	     
	     
	     if (products == null) {
	         products = new ArrayList<>();  // Initialize to an empty list if null
	     }
	     
	     for(ProductDto dto: products) {
	    	 Sale sale = new Sale();
	    	
	    	 sale.setProduct_id(dto.getProduct_id());
	    	 sale.setSale_quantity(dto.getSale_quantity());
	    	 sale.setStore_id(dto.getStore_id());
	    	 sale.setMrp(dto.getMrp());
	    	 sale.setDate(date);;
	    	 
	    	 totalSaleDao.addSale(sale);
	     }
		
		
	}

	public void updateProductMrp(int id,UpdateMrp newMrp) {
		// TODO Auto-generated method stub
		if(totalSaleDao.searchProduct(id) == 0) {
			throw new StoreNotFoundException("Product is not present");
		}
		totalSaleDao.updateMrp(id,newMrp.getMrp());
		
	}

	public List<TotalSalesDto> getTotalSales(Long id, LocalDate date) {
		// TODO Auto-generated method stub
		
		
		 boolean storeFound = totalSaleDao.getAllStores().stream()
                .anyMatch(store -> store.getId() == id);

       if (!storeFound) {
          throw new StoreNotFoundException("Store with id " + id + " is not available.");
       }
       
      
       if(totalSaleDao.searchDate(date) == 0) {
    	   throw new StoreNotFoundException("Sale for " + date + " is not present for given store ");
       }
		return totalSaleDao.getAllSales(id,date);
	}

	public List<Store> getAllStoreIds() {
		// TODO Auto-generated method stub
		return totalSaleDao.getAllStoreId();
	}

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return totalSaleDao.getAllProducts();
	}

}

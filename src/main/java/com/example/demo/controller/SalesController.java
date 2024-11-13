package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SaleDto;
import com.example.demo.dto.TotalSalesDto;
import com.example.demo.dto.UpdateMrp;
import com.example.demo.exception.StoreNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.Store;
import com.example.demo.service.SaleService;

@RestController
@RequestMapping("/sale")
@CrossOrigin(origins = "http://localhost:4200") 
public class SalesController {
	
	@Autowired
	private SaleService saleService;
	
	@GetMapping("/total/{id}")
	public ResponseEntity<?> getTotalSales(@PathVariable int id){
		try {
            List<TotalSalesDto> salesData = saleService.getSales(id);
            return new ResponseEntity<>(salesData, HttpStatus.OK);
        } catch (StoreNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); 
        }
	}
	
	@PostMapping("/addStore")
	public ResponseEntity<String> addNewStore(@RequestBody Store store){
		saleService.addStore(store);
		return new ResponseEntity<>("Store Added Successfully",HttpStatus.CREATED);
	}
	
  
	@PostMapping("/addSale")
	public ResponseEntity<Map<String, String>> addSaleContent(@RequestBody SaleDto saledto){
	    saleService.addSale(saledto);
	    
	    // Create a response map
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Sale Added Successfully");

	    // Return the response as JSON
	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/product/updateMrp/{id}")
	public ResponseEntity<String> updateMrp(@PathVariable int id,@RequestBody UpdateMrp newMrp){
		try {
			saleService.updateProductMrp(id,newMrp);
			return new ResponseEntity<>("Product MRP updated Successfully",HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	   @GetMapping("/totalSaleData")
	    public ResponseEntity<?> getData(@RequestParam Long id, @RequestParam("date") String dateStr) {


	        

	        try {
	        	LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	            List<TotalSalesDto> salesData = saleService.getTotalSales(id,date);
	            return new ResponseEntity<>(salesData, HttpStatus.OK);
	        } catch (StoreNotFoundException e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); 
	        }
	        
	    }
	
	   @GetMapping("/storesId")
	   public List<Store> getAllStoreIds() {
	       return saleService.getAllStoreIds();
	   }
	   
	   
	   @GetMapping("/getAllProducts")
	   public List<Product> getProducts(){
		   return saleService.getAllProducts();
	   }
	
	

}

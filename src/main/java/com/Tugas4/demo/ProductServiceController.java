/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tugas4.demo;

/**
 *
 * @author Zuhan
 */
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Zuhan
 */
@RestController
public class ProductServiceController {
    private static Map<String, Product> productRepo = new HashMap<>();
    static{
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        honey.setNumber(5);
        honey.setPrice(5000);
        honey.setTotal();
        productRepo.put(honey.getId(), honey);
        
        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        almond.setNumber(5);
        almond.setPrice(7000);
        almond.setTotal();
        productRepo.put(almond.getId(), almond);
    }
    
    //Method hapus data
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id")String id){
        //Kondisi ID tidak ada ketika dihapus
        if(!productRepo.containsKey(id))throw new ProductNotfoundException();
        productRepo.remove(id);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }
    
    //Method edit data
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        //Kondisi ID tidak ada ketika diedit
        if(!productRepo.containsKey(id))throw new ProductNotfoundException();
        productRepo.remove(id);
        product.setId(id);
        productRepo.put(id, product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }
    
    //Method tambah data
    @RequestMapping(value = "/products", method = RequestMethod.POST)
      public ResponseEntity<Object> createProduct(@RequestBody Product product){
        //Kondisi ID telah ada yang mengisi
        if(productRepo.containsKey(product.getId())){ 
            return new ResponseEntity<>("ID is already fill", HttpStatus.OK); 
        }
        //Kondisi ID telah ditambahkan
        else{
            productRepo.put(product.getId(), product);
            return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
        }    
   }
    
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
}
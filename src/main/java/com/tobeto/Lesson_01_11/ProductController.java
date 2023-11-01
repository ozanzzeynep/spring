package com.tobeto.Lesson_01_11;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private List<Product> productList = new ArrayList<>();

    @GetMapping
    public List<Product> getAll(){
        return productList;
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable int id) throws Exception {
      Optional<Product> result =  productList.stream()
              .filter(product -> product.getId() == id)
              .findFirst();
      if(result.isPresent()){
          return result.get();
      }
      else{
          throw  new Exception(id + " id'li ürün bulunamadı.");
      }
    }

    @PostMapping
    public String add(@RequestBody Product product){
        productList.add(product);
        return "Ürün eklendi";
    }
}

package com.example.productmaintain.data;


import java.util.List;

import com.example.productmaintain.business.Product;

public interface ProductRepository {
    List<Product> getProducts();
    Product getProduct(String pCode);
    Product save(Product p);
    Product update(Product p);
    void deleteProduct(String productCode);
}

package org.example;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private final String name;
    private final List<Product> products;

    public Category(String name){
        this.name = name;
        this.products = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public List<Product> getProducts(){
        return products;
    }
    public void addProduct(Product product){
        products.add(product);
    }
}

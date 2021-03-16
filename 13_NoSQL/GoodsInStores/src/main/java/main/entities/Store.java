package main.entities;

import lombok.Data;

import java.util.List;

@Data
public class Store {
    String name;
    List<Product> productList;
}
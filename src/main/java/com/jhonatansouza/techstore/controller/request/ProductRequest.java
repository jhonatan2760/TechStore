package com.jhonatansouza.techstore.controller.request;

import com.jhonatansouza.techstore.model.ProductItem;

public class ProductRequest {

    private String name;
    private String description;
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductItem toItem(){
        ProductItem productItem  = new ProductItem();
        productItem.setDescription(this.description);
        productItem.setName(this.name);
        productItem.setQuantity(this.quantity);
        return productItem;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

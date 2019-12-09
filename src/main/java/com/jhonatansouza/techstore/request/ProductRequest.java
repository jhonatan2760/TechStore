package com.jhonatansouza.techstore.request;

import com.jhonatansouza.techstore.model.ProductItem;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductRequest {

    @NotNull
    private String name;
    @NotNull
    private String description;
    @Min(1)
    @Max(100)
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

    //avoid builder use
    public ProductItem toItemWithId(String uuid){
        ProductItem productItem = this.toItem();
        productItem.setUuid(uuid);
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

package com.jhonatansouza.techstore.model;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ProductItem {

    private String uuid;
    private String name;
    private String description;
    private int quantity;

    public ProductItem() {
    }

    public ProductItem(Map<String, AttributeValue> preItem){
            this.uuid = preItem.get("uuid").s();
            this.name = preItem.get("name").s();
            this.description = preItem.get("description").s();
            this.quantity = Integer.parseInt(preItem.get("quantity").n());
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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

    //caution to use getKeyMapper cuz the jersey can confuse the key jax-rs
    public Map<String, AttributeValue> keyMapper(){
        Map<String, AttributeValue> mp = new HashMap<>();
        mp.put("uuid", AttributeValue.builder().s(this.uuid).build());
        return mp;
    }

    public Map<String, AttributeValue> mapper(){
        Map<String, AttributeValue> att = new HashMap<>();
        att.put("uuid", AttributeValue.builder().s(this.uuid).build());
        att.put("name", AttributeValue.builder().s(this.name).build());
        att.put("description", AttributeValue.builder().s(this.description).build());
        att.put("quantity", AttributeValue.builder().n(String.valueOf(this.quantity)).build());
        return att;
    }

    public Map<String, AttributeValueUpdate> update(){
        Map<String, AttributeValueUpdate> att = new HashMap<>();
        att.put("name", AttributeValueUpdate.builder().value(AttributeValue.builder().s(this.name).build()).build());
        att.put("description", AttributeValueUpdate.builder().value(AttributeValue.builder().s(this.description).build()).build());
        att.put("quantity", AttributeValueUpdate.builder().value(AttributeValue.builder().n(String.valueOf(this.quantity)).build()).build());
        return att;
    }
}

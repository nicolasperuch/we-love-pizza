package com.github.nicolasperuch.model;

public class OrderModel {

    private String flavor;
    private String size;
    private String currentStatus;

    public String getFlavor() {
        return flavor;
    }

    public OrderModel setFlavor(String flavor) {
        this.flavor = flavor;
        return this;
    }

    public String getSize() {
        return size;
    }

    public OrderModel setSize(String size) {
        this.size = size;
        return this;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public OrderModel setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
        return this;
    }
}

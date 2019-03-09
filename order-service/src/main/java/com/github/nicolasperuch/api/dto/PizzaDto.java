package com.github.nicolasperuch.api.dto;

public class PizzaDto {
    private String flavor;
    private String size;

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

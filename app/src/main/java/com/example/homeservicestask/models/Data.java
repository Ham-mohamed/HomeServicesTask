package com.example.homeservicestask.models;

import java.util.ArrayList;
import java.util.List;

public class Data {
private List<CategoryData> categories=new ArrayList<>();

    public List<CategoryData> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryData> categories) {
        this.categories = categories;
    }
}

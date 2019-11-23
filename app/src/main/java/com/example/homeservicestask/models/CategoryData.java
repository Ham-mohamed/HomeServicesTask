package com.example.homeservicestask.models;

import java.util.ArrayList;
import java.util.List;

public class CategoryData {

    private int id;
    private String created_at;
    private String updated_at;
    private String name;
    private String description;
    private String image;
    private int order;
    private String deleted_at;

    private int type;
    private String customePrice;
    private String can_scheduled;
    private String time_interval;
    private int typevtwo;
    private String status;
    private String destination;
    private String request_image;
    private String request_image_text;
    private String cancelCustomeMsg;
    private List<Services> services =new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCustomePrice() {
        return customePrice;
    }

    public void setCustomePrice(String customePrice) {
        this.customePrice = customePrice;
    }

    public String getCan_scheduled() {
        return can_scheduled;
    }

    public void setCan_scheduled(String can_scheduled) {
        this.can_scheduled = can_scheduled;
    }

    public String getTime_interval() {
        return time_interval;
    }

    public void setTime_interval(String time_interval) {
        this.time_interval = time_interval;
    }

    public int getTypevtwo() {
        return typevtwo;
    }

    public void setTypevtwo(int typevtwo) {
        this.typevtwo = typevtwo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getRequest_image() {
        return request_image;
    }

    public void setRequest_image(String request_image) {
        this.request_image = request_image;
    }

    public String getRequest_image_text() {
        return request_image_text;
    }

    public void setRequest_image_text(String request_image_text) {
        this.request_image_text = request_image_text;
    }

    public String getCancelCustomeMsg() {
        return cancelCustomeMsg;
    }

    public void setCancelCustomeMsg(String cancelCustomeMsg) {
        this.cancelCustomeMsg = cancelCustomeMsg;
    }

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> services) {
        this.services = services;
    }
}

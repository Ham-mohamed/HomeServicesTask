package com.example.homeservicestask.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Services {

    private int id;
    private String created_at;
    private String updated_at;
    private int category_id;
    private String name;
    private String description;
    private String image;
    private int policy_id;
    private int price;
    private String deleted_at;
    private String commission;
    private String status;
    private int providercount;
    private String currency;
    private String catid;
    private boolean isCompaninesService;

    private int type=4;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    //    @SerializedName("policy")
//    @Expose
//    private String policy;
   // private List<Components> services_components;
  //  private List<SubServices>sub_services;
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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPolicy_id() {
        return policy_id;
    }

    public void setPolicy_id(int policy_id) {
        this.policy_id = policy_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProvidercount() {
        return providercount;
    }

    public void setProvidercount(int providercount) {
        this.providercount = providercount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public boolean isCompaninesService() {
        return isCompaninesService;
    }

    public void setCompaninesService(boolean companinesService) {
        isCompaninesService = companinesService;
    }

//    public String getPolicy() {
//        return policy;
//    }
//
//    public void setPolicy(String policy) {
//        this.policy = policy;
//    }
}

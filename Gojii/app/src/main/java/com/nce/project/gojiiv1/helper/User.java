package com.nce.project.gojiiv1.helper;

import java.sql.Timestamp;

public class User {
    private String id,email,role,name;
   private String balance,phone,password;
    private String accessToken,socialId;
    private Timestamp createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public User(String id, String email, String role, String name, String balance, String phone, String password, String accessToken, String socialId, Timestamp createdAt) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.name = name;
        this.balance = balance;
        this.phone = phone;
        this.password = password;
        this.accessToken = accessToken;
        this.socialId = socialId;
        this.createdAt = createdAt;
    }
}

package com.example.lamur.exchangesstudents;

import java.io.Serializable;

public abstract class User implements Serializable {
    private int _id;
    private String _username;
    private String mdp;
    private String adresse;
    private int code_postal;
    private String description;
    private boolean license;
    private String company;
    private String phone;



    public User() {
    }

    public User(int _id, String _username, String mdp, String adresse, int code_postal, String description, boolean license, String company, String phone) {
        this._id = _id;
        this._username = _username;
        this.mdp = mdp;
        this.adresse = adresse;
        this.code_postal = code_postal;
        this.description = description;
        this.license = license;
        this.company = company;
        this.phone = phone;
    }

    public User(int _id, String _username, String mdp) {
            this._id = _id;
            this._username = _username;
            this.mdp = mdp;
        }

    public User(String _username, String mdp) {
        this._username = _username;
        this.mdp = mdp;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLicense() {
        return license;
    }

    public void setLicense(boolean license) {
        this.license = license;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

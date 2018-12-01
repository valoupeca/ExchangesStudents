package com.example.lamur.exchangesstudents;

import java.io.Serializable;

public abstract class User implements Serializable {
    private int _id;
    private String _username;
    private String mdp;
    private String adresse;
    private String ville;
    private String code_postal;
    private String description;
    private boolean license;
    private String company;
    private String phone;
    static Integer cle[] = {1,2,3,5,4,7};
    static String alphabet1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWzXYZ1234567890&é'-è_çà)(=/*-+²";



    public User() {
    }

    public User(int _id, String _username, String mdp, String adresse, String ville, String code_postal, String description, boolean license, String company, String phone) {
        this._id = _id;
        this._username = _username;
        this.mdp = mdp;
        this.adresse = adresse;
        this.ville = ville;
        this.code_postal = code_postal;
        this.description = description;
        this.license = license;
        this.company = company;
        this.phone = phone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public User(int _id, String _username, String mdp, String adresse, String code_postal, String description, boolean license, String company, String phone) {
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

    public User(String _username) {
        this._username = _username;
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

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
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

    public static String cryptage(String mdp){
        int a = mdp.length();
        int b = alphabet1.length();
        char[] tab = new char[a];
        char[] alpha = new char[b];
        for(int i = 0; i < b;i++){
            alpha[i] = alphabet1.charAt(i);
        }
        for(int i = 0; i < a;i++){
            tab[i] = mdp.charAt(i);
        }

        int k=0;
        int j=0;
        for(int i=0;i<mdp.length(); i++){

            while(!(tab[i]==alpha[j])){
                j++;
            }
            if ((j+cle[k])>=alphabet1.length()){
                tab[i]=alpha[j+cle[k]-alphabet1.length()];
            }
            else{
                tab[i]=alpha[j+cle[k]];
            }
            j=0;
            k=+1;
            if(k>cle.length) {
                k=0;
            }
        }
        String test = new String(tab);
        return test;
    }


    public static String decryptage(String mdp){
        int a = mdp.length();
        int b = alphabet1.length();
        char[] tab = new char[a];
        char[] alpha = new char[b];
        for(int i = 0; i < b;i++){
            alpha[i] = alphabet1.charAt(i);
        }
        for(int i = 0; i < a;i++){
            tab[i] = mdp.charAt(i);
        }

        int k=0;
        for(int i=0;i<mdp.length(); i++){
            int j=0;
            while(!(tab[i]==alpha[j])){
                j++;
            }
            if (j-cle[k]<0){
                tab[i]=alpha[j-cle[k]+alphabet1.length()];
            }
            else{
                tab[i]=alpha[j-cle[k]];
            }

            k=+1;
            if(k>cle.length) {
                k=0;
            }
        }
        String test = new String(tab);
        return test;
    }

}

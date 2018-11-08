package com.example.lamur.exchangesstudents;

import java.io.Serializable;

public abstract class User implements Serializable {
    private int _id;
    private String _username;
    private String mdp;

    public User() {
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
}

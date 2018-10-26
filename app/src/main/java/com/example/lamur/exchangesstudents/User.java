package com.example.lamur.exchangesstudents;

public class User {
    private int _id;
    private String _username;
    private String mdp;
    private String role;

    public User() {
    }

        public User(int _id, String _username, String mdp, String role) {
            this._id = _id;
            this._username = _username;
            this.mdp = mdp;
            this.role = role;
        }

    public User(String _username, String mdp, String role) {
        this._username = _username;
        this.mdp = mdp;
        this.role = role;
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

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;


    }
}

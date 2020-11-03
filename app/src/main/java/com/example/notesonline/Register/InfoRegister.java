package com.example.notesonline.Register;

public class InfoRegister {


    private String username;
    private String password;
    private String gmail;
    private String fullname;

    public InfoRegister(String username, String password, String gmail, String fullname) {
        this.username = username;
        this.password = password;
        this.gmail = gmail;
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}

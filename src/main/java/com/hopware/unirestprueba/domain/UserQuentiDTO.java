package com.hopware.unirestprueba.domain;

/**
 * Created by DouglasBrenes on 13/03/2017.
 */
public class UserQuentiDTO {

    private String username;
    private String password;
    private String userIPAddress;
    private String userClientId;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserIPAddress(String userIPAddress) {
        this.userIPAddress = userIPAddress;
    }

    public void setUserClientId(String userClientId) {
        this.userClientId = userClientId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserIPAddress() {
        return userIPAddress;
    }

    public String getUserClientId() {
        return userClientId;
    }




//    "Hopware","password": "C3nf0t3c","userIPAddress": "127.0.0.1","userClientId": "Hopware"

}

package com.winter.message;

import java.io.Serializable;

/**
 * @author tianweichang
 * @date 2018-05-09 9:17
 **/
public class RabbitMessages implements Serializable{
    private String userName;
    private String password;
    private String phone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

package com.djcanadastudio.appifymap.Model;

/**
 * Created by desenguo on 2016-09-08.
 */
public class Phones {
    private String main;
    private String mobile_phone;

    public Phones(String main,String mobile_phone)
    {
        this.main=main;
        this.mobile_phone=mobile_phone;
    }
    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

}

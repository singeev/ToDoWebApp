package com.honsu;

public class UserValidationService {

    public boolean isUserValid(String name, String pass){
        if(name.equals("Honsu") && pass.equals("12345"))
            return true;
        return false;
    }
}

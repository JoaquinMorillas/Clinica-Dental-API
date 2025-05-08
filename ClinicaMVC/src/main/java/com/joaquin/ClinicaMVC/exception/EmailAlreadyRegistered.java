package com.joaquin.ClinicaMVC.exception;

public class EmailAlreadyRegistered extends Exception{

    public EmailAlreadyRegistered(String msg){
        super(msg);
    }

}

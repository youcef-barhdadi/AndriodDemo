package com.thor.table;

import java.security.PublicKey;

public class Admin {
    private  int ID;
    private  String Name;

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public  void  setName(String name){
        this.Name = name;
    }


    public  void  setID(int ID){
        this.ID = ID;

    }

    public Admin(int ID,String name) {
        this.ID=ID;
        Name = name;
    }

    public Admin() {

    }
}

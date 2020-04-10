package com.example.lifelinev2.Patient;

/**
 * Created by Khizer Mehmood on 12/6/2018.
 */

public class User {

    String ID;
    String Name;
    String Email;
    String Password;
    String Allergy;
    String Gender;
    //String Status;
    String Blood_Group;
    String Last_Checkup;
    String Phone;


    public User() {

    }

    public User(String ID, String name, String email, String password, String allergy, String gender, String blood_Group, String last_checkup, String phone) {
        this.ID = ID;
        Name = name;
        Email = email;
        Password = password;
        Allergy = allergy;
        Gender = gender;
        //Status = status;
        Blood_Group = blood_Group;
        Last_Checkup = last_checkup;
        Phone = phone;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getAllergy() {
        return Allergy;
    }

    public String getGender() {
        return Gender;
    }

    //public String g( {
      //  return Status;
    //}

    public String getBlood_Group() {
        return Blood_Group;
    }

    public String getLast_Checkup() {
        return Last_Checkup;
    }

    public String getPhone() {
        return Phone;
    }
}

package com.example.lifelinev2.Doctor;

/**
 * Created by Khizer Mehmood on 12/6/2018.
 */

public class Doctor {

    String ID;
    String Name;
    String Email;
    String Password;
//    String PhotoURL;
    String Schedule;
    String Gender;
//    String Status;
    String Availability;
//    String Blood_Group;
    String Category;
//    String Last_Donated;
    String Phone;


    public Doctor() {

    }

    public Doctor(String ID, String name, String email, String password, String schedule, String gender, String availability, String category,  String phone) {
        this.ID = ID;
        Name = name;
        Email = email;
        Password = password;
        Schedule = schedule;
        Gender = gender;
        Availability = availability;
        Category = category;
       // Last_Donated = last_Donated;
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

    public String getSchedule() {
        return Schedule;
    }

    public String getGender() {
        return Gender;
    }

    public String getAvailability() {
        return Availability;
    }

    public String getCategory() {
        return Category;
    }

   // public String getLast_Donated() {
     //   return Last_Donated;
    //}

    public String getPhone() {
        return Phone;
    }
}

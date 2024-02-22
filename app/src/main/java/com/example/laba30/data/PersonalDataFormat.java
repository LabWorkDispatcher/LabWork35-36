package com.example.laba30.data;

import java.util.ArrayList;

public class PersonalDataFormat {
    public String name, surname, group;
    public int completedWorksAmount, acceptedWorksAmount;
    public ArrayList<Date> completedWorksDates, acceptedWorksDates;

    public PersonalDataFormat(String name, String surname, String group, int completedWorksAmount, int acceptedWorksAmount, ArrayList<Date> completedWorksDates, ArrayList<Date> acceptedWorksDates) {
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.completedWorksAmount = completedWorksAmount;
        this.acceptedWorksAmount = acceptedWorksAmount;
        this.completedWorksDates = completedWorksDates;
        this.acceptedWorksDates = acceptedWorksDates;
    }
}

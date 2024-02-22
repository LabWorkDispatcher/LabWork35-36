package com.example.laba30.adapters;

public class RecyclerViewItem {
    public int year, month, day, labNumber;
    public boolean accepted;

    public RecyclerViewItem(int year, int month, int day, int labNumber, boolean accepted) {
        this.accepted = accepted;
        this.labNumber = labNumber;
        this.year = year;
        this.month = month;
        this.day = day;
    }
}

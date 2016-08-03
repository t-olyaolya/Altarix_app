package com.tolyaolya.mygoals;

/**
 * Created by 111 on 07.07.2016.
 */
public class GoalsHandler {
    public int id;
    public String name;
    public String  details;
    public String date;
    public int flag;
    public int day;
    public int month;
    public int year;

    public GoalsHandler(){

    }
    public GoalsHandler(int id, String name, String date, String details, int flag){
        this.id=id;
        this.name=name;
        this.date=date;
        this.flag=flag;
        this.details=details;
    }

    public GoalsHandler(String name, String details, String date, int id){
        this.name=name;
        this.details=details;
        this.date=date;
        this.id=id;

    }

    public GoalsHandler(int id, String name, String details, String date,int flag, int day, int month, int year) {
        this.id=id;
        this.name=name;
        this.details=details;
        this.date=date;
        this.flag=flag;
        this.day=day;
        this.month=month;
        this.year=year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails(){
        return details;
    }

    public void setDetails(String details){
        this.details=details;
    }

    public int getFlag(){
        return flag;
    }

    public void setFlag(int flag){
        this.flag=flag;
    }
}

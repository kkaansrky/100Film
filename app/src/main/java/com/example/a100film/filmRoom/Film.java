package com.example.a100film.filmRoom;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;




@Entity(tableName = "film_table")
public class Film {

    @PrimaryKey()
    @NonNull
    private String  listNumbers;
    @NonNull
    private String name;
    @NonNull
    private String nameEng;
    @NonNull
    private String rating;


    @NonNull
    private String nimage;

    private  String detail;
    public Film(){

    }

    public Film(String listNumbers, String name, String nameEng, String rating, String nimage, String detail) {
        this.listNumbers = listNumbers;
        this.name = name;
        this.nameEng = nameEng;
        this.rating = rating;
        this.nimage = nimage;
        this.detail = detail;
    }

    @NonNull
    public String getListNumbers() {
        return listNumbers;
    }

    public void setListNumbers(@NonNull String listNumbers) {
        this.listNumbers = listNumbers;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getNameEng() { return nameEng; }

    public void setNameEng(String nameEng) { this.nameEng = nameEng; }

    public String getRating() { return rating; }

    public void setRating(String rating) {
        this.rating = rating;
    }
    @NonNull
    public String getNimage() {
        return nimage;
    }

    public void setNimage(@NonNull String nimage) {
        this.nimage = nimage;
    }

    public String getDetail() { return detail; }

    public void setDetail(String detail) { this.detail = detail; }
}

package com.example.wcmovie.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.sql.Timestamp;

public class Comment {
    private Integer id;
    private String movieid;
    private String moviecontent;
    private String username;
    private Timestamp created;
    private String moviename;

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieid() {
        return movieid;
    }

    public void setMovieid(String movieid) {
        this.movieid = movieid;
    }

    public String getMoviecontent() {
        return moviecontent;
    }

    public void setMoviecontent(String moviecontent) {
        this.moviecontent = moviecontent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm", timezone = "GMT+8")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}

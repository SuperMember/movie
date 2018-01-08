package com.example.wcmovie.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "movie")
public class MovieProperties {
    private String baseUrl;
    private String showing;
    private String preshow;
    private String top250;
    private String weekly;
    private String newmovie;
    private String usmovie;
    private String search;
    private String movie;

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getShowing() {
        return showing;
    }

    public void setShowing(String showing) {
        this.showing = showing;
    }

    public String getPreshow() {
        return preshow;
    }

    public void setPreshow(String preshow) {
        this.preshow = preshow;
    }

    public String getTop250() {
        return top250;
    }

    public void setTop250(String top250) {
        this.top250 = top250;
    }

    public String getWeekly() {
        return weekly;
    }

    public void setWeekly(String weekly) {
        this.weekly = weekly;
    }

    public String getNewmovie() {
        return newmovie;
    }

    public void setNewmovie(String newmovie) {
        this.newmovie = newmovie;
    }

    public String getUsmovie() {
        return usmovie;
    }

    public void setUsmovie(String usmovie) {
        this.usmovie = usmovie;
    }
}

package com.example.hind.newsapp;

/**
 * Created by hind on 27/05/2018 AD.
 */

public class News {
    String section;
    String title;
    String url;
    String publishedAt;
    String author;

    public News(String section, String title, String url, String publishedAt, String author){
        this.section = section;
        this.title = title;
        this.url = url;
        this.publishedAt = publishedAt;
        this.author = author;
    }

    public String getSection(){return section;}

    public String getTitle(){return title;}

    public String getUrl(){return url;}

    public String getPublishedAt(){return publishedAt;}

    public String getAuthor(){return author;}
}

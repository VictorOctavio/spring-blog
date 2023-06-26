package com.practice.demo.dto;

public class PublicationDTO {
    private long id;
    private String title;
    private String description;
    private  String content;


    public PublicationDTO() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String contain) {
        this.content = contain;
    }
}

package com.ll.domain;

public class Quotaiton {

    private int id;
    private String content;
    private String authorName;

    public Quotaiton(int id, String content, String authorName) {
        this.id = id;
        this.content = content;
        this.authorName = authorName;
    }

    public int getId() {
        return id;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}

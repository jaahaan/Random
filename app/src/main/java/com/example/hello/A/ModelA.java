package com.example.hello.A;

public class ModelA {
    private String title, subtitle, imageUrl, key;

    public ModelA() {
    }

    public ModelA(String title, String subtitle, String imageUrl, String key) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageUrl = imageUrl;
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

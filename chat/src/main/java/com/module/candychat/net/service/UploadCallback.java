package com.module.candychat.net.service;

import co.moonmonkeylabs.realmsearchview.model.TheMessageObject;

public class UploadCallback extends TheMessageObject.DataEntity {

    private int id;
    private String fileType;
    private String fileName;
    private int active;
    private String full_path;
    private String thumb;
    private String url;

    public void setId(int id) {
        this.id = id;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setFull_path(String full_path) {
        this.full_path = full_path;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public int getActive() {
        return active;
    }

    public String getFull_path() {
        return full_path;
    }

    public String getThumb() {
        return thumb;
    }

    public String getUrl() {
        return url;
    }
}

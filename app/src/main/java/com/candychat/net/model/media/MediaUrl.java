package com.candychat.net.model.media;

/**
 * Created by root1 on 2/3/16.
 */
public class MediaUrl {
    String urlPhoto;
    String urlVoice;
    String urlVideo;
    String fileName;

    public MediaUrl(){

    }

    public MediaUrl(String urlPhoto, String urlVoice, String urlVideo, String fileName) {
        this.urlPhoto = urlPhoto;
        this.urlVoice = urlVoice;
        this.urlVideo = urlVideo;
        this.fileName = fileName;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getUrlVoice() {
        return urlVoice;
    }

    public void setUrlVoice(String urlVoice) {
        this.urlVoice = urlVoice;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

package com.candychat.net.model.response;

import com.candychat.net.model.UserModel;

import java.util.List;

public class FriendsDataResponse {


    /**
     * status : 1
     * page : 1
     * per_page : 20
     * pages : 1
     * total : 2
     * users : [{"id":3,"about":"","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"anyarimpanus@gmail.com","email_verification_key":"4698f890b3f02cb08369b17f5adbabd7","email_verified":0,"language":"","last_logged":1448955321,"name":"Anya","password":"039a726ac0aeec3dde33e45387a7d4ac","timeTv":1448944359,"timestamp":"2015-12-01 02:35:21","timezone":"","type":"user","username":"anya","phone_code":"","phone":"","verified":0,"birthday":"01/01/1990","gender":"male","avatarIv":"themes/grape/images/default-male-avatarIv.png","cover":"themes/grape/images/default-cover.png","is_following":false,"online":true},{"id":7,"about":"Ok","active":1,"avatar_id":17,"cover_id":0,"cover_position":0,"email":"Test@gmail.com","email_verification_key":"e5e94ef0761d1178b0547caab7bd97da","email_verified":0,"language":"","last_logged":1449916657,"name":"Test","password":"81dc9bdb52d04dc20036dbd8313ed055","timeTv":1449164579,"timestamp":"2015-12-12 05:37:37","timezone":"Pacific/Midway","type":"user","username":"Test","phone_code":"","phone":"","verified":0,"birthday":"01/01/1990","gender":"male","avatarIv":"photos/2015/12/wG8Km_17_70efdf2ec9b086079795c442636b55fb_100x100.jpg","cover":"themes/grape/images/default-cover.png","is_following":false,"online":true}]
     */

    private String status;
    private int page;
    private int per_page;
    private int pages;
    private int total;
    /**
     * id : 3
     * about :
     * active : 1
     * avatar_id : 0
     * cover_id : 0
     * cover_position : 0
     * email : anyarimpanus@gmail.com
     * email_verification_key : 4698f890b3f02cb08369b17f5adbabd7
     * email_verified : 0
     * language :
     * last_logged : 1448955321
     * name : Anya
     * password : 039a726ac0aeec3dde33e45387a7d4ac
     * timeTv : 1448944359
     * timestamp : 2015-12-01 02:35:21
     * timezone :
     * type : user
     * username : anya
     * phone_code :
     * phone :
     * verified : 0
     * birthday : 01/01/1990
     * gender : male
     * avatarIv : themes/grape/images/default-male-avatarIv.png
     * cover : themes/grape/images/default-cover.png
     * is_following : false
     * online : true
     */

    private List<UserModel> users;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    public String getStatus() {
        return status;
    }

    public int getPage() {
        return page;
    }

    public int getPer_page() {
        return per_page;
    }

    public int getPages() {
        return pages;
    }

    public int getTotal() {
        return total;
    }

    public List<UserModel> getUsers() {
        return users;
    }

}

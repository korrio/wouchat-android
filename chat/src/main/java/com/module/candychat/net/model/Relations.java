package com.module.candychat.net.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by korrio on 12/21/16.
 */

public class Relations {

    /**
     * status : 1
     * count : {"me":1,"favorite":11,"group":42,"friends":14}
     * me : [{"id":2,"about":"Working","active":1,"avatar_id":992,"cover_id":0,"cover_position":0,"email":"online agriculture","email_verification_key":"3cea59cdd74660ff55be53543cb1c1e8","email_verified":0,"language":"english","last_logged":1463117044,"last_fb_token":"","name":"kOrr5566","password":"039a726ac0aeec3dde33e45387a7d4ac","time":1448944317,"timestamp":"2016-10-22 20:08:02","timezone":"Pacific/Midway","type":"user","username":"korrio","phone_code":"","phone":"","verified":0,"updated_at":"-0001-11-30 00:00:00","created_at":"0000-00-00 00:00:00","birthday":"01/01/1990","gender":"male","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_100x100.jpg","cover":"themes/grape/images/default-cover.png","cid":66}]
     * favorite : [{"id":303,"about":"มีปม","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"sattboot11@gmail.com","email_verification_key":"7c2eb74ca2144900842d373772f5fe63","email_verified":0,"language":"","last_logged":1454256771,"last_fb_token":"","name":"ChonlakantSattboot","password":"ee959c5ce4a6e2055b857a924b8388a9","time":1454256529,"timestamp":"2016-02-03 09:25:46","timezone":"","type":"user","username":"chonlakant","phone_code":"","phone":"","verified":0,"updated_at":"-0001-11-30 00:00:00","created_at":"0000-00-00 00:00:00","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":true,"online":false},{"id":342,"about":"","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"free2herme@yahoo.com","email_verification_key":"xXroKQDgJCx1KNWuBhUjg4VoUOvTIh","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"free2herme","password":"e35cf7b66449df565f93c607d5a81d09","time":0,"timestamp":"2016-02-09 13:23:07","timezone":"","type":"user","username":"free2herme","phone_code":"","phone":"","verified":0,"updated_at":"2016-02-09 13:23:07","created_at":"2016-02-09 13:23:07","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":true,"online":false},{"id":354,"about":"","active":1,"avatar_id":442,"cover_id":0,"cover_position":0,"email":"sattboot1@gmail.com","email_verification_key":"BZ13VHjSTrAKiylfto1fys7byTJqqw","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"chonlakant","password":"25d55ad283aa400af464c76d713c07ad","time":0,"timestamp":"2016-02-11 07:42:14","timezone":"","type":"user","username":"sattboot1","phone_code":"","phone":"","verified":0,"updated_at":"2016-02-10 08:09:26","created_at":"2016-02-10 08:09:26","birthday":"01/01/1990","gender":"male","avatar":"photos/2016/02/eomyO_442_c203d8a151612acf12457e4d67635a95_100x100.jpeg","cover":"themes/grape/images/default-cover.png","is_following":true,"online":false},{"id":384,"about":"","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"test0926366365@wouchat.com","email_verification_key":"B5Hyr62MTjxySvp6UI2ld7eF1qG32I","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"+66926366365","password":"d41d8cd98f00b204e9800998ecf8427e","time":0,"timestamp":"2016-02-13 12:58:55","timezone":"","type":"user","username":"test0926366365","phone_code":"66","phone":"+66926366365","verified":0,"updated_at":"2016-02-13 12:58:55","created_at":"2016-02-13 12:58:55","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":true,"online":false},{"id":400,"about":"","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"fb_543346439173167@facebook.com","email_verification_key":"23148bef0a5259306a5d6a39a7fb09d4","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"ศิลป์ บ้านเรือนไทย","password":"a1378fc54a9200e3be7a731a867ac1fc","time":1455760746,"timestamp":"2016-02-17 20:59:06","timezone":"","type":"user","username":"fb_543346439173167","phone_code":"","phone":"","verified":0,"updated_at":"-0001-11-30 00:00:00","created_at":"0000-00-00 00:00:00","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":true,"online":false},{"id":248,"about":"hhhhhh","active":1,"avatar_id":449,"cover_id":0,"cover_position":0,"email":"hhhhhh","email_verification_key":"6fefc82a104b6daeeea99e9eb14d7d66","email_verified":0,"language":"","last_logged":1454674481,"last_fb_token":"","name":"vanessa fisher","password":"29e4d16a20ab72464eece0b752d17910","time":1451648495,"timestamp":"2016-02-18 05:45:12","timezone":"","type":"user","username":"whitedog408","phone_code":"","phone":"","verified":0,"updated_at":"-0001-11-30 00:00:00","created_at":"0000-00-00 00:00:00","birthday":"01/01/1990","gender":"male","avatar":"photos/2016/02/OZi4z_449_d61e4bbd6393c9111e6526ea173a7c8b_100x100.jpg","cover":"themes/grape/images/default-cover.png","is_following":true,"online":false},{"id":394,"about":"","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"fb_849768615073647@facebook.com","email_verification_key":"ab6bb6ae8362071f29b49bfd2df4229c","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"dddf","password":"578360e1412fc72ca0d2e146267ee019","time":1455635989,"timestamp":"2016-05-12 23:25:57","timezone":"","type":"user","username":"llooi","phone_code":"","phone":"","verified":0,"updated_at":"-0001-11-30 00:00:00","created_at":"0000-00-00 00:00:00","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":true,"online":false},{"id":333,"about":"","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"test03@test03.com","email_verification_key":"df4f383eef0650cedd60deddd02d41b7","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"test test","password":"5f4dcc3b5aa765d61d8327deb882cf99","time":1454850008,"timestamp":"2016-02-07 08:00:08","timezone":"","type":"user","username":"test03","phone_code":"","phone":"","verified":0,"updated_at":"-0001-11-30 00:00:00","created_at":"0000-00-00 00:00:00","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":true,"online":false},{"id":3,"about":"At gym","active":1,"avatar_id":435,"cover_id":0,"cover_position":0,"email":"anyarimpanus@gmail.com","email_verification_key":"4698f890b3f02cb08369b17f5adbabd7","email_verified":0,"language":"","last_logged":1455894932,"last_fb_token":"","name":"Anya Rimpanus","password":"039a726ac0aeec3dde33e45387a7d4ac","time":1448944359,"timestamp":"2016-05-13 06:59:49","timezone":"","type":"user","username":"anya","phone_code":"","phone":"","verified":0,"updated_at":"-0001-11-30 00:00:00","created_at":"0000-00-00 00:00:00","birthday":"01/01/1990","gender":"male","avatar":"photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_100x100.jpg","cover":"themes/grape/images/default-cover.png","is_following":true,"online":false},{"id":363,"about":"","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"free2herme3@yahoo.com","email_verification_key":"tSCs8y3pfH90ZQlYemtzNCPlXeKGc7","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"test11feb1","password":"e10adc3949ba59abbe56e057f20f883e","time":0,"timestamp":"2016-05-14 04:22:08","timezone":"","type":"user","username":"test11feb1","phone_code":"","phone":"","verified":0,"updated_at":"2016-02-10 13:01:21","created_at":"2016-02-10 13:01:21","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":true,"online":false},{"id":425,"about":"Available","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"sattaboot11@gmail.com","email_verification_key":"oHXviAdP4JW74WHaTmWs3dNUBgeQu0","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"sattaboot11","password":"039a726ac0aeec3dde33e45387a7d4ac","time":0,"timestamp":"2016-09-07 12:48:05","timezone":"","type":"user","username":"sattaboot11","phone_code":"","phone":"","verified":0,"updated_at":"2016-09-07 23:48:05","created_at":"2016-09-07 23:48:05","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":true,"online":false}]
     * group : [{"conversationId":3,"id":3,"conversationType":"PRIVATE","name":"่่ิินนืืนื","createdBy":"3","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-02-03T13:38:21.000Z","conversationMembers":[{"userId":3,"username":"anya","name":"Anya Rimpanus","avatar":"photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":3},{"userId":286,"username":"haahaa","name":"haa haa haa haa","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":3},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":3},{"userId":354,"username":"sattboot1","name":"chonlakant","avatar":"photos/2016/02/eomyO_442_c203d8a151612acf12457e4d67635a95_thumb","extension":"jpeg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":3}]},{"conversationId":10,"id":10,"conversationType":"PRIVATE","name":"556","createdBy":"3","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-02-04T12:57:52.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":10},{"userId":386,"username":"0917366196","name":"ออกมาเลย","avatar":"photos/2016/02/iwh4C_461_0353ab4cbed5beae847a7ff6e220b5cf_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":10},{"userId":285,"username":"heyhaa","name":"heyhaa","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":10}]},{"conversationId":25,"id":25,"conversationType":"PRIVATE","name":"อะนะ","createdBy":"247","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-02-09T12:08:05.000Z","conversationMembers":[{"userId":247,"username":"bluewolf224","name":"talisha bons","avatar":"photos/2016/02/7of6I_448_9b70e8fe62e40c570a322f1b0b659098_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":25},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":25},{"userId":248,"username":"whitedog408","name":"vanessa fisher","avatar":"photos/2016/02/OZi4z_449_d61e4bbd6393c9111e6526ea173a7c8b_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":25}]},{"conversationId":39,"id":39,"conversationType":"PRIVATE","name":"นาาาาา","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-02-11T14:10:50.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":39},{"userId":285,"username":"heyhaa","name":"heyhaa","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":39},{"userId":251,"username":"edgurfgjkk","name":"Ethiopia ccvnnn","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":39},{"userId":274,"username":"redmouse461","name":"sara bonnet","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":39}]},{"conversationId":46,"id":46,"conversationType":"PRIVATE","name":"ไม่รู้แหละ","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-02-12T05:56:44.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":46},{"userId":285,"username":"heyhaa","name":"heyhaa","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":46},{"userId":251,"username":"edgurfgjkk","name":"Ethiopia ccvnnn","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":46},{"userId":274,"username":"redmouse461","name":"sara bonnet","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":46},{"userId":304,"username":"test01","name":"test test","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":46},{"userId":343,"username":"test456","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":46}]},{"conversationId":73,"id":73,"conversationType":"PRIVATE","name":"hshsh","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-02-16T01:31:43.000Z","conversationMembers":[{"userId":1,"username":"korrbestbefore","name":"Korr Best Before","avatar":"photos/2015/12/Qglgk_41_3416a75f4cea9109507cacd8e2f2aefc_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":73},{"userId":363,"username":"test11feb1","name":"test11feb1","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":73},{"userId":354,"username":"sattboot1","name":"chonlakant","avatar":"photos/2016/02/eomyO_442_c203d8a151612acf12457e4d67635a95_thumb","extension":"jpeg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":73},{"userId":248,"username":"whitedog408","name":"vanessa fisher","avatar":"photos/2016/02/OZi4z_449_d61e4bbd6393c9111e6526ea173a7c8b_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":73},{"userId":333,"username":"test03","name":"test test","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":73},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":73},{"userId":3,"username":"anya","name":"Anya Rimpanus","avatar":"photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":73}]},{"conversationId":64,"id":64,"conversationType":"PRIVATE","name":"yyy","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-02-15T15:29:53.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":64},{"userId":303,"username":"chonlakant","name":"ChonlakantSattboot","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":64},{"userId":342,"username":"free2herme","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":64},{"userId":408,"username":"NATZ","name":"NATZ","avatar":"photos/2016/05/dUkoU_1015_298923c8190045e91288b430794814c4_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":64}]},{"conversationId":80,"id":80,"conversationType":"PRIVATE","name":"iiiiig","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-02-16T11:42:49.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":80},{"userId":342,"username":"free2herme","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":80},{"userId":384,"username":"test0926366365","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":80},{"userId":303,"username":"chonlakant","name":"ChonlakantSattboot","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":80}]},{"conversationId":81,"id":81,"conversationType":"PRIVATE","name":"hehshsh","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-02-16T11:57:55.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":81},{"userId":342,"username":"free2herme","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":81}]},{"conversationId":85,"id":85,"conversationType":"PRIVATE","name":"yggh","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-02-16T12:21:10.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":85},{"userId":303,"username":"chonlakant","name":"ChonlakantSattboot","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":85},{"userId":342,"username":"free2herme","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":85},{"userId":363,"username":"test11feb1","name":"test11feb1","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":85},{"userId":354,"username":"sattboot1","name":"chonlakant","avatar":"photos/2016/02/eomyO_442_c203d8a151612acf12457e4d67635a95_thumb","extension":"jpeg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":85},{"userId":3,"username":"anya","name":"Anya Rimpanus","avatar":"photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":85}]},{"conversationId":86,"id":86,"conversationType":"PRIVATE","name":"iijjhc","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-02-16T12:30:30.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":86},{"userId":303,"username":"chonlakant","name":"ChonlakantSattboot","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":86},{"userId":342,"username":"free2herme","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":86},{"userId":363,"username":"test11feb1","name":"test11feb1","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":86},{"userId":384,"username":"test0926366365","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":86},{"userId":248,"username":"whitedog408","name":"vanessa fisher","avatar":"photos/2016/02/OZi4z_449_d61e4bbd6393c9111e6526ea173a7c8b_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":86},{"userId":3,"username":"anya","name":"Anya Rimpanus","avatar":"photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":86},{"userId":354,"username":"sattboot1","name":"chonlakant","avatar":"photos/2016/02/eomyO_442_c203d8a151612acf12457e4d67635a95_thumb","extension":"jpeg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":86}]},{"conversationId":108,"id":108,"conversationType":"PRIVATE","name":"cool","createdBy":"394","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-02-18T11:57:21.000Z","conversationMembers":[{"userId":394,"username":"llooi","name":"dddf","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":108},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":108}]},{"conversationId":112,"id":112,"conversationType":"PRIVATE","name":"test","createdBy":"394","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-02-18T12:21:15.000Z","conversationMembers":[{"userId":394,"username":"llooi","name":"dddf","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":112},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":112}]},{"conversationId":113,"id":113,"conversationType":"PRIVATE","name":"the","createdBy":"394","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-02-18T12:21:52.000Z","conversationMembers":[{"userId":394,"username":"llooi","name":"dddf","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":113},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":113}]},{"conversationId":127,"id":127,"conversationType":"PRIVATE","name":"นนนนย","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-02-23T17:18:46.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":127},{"userId":342,"username":"free2herme","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":127},{"userId":303,"username":"chonlakant","name":"ChonlakantSattboot","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":127},{"userId":363,"username":"test11feb1","name":"test11feb1","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":127},{"userId":354,"username":"sattboot1","name":"chonlakant","avatar":"photos/2016/02/eomyO_442_c203d8a151612acf12457e4d67635a95_thumb","extension":"jpeg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":127},{"userId":384,"username":"test0926366365","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":127},{"userId":248,"username":"whitedog408","name":"vanessa fisher","avatar":"photos/2016/02/OZi4z_449_d61e4bbd6393c9111e6526ea173a7c8b_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":127},{"userId":394,"username":"llooi","name":"dddf","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":127},{"userId":333,"username":"test03","name":"test test","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":127}]},{"conversationId":128,"id":128,"conversationType":"PRIVATE","name":"fdfdfdfdf","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-02-24T11:22:15.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":128}]},{"conversationId":138,"id":138,"conversationType":"PRIVATE","name":"hhhh","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-03-04T13:28:37.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":138},{"userId":303,"username":"chonlakant","name":"ChonlakantSattboot","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":138},{"userId":342,"username":"free2herme","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":138},{"userId":386,"username":"0917366196","name":"ออกมาเลย","avatar":"photos/2016/02/iwh4C_461_0353ab4cbed5beae847a7ff6e220b5cf_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":138}]},{"conversationId":146,"id":146,"conversationType":"PRIVATE","name":"hello","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-03-09T10:23:05.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":146},{"userId":386,"username":"0917366196","name":"ออกมาเลย","avatar":"photos/2016/02/iwh4C_461_0353ab4cbed5beae847a7ff6e220b5cf_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":146},{"userId":408,"username":"NATZ","name":"NATZ","avatar":"photos/2016/05/dUkoU_1015_298923c8190045e91288b430794814c4_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":146}]},{"conversationId":150,"id":150,"conversationType":"PRIVATE","name":"test","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-03-10T12:39:19.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":150},{"userId":342,"username":"free2herme","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":150}]},{"conversationId":151,"id":151,"conversationType":"PRIVATE","name":"ไม่ผิดหรอก","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-03-10T13:18:11.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":151},{"userId":3,"username":"anya","name":"Anya Rimpanus","avatar":"photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":151}]},{"conversationId":154,"id":154,"conversationType":"PRIVATE","name":"WOUchatAndroid24","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-03-11T03:42:42.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":154},{"userId":385,"username":"0133656365","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":154},{"userId":285,"username":"heyhaa","name":"heyhaa","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":154},{"userId":363,"username":"test11feb1","name":"test11feb1","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":154},{"userId":386,"username":"0917366196","name":"ออกมาเลย","avatar":"photos/2016/02/iwh4C_461_0353ab4cbed5beae847a7ff6e220b5cf_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":154},{"userId":247,"username":"bluewolf224","name":"talisha bons","avatar":"photos/2016/02/7of6I_448_9b70e8fe62e40c570a322f1b0b659098_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":154}]},{"conversationId":156,"id":156,"conversationType":"PRIVATE","name":"Gr001","createdBy":"3","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-03-11T04:34:32.000Z","conversationMembers":[{"userId":3,"username":"anya","name":"Anya Rimpanus","avatar":"photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":156},{"userId":1,"username":"korrbestbefore","name":"Korr Best Before","avatar":"photos/2015/12/Qglgk_41_3416a75f4cea9109507cacd8e2f2aefc_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":156},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":156}]},{"conversationId":157,"id":157,"conversationType":"PRIVATE","name":"Gr001","createdBy":"3","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-03-11T04:35:00.000Z","conversationMembers":[{"userId":3,"username":"anya","name":"Anya Rimpanus","avatar":"photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":157},{"userId":3,"username":"anya","name":"Anya Rimpanus","avatar":"photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":157},{"userId":3,"username":"anya","name":"Anya Rimpanus","avatar":"photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":157},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":157}]},{"conversationId":159,"id":159,"conversationType":"PRIVATE","name":"Gr001","createdBy":"3","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-03-11T04:41:53.000Z","conversationMembers":[{"userId":3,"username":"anya","name":"Anya Rimpanus","avatar":"photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":159},{"userId":3,"username":"anya","name":"Anya Rimpanus","avatar":"photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":159},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":159}]},{"conversationId":163,"id":163,"conversationType":"PRIVATE","name":"testG2","createdBy":"363","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-03-16T23:14:00.000Z","conversationMembers":[{"userId":363,"username":"test11feb1","name":"test11feb1","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":163},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":163}]},{"conversationId":167,"id":167,"conversationType":"PRIVATE","name":"ถถถ","createdBy":"3","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-03-21T08:01:00.000Z","conversationMembers":[{"userId":3,"username":"anya","name":"Anya Rimpanus","avatar":"photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":167},{"userId":1,"username":"korrbestbefore","name":"Korr Best Before","avatar":"photos/2015/12/Qglgk_41_3416a75f4cea9109507cacd8e2f2aefc_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":167},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":167}]},{"conversationId":184,"id":184,"conversationType":"PRIVATE","name":"heheha","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-05-20T09:22:56.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":184},{"userId":285,"username":"heyhaa","name":"heyhaa","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":184},{"userId":251,"username":"edgurfgjkk","name":"Ethiopia ccvnnn","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":184}]},{"conversationId":197,"id":197,"conversationType":"PRIVATE","name":"BROADCAST","createdBy":"1","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-05-21T05:56:20.000Z","conversationMembers":[{"userId":1,"username":"korrbestbefore","name":"Korr Best Before","avatar":"photos/2015/12/Qglgk_41_3416a75f4cea9109507cacd8e2f2aefc_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":197},{"userId":1,"username":"korrbestbefore","name":"Korr Best Before","avatar":"photos/2015/12/Qglgk_41_3416a75f4cea9109507cacd8e2f2aefc_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":197},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":197}]},{"conversationId":198,"id":198,"conversationType":"PRIVATE","name":"BROADCAST","createdBy":"1","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-05-21T05:56:59.000Z","conversationMembers":[{"userId":1,"username":"korrbestbefore","name":"Korr Best Before","avatar":"photos/2015/12/Qglgk_41_3416a75f4cea9109507cacd8e2f2aefc_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":198},{"userId":3,"username":"anya","name":"Anya Rimpanus","avatar":"photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":198},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":198}]},{"conversationId":199,"id":199,"conversationType":"PRIVATE","name":"BROADCAST","createdBy":"1","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-05-21T05:58:01.000Z","conversationMembers":[{"userId":1,"username":"korrbestbefore","name":"Korr Best Before","avatar":"photos/2015/12/Qglgk_41_3416a75f4cea9109507cacd8e2f2aefc_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":199},{"userId":3,"username":"anya","name":"Anya Rimpanus","avatar":"photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":199},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":199}]},{"conversationId":200,"id":200,"conversationType":"PRIVATE","name":"THIS IS GROUP","createdBy":"1","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-05-21T05:58:10.000Z","conversationMembers":[{"userId":1,"username":"korrbestbefore","name":"Korr Best Before","avatar":"photos/2015/12/Qglgk_41_3416a75f4cea9109507cacd8e2f2aefc_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":200},{"userId":3,"username":"anya","name":"Anya Rimpanus","avatar":"photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":200},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":200}]},{"conversationId":214,"id":214,"conversationType":"PRIVATE","name":"555","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-05-21T06:04:16.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":214},{"userId":285,"username":"heyhaa","name":"heyhaa","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":214},{"userId":251,"username":"edgurfgjkk","name":"Ethiopia ccvnnn","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":214}]},{"conversationId":229,"id":229,"conversationType":"PRIVATE","name":"ทดสอบ","createdBy":"425","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-09-07T16:57:41.000Z","conversationMembers":[{"userId":425,"username":"sattaboot11","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":229},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":229}]},{"conversationId":230,"id":230,"conversationType":"PRIVATE","name":"ทดสอบ","createdBy":"425","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-09-07T16:57:41.000Z","conversationMembers":[{"userId":425,"username":"sattaboot11","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":230},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":230}]},{"conversationId":231,"id":231,"conversationType":"PRIVATE","name":"nnnn","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-09-07T16:58:00.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":231},{"userId":425,"username":"sattaboot11","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":231}]},{"conversationId":232,"id":232,"conversationType":"PRIVATE","name":"nnnn","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-09-07T16:58:00.000Z","conversationMembers":[{"userId":425,"username":"sattaboot11","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":232},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":232}]},{"conversationId":233,"id":233,"conversationType":"PRIVATE","name":"yyyyyyyy","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-09-07T16:58:27.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":233},{"userId":425,"username":"sattaboot11","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":233}]},{"conversationId":234,"id":234,"conversationType":"PRIVATE","name":"yyyyyyyy","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-09-07T16:58:27.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":234},{"userId":425,"username":"sattaboot11","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":234}]},{"conversationId":235,"id":235,"conversationType":"PRIVATE","name":"nonobo","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-09-07T16:58:49.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":235},{"userId":425,"username":"sattaboot11","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":235}]},{"conversationId":236,"id":236,"conversationType":"PRIVATE","name":"nonobo","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-09-07T16:58:49.000Z","conversationMembers":[{"userId":425,"username":"sattaboot11","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":236},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":236}]},{"conversationId":248,"id":248,"conversationType":"PRIVATE","name":"bas1234","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-09-20T03:01:09.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":248},{"userId":251,"username":"edgurfgjkk","name":"Ethiopia ccvnnn","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":248},{"userId":285,"username":"heyhaa","name":"heyhaa","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":248},{"userId":343,"username":"test456","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":248}]},{"conversationId":249,"id":249,"conversationType":"PRIVATE","name":"bas1234","createdBy":"2","avatar":"/config_uploads/default/no_group_avatar.jpg","createdAt":"2016-09-20T03:01:09.000Z","conversationMembers":[{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":249},{"userId":285,"username":"heyhaa","name":"heyhaa","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":249},{"userId":251,"username":"edgurfgjkk","name":"Ethiopia ccvnnn","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":249},{"userId":343,"username":"test456","name":"","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":249}]}]
     * friends : [{"id":285,"about":"โม้ยังไง","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"heyhaa@gmail.com","email_verification_key":"aec92a8000b08b1dd4219f0215659cbb","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"heyhaa","password":"b1ae5e93417bcad38289744575885590","time":1452937639,"timestamp":"2016-02-03 09:26:19","timezone":"","type":"user","username":"heyhaa","phone_code":"","phone":"","verified":0,"updated_at":"-0001-11-30 00:00:00","created_at":"0000-00-00 00:00:00","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":false,"online":true},{"id":251,"about":"แววตา","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"dhhhxcbh@yahoo.com","email_verification_key":"bde1230d8917ee5443a2785c881a4480","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"Ethiopia ccvnnn","password":"7f641ee2aab74d1af446edf09f6bb293","time":1451739398,"timestamp":"2016-02-03 09:25:54","timezone":"","type":"user","username":"edgurfgjkk","phone_code":"","phone":"","verified":0,"updated_at":"-0001-11-30 00:00:00","created_at":"0000-00-00 00:00:00","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":false,"online":true},{"id":304,"about":"","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"test@test.com","email_verification_key":"83db6fac5c4bb4c48da2d4db05d0aee7","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"test test","password":"5f4dcc3b5aa765d61d8327deb882cf99","time":1454716119,"timestamp":"2016-02-05 18:48:39","timezone":"","type":"user","username":"test01","phone_code":"","phone":"","verified":0,"updated_at":"-0001-11-30 00:00:00","created_at":"0000-00-00 00:00:00","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":false,"online":true},{"id":343,"about":"","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"free2herme2@yahoo.com","email_verification_key":"3v2HRfk9vuxgksHtk0AWhwI7uWcS4r","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"test456","password":"e10adc3949ba59abbe56e057f20f883e","time":0,"timestamp":"2016-02-09 13:24:48","timezone":"","type":"user","username":"test456","phone_code":"","phone":"","verified":0,"updated_at":"2016-02-09 13:24:48","created_at":"2016-02-09 13:24:48","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":false,"online":true},{"id":286,"about":"โอ้วโหวว","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"haahaa@ggg.com","email_verification_key":"1e5aef4abadb93a28bccd1e734b365c5","email_verified":0,"language":"","last_logged":1453001321,"last_fb_token":"","name":"haa haa haa haa","password":"97dd718528e256f811699584767e21a6","time":1452937845,"timestamp":"2016-02-03 09:26:32","timezone":"","type":"user","username":"haahaa","phone_code":"","phone":"","verified":0,"updated_at":"-0001-11-30 00:00:00","created_at":"0000-00-00 00:00:00","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":false,"online":true},{"id":303,"about":"มีปม","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"sattboot11@gmail.com","email_verification_key":"7c2eb74ca2144900842d373772f5fe63","email_verified":0,"language":"","last_logged":1454256771,"last_fb_token":"","name":"ChonlakantSattboot","password":"ee959c5ce4a6e2055b857a924b8388a9","time":1454256529,"timestamp":"2016-02-03 09:25:46","timezone":"","type":"user","username":"chonlakant","phone_code":"","phone":"","verified":0,"updated_at":"-0001-11-30 00:00:00","created_at":"0000-00-00 00:00:00","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":true,"online":true},{"id":277,"about":"","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"celia.herrero@example.com","email_verification_key":"43bdbdbf6ef5c2b937411294cf772959","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"celia herrero","password":"039a726ac0aeec3dde33e45387a7d4ac","time":1451820797,"timestamp":"2016-01-03 06:33:17","timezone":"","type":"user","username":"whiteostrich239","phone_code":"","phone":"","verified":0,"updated_at":"-0001-11-30 00:00:00","created_at":"0000-00-00 00:00:00","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":false,"online":true},{"id":394,"about":"","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"fb_849768615073647@facebook.com","email_verification_key":"ab6bb6ae8362071f29b49bfd2df4229c","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"dddf","password":"578360e1412fc72ca0d2e146267ee019","time":1455635989,"timestamp":"2016-05-12 23:25:57","timezone":"","type":"user","username":"llooi","phone_code":"","phone":"","verified":0,"updated_at":"-0001-11-30 00:00:00","created_at":"0000-00-00 00:00:00","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":true,"online":true},{"id":386,"about":"hahaha","active":1,"avatar_id":461,"cover_id":0,"cover_position":0,"email":"hahaha","email_verification_key":"9CwyjAxgedwsOKiSXeAKQEmYwpO8BD","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"+66917366196","password":"d41d8cd98f00b204e9800998ecf8427e","time":0,"timestamp":"2016-02-15 19:34:58","timezone":"","type":"user","username":"0917366196","phone_code":"66","phone":"+66917366196","verified":0,"updated_at":"2016-02-15 17:38:25","created_at":"2016-02-15 17:38:25","birthday":"01/01/1990","gender":"male","avatar":"photos/2016/02/iwh4C_461_0353ab4cbed5beae847a7ff6e220b5cf_100x100.jpg","cover":"themes/grape/images/default-cover.png","is_following":false,"online":true},{"id":396,"about":"","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"fb_10154988876680571@facebook.com","email_verification_key":"d26902b701d5949050fa442b16be23f4","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"Korr Best Before","password":"dfe7b2d830f657da87fa81c302d7f5da","time":1455638321,"timestamp":"2016-02-18 05:43:57","timezone":"","type":"user","username":"korrfb","phone_code":"","phone":"","verified":0,"updated_at":"-0001-11-30 00:00:00","created_at":"0000-00-00 00:00:00","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":false,"online":true},{"id":408,"about":"Happy","active":1,"avatar_id":1015,"cover_id":0,"cover_position":0,"email":"Happy","email_verification_key":"Xjk1lO1smghPVEBBG9mamCOe90Z8Zk","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"+66Happy","password":"5d9ce5c7eb058d9c40970e3ecca44c2a","time":0,"timestamp":"2016-05-19 05:40:38","timezone":"","type":"user","username":"NATZ","phone_code":"66","phone":"+66Happy","verified":0,"updated_at":"2016-02-29 21:55:53","created_at":"2016-02-29 21:55:53","birthday":"01/01/1990","gender":"male","avatar":"photos/2016/05/dUkoU_1015_298923c8190045e91288b430794814c4_100x100.jpg","cover":"themes/grape/images/default-cover.png","is_following":false,"online":true},{"id":385,"about":"","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"test0133656365@yahoo.com","email_verification_key":"kEWbblprkpbpPVKGb3g1YRrbRUfVgm","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"+60133656365","password":"e10adc3949ba59abbe56e057f20f883e","time":0,"timestamp":"2016-02-13 15:29:07","timezone":"","type":"user","username":"0133656365","phone_code":"60","phone":"+60133656365","verified":0,"updated_at":"2016-02-13 15:29:07","created_at":"2016-02-13 15:29:07","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":false,"online":true},{"id":302,"about":"มีตัง","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"fafdsaaa122@gmail.com","email_verification_key":"0d7373d7f0d4d4f914b90151f07b2b7c","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"asdf5555 asdf555","password":"25d55ad283aa400af464c76d713c07ad","time":1454255566,"timestamp":"2016-02-03 09:25:22","timezone":"","type":"user","username":"bas_1","phone_code":"","phone":"","verified":0,"updated_at":"-0001-11-30 00:00:00","created_at":"0000-00-00 00:00:00","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":false,"online":true},{"id":425,"about":"Available","active":1,"avatar_id":0,"cover_id":0,"cover_position":0,"email":"sattaboot11@gmail.com","email_verification_key":"oHXviAdP4JW74WHaTmWs3dNUBgeQu0","email_verified":0,"language":"","last_logged":0,"last_fb_token":"","name":"sattaboot11","password":"039a726ac0aeec3dde33e45387a7d4ac","time":0,"timestamp":"2016-09-07 12:48:05","timezone":"","type":"user","username":"sattaboot11","phone_code":"","phone":"","verified":0,"updated_at":"2016-09-07 23:48:05","created_at":"2016-09-07 23:48:05","birthday":"01/01/1990","gender":"male","avatar":"themes/grape/images/default-male-avatar.png","cover":"themes/grape/images/default-cover.png","is_following":true,"online":true}]
     */

    private String status;
    private CountBean count;
    private ArrayList<MeBean> me;
    private ArrayList<FavoriteBean> favorite;
    private ArrayList<GroupBean> group;
    private ArrayList<FriendsBean> friends;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CountBean getCount() {
        return count;
    }

    public void setCount(CountBean count) {
        this.count = count;
    }

    public ArrayList<MeBean> getMe() {
        return me;
    }

    public void setMe(ArrayList<MeBean> me) {
        this.me = me;
    }

    public ArrayList<FavoriteBean> getFavorite() {
        return favorite;
    }

    public void setFavorite(ArrayList<FavoriteBean> favorite) {
        this.favorite = favorite;
    }

    public ArrayList<GroupBean> getGroup() {
        return group;
    }

    public void setGroup(ArrayList<GroupBean> group) {
        this.group = group;
    }

    public ArrayList<FriendsBean> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<FriendsBean> friends) {
        this.friends = friends;
    }

    public static class CountBean {
        /**
         * me : 1
         * favorite : 11
         * group : 42
         * friends : 14
         */

        private int me;
        private int favorite;
        private int group;
        private int friends;

        public int getMe() {
            return me;
        }

        public void setMe(int me) {
            this.me = me;
        }

        public int getFavorite() {
            return favorite;
        }

        public void setFavorite(int favorite) {
            this.favorite = favorite;
        }

        public int getGroup() {
            return group;
        }

        public void setGroup(int group) {
            this.group = group;
        }

        public int getFriends() {
            return friends;
        }

        public void setFriends(int friends) {
            this.friends = friends;
        }
    }

    public static class MeBean {
        /**
         * id : 2
         * about : Working
         * active : 1
         * avatar_id : 992
         * cover_id : 0
         * cover_position : 0
         * email : online agriculture
         * email_verification_key : 3cea59cdd74660ff55be53543cb1c1e8
         * email_verified : 0
         * language : english
         * last_logged : 1463117044
         * last_fb_token :
         * name : kOrr5566
         * password : 039a726ac0aeec3dde33e45387a7d4ac
         * time : 1448944317
         * timestamp : 2016-10-22 20:08:02
         * timezone : Pacific/Midway
         * type : user
         * username : korrio
         * phone_code :
         * phone :
         * verified : 0
         * updated_at : -0001-11-30 00:00:00
         * created_at : 0000-00-00 00:00:00
         * birthday : 01/01/1990
         * gender : male
         * avatar : photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_100x100.jpg
         * cover : themes/grape/images/default-cover.png
         * cid : 66
         */

        private int id;
        private String about;
        private int active;
        private int avatar_id;
        private int cover_id;
        private int cover_position;
        private String email;
        private String email_verification_key;
        private int email_verified;
        private String language;
        private int last_logged;
        private String last_fb_token;
        private String name;
        private String password;
        private int time;
        private String timestamp;
        private String timezone;
        private String type;
        private String username;
        private String phone_code;
        private String phone;
        private int verified;
        private String updated_at;
        private String created_at;
        private String birthday;
        private String gender;
        private String avatar;
        private String cover;
        private int cid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public int getAvatar_id() {
            return avatar_id;
        }

        public void setAvatar_id(int avatar_id) {
            this.avatar_id = avatar_id;
        }

        public int getCover_id() {
            return cover_id;
        }

        public void setCover_id(int cover_id) {
            this.cover_id = cover_id;
        }

        public int getCover_position() {
            return cover_position;
        }

        public void setCover_position(int cover_position) {
            this.cover_position = cover_position;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmail_verification_key() {
            return email_verification_key;
        }

        public void setEmail_verification_key(String email_verification_key) {
            this.email_verification_key = email_verification_key;
        }

        public int getEmail_verified() {
            return email_verified;
        }

        public void setEmail_verified(int email_verified) {
            this.email_verified = email_verified;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public int getLast_logged() {
            return last_logged;
        }

        public void setLast_logged(int last_logged) {
            this.last_logged = last_logged;
        }

        public String getLast_fb_token() {
            return last_fb_token;
        }

        public void setLast_fb_token(String last_fb_token) {
            this.last_fb_token = last_fb_token;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone_code() {
            return phone_code;
        }

        public void setPhone_code(String phone_code) {
            this.phone_code = phone_code;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getVerified() {
            return verified;
        }

        public void setVerified(int verified) {
            this.verified = verified;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }
    }

    public static class FavoriteBean {
        /**
         * id : 303
         * about : มีปม
         * active : 1
         * avatar_id : 0
         * cover_id : 0
         * cover_position : 0
         * email : sattboot11@gmail.com
         * email_verification_key : 7c2eb74ca2144900842d373772f5fe63
         * email_verified : 0
         * language :
         * last_logged : 1454256771
         * last_fb_token :
         * name : ChonlakantSattboot
         * password : ee959c5ce4a6e2055b857a924b8388a9
         * time : 1454256529
         * timestamp : 2016-02-03 09:25:46
         * timezone :
         * type : user
         * username : chonlakant
         * phone_code :
         * phone :
         * verified : 0
         * updated_at : -0001-11-30 00:00:00
         * created_at : 0000-00-00 00:00:00
         * birthday : 01/01/1990
         * gender : male
         * avatar : themes/grape/images/default-male-avatar.png
         * cover : themes/grape/images/default-cover.png
         * is_following : true
         * online : false
         */

        private int id;
        private String about;
        private int active;
        private int avatar_id;
        private int cover_id;
        private int cover_position;
        private String email;
        private String email_verification_key;
        private int email_verified;
        private String language;
        private int last_logged;
        private String last_fb_token;
        private String name;
        private String password;
        private int time;
        private String timestamp;
        private String timezone;
        private String type;
        private String username;
        private String phone_code;
        private String phone;
        private int verified;
        private String updated_at;
        private String created_at;
        private String birthday;
        private String gender;
        private String avatar;
        private String cover;
        private boolean is_following;
        private boolean online;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public int getAvatar_id() {
            return avatar_id;
        }

        public void setAvatar_id(int avatar_id) {
            this.avatar_id = avatar_id;
        }

        public int getCover_id() {
            return cover_id;
        }

        public void setCover_id(int cover_id) {
            this.cover_id = cover_id;
        }

        public int getCover_position() {
            return cover_position;
        }

        public void setCover_position(int cover_position) {
            this.cover_position = cover_position;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmail_verification_key() {
            return email_verification_key;
        }

        public void setEmail_verification_key(String email_verification_key) {
            this.email_verification_key = email_verification_key;
        }

        public int getEmail_verified() {
            return email_verified;
        }

        public void setEmail_verified(int email_verified) {
            this.email_verified = email_verified;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public int getLast_logged() {
            return last_logged;
        }

        public void setLast_logged(int last_logged) {
            this.last_logged = last_logged;
        }

        public String getLast_fb_token() {
            return last_fb_token;
        }

        public void setLast_fb_token(String last_fb_token) {
            this.last_fb_token = last_fb_token;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone_code() {
            return phone_code;
        }

        public void setPhone_code(String phone_code) {
            this.phone_code = phone_code;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getVerified() {
            return verified;
        }

        public void setVerified(int verified) {
            this.verified = verified;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public boolean isIs_following() {
            return is_following;
        }

        public void setIs_following(boolean is_following) {
            this.is_following = is_following;
        }

        public boolean isOnline() {
            return online;
        }

        public void setOnline(boolean online) {
            this.online = online;
        }
    }

    public static class GroupBean {
        /**
         * conversationId : 3
         * id : 3
         * conversationType : PRIVATE
         * name : ่่ิินนืืนื
         * createdBy : 3
         * avatar : /config_uploads/default/no_group_avatar.jpg
         * createdAt : 2016-02-03T13:38:21.000Z
         * conversationMembers : [{"userId":3,"username":"anya","name":"Anya Rimpanus","avatar":"photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":1,"conversationId":3},{"userId":286,"username":"haahaa","name":"haa haa haa haa","avatar":"themes/grape/images/default-male-avatar","extension":"png","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":3},{"userId":2,"username":"korrio","name":"kOrr5566","avatar":"photos/2016/05/lb2VQ_992_860320be12a1c050cd7731794e231bd3_thumb","extension":"jpg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":1,"inviteAcceptFlag":0,"conversationId":3},{"userId":354,"username":"sattboot1","name":"chonlakant","avatar":"photos/2016/02/eomyO_442_c203d8a151612acf12457e4d67635a95_thumb","extension":"jpeg","activeFlag":0,"adminFlag":0,"inviteFlag":1,"canInviteFlag":0,"inviteAcceptFlag":0,"conversationId":3}]
         */

        private int conversationId;
        private int id;
        private String conversationType;
        private String name;
        private String createdBy;
        private String avatar;
        private String createdAt;
        private List<ConversationMembersBean> conversationMembers;

        public int getConversationId() {
            return conversationId;
        }

        public void setConversationId(int conversationId) {
            this.conversationId = conversationId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getConversationType() {
            return conversationType;
        }

        public void setConversationType(String conversationType) {
            this.conversationType = conversationType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public List<ConversationMembersBean> getConversationMembers() {
            return conversationMembers;
        }

        public void setConversationMembers(List<ConversationMembersBean> conversationMembers) {
            this.conversationMembers = conversationMembers;
        }

        public static class ConversationMembersBean {
            /**
             * userId : 3
             * username : anya
             * name : Anya Rimpanus
             * avatar : photos/2016/02/VeTf5_435_ddb30680a691d157187ee1cf9e896d03_thumb
             * extension : jpg
             * activeFlag : 0
             * adminFlag : 0
             * inviteFlag : 1
             * canInviteFlag : 1
             * inviteAcceptFlag : 1
             * conversationId : 3
             */

            private int userId;
            private String username;
            private String name;
            private String avatar;
            private String extension;
            private int activeFlag;
            private int adminFlag;
            private int inviteFlag;
            private int canInviteFlag;
            private int inviteAcceptFlag;
            private int conversationId;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getExtension() {
                return extension;
            }

            public void setExtension(String extension) {
                this.extension = extension;
            }

            public int getActiveFlag() {
                return activeFlag;
            }

            public void setActiveFlag(int activeFlag) {
                this.activeFlag = activeFlag;
            }

            public int getAdminFlag() {
                return adminFlag;
            }

            public void setAdminFlag(int adminFlag) {
                this.adminFlag = adminFlag;
            }

            public int getInviteFlag() {
                return inviteFlag;
            }

            public void setInviteFlag(int inviteFlag) {
                this.inviteFlag = inviteFlag;
            }

            public int getCanInviteFlag() {
                return canInviteFlag;
            }

            public void setCanInviteFlag(int canInviteFlag) {
                this.canInviteFlag = canInviteFlag;
            }

            public int getInviteAcceptFlag() {
                return inviteAcceptFlag;
            }

            public void setInviteAcceptFlag(int inviteAcceptFlag) {
                this.inviteAcceptFlag = inviteAcceptFlag;
            }

            public int getConversationId() {
                return conversationId;
            }

            public void setConversationId(int conversationId) {
                this.conversationId = conversationId;
            }
        }
    }

    public static class FriendsBean {
        /**
         * id : 285
         * about : โม้ยังไง
         * active : 1
         * avatar_id : 0
         * cover_id : 0
         * cover_position : 0
         * email : heyhaa@gmail.com
         * email_verification_key : aec92a8000b08b1dd4219f0215659cbb
         * email_verified : 0
         * language :
         * last_logged : 0
         * last_fb_token :
         * name : heyhaa
         * password : b1ae5e93417bcad38289744575885590
         * time : 1452937639
         * timestamp : 2016-02-03 09:26:19
         * timezone :
         * type : user
         * username : heyhaa
         * phone_code :
         * phone :
         * verified : 0
         * updated_at : -0001-11-30 00:00:00
         * created_at : 0000-00-00 00:00:00
         * birthday : 01/01/1990
         * gender : male
         * avatar : themes/grape/images/default-male-avatar.png
         * cover : themes/grape/images/default-cover.png
         * is_following : false
         * online : true
         */

        private int id;
        private String about;
        private int active;
        private int avatar_id;
        private int cover_id;
        private int cover_position;
        private String email;
        private String email_verification_key;
        private int email_verified;
        private String language;
        private int last_logged;
        private String last_fb_token;
        private String name;
        private String password;
        private int time;
        private String timestamp;
        private String timezone;
        private String type;
        private String username;
        private String phone_code;
        private String phone;
        private int verified;
        private String updated_at;
        private String created_at;
        private String birthday;
        private String gender;
        private String avatar;
        private String cover;
        private boolean is_following;
        private boolean online;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public int getAvatar_id() {
            return avatar_id;
        }

        public void setAvatar_id(int avatar_id) {
            this.avatar_id = avatar_id;
        }

        public int getCover_id() {
            return cover_id;
        }

        public void setCover_id(int cover_id) {
            this.cover_id = cover_id;
        }

        public int getCover_position() {
            return cover_position;
        }

        public void setCover_position(int cover_position) {
            this.cover_position = cover_position;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmail_verification_key() {
            return email_verification_key;
        }

        public void setEmail_verification_key(String email_verification_key) {
            this.email_verification_key = email_verification_key;
        }

        public int getEmail_verified() {
            return email_verified;
        }

        public void setEmail_verified(int email_verified) {
            this.email_verified = email_verified;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public int getLast_logged() {
            return last_logged;
        }

        public void setLast_logged(int last_logged) {
            this.last_logged = last_logged;
        }

        public String getLast_fb_token() {
            return last_fb_token;
        }

        public void setLast_fb_token(String last_fb_token) {
            this.last_fb_token = last_fb_token;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone_code() {
            return phone_code;
        }

        public void setPhone_code(String phone_code) {
            this.phone_code = phone_code;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getVerified() {
            return verified;
        }

        public void setVerified(int verified) {
            this.verified = verified;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public boolean isIs_following() {
            return is_following;
        }

        public void setIs_following(boolean is_following) {
            this.is_following = is_following;
        }

        public boolean isOnline() {
            return online;
        }

        public void setOnline(boolean online) {
            this.online = online;
        }
    }
}

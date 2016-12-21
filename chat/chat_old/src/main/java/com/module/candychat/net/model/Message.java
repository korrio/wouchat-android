package com.module.candychat.net.model;

import android.graphics.Bitmap;

import java.util.Date;


public class Message {
	public final static int MSG_TYPE_TEXT 	= 0;
	public final static int MSG_TYPE_STICKER = 1;
	public final static int MSG_TYPE_PHOTO 	= 2;
    public final static int MSG_TYPE_CLIP = 3;

    //public final static int MSG_TYPE_YOUTUBE_LINK = 3;
    public final static int MSG_TYPE_YOUTUBE_OBJ = 31;
    public final static int MSG_TYPE_SOUNDCLOUD_OBJ = 32;
    public final static int MSG_TYPE_AUDIO_CALL = 4;
    public final static int MSG_TYPE_VIDEO_CALL = 5;
    public final static int MSG_TYPE_LOCATION = 6;
    public final static int MSG_TYPE_CONTACT = 7;
    public final static int MSG_TYPE_VOICE = 8;

	public final static int MSG_STATE_SENDING 	= 0;
	public final static int MSG_STATE_SUCCESS 	= 1;
	public final static int MSG_STATE_FAIL 		= 2;

	private Long id;
	private Integer type;		// 0-text | 1-face | 2-photo | more type ... TODO://
	private Integer state; 		// 0-sending | 1-success | 2-fail
	private String fromUserName;
	private String fromUserAvatar;
	private String toUserName;
	private String toUserAvatar;
	private String content;
	private String data;

	private Boolean isSend = false;
	private Boolean sendSuccess;
	private Date time;
	private String typeColor;
	private String typeStyle;
	private Bitmap mImage;

	float timeVoice;
	String filePath;

	int postion;
	int postionEn;
	String fontType;
	int sizeFont;

	public Message(){

	}

	public Message(Integer type, Integer state, String fromUserName,
				   String fromUserAvatar, String toUserName, String toUserAvatar,
				   String content, String data, Boolean isSend, Boolean sendSuccess, Date time, String typeColor, String typeStyle, Bitmap mImage
			, float timeVoice, String filePath,String fontType,int postion,int postionEn,int sizeFont) {
		super();
		this.type = type;
		this.state = state;
		this.fromUserName = fromUserName;
		this.fromUserAvatar = fromUserAvatar;
		this.toUserName = toUserName;
		this.toUserAvatar = toUserAvatar;
		this.content = content;
		this.data = data;

		this.isSend = isSend;
		this.sendSuccess = sendSuccess;
		this.time = time;
		this.typeColor = typeColor;
		this.typeStyle = typeStyle;
		this.mImage = mImage;
		this.filePath = filePath;
		this.timeVoice = timeVoice;
		this.fontType = fontType;
		this.postion = postion;
		this.postionEn = postionEn;
		this.sizeFont = sizeFont;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getFromUserAvatar() {
		return fromUserAvatar;
	}

	public void setFromUserAvatar(String fromUserAvatar) {
		this.fromUserAvatar = fromUserAvatar;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getToUserAvatar() {
		return toUserAvatar;
	}

	public void setToUserAvatar(String toUserAvatar) {
		this.toUserAvatar = toUserAvatar;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Boolean getIsSender() {
		if(isSend != null)
		return isSend;
		else return false;
	}

	public void setIsSend(Boolean isSend) {
		this.isSend = isSend;
	}

	public Boolean getSendSuccess() {
		return sendSuccess;
	}

	public void setSendSuccess(Boolean sendSuccess) {
		this.sendSuccess = sendSuccess;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTypeColor() {
		return typeColor;
	}

	public void setTypeColor(String typeColor) {
		this.typeColor = typeColor;
	}

	public String getTypeStyle() {
		return typeStyle;
	}

	public void setTypeStyle(String typeStyle) {
		this.typeStyle = typeStyle;
	}

	public Bitmap getmImage() {
		return mImage;
	}

	public void setmImage(Bitmap mImage) {
		this.mImage = mImage;
	}

	public float getTimeVoice() {
		return timeVoice;
	}

	public void setTimeVoice(float timeVoice) {
		this.timeVoice = timeVoice;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFontType() {
		return fontType;
	}

	public void setFontType(String fontType) {
		this.fontType = fontType;
	}

	public int getPostion() {
		return postion;
	}

	public void setPostion(int postion) {
		this.postion = postion;
	}

	public int getPostionEn() {
		return postionEn;
	}

	public void setPostionEn(int postionEn) {
		this.postionEn = postionEn;
	}

	public int getSizeFont() {
		return sizeFont;
	}

	public void setSizeFont(int sizeFont) {
		this.sizeFont = sizeFont;
	}
}

package com.msg.response;

public class TextRespMsg {
	// ToUserName 成员UserID
	// FromUserName 企业号CorpID
	// CreateTime 消息创建时间（整型）
	// MsgType 消息类型，此时固定为：text
	// Content 文本消息内容

	private String ToUserName; // 成员UserID
	private String FromUserName="wxa50ffd87271d6e9e";// 企业号CorpID
	private long CreateTime;// 消息创建时间（整型）
	private String MsgType;// 消息类型，此时固定为：text
	private String Content;// 文本消息内容

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}

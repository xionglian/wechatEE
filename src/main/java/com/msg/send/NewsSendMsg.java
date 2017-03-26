package com.msg.send;

import com.util.PropertyUtil;

import java.util.Map;

/**
 * @author mazhejiayu at 2016年2月15日
 *
 */
public class NewsSendMsg {
//	{
//		"touser": "UserID1|UserID2|UserID3",
//			"toparty": " PartyID1 | PartyID2 ",
//			"totag": " TagID1 | TagID2 ",
//			"msgtype": "news",
//			"agentid": 1,
//			"news": {
//		"articles":[
//		{
//			"title": "Title",
//				"description": "Description",
//				"url": "URL",
//				"picurl": "PIC_URL"
//		},
//		{
//			"title": "Title",
//				"description": "Description",
//				"url": "URL",
//				"picurl": "PIC_URL"
//		}
//		]
//	}
//	}
	private String touser="";
	private String toparty="";
	private String totag="";
	private String msgtype = "news";
	private String agentid = PropertyUtil.getProperty("AgentID");
	private Map<String,Object> news;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String... touser) {
		for(String tu:touser){
			this.touser += tu+"|";
		}
		this.touser = this.touser.substring(0, this.touser.length()-1);
		
	}

	public String getToparty() {
		return toparty;
	}

	public void setToparty(String... toparty) {
		for(String tp:toparty){
			this.toparty += tp+"|";
		}
		this.toparty = this.toparty.substring(0, this.toparty.length()-1);
	}

	public String getTotag() {
		return totag;
	}

	public void setTotag(String... totag) {
		for(String tg:totag){
			this.totag += tg+"|";
		}
		this.totag = this.totag.substring(0, this.totag.length()-1);
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}


}

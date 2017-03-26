package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.SystemDefaultCredentialsProvider;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.util.PropertyUtil;
import org.apache.http.entity.StringEntity;
import com.util.AESUtil;
import com.msg.send.TextSendMsg;
import java.net.URLEncoder;

/**
 * @author phan at 2016年2月17日
 *
 */
public class SendUtil {
	// https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN
	private final static String SEND_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";

	public static void sendWechatMobile(String accessToken, String tousers){

		try {
			TextSendMsg msg = new TextSendMsg();
			msg.setTouser(tousers);
			String userEncrypt = AESUtil.encryptOpenId(tousers);
			String domain = PropertyUtil.getProperty("domain");
			StringBuffer sb = new StringBuffer();
			sb.append("{\"touser\":\""+msg.getTouser()+"\",");
			sb.append("\"toparty\":\""+msg.getToparty()+"\",");
			sb.append("\"totag\":\""+msg.getTotag()+"\",");
			sb.append("\"msgtype\":\""+"news"+"\",");
			sb.append("\"agentid\":"+msg.getAgentid()+",");
			sb.append("\"news\":"+"{" +
					"\"articles\":[" +
					"{" +
					"\"title\":\"WechatPM|我的任务\"," +
					"\"description\":\"本页面有效期到"+(new Date().getMonth()+1)+"月末,请不要将该页面分享。获取最新资讯请点击我的任务中的全部任务。\"," +
					"\"content\":\"。。。\"," +
					"\"url\":\""+domain+"/wechatEE/html/wechatmobile_comp.html#/"+userEncrypt+"\"," +
					"}" +
					"]" +
					"}"+"}");
			String json = sb.toString();
			System.out.println(json);
			HttpClient client = HttpClients.createDefault();
			HttpPost post = new HttpPost(SEND_URL+accessToken);
			StringEntity sentity = new StringEntity(json, "utf-8");
			post.setEntity(sentity);
			post.setHeader("Content-type","application/json; charset=utf-8");
			HttpResponse response = client.execute(post);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				HttpEntity entity = response.getEntity();
				if(entity!=null){
					BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
					String line = null;
					while((line = br.readLine())!=null){
						System.out.println(line);
					}
				}
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	public static void send(String accessToken, String content, String tousers) {
		TextSendMsg msg = new TextSendMsg();
		msg.setTouser(tousers);
		msg.setText(content);
		String json = Msg2Json(msg);
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(SEND_URL+accessToken);
		StringEntity sentity = new StringEntity(json, "utf-8");
		post.setEntity(sentity);
		post.setHeader("Content-type","application/json; charset=utf-8");

		try {
			HttpResponse response = client.execute(post);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				HttpEntity entity = response.getEntity();
				if(entity!=null){
					BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
					String line = null;
					while((line = br.readLine())!=null){
						System.out.println(line);
					}
				}
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String Msg2Json(TextSendMsg msg) {
		
//			{
//			   "touser": "UserID1|UserID2|UserID3",
//			   "toparty": " PartyID1 | PartyID2 ",
//			   "totag": " TagID1 | TagID2 ",
//			   "msgtype": "text",
//			   "agentid": 1,
//			   "text": {
//			       "content": "Holiday Request For Pony(http://xxxxx)"
//			   },
//			   "safe":"0"
//			}
		String json = null;
		if(msg!=null){
			StringBuffer sb = new StringBuffer();
			sb.append("{\"touser\":\""+msg.getTouser()+"\",");
			sb.append("\"toparty\":\""+msg.getToparty()+"\",");
			sb.append("\"totag\":\""+msg.getTotag()+"\",");
			sb.append("\"msgtype\":\""+msg.getMsgtype()+"\",");
			sb.append("\"agentid\":"+msg.getAgentid()+",");
			sb.append("\"text\":"+msg.getText()+",");
			sb.append("\"safe\":\""+msg.getSafe()+"\"}");
			json = sb.toString();
		}
		return json;
	}
	
	public static void main(String[] args){
		SendUtil u = new  SendUtil();
		String accessToken = AccessTokenUtil.getAccessToken(null);
		u.sendWechatMobile(accessToken,"dev-mazhe");
	}
}

package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;



/**
 * @author phan at 2016年2月15日
 *
 */
public class AccessTokenUtil {
	// 获取访问权限码URL
    private final static String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
    
	public static String getAccessToken(HttpServletRequest request){
		if(request==null){
			return refleshAccessToken(null);
		}
		AccessToken accesstoken =null;
		ServletContext context = request.getSession().getServletContext();
		accesstoken = (AccessToken) context.getAttribute("accesstoken");
		if(accesstoken!=null){
			Date createTime = accesstoken.getCreateTime();
			if((new Date().getTime()-createTime.getTime())<7200*1000){
				System.out.println("accesstoken:"+accesstoken.getAccessToken());
				return accesstoken.getAccessToken();
			}
		}
		return refleshAccessToken(request);
		
	}
	private static String refleshAccessToken(HttpServletRequest request){
		String result = null;
		HttpClient client =HttpClients.createDefault();
		//https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=id&corpsecret=secrect
		HttpGet get = new HttpGet(ACCESS_TOKEN_URL+"?corpid="+PropertyUtil.getProperty("CorpID")+"&corpsecret="+PropertyUtil.getProperty("CorpSecret"));
		try {
			HttpResponse response = client.execute(get);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				HttpEntity entity = response.getEntity();
				if(entity!=null){
					BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
					String line = null;
					while((line = br.readLine())!=null){
						try {
							JSONObject json = new JSONObject(line);
							result = json.getString("access_token");
							if(request!=null){
								ServletContext context = request.getSession().getServletContext();
								AccessToken at = new AccessToken();
								at.setAccessToken(result);
								at.setCreateTime(new Date());
								context.setAttribute("accesstoken", at);
							}
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
		get.releaseConnection();
		System.out.println("accesstoken:"+result);
		return result;
		
	}
	
	
}

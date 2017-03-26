package com.util;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.msg.response.TextRespMsg;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;



/**
 * @author phan
 *
 */
public class ResponseUtil {

//	public static void sendTextResponse(HttpServletResponse response,
//			TextRespMsg Msg) {
//		String RespXml = XMLUtil.TextToXml(Msg);
//		PrintWriter out;
//		try {
//			out = response.getWriter();
//			out.print(RespXml);
//			
//			out.close();
//		} catch (IOException e) {
//			System.out.println("sendTextResponse发生异常");
//			e.printStackTrace();
//		}
//
//	}
	public static void sendTextResponse(HttpServletResponse response,String content ,String toUserName, WXBizMsgCrypt wxcpt ,String timeStamp, String nonce)
	{
		TextRespMsg msg = new TextRespMsg();
		msg.setFromUserName("wxa50ffd87271d6e9e");
		msg.setToUserName(toUserName);
		msg.setMsgType("text");
		msg.setCreateTime(new Date().getTime());
		msg.setContent(content);
		String RespXml = XMLUtil.TextToXml(msg);
		
		
		PrintWriter out;
		try {
			String encryptXml = wxcpt.EncryptMsg(RespXml, timeStamp, nonce);
			out = response.getWriter();
			out.print(encryptXml);
			
			out.close();
		} catch (Exception e) {
			System.out.println("sendTextResponse发生异常");
			e.printStackTrace();
		}
		
	}

	
	public static String getInputTips()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("你好，请按照以下形式输入查询：").append("\n\n");
		sb.append("[项目名]\n");
		sb.append("[子项目名]\n");
		
		
		return sb.toString();
	}
}

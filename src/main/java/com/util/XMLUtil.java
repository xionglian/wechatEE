package com.util;

import java.io.InputStream;
import java.io.StringReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.msg.response.TextRespMsg;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * @author phan
 *
 */
public class XMLUtil {
	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "click";//开发文档是大写，解析到的xml却是小写

	public static Map<String, String> parseXML(String Msg) throws Exception {
		System.out.println("parseXML");
		Map<String, String> map = new HashMap<String, String>();

		System.out.println(Msg);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(Msg);
		InputSource is = new InputSource(sr);
		org.w3c.dom.Document document = db.parse(is);
		org.w3c.dom.Element root = document.getDocumentElement();
		NodeList nodelist = root.getChildNodes();
		// NodeList nodelist1 = root.getElementsByTagName("Content");
		// String Content = nodelist1.item(0).getTextContent();
		// System.out.println("Content：" + Content);
		//
		for (int i = 0; i < nodelist.getLength(); i++)
			map.put(nodelist.item(i).getNodeName(), nodelist.item(i).getTextContent());

		return map;

	}
	// OLD
	 public static Map<String, String> parseXML(HttpServletRequest request,
	 HttpServletResponse response)
	 throws Exception {
	 System.out.println("parseXML");
	 Map<String, String> map = new HashMap<String, String>();
	 InputStream inputStream = request.getInputStream();
	 System.out.println(inputStream.toString());
	 // 读取输入流
	 SAXReader reader = new SAXReader();
	 Document document = reader.read(inputStream);
	 // 得到xml根元素
	 Element root = document.getRootElement();
	 // 得到根元素的所有子节点
	 List<Element> elementList = root.elements();
	
	 // 遍历所有子节点
	 for (Element e : elementList)
	 map.put(e.getName(), e.getText());
	
	 // 释放资源
	 inputStream.close();
	 inputStream = null;
	 return map;
	
	 }

	public static String TextToXml(TextRespMsg trm) {
		xstream.alias("xml", trm.getClass());
		String xml = xstream.toXML(trm);
		System.out.println(xml);
		return xml;
		// return null;
	}

	/**
	 * 扩展xstream，使其支持CDATA块
	 * 
	 * @date 2013-05-19
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

}

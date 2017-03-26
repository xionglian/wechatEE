package com.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
/**
 * json解析工具
 * @author gzw
 *
 */
public class JsonUtil
{
	public static List<Map<String, Object>> parseJSON2List(String jsonStr) throws Exception{
        JSONArray jsonArr = new JSONArray(jsonStr);
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        for(int i=0;i<jsonArr.length();i++){
            JSONObject json2 = jsonArr.getJSONObject(i);
            list.add(parseJSON2Map(json2.toString()));
        }
        return list;
    }
    
   
    public static Map<String, Object> parseJSON2Map(String jsonStr) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json =new JSONObject(jsonStr);
        Iterator<?> itr = json.keys();
        while(itr.hasNext()){
            String key = (String) itr.next(); 
            String value=json.get(key).toString();
            if(value==null){
            	map.put(key, value);
            }else if(value.startsWith("[")&&value.endsWith("]")){  
                map.put(key, parseJSON2List(value));  
            }else if(value.startsWith("{")&&value.endsWith("}")){  
                map.put(key, parseJSON2Map(value));  
            }else{  
                map.put(key, value);  
            } 
        }
        return map;
    }
    //test
//    public static void main(String[] args) throws Exception {
//        String url = "{project:\"12\",members:[{opanId:\"\",userName:\"\",},{opanId:\"\",userName:\"\",}]}";
//        JSONObject u=new JSONObject(url);
//        Map<String, Object> list = parseJSON2Map(url);
//        System.out.println(u.getInt("project"));
//        System.out.println(list.get("project"));
//        System.out.println(((ArrayList<Object>)list.get("members")).get(0));
//    }
}

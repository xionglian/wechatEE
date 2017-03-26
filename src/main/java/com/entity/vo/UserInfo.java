package com.entity.vo;

import java.util.ArrayList;
import java.util.HashMap;

public class UserInfo
{
	private String openId;
	private String userName;
	private String phoneNum;
	private HashMap<Character, ArrayList<ScheduleInfo>> scheduleMap;
	public String getOpenId()
	{
		return openId;
	}
	public void setOpenId(String openId)
	{
		this.openId = openId;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPhoneNum()
	{
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}
	public HashMap<Character, ArrayList<ScheduleInfo>> getScheduleMap()
	{
		return scheduleMap;
	}
	public void setScheduleMap(
			HashMap<Character, ArrayList<ScheduleInfo>> scheduleMap)
	{
		this.scheduleMap = scheduleMap;
	}
	
}

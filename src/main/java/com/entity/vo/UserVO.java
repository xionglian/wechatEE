package com.entity.vo;

/**
 * 用户基本信息视图
 * @author gzw
 *
 */
public class UserVO
{
	private String openId;
	private String userName;
	private String phoneNum;
	private String email;
	private String sex;
	private String telephone;
	private String position;
	private String qqNum;
	private String wechatNum;
	private String address;
	private Boolean isfocus;
	private String password;
	
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
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public String getTelephone()
	{
		return telephone;
	}
	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}
	public String getPosition()
	{
		return position;
	}
	public void setPosition(String position)
	{
		this.position = position;
	}
	public String getQqNum()
	{
		return qqNum;
	}
	public void setQqNum(String qqNum)
	{
		this.qqNum = qqNum;
	}
	public String getWechatNum()
	{
		return wechatNum;
	}
	public void setWechatNum(String wechatNum)
	{
		this.wechatNum = wechatNum;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public Boolean getIsfocus()
	{
		return isfocus;
	}
	public void setIsfocus(Boolean isfocus)
	{
		this.isfocus = isfocus;
	}
	public String getPassword(){return password;}
	public void setPassword(String p){this.password=p;}
}

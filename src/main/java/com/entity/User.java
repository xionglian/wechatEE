package com.entity;

// Generated 2016-4-16 15:43:05 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "projectdatabase")
public class User implements java.io.Serializable
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
	private String password;
	private Boolean isfocus;
	private Set<Schedule> schedules = new HashSet<Schedule>(0);
	private Set<ProjectMember> projectMembers = new HashSet<ProjectMember>(0);
	private Set<ScheduleMember> scheduleMembers = new HashSet<ScheduleMember>(0);
	private Set<TeamUser> teamUsers = new HashSet<TeamUser>(0);
	public User()
	{
	}

	public User(String openId)
	{
		this.openId = openId;
	}

	public User(String openId, String userName, String phoneNum, String email,
			String sex, String telephone, String position, String qqNum,
			String wechatNum, String address, Boolean isfocus,
			Set<Schedule> schedules, Set<ProjectMember> projectMembers,
			Set<ScheduleMember> scheduleMembers)
	{
		this.openId = openId;
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.email = email;
		this.sex = sex;
		this.telephone = telephone;
		this.position = position;
		this.qqNum = qqNum;
		this.wechatNum = wechatNum;
		this.address = address;
		this.isfocus = isfocus;
		this.schedules = schedules;
		this.projectMembers = projectMembers;
		this.scheduleMembers = scheduleMembers;
	}

	@Id
	@Column(name = "openId", unique = true, nullable = false, length = 50)
	public String getOpenId()
	{
		return this.openId;
	}

	public void setOpenId(String openId)
	{
		this.openId = openId;
	}

	@Column(name = "userName", length = 20)
	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	@Column(name = "phoneNum", length = 11)
	public String getPhoneNum()
	{
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}

	@Column(name = "email", length = 50)
	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Column(name = "sex", length = 2)
	public String getSex()
	{
		return this.sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	@Column(name = "telephone", length = 20)
	public String getTelephone()
	{
		return this.telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	@Column(name = "position", length = 100)
	public String getPosition()
	{
		return this.position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}

	@Column(name = "qqNum", length = 20)
	public String getQqNum()
	{
		return this.qqNum;
	}

	public void setQqNum(String qqNum)
	{
		this.qqNum = qqNum;
	}

	@Column(name = "wechatNum", length = 20)
	public String getWechatNum()
	{
		return this.wechatNum;
	}

	public void setWechatNum(String wechatNum)
	{
		this.wechatNum = wechatNum;
	}

	@Column(name = "address", length = 100)
	public String getAddress()
	{
		return this.address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@Column(name = "password", length = 45)
	public String getPassword()
	{
		if(this.password==null){
			this.password = "888888";
		}
		return this.password;
	}
	public void setPassword(String password){
		this.password = password;
	}

	@Column(name = "isfocus")
	public Boolean getIsfocus()
	{
		return this.isfocus;
	}

	public void setIsfocus(Boolean isfocus)
	{
		this.isfocus = isfocus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Schedule> getSchedules()
	{
		return this.schedules;
	}

	public void setSchedules(Set<Schedule> schedules)
	{
		this.schedules = schedules;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<ProjectMember> getProjectMembers()
	{
		return this.projectMembers;
	}

	public void setProjectMembers(Set<ProjectMember> projectMembers)
	{
		this.projectMembers = projectMembers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<ScheduleMember> getScheduleMembers()
	{
		return this.scheduleMembers;
	}

	public void setScheduleMembers(Set<ScheduleMember> scheduleMembers)
	{
		this.scheduleMembers = scheduleMembers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<TeamUser> getTeamUsers(){
		return this.teamUsers;
	}
	public void setTeamUsers(Set<TeamUser> teamUsers){this.teamUsers = teamUsers;}


}
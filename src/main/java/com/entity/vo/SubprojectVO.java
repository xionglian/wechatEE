package com.entity.vo;

import java.util.ArrayList;
import java.util.Map;

/**
 * 子项目信息视图
 * @author gzw
 *
 */
public class SubprojectVO
{
	private String projectId;
	private String projectName;
	private String subProjectName;
	private String subProjectId;
	private String teamStatus;
	private String contractStatus;
	private String paymentStatus;
	private String allocationStatus;


	private boolean canModify;


	private Integer teamId;
	private ArrayList<UserVO> leaders;
	private ArrayList<UserVO> secretarys;
	private ArrayList<UserVO> members;
	private ArrayList<ScheduleVO> schedules;
	private ArrayList<Map<String,Object>> membersList;

	public ArrayList<Map<String,Object>> getMembersList() {
		return membersList;
	}

	public void setMembersList(ArrayList<Map<String,Object>> membersList) {
		this.membersList = membersList;
	}


	public ProjectVO getProject() {
		return project;
	}

	public void setProject(ProjectVO project) {
		this.project = project;
	}

	private ProjectVO project;
	public String getProjectName()
	{
		return projectName;
	}
	public void setProjectName(String projectName)
	{
		this.projectName = projectName;
	}
	public String getSubProjectName()
	{
		return subProjectName;
	}
	public void setSubProjectName(String subProjectName)
	{
		this.subProjectName = subProjectName;
	}
	public String getProjectId()
	{
		return projectId;
	}
	public void setProjectId(String projectId)
	{
		this.projectId = projectId;
	}
	public String getTeamStatus()
	{
		return teamStatus;
	}
	public void setTeamStatus(String teamStatus)
	{
		this.teamStatus = teamStatus;
	}
	public String getContractStatus()
	{
		return contractStatus;
	}
	public void setContractStatus(String contractStatus)
	{
		this.contractStatus = contractStatus;
	}
	public String getPaymentStatus()
	{
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus)
	{
		this.paymentStatus = paymentStatus;
	}
	public String getAllocationStatus()
	{
		return allocationStatus;
	}
	public void setAllocationStatus(String allocationStatus)
	{
		this.allocationStatus = allocationStatus;
	}
	public ArrayList<UserVO> getLeaders()
	{
		return leaders;
	}
	public void setLeaders(ArrayList<UserVO> leaders)
	{
		this.leaders = leaders;
	}
	public ArrayList<UserVO> getSecretarys()
	{
		return secretarys;
	}
	public void setSecretarys(ArrayList<UserVO> secretarys)
	{
		this.secretarys = secretarys;
	}
	public ArrayList<UserVO> getMembers()
	{
		return members;
	}
	public void setMembers(ArrayList<UserVO> members)
	{
		this.members = members;
	}
	public ArrayList<ScheduleVO> getSchedules()
	{
		return schedules;
	}
	public void setSchedules(ArrayList<ScheduleVO> schedules)
	{
		this.schedules = schedules;
	}
	public String getSubProjectId()
	{
		return subProjectId;
	}
	public void setSubProjectId(String subProjectId)
	{
		this.subProjectId = subProjectId;
	}
	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public boolean isCanModify() {
		return canModify;
	}

	public void setCanModify(boolean canModify) {
		this.canModify = canModify;
	}

}

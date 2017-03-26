package com.entity.vo;

import java.util.ArrayList;
import java.util.Date;
/**
 * 进度信息
 * @author gzw
 *
 */
public class ScheduleVO
{
	private String scheduleId;
	private String taskContent;
	private String taskTime;
	private String taskStartTime;
	private String taskType;
	private String taskStatus;
	private String taskReply;

	public TeamVO getTeam() {
		return team;
	}

	public void setTeam(TeamVO team) {
		this.team = team;
	}

	private TeamVO team;
	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	private Integer teamId;
	public Integer getSubprojectId() {
		return subprojectId;
	}

	public void setSubprojectId(Integer subprojectId) {
		this.subprojectId = subprojectId;
	}

	private Integer subprojectId;
	public String getSubprojectName() {
		return subprojectName;
	}

	public void setSubprojectName(String subprojectName) {
		this.subprojectName = subprojectName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	private String projectName;
	private  String subprojectName;
	private ArrayList<UserVO> scheduleMembers;

	public String getScheduleId()
	{
		return scheduleId;
	}
	public void setScheduleId(String scheduleId)
	{
		this.scheduleId = scheduleId;
	}
	public String getTaskContent()
	{
		return taskContent;
	}
	public void setTaskContent(String taskContent)
	{
		this.taskContent = taskContent;
	}
	public String getTaskTime()
	{
		return taskTime;
	}
	public void setTaskTime(String taskTime)
	{
		this.taskTime = taskTime;
	}
	public String getTaskType()
	{
		return taskType;
	}
	public void setTaskType(String taskType)
	{
		this.taskType = taskType;
	}
	public String getTaskStatus()
	{
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus)
	{
		this.taskStatus = taskStatus;
	}
	public ArrayList<UserVO> getScheduleMembers()
	{
		return scheduleMembers;
	}
	public void setScheduleMembers(ArrayList<UserVO> scheduleMembers)
	{
		this.scheduleMembers = scheduleMembers;
	}
	public String getTaskStartTime()
	{
		return taskStartTime;
	}
	public void setTaskStartTime(String taskStartTime)
	{
		this.taskStartTime = taskStartTime;
	}
	public String getTaskReply(){return taskReply;}
	public void setTaskReply(String taskReply){this.taskReply = taskReply;};
	
}

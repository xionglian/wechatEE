package com.entity.vo;

/**
 * 
 * @author gzw
 *用于存储微信返回任务所需的信息
 */
public class ScheduleInfo
{
	private Object scheduleId;
	private String taskContent;
	private String taskTime;
	private String taskStartTime;
	private String taskType;
	private String taskStatus;
	private String subproject;
	private String project;
	public Object getScheduleId()
	{
		return scheduleId;
	}
	public void setScheduleId(Object scheduleId)
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
	public String getSubproject()
	{
		return subproject;
	}
	public void setSubproject(String subproject)
	{
		this.subproject = subproject;
	}
	public String getProject()
	{
		return project;
	}
	public void setProject(String project)
	{
		this.project = project;
	}
	public String getTaskStartTime()
	{
		return taskStartTime;
	}
	public void setTaskStartTime(String taskStartTime)
	{
		this.taskStartTime = taskStartTime;
	}
	
}

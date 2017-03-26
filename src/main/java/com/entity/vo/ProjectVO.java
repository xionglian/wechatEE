package com.entity.vo;

import java.util.Date;

/**
 * 用户基本信息视图
 * @author mzhejiayu
 *
 */
public class ProjectVO
{
	private Integer projectId;
	private String project;
	private Character type;
	private Character area;
	private Character contractStage;
	private Character projectStage;
	private Double contractAmount;
	private Double completedAmount;
	private Double receivedAmount;
	private Double contractBalance;
	private Date projectTime;
	private Integer team;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Character getType() {
		return type;
	}

	public void setType(Character type) {
		this.type = type;
	}

	public Character getArea() {
		return area;
	}

	public void setArea(Character area) {
		this.area = area;
	}

	public Character getContractStage() {
		return contractStage;
	}

	public void setContractStage(Character contractStage) {
		this.contractStage = contractStage;
	}

	public Character getProjectStage() {
		return projectStage;
	}

	public void setProjectStage(Character projectStage) {
		this.projectStage = projectStage;
	}

	public Double getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(Double contractAmount) {
		this.contractAmount = contractAmount;
	}

	public Double getCompletedAmount() {
		return completedAmount;
	}

	public void setCompletedAmount(Double completedAmount) {
		this.completedAmount = completedAmount;
	}

	public Double getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(Double receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public Double getContractBalance() {
		return contractBalance;
	}

	public void setContractBalance(Double contractBalance) {
		this.contractBalance = contractBalance;
	}

	public Date getProjectTime() {
		return projectTime;
	}

	public void setProjectTime(Date projectTime) {
		this.projectTime = projectTime;
	}

	public Integer getTeam() {
		return team;
	}

	public void setTeam(Integer team) {
		this.team = team;
	}


	

}

package com.entity;

/**
 * Created by zengqin on 2017/3/23.
 */
public class ProjectT{
    private Integer projectId;
    private  Integer  subprjectId;
    private String subproject;
    private String project;
    private char projectStage;
    private String openId;
    private String userName;
    //Integer projectId, Integer subprjectId,
    public ProjectT(Integer projectId,Integer  subprjectId, String subproject, String project, char projectStage, String openId, String userName) {
        this.projectId = projectId;
        if(subproject==null){
            this.subproject =null;
        }
        this.subprjectId = subprjectId;
        this.subproject = subproject;
        if(project==null){
            this.project =null;
        }
        this.project = project;
        if((Character)projectStage==null){
            this.projectStage ='a';
        }
        this.projectStage = projectStage;
        this.openId = openId;
        this.userName = userName;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getSubprjectId() {
        return subprjectId;
    }

    public void setSubprjectId(Integer subprjectId) {
        this.subprjectId = subprjectId;
    }

    public String getSubproject() {
        return subproject;
    }

    public void setSubproject(String subproject) {
        this.subproject = subproject;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public char getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(char projectStage) {
        this.projectStage = projectStage;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
package com.entity.vo;

/**
 * Created by @author mazhejiayu on 7/20/16.
 */

/**
 * 用户的团队和相应的权限
 *
 */
public class TeamUserVO
{
    private String openId;
    private String userName;
    private Integer teamId;
    private Integer power;
    private String teamName;


    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOpenId(){
        return this.openId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getRole() {
        return power;
    }

    public void setRole(Integer power) {
        this.power = power;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }



}

package com.entity.vo;

/**
 * Created by @author mazhejiayu on 7/20/16.
 */

import java.util.ArrayList;

/**
 * 用户的团队和相应的权限
 *
 */
public class TeamVO
{
    private Integer teamId;
    private String teamName;
    private String relatedTitle;
    private ArrayList<UserVO> leaders;
    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getRelatedTitle() {
        return relatedTitle;
    }

    public void setRelatedTitle(String relatedTitle) {
        this.relatedTitle = relatedTitle;
    }

    public ArrayList<UserVO> getLeaders(){return leaders;}
    public void setLeaders(ArrayList<UserVO> set){this.leaders = set;}
    public void show(){
        System.out.println(teamId+":"+teamName+":"+relatedTitle);
    }
}

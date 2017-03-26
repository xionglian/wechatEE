package com.entity.newT;

/**
 * Created by  xionglian on 2017/3/25.
 */
public class TeamUserT {
    private Integer TeamUserId;
    private String userName;

    public TeamUserT(Integer teamUserId, String userName) {
        TeamUserId = teamUserId;
        this.userName = userName;
    }

    public Integer getTeamUserId() {
        return TeamUserId;
    }

    public void setTeamUserId(Integer teamUserId) {
        TeamUserId = teamUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

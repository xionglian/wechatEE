package com.entity.newT;

import com.entity.Schedule;
import com.entity.User;

/**
 * Created by  xionglian on 2017/3/25.
 */
public class ScheduleMemberT {
    private Integer scheduleMemberId;
    private String userName;

    public ScheduleMemberT(Integer scheduleMemberId, String userName) {
        this.scheduleMemberId = scheduleMemberId;
        this.userName = userName;
    }

    public Integer getScheduleMemberId() {
        return scheduleMemberId;
    }

    public void setScheduleMemberId(Integer scheduleMemberId) {
        this.scheduleMemberId = scheduleMemberId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

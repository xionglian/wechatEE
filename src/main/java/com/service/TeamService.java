package com.service;

import com.dao.impl.TeamDao;
import com.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zengqin on 2017/3/26.
 */
@Service
public class TeamService {
    @Autowired
    private TeamDao teamDao;
    public Team findTeam(Integer teamId){
       return teamDao.findTeam(teamId);
    }
}

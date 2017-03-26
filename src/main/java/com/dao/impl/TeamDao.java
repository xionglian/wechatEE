package com.dao.impl;

import com.dao.BaseDao;
import com.entity.Team;
import org.springframework.stereotype.Repository;

/**
 * Created by zengqin on 2017/3/25.
 */
@Repository("TeamDao")
public class TeamDao extends BaseDao<Team> {
        public Team findTeam(Integer teamId){
            return this.get(Team.class,teamId);
        }
}

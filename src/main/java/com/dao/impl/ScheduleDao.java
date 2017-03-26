package com.dao.impl;

import com.dao.BaseDao;
import com.entity.Schedule;
import org.springframework.stereotype.Repository;

/**
 * Created by  xionglian on 2017/3/26.
 */
@Repository
public class ScheduleDao extends BaseDao<Schedule> {

    public boolean doUpdate(Schedule schedule){
        try {
            this.update(schedule);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}

package com.dao.impl;

import com.dao.BaseDao;
import com.entity.ScheduleMember;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by  xionglian on 2017/3/25.
 */
@Repository
public class ScheduleMemberDao extends BaseDao<ScheduleMember>{

    public ScheduleMember getCertain(String hql, Map<String, Object> params, Query query)
    {
        ScheduleMember scheduleMember = this.findByHql(hql, params, query).get(0);

        return scheduleMember;
    }

}

package com.dao;


import com.entity.Pager;
import com.entity.Project;
import org.hibernate.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by zengqin on 2017/3/21.
 */
public interface ProjectDaoI {
    /**
     * 分页查询
     */
    public Pager findByPage(String hql,Pager pagerModel,Map<String, Object> params);

    /**
     * 查询所有团队
     */
    public List findAllTeam(String hql);

    /**
     * 获取团队所有人员
     */
    public List  findAllMember(Map<String, Object> params,String hql);
    /**
     *根据项目Id查询项目
     * */
    public List findById(Map<String,Object> params,String hql);
    /**
     * 创建新项目
     */
    public boolean create_project(Project project);
    /**
     * 根据人员职位查询人员
     */
    public List findByPosition(String hql, Map<String, Object> params, Query query);

/**
 *根据任务类型查询任务列表
 */
//  public Pager findByTask_type(String projectId,String subprojectId,String task_Type);
/**
 * 修改任务完成的最后时间
 */
//  public boolean doExtension(String scheduleId,String date);
/**
 * 标记已完成
 */
//  public boolean markAsDone(String scheduleId);
/**
 * 标记未完成
 */
//  public boolean markAsUndone(String scheduleId);
/**
 * 获取任务现有人员并可根据人员姓名搜索
 */
// public List findScheduleMember(String scheduleId);
/**
 * 查询团队中不在项目内的成员并可根据人员姓名搜索
 */
// public  List findOtherMember(String teamId,String projectId);
/**
 * 添加任务成员
 */
//  public boolean addScheduleMember(List openIds);
/**
 * 删除任务成员
 */
//  public  boolean deleteScheduleMember(List openids);
/**
 * 创建新的任务
 */
//  public boolean addNewSchedule(String projectName,String task_type,List openIds,String task_time,String task_reply);
/**
 * 更新某人对某一任务的最新回复
 */
// public boolean updateReply(String task_reply,String scheduleId);
}

package com.service;

import com.dao.ProjectDaoI;
import com.dao.impl.*;
import com.entity.*;
import com.entity.newT.ScheduleMemberT;
import com.entity.vo.ScheduleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zengqin on 2017/3/24.
 */
@Service
public class ProjectService {
    @Autowired
    private ProjectDaoI projectDao;
    private TeamDao teamDao;
    @Autowired
    private ScheduleDao scheduleDao;
    @Autowired
    private SubprojectDao subprojectDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ScheduleMemberDao scheduleMemberDao;
    @Autowired
    private TeamUserDao teamUserDao;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * 分页显示项目列表 按子项目状态查询 已完成/未完成
     *
     * @param scope
     * @param pagerModel
     * @return
     */
    public Pager findByStatus(String scope, Pager pagerModel) {
        String hql = "select new com.entity.newT.ProjectT( p.projectId,sub.subprojectId,sub.subproject, p.project, p.projectStage, m.user.openId, m.user.userName)"
                + " from Subproject sub left join sub.project p left join sub.projectMembers m "
                + "where m.roleType=:roleType ";
        Map<String, Object> params = new HashMap<String, Object>();
        if (scope != null) {
            char projectStatus;
            if (scope.equals("active")) {
                projectStatus = 'b';
                params.put("projectStatus", projectStatus);
            } else if (scope.equals("done")) {
                projectStatus = 'a';
                params.put("projectStatus", projectStatus);
            }
            hql += "and sub.projectStatus=:projectStatus";
        }
        params.put("roleType", 'a');
        return projectDao.findByPage(hql, pagerModel, params);
    }

    /**
     * 根据项目所处阶段查询项目
     */
    public Pager findByStage(Pager pagerModel, char projectStage) {
        String hql = "select new com.entity.newT.ProjectT( p.projectId,sub.subprojectId,sub.subproject, p.project, p.projectStage, m.user.openId, m.user.userName)"
                + " from Subproject sub left join sub.project p left join sub.projectMembers m "
                + "where m.roleType=:roleType and p.projectStage=:projectStage ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleType", 'a');
        params.put("projectStage", projectStage);
        return projectDao.findByPage(hql, pagerModel, params);
    }

    /**
     * 根据项目名字查询
     */
    public Pager findByProjectName(Pager pagerModel, String project) {
        String hql = "select new com.entity.newT.ProjectT( p.projectId,sub.subprojectId,sub.subproject, p.project, p.projectStage, m.user.openId, m.user.userName)"
                + " from Subproject sub left join sub.project p left join sub.projectMembers m "
                + "where m.roleType=:roleType and p.project like :project";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleType", 'a');
        params.put("project", "%" + project + "%");
        return projectDao.findByPage(hql, pagerModel, params);
    }

    /**
     * 查询所有团队
     */
    public List findAllTeam() {
        String hql = "select t.teamId,t.teamName from Team t";
        return projectDao.findAllTeam(hql);

    }
    /**
     * 按teamId查询团队
     */
    public List findByTeamId(int teamId){
        String hql="select t.teamId,t.teamName from Team t "
                +" where t.teamId=:teamId";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("teamId",teamId);
        return projectDao.findById(params,hql);
    }
    /**
     * 获取团队所有人员
     */
    public List  findAllMember(int teamId){
        String hql="select new com.entity.newT.UserT(u.openId,u.userName) " +
                "from TeamUser tu left join tu.user u left join tu.team t" +
                " where t.teamId=:teamId";
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("teamId",teamId);
        return projectDao.findAllMember(params,hql);
    }
    /**
     * 创建新项目
     */
    public boolean createNewProject(Project project){
        return projectDao.create_project(project);
    }
    /**
     * 根据人员职位查询人员
     */
    public List findByPosition(int teamId,String position){
        String hql="select new com.entity.newT.UserT(u.openId,u.userName) " +
                "from TeamUser tu left join tu.user u left join tu.team t" +
                " where t.teamId=:teamId and u.position like :position";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("teamId", teamId);
        params.put("position", "%" + position + "%");
        return projectDao.findByPosition(hql,params,null);
    }
    /**
     * 显示项目下的任务列表
     */
    public Pager findSchedules(int projectId,int subprojectId,Pager pagerModel){
        String hql="select new com.entity.newT.ScheduleT(task.taskContent, task.taskReply, task.taskType, task.scheduleId,p.projectId, p.project, sub.subprojectId, sub.subproject,task.taskTime)"
                +"from Subproject sub left join sub.project p left join sub.schedules task "
                +"where sub.subprojectId=:subprojectId and p.projectId=:projectId";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("projectId", projectId);
        params.put("subprojectId",subprojectId);
        return projectDao.findByPage(hql, pagerModel, params);

    }

    public Pager findByTaskContent(int projectId,int subprojectId,String taskContent,Pager pagerModel){
        String hql="select new com.entity.newT.ScheduleT(task.taskContent, task.taskReply, task.taskType, task.scheduleId,p.projectId, p.project, sub.subprojectId, sub.subproject,task.taskTime) "
                +"from Subproject sub left join sub.project p left join sub.schedules task "
                +"where sub.subprojectId=:subprojectId and p.projectId=:projectId and task.taskContent like :taskContent";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("projectId", projectId);
        params.put("subprojectId",subprojectId);
        params.put("taskContent", "%" + taskContent + "%");
        return projectDao.findByPage(hql, pagerModel, params);
    }

    /**
     *根据任务类型查询任务列表
     */
      public Pager findByTask_type(String projectId,String subprojectId,Pager pager,String task_Type){


          try {
              String hql = "select new com.entity.newT.ScheduleT(s.taskContent,s.scheduleId,s.subproject.project.project,s.subproject.subproject,s.taskType,s.taskReply,s.taskTime) from Schedule s"
                      +" where s.taskType="+" '"+task_Type+"' ";
              if(projectId!=null&&!projectId.isEmpty())
              {
                  hql+= " and s.subproject.subjectId="+"projectId";
              }
              if(projectId!=null&&!subprojectId.isEmpty())
              {
                  hql+=" and s.subproject.project.projectId="+subprojectId;
              }
              pager = projectDao.findByPage(hql,pager,null);
          } catch (Exception e) {
              e.printStackTrace();
          }
          return  pager;

      }
    /**
     * 修改任务完成的最后时间
     */
     @Transactional //解决 No Session found for current thread
      public boolean doExtension(String scheduleId,String date){

          try {
              Schedule s = scheduleDao.load(Schedule.class,Integer.parseInt(scheduleId));
              Date date1 = simpleDateFormat.parse(date);
              s.setTaskTime(date1);
              scheduleDao.save(s);
          } catch (ParseException e) {
              e.printStackTrace();
          }
          return true;
      };
    /**
     * 标记已完成
     */
    @Transactional
      public boolean markAsDone(String scheduleId){
          try {
              Schedule s = scheduleDao.load(Schedule.class,Integer.parseInt(scheduleId));
              s.setTaskStatus('a');
              scheduleDao.save(s);
          } catch (NumberFormatException e) {
              e.printStackTrace();
          }
          return true;
      }
    /**
     * 标记未完成
     */
    @Transactional
    public boolean markAsUndone(String scheduleId){

          try {
              Schedule s = scheduleDao.load(Schedule.class,Integer.parseInt(scheduleId));
              s.setTaskStatus('b');
              scheduleDao.save(s);
          } catch (NumberFormatException e) {
              e.printStackTrace();
          }
          return true;
      }
    /**
     * 标记超期
     */
    @Transactional
    public boolean markAsOverdue(String scheduleId){

        try {
            Schedule s = scheduleDao.load(Schedule.class,Integer.parseInt(scheduleId));
            s.setTaskStatus('c');
            scheduleDao.save(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return true;
    }
    /**
     * 获取任务现有人员并可根据人员姓名搜索
     */
    @Transactional
    public Pager findScheduleMember(String scheduleId,Pager pager){

         try {
                String hql = "select new com.entity.newT.ScheduleMemberT(sm.scheduleMemberId,sm.user.userName) from ScheduleMember sm where sm.schedule.scheduleId="+scheduleId;
                pager =   projectDao.findByPage(hql,pager,null);

         } catch (NumberFormatException e) {
             e.printStackTrace();
         }
         return   pager;
     }
    /**
     * 查询团队中不在项目内的成员并可根据人员姓名搜索
     */
    @Transactional
    public  Pager findOtherMember(String teamId,String projectId,Pager pager){
         List list = null;
         try {

             String hql ="select new com.entity.newT.TeamUserT(tu.teamUserId,tu.user.userName) from TeamUser tu where tu.team.teamId="+teamId+" and tu.user.openId not in(select sm.user.openId from ScheduleMember sm where sm.schedule.subproject.project.projectId="+projectId+")";
             pager =   projectDao.findByPage(hql,pager,null);
         } catch (NumberFormatException e) {
             e.printStackTrace();
         }
         return pager;
     }
    /**
     * 添加任务成员
     */
    @Transactional
    public boolean addScheduleMember(String scheduleId,Set<Integer> TeamUserIds){
          try {
              Set<ScheduleMember>  setScheduleMenmber= new HashSet<ScheduleMember>();
              Schedule schedule = scheduleDao.load(Schedule.class,Integer.parseInt(scheduleId));
              //查找原来的
//              String hql ="from ScheduleMember sm where sm.schedule.scheduleId="+scheduleId;
//              setScheduleMenmber.addAll(projectDao.findAllTeam(hql));
              //增加新的
              for(Integer id :TeamUserIds) {
                  TeamUser tu = teamUserDao.load(TeamUser.class,id);
                  User user = tu.getUser();
                  scheduleMemberDao.save(new ScheduleMember(schedule,user));
              }


          } catch (NumberFormatException e) {
              e.printStackTrace();
          }

          return true;
      }
    /**
     * 删除任务成员
     */
    @Transactional
    public  boolean deleteScheduleMember(String scheduleId,Set<Integer> teamUserIds){

        for(Integer id:teamUserIds) {
            String deleteScheduleMemerr ="select sm from ScheduleMember sm,TeamUser tu where sm.schedule.scheduleId="+scheduleId+" and sm.user = tu.user and tu.teamUserId="+id;
            ScheduleMember scheduleMember = scheduleMemberDao.getCertain(deleteScheduleMemerr,null,null);
            scheduleMemberDao.delete(scheduleMember);
        }
        return true;
 }
    /**
     * 创建新的任务
     */
    @Transactional
    public boolean addNewSchedulepublic (String subprojectId, String userid, String taskContent,
                                           Character taskType, Character taskStatus, String taskTime,
                                           String taskStartTime, Set<String> scheduleMemberIds, String taskReply) {

        try {
            Subproject subproject = subprojectDao.load(Subproject.class,Integer.parseInt(subprojectId));
            User user = userDao.load(User.class,userid);
            Date taskTime1 = simpleDateFormat.parse(taskTime);
            Date taskStartTime1 = simpleDateFormat.parse(taskStartTime);
            Set<ScheduleMember> scheduleMembers = new HashSet<ScheduleMember>();
            for(String id:scheduleMemberIds)
            {
                scheduleMembers.add(scheduleMemberDao.load(ScheduleMember.class, Integer.parseInt(id)));
            }
            scheduleDao.save(new Schedule(subproject,user,taskContent,taskType,taskStatus,taskTime1,taskStartTime1,scheduleMembers,taskReply));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }
    /**
     * 更新某人对某一任务的最新回复
     */
    @Transactional
    public boolean updateReply(String task_reply,String scheduleId){
         Schedule s = null;
         try {
             s = scheduleDao.load(Schedule.class,Integer.parseInt(scheduleId));
             String oldReply = s.getTaskReply();
             s.setTaskReply(oldReply+"</br>"+task_reply);
             scheduleDao.save(s);
         } catch (NumberFormatException e) {
             e.printStackTrace();
         }

         return true;
     }

}
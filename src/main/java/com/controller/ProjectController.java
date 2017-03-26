package com.controller;

import com.entity.*;
import com.entity.newT.ProjectT;
import com.entity.newT.ScheduleT;
import com.entity.newT.TeamT;
import com.entity.newT.UserT;
import com.service.ProjectService;
import com.service.TeamService;
import com.util.JsonUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by xionglian on 2017/3/25.
 */
@Controller
@RequestMapping(value="project")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @Autowired
    TeamService T;
    @Autowired
    private Map<String, Object> dataMap = new HashMap<String, Object>();
    private Pager pagerModel = new Pager(1, 5);

    /**
     * 分页显示项目列表 按子项目状态查询 已完成/未完成
     */
    @RequestMapping(value = "projectList")
    public Map<String, Object> findByStatus(String scope, int currentPageNumber, int pageSize) {
        dataMap.clear();
        try {
            Pager pagerModel = new Pager(currentPageNumber, pageSize);
            pagerModel = projectService.findByStatus(scope, pagerModel);
            List<ProjectT> projects = pagerModel.getDataList();
            int totalSize = pagerModel.getTotalSize();
            dataMap.put("result", "success");
            dataMap.put("resultTip", "");
            dataMap.put("projects", projects);
            dataMap.put("totalSize", totalSize);
            System.out.println(dataMap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            dataMap.put("result", "fail");
            dataMap.put("resultTip", e.getMessage());
        }
        return dataMap;
    }


    /**
     * 根据项目所处阶段查询项目
     */
    @RequestMapping(value = "projectListByStage")
    public Map<String, Object> findByStage(char projectStage, int currentPageNumber, int pageSize) {
        dataMap.clear();
        try {
            Pager pagerModel = new Pager(currentPageNumber, pageSize);
            pagerModel = projectService.findByStage(pagerModel, projectStage);
            List<ProjectT> projects = pagerModel.getDataList();
            int totalSize = pagerModel.getTotalSize();
            dataMap.put("result", "success");
            dataMap.put("resultTip", "");
            dataMap.put("projects", projects);
            dataMap.put("totalSize", totalSize);
            System.out.println(dataMap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            dataMap.put("result", "fail");
            dataMap.put("resultTip", e.getMessage());
        }
        return dataMap;

    }
    /**
     * 根据项目名字查询
     */
    @RequestMapping(value="projectSearch")
    public Map<String, Object> findByProjectName(String project, int currentPageNumber, int pageSize){
        dataMap.clear();
        try {
            Pager pagerModel = new Pager(currentPageNumber, pageSize);
            pagerModel = projectService.findByProjectName(pagerModel, project);
            List<ProjectT> projects = pagerModel.getDataList();
            int totalSize = pagerModel.getTotalSize();
            dataMap.put("result", "success");
            dataMap.put("resultTip", "");
            dataMap.put("projects", projects);
            dataMap.put("totalSize", totalSize);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            dataMap.put("result", "fail");
            dataMap.put("resultTip", e.getMessage());
        }
        return dataMap;
    }
    /**
     * 查询所有团队
     */
    @RequestMapping(value="findAllTeam")
    public Map<String, Object> findAllTeam(){
        dataMap.clear();
        try {
            List<TeamT> teams = projectService.findAllTeam();
            dataMap.put("result", "success");
            dataMap.put("resultTip", "");
            dataMap.put("teams", teams);
            System.out.println(dataMap);
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            dataMap.put("result", "fail");
            dataMap.put("resultTip", e.getMessage());
        }
        return dataMap;

    }
    /**
     * 获取团队所有人员
     */
    @RequestMapping(value="findAllMember")
    public Map<String, Object>  findAllMember(Integer teamId){
        dataMap.clear();
        try {
            List<UserT> userT=projectService.findAllMember(teamId);
            dataMap.put("result", "success");
            dataMap.put("resultTip", "");
            dataMap.put("users", userT);

        } catch (Exception e) {
            e.printStackTrace();
            dataMap.put("result", "fail");
            dataMap.put("resultTip", e.getMessage());
        }
        return dataMap;

    }
    /**
     * 创建新项目
     */
    @RequestMapping(value = "createNewProject")
    @ResponseBody
    public Map<String, Object> createNewProject(@RequestBody String request) {
        dataMap.clear();
        try {
            Map<String, Object> json = JsonUtil.parseJSON2Map(request);
            String project = (String) json.get("project");
            String projectStageS = (String) json.get("projectStage");
            char projectStage=projectStageS.charAt(0);
            String teamIdS = (String) json.get("teamId");
            int teamId=Integer.parseInt(teamIdS);
            Team team= T.findTeam(teamId);
            ArrayList<Map<String, Object>> subprojectList = (ArrayList<Map<String, Object>>) json.get("subprojects");
            Set<ProjectMember> projectMembers = new HashSet<ProjectMember>();
            Set<Subproject> subprojects = new HashSet<Subproject>();
            for (Map<String, Object> map : subprojectList) {
                String subproject = (String) map.get("subproject");
                ArrayList<Map<String, Object>> projectMemberList = (ArrayList<Map<String, Object>>) map.get("projectMembers");
                for (Map<String, Object> m : projectMemberList) {
                    String openId = (String) m.get("openId");
                    String roleTypeS = (String) m.get("roleType");
                    char roleType=roleTypeS.charAt(0);
                    User user = new User();
                    user.setOpenId(openId);
                    ProjectMember projectMember = new ProjectMember();
                    projectMember.setRoleType(roleType);
                    projectMember.setUser(user);
                    projectMembers.add(projectMember);
                }
                Subproject subprojectT = new Subproject();
                subprojectT.setSubproject(subproject);
                subprojectT.setProjectMembers(projectMembers);
                subprojects.add(subprojectT);
            }
            Project projectT = new Project();
            projectT.setTeam(team);
            projectT.setProject(project);
            projectT.setProjectStage(projectStage);
            projectT.setSubprojects(subprojects);
            projectService.createNewProject(projectT);
            dataMap.put("result", "success");
            dataMap.put("resultTip", "");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            dataMap.put("result", "fail");
            dataMap.put("resultTip", e.getMessage());
        }
        return dataMap;
    }
    /**
     * 显示项目下的任务列表
     */
    @RequestMapping(value="findSchedules")
    public Map<String,Object> findSchedules(int projectId,int subprojectId,int currentPageNumber, int pageSize){
        dataMap.clear();
        try{
            Pager pagerModel = new Pager(currentPageNumber, pageSize);
            pagerModel=projectService.findSchedules(projectId,subprojectId,pagerModel);
            List<ScheduleT> schedules = pagerModel.getDataList();
            for(ScheduleT T:schedules){
                System.out.println(T.getTaskContent());
            }
            int totalSize = pagerModel.getTotalSize();
            dataMap.put("result", "success");
            dataMap.put("resultTip", "");
            dataMap.put("schedules", schedules);
            dataMap.put("totalSize", totalSize);
            System.out.println(dataMap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            dataMap.put("result", "fail");
            dataMap.put("resultTip", e.getMessage());
        }
        return dataMap;
    }
    @RequestMapping(value="findByTaskContent")
    public  Map<String, Object> findByTaskContent(int projectId,int subprojectId,String taskContent,int currentPageNumber,int pageSize){
        dataMap.clear();
        try{
            Pager pagerModel = new Pager(currentPageNumber, pageSize);
            pagerModel=projectService.findByTaskContent(projectId,subprojectId,taskContent,pagerModel);
            List<ScheduleT> schedules = pagerModel.getDataList();
            int totalSize = pagerModel.getTotalSize();
            dataMap.put("result", "success");
            dataMap.put("resultTip", "");
            dataMap.put("schedules", schedules);
            dataMap.put("totalSize", totalSize);
            System.out.println(dataMap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            dataMap.put("result", "fail");
            dataMap.put("resultTip", e.getMessage());
        }
        return dataMap;
    }

















    /**
     * 根据任务类型查找任务
     * @param request
     * @return
     */
    @RequestMapping(value="listScheduleByType",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String, Object> listScheduleByType( HttpServletRequest request) {
        dataMap.clear();
        try {

            int currentPage = request.getParameter("currentPage")!=null?Integer.parseInt(request.getParameter("currentPage")):1;
            int pageSize = request.getParameter("pageSize")!=null?Integer.parseInt(request.getParameter("pageSize")):5;
            String projectId = request.getParameter("projectId");
            String suprojectId = request.getParameter("subprojectId");
            String taskType = request.getParameter("taskType");
            pagerModel.setCurrentPageNumber(currentPage);
            pagerModel.setPageSize(pageSize);
            if(taskType==null||taskType.isEmpty()) {
                dataMap.put("result", "fail");
                dataMap.put("resultTip", "任务类型不能为空");
                return dataMap;
            }
            Pager pager= projectService.findByTask_type(projectId,suprojectId,pagerModel,taskType);
            dataMap.put("result","success");
            dataMap.put("schedule",pager.getDataList());
            dataMap.put("totalSize",pager.getTotalSize());
        } catch (Exception e) {
            e.printStackTrace();
            dataMap.put("result", "fail");
            dataMap.put("resultTip", e.getMessage());
        }

        return  dataMap;
    }

    /**
     * 更新任务结束时间
     * @param request
     * @return
     */
    @RequestMapping(value="updateScheduleBydate",method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, Object> updateScheduleByDate(@RequestBody String request ){
        dataMap.clear();
        try {
            Map<String, Object> json = JsonUtil.parseJSON2Map(request);
            String id = (String)json.get("id");
            String date =(String) json.get("date");
            if(date==null||date.isEmpty()) {
                dataMap.put("result", "fail");
                dataMap.put("resultTip", "日期不能为空");
                return dataMap;
            }
            projectService.doExtension(id,date);
            dataMap.put("result","success");
            dataMap.put("resultTip",null);
        } catch (Exception e) {
            e.printStackTrace();
            dataMap.put("result", "fail");
            dataMap.put("resultTip", e.getMessage());
        }

        return  dataMap;
    }

    /**
     * 更新任务状态
     * @param request
     * @return
     */
    @RequestMapping(value="updateScheduleByStatus",method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, Object> updateScheduleByStatus( @RequestBody String request) {
        dataMap.clear();
        try {
            Map<String, Object> json = JsonUtil.parseJSON2Map(request);
            String id = (String)json.get("id");
            String status = (String)json.get("status");
            if(status==null||status.isEmpty()) {
                dataMap.put("result", "fail");
                dataMap.put("resultTip","");
                return dataMap;
            }
            if(status.equals("a"))
                projectService.markAsDone(id);
            else if(status.equals("c"))
                projectService.markAsOverdue(id);
            else if(status.equals("b"))
                projectService.markAsUndone(id);
            else{
                dataMap.put("result", "fail");
                dataMap.put("resultTip", "日期格式不正确");
                return dataMap;
            }

            dataMap.put("result","success");
            dataMap.put("resultTip",null);
        } catch (Exception e) {
            e.printStackTrace();
            dataMap.put("result", "fail");
            dataMap.put("resultTip", e.getMessage());
        }

        return  dataMap;
    }

    /**
     * 获取任务现有人员
     * @param request
     * @return
     */
    @RequestMapping(value="findScheduleMember",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String, Object> findScheduleMember( HttpServletRequest request) {
        dataMap.clear();
        try {

            String currentPage = request.getParameter("currentPage")!=null?request.getParameter("currentPage"):"1";
            String pageSize = request.getParameter("pageSize")!=null?request.getParameter("pageSize"):"5";
            String scheduleId = request.getParameter("scheduleId");
            pagerModel.setCurrentPageNumber(Integer.parseInt(currentPage));
            pagerModel.setPageSize(Integer.parseInt(pageSize));
            if(scheduleId==null||scheduleId.isEmpty()) {
                dataMap.put("result", "fail");
                dataMap.put("resultTip", "任务Id不能为空");
                return dataMap;
            }
            pagerModel = projectService.findScheduleMember(scheduleId,pagerModel);
            dataMap.put("result","success");
            dataMap.put("totalSize",pagerModel.getTotalSize());
            dataMap.put("scheduleMember",pagerModel.getDataList());
        } catch (Exception e) {
            e.printStackTrace();
            dataMap.put("result", "fail");
            dataMap.put("resultTip", e.getMessage());
        }

        return  dataMap;
    }

    /**
     * 查询团队中不在项目内的成员
     * @param request
     * @return
     */
    @RequestMapping(value="findOtherMember",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String, Object> findOtherMember( HttpServletRequest request) {
        dataMap.clear();
        try {

            String currentPage = request.getParameter("currentPage")!=null?request.getParameter("currentPage"):"1";
            String pageSize = request.getParameter("pageSize")!=null?request.getParameter("pageSize"):"5";
            String teamId = request.getParameter("teamId");
            String projectId = request.getParameter("projectId");
            pagerModel.setCurrentPageNumber(Integer.parseInt(currentPage));
            pagerModel.setPageSize(Integer.parseInt(pageSize));
            pagerModel = projectService.findOtherMember(teamId,projectId,pagerModel);
            dataMap.put("result","success");
            dataMap.put("totalSize",pagerModel.getTotalSize());
            dataMap.put("teamUser",pagerModel.getDataList());
        } catch (Exception e) {
            e.printStackTrace();
            dataMap.put("result", "fail");
            dataMap.put("resultTip", e.getMessage());
        }

        return  dataMap;
    }

    /**
     * 添加任务成员
     * @param request
     * @return
     */
    @RequestMapping(value="addScheduleMember",method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, Object> addScheduleMember( @RequestBody String request) {
        dataMap.clear();
        try {
            Map<String, Object> json = JsonUtil.parseJSON2Map(request);
            String scheduleId = (String)json.get("scheduleId");
            ArrayList<Map<String, String>> userTeamIds = (ArrayList<Map<String, String>>) json.get("teamUserIds");
            Set<Integer> openIds = new HashSet<Integer>();
            int i = 0;
            for(Map<String, String> id: userTeamIds)
            {
                openIds.add(Integer.parseInt(id.get("id"+i++)));
            }

            projectService.addScheduleMember(scheduleId,openIds);
            dataMap.put("result","success");
            dataMap.put("resultTip","");
        } catch (Exception e) {
            e.printStackTrace();
            dataMap.put("result", "fail");
            dataMap.put("resultTip", e.getMessage());
        }

        return  dataMap;
    }

    /**
     * 删除任务成员
     * @param request
     * @return
     */
    @RequestMapping(value="deleteScheduleMember",method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, Object> deleteScheduleMember( @RequestBody String request) {
        dataMap.clear();
        try {
            Map<String, Object> json = JsonUtil.parseJSON2Map(request);
            String scheduleId = (String) json.get("scheduleId");
            ArrayList<Map<String, String>> userTeamIds = (ArrayList<Map<String, String>>) json.get("teamUserIds");
            Set<Integer> openIds = new HashSet<Integer>();
            int i = 0;
            for(Map<String, String> id: userTeamIds)
            {
                openIds.add(Integer.parseInt(id.get("id"+i++)));
            }
            projectService.deleteScheduleMember(scheduleId,openIds);
            dataMap.put("result","success");
            dataMap.put("resultTip","");
        } catch (Exception e) {
            e.printStackTrace();
            dataMap.put("result", "fail");
            dataMap.put("resultTip", e.getMessage());
        }

        return  dataMap;
    }

    /**
     * 创建新的任务
     * @param request
     * @return
     */
    @RequestMapping(value="addNewSchedulepublic",method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, Object> addNewSchedulepublic( @RequestBody String request) {
        dataMap.clear();
        try {
                Map<String, Object> json = JsonUtil.parseJSON2Map(request);
                String subprojectId = (String) json.get("subprojectId");
                String userid = (String)json.get("openid");
                String taskContent =(String) json.get("taskContent");
                String taskType = (String)json.get("taskType");
                String taskStatus =(String) json.get("taskStatus");
                String taskTime = (String)json.get("taskTime");
                String taskStartTime =(String) json.get("taskStartTime");
                String taskReply =(String) json.get("taskReply");

                ArrayList<Map<String, String>> scheduleMemberIdString1 = (ArrayList<Map<String, String>>) json.get("scheduleMemberIds");
                Set<String> openIds = new HashSet<String>();
                int i = 0;
                for(Map<String,String> id: scheduleMemberIdString1)
                {

                    openIds.add(id.get("id" + i++));
                }

                projectService.addNewSchedulepublic(subprojectId,userid,taskContent,taskType.charAt(0),taskStatus.charAt(0),taskTime,taskStartTime,openIds,taskReply);
                dataMap.put("result","success");
                dataMap.put("resultTip","");
        } catch (Exception e) {
            e.printStackTrace();
            dataMap.put("result", "fail");
            dataMap.put("resultTip", e.getMessage());
        }

        return  dataMap;
    }

    /**
     * 更新某人对某一任务的最新回复
     * @param request
     * @return
     */
    @RequestMapping(value="updateReply",method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, Object> updateReply( @RequestBody String request) {
        dataMap.clear();
        try {
            Map<String, Object> json = JsonUtil.parseJSON2Map(request);
            String task_reply = (String) json.get("task_reply");
            String scheduleId = (String)json.get("scheduleId");
            projectService.updateReply(task_reply,scheduleId);
            dataMap.put("result","success");
            dataMap.put("resultTip","");
        } catch (Exception e) {
            e.printStackTrace();
            dataMap.put("result", "fail");
            dataMap.put("resultTip", e.getMessage());
        }

        return  dataMap;
    }
}



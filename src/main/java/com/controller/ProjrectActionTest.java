package com.controller;

import com.entity.Pager;
import com.entity.newT.ProjectT;
import com.service.ProjectServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Created by zengqin on 2017/3/21.
 */
@Controller
@RequestMapping("/projectTest")
public class ProjrectActionTest {
    @Autowired
    ProjectServiceTest projectService;
    @RequestMapping(value = "/projectList")

    public Pager findAllProject(){
        int a=5;
        System.out.println(a);
        String scope="active";
        Pager pagerModel=new Pager(1,5);
        Pager pager= projectService.findAllProject(scope,pagerModel);
        List<ProjectT> list=pager.getDataList();

        for(ProjectT s:list){

            System.out.println(s.getProject()+"  "+s.getSubproject());
        }
        return pager;
    }

}

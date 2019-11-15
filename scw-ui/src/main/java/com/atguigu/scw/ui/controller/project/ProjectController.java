package com.atguigu.scw.ui.controller.project;

import com.atguigu.scw.common.bean.ResponseVo;
import com.atguigu.scw.ui.service.ProjectInfoControllerFeign;
import com.atguigu.scw.ui.vo.reponse.ProjectDetailsResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectInfoControllerFeign projectInfoControllerFeign;

    @GetMapping("/projectDetails")
    public String projectDetails(Integer id, HttpSession session){
        ResponseVo<ProjectDetailsResponseVo> projectDetails = projectInfoControllerFeign.getProjectDetails(id);
        log.info("查询到的数据:{}", projectDetails);
        session.setAttribute("projectDetails", projectDetails.getData());
        return "project/project";
    }
}

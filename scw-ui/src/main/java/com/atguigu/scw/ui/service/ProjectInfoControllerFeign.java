package com.atguigu.scw.ui.service;

import com.atguigu.scw.common.bean.ResponseVo;
import com.atguigu.scw.ui.bean.TReturn;
import com.atguigu.scw.ui.vo.reponse.ProjectDetailsResponseVo;
import com.atguigu.scw.ui.vo.reponse.ProjectResponseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("SCW-PROJECT")
public interface ProjectInfoControllerFeign {

    @GetMapping("/getAllProjects")
    public ResponseVo<List<ProjectResponseVo>> getAllProjects();

    @GetMapping("/projectDetails")
    public ResponseVo<ProjectDetailsResponseVo> getProjectDetails(@RequestParam("id") Integer id);

    @GetMapping("/getReturn")
    public ResponseVo<TReturn> getReturn(@RequestParam("id") Integer id);

}

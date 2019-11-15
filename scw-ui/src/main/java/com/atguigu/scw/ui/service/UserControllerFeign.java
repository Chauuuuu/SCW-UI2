package com.atguigu.scw.ui.service;

import com.atguigu.scw.common.bean.ResponseVo;
import com.atguigu.scw.ui.bean.TMemberAddress;
import com.atguigu.scw.ui.vo.reponse.UserResponseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("SCW-USER")
public interface UserControllerFeign {

    @PostMapping("/user/doLogin")
    public ResponseVo<UserResponseVo> doLogin(@RequestParam("loginacct") String loginacct, @RequestParam("userpswd") String userpswd);

    @GetMapping("/user/getAddress")
    public ResponseVo<List<TMemberAddress>> getAddress(@RequestParam("accessToken") String accessToken);
}

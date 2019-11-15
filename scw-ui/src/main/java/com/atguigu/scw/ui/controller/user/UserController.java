package com.atguigu.scw.ui.controller.user;

import com.atguigu.scw.common.bean.ResponseVo;
import com.atguigu.scw.ui.vo.reponse.UserResponseVo;
import com.atguigu.scw.ui.service.UserControllerFeign;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserControllerFeign userControllerFeign;

    @PostMapping("/doLogin")
    public String doLogin(String loginacct, String userpswd, Model model, HttpSession session){
        ResponseVo<UserResponseVo> vo = userControllerFeign.doLogin(loginacct, userpswd);
        if ("200".equals(vo.getCode())){
            session.setAttribute("responseVo", vo.getData());
            String ref = (String )session.getAttribute("ref");
            if (StringUtils.isEmpty(ref)){
                return "redirect:/";
            }
            session.removeAttribute("ref");
            return "redirect:"+ref;
        }
        else {
            String errorMsg = vo.getMessage();
            model.addAttribute("errorMsg",errorMsg);
            return "user/login";
        }
    }
}

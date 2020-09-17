package com.baizhi.controller;


import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.utils.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(String username,String password){
        System.out.println("here!?!?!?!?!?!?!?!?");
        User login=userService.login(username,password);
        System.out.println("11111111111111111111111111");
        if(login!=null){
            System.out.println("login successed!!");
            return "redirect:/emp/findAll";
        }else{
            return "redirect:/index";
        }
    }

    @GetMapping("/code")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        String securityCode=ValidateImageCodeUtils.getSecurityCode();
        //生成图片
        BufferedImage image=ValidateImageCodeUtils.createImage(securityCode);
        session.setAttribute("code",securityCode);
        //响应图片
        ServletOutputStream os=response.getOutputStream();
        ImageIO.write(image,"png",os);
    }

    @PostMapping("/register")
    public String register(User user,String code,HttpSession session){
        String sessionCode=(String)session.getAttribute("code");
        if(sessionCode.equalsIgnoreCase(code)){
            userService.register(user);
            return "redirect:/index";
        }else{
            return "redirect:/toRegister";
        }

    }
}

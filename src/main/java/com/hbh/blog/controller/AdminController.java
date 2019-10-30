package com.hbh.blog.controller;

import com.hbh.blog.entity.Admin;
import com.hbh.blog.service.IAdminService;
import com.hbh.blog.util.JsonResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @Author:Hebinhua@foresee.com.cn
 * @Date:2019/10/9
 * @Des:管理员控制器
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private Logger log = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    IAdminService iAdminService;

    @RequestMapping("/login")
    public String login(){
        log.debug("test");
        return "admin/login";
    }
    @RequestMapping("/doLogin")
    public String dologin(@RequestParam("account") String account, @RequestParam("pwd") String pwd, HttpSession session) {
        String msg="";
        log.debug("test");
        String name =iAdminService.login(account,pwd);
        if(StringUtils.isNotBlank(name)){
            Admin admin = new Admin();
            admin.setName(name);
            admin.setAccount(account);
            session.setAttribute("Admin", admin);
            return "redirect:/admin/index";
        }else{
           msg="账号密码不正确，请重新登陆";
            return JsonResultUtil.getErrorResponse(msg);
        }
    }
    @RequestMapping("/update")
    public String update(HttpServletRequest request,String pwd,String name){
        int count =0;
        String msg="";
        Admin admin= new Admin();
        String account= (String)request.getSession().getAttribute("account");
        admin.setAccount(account);
        admin.setPwd(pwd);
        admin.setName(name);
        count =iAdminService.updateByPrimaryKeySelective(admin);
        if(count>0){
            return "admin/login";
        }else{
            msg="修改失败";
            return JsonResultUtil.getErrorResponse(msg);
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("account");
        session.removeAttribute("name");
        return "admin/login";
    }

    @RequestMapping("/index")
    public String index(HttpSession session) {
        if (session.getAttribute("Admin") != null) {
            return "admin/index";
        }
        return "admin/login";
    }

}

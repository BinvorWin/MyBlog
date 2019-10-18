package com.hbh.blog.controller;

import com.hbh.blog.entity.Admin;
import com.hbh.blog.service.IAdminService;
import com.hbh.blog.util.JsonResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @Author:Hebinhua@foresee.com.cn
 * @Date:2019/10/9
 * @Des:管理员控制器
 */
@Controller
public class AdminController {

    private static Logger logger = Logger.getLogger(AdminController.class);
    @Autowired
    IAdminService iAdminService;
    @RequestMapping("login")
    public String login(){
        logger.info("登陆.......................");
        return "admin/login";
    }
    @RequestMapping("/doLogin")
    public String dologin(String account,String pwd, HttpSession session){
        logger.debug(account+pwd);
        String msg="";
        String name =iAdminService.login(account,pwd);
        if(StringUtils.isNotBlank(name)){
            session.setAttribute("account",account);
            session.setAttribute("name",name);
            msg="登录成功";
            return "/admin/index";
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


}

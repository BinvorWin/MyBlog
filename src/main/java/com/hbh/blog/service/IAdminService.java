package com.hbh.blog.service;

import com.hbh.blog.entity.Admin;

/**
 * @Author:Hebinhua@foresee.com.cn
 * @Date:2019/10/9
 * @Des:管理员接口
 */
public interface IAdminService {
    /**
     * @Description 查询信息
     * @param account
     * @return com.hbh.blog.entity.Article
     * @Author HeBinhua
     * @Date 2019/10/9
     */
    public Admin selectByPrimaryKey(String account);

    /**
     * @Description 修改信息
     * @param record
     * @return int
     * @Author HeBinhua
     * @Date 2019/10/9
     */
    public int updateByPrimaryKeySelective(Admin record);

    /**
     * @Description 登陆校验
     * @param account,pwd
     * @return boolean
     * @Author HeBinhua
     * @Date 2019/10/9
     */
    public String login(String account,String pwd);
}

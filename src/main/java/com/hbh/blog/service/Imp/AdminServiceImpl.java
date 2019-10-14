package com.hbh.blog.service.Imp;

import com.hbh.blog.dao.AdminMapper;
import com.hbh.blog.entity.Admin;
import com.hbh.blog.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:Hebinhua@foresee.com.cn
 * @Date:2019/10/9
 * @Des: 管理员实现类
 */
@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired(required=false)
    AdminMapper adminMapper;

    @Override
    public Admin selectByPrimaryKey(String account) {
        return adminMapper.selectByPrimaryKey(account);
    }

    @Override
    public int updateByPrimaryKeySelective(Admin record) {
        return adminMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public String  login(String account,String pwd) {
        Admin user=adminMapper.selectByPrimaryKey(account);
        if(pwd.equals(user.getPwd())){
            return user.getName();
        }else{
        return "";
        }
    }
}

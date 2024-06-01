package com.dech53.schedule.test;

import com.dech53.schedule.dao.BaseDao;
import com.dech53.schedule.dao.SysScheduleDao;
import com.dech53.schedule.dao.impl.SysScheduleDaoImpl;
import com.dech53.schedule.pojo.SysSchedule;
import com.dech53.schedule.pojo.SysUser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class testBaseDao {
    private BaseDao baseDao;

    @BeforeClass
    public void initBaseDao(){
        baseDao = new BaseDao();
    }
    @Test
    public void testBaseQueryObject(){
        String sql = "select count(1) from sys_user";
        Long count =  baseDao.baseQueryObject(Long.class,sql);
        System.out.println(count);
    }
    @Test
    public void testBaseQuery(){
        String sql = "select uid,username,user_pwd userPwd from sys_user";
        List<SysUser> sysUsers = baseDao.baseQuery(SysUser.class, sql);
        sysUsers.forEach(System.out::println);
    }
    @Test
    public void testBaseUpdate(){
        String sql = "insert into sys_schedule values(DEFAULT,?,?,?)";
        int rows = baseDao.baseUpdate(sql, 1, "学习java", 0);
        System.out.println(rows);
    }
    @Test
    public void test(){
        SysScheduleDaoImpl sysScheduleDao = new SysScheduleDaoImpl();
        List<SysSchedule> sysScheduleList = sysScheduleDao.findAll();
        sysScheduleList.forEach(System.out::println);
        int rows = sysScheduleDao.addSchedule(new SysSchedule(null, 2, "学习数据库", 1));
        System.out.println(rows);
    }
}

package com.dech53.schedule.dao;

import com.dech53.schedule.pojo.SysSchedule;

import java.util.List;

public interface SysScheduleDao {
     int addSchedule(SysSchedule schedule);
     List<SysSchedule> findAll();

    List<SysSchedule> findItemListByUid(int uid);

    Integer addDefault(int uid);
    Integer updateSchedule(SysSchedule sysSchedule);
    Integer removeBySid(int sid);
}

package com.dech53.schedule.service;

import com.dech53.schedule.pojo.SysSchedule;

import java.util.List;

public interface SysScheduleService {
    List<SysSchedule> findItemListByUid(int uid);

    Integer addDefault(int uid);
    Integer updateSchedule(SysSchedule sysSchedule);
    Integer removeSchedule(int sid);
}

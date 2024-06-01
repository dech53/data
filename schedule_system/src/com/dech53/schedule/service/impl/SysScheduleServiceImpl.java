package com.dech53.schedule.service.impl;

import com.dech53.schedule.dao.SysScheduleDao;
import com.dech53.schedule.dao.impl.SysScheduleDaoImpl;
import com.dech53.schedule.pojo.SysSchedule;
import com.dech53.schedule.service.SysScheduleService;

import java.util.List;

public class SysScheduleServiceImpl implements SysScheduleService {
    private SysScheduleDao scheduleDao = new SysScheduleDaoImpl();
    @Override
    public List<SysSchedule> findItemListByUid(int uid) {
        return scheduleDao.findItemListByUid(uid);
    }

    @Override
    public Integer addDefault(int uid) {
        return scheduleDao.addDefault(uid);
    }
    @Override
    public Integer updateSchedule(SysSchedule sysSchedule) {
        return scheduleDao.updateSchedule(sysSchedule);
    }
    @Override
    public Integer removeSchedule(int sid) {
        return scheduleDao.removeBySid(sid);
    }
}

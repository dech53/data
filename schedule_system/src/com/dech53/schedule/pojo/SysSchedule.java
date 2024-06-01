package com.dech53.schedule.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysSchedule {
    private Integer sid;
    private Integer uid;
    private String title;
    private Integer completed;
}

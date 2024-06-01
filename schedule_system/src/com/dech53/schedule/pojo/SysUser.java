package com.dech53.schedule.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysUser {
    private Integer uid;
    private String username;
    private String userPwd;
}

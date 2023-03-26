package org.hhh.system.service;

public interface LoginLogService {
    public void recordLoginLog (String username,Integer status,String ipaddr,String message);
}

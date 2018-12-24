package cn.hz.service;

import cn.hz.domain.Syslog;

import java.util.List;

public interface ISysLogService {
    void save(Syslog syslog) throws Exception;

    List<Syslog> findAll() throws Exception;
}

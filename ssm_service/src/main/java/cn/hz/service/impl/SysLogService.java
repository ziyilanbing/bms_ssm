package cn.hz.service.impl;

import cn.hz.dao.ISysLogDao;
import cn.hz.domain.Syslog;
import cn.hz.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogService implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(Syslog syslog) throws Exception {
        sysLogDao.save(syslog);
    }

    @Override
    public List<Syslog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}

package cn.hz.service.impl;

import cn.hz.dao.IPermissionDao;
import cn.hz.domain.Permission;
import cn.hz.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionService implements IPermissionService {
    @Autowired
    private IPermissionDao dao;

    @Override
    public List<Permission> findAll() throws Exception {
        return dao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        dao.save(permission);
    }
}

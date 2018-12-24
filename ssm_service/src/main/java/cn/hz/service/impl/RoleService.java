package cn.hz.service.impl;

import cn.hz.dao.IRoleDao;
import cn.hz.domain.Permission;
import cn.hz.domain.Role;
import cn.hz.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RoleService implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) throws Exception{
        return roleDao.findById(id);
    }

    @Override
    public List<Permission> findOtherPermission(String id) throws Exception{
        return roleDao.findOtherPermission(id);
    }

}

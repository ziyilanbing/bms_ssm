package cn.hz.service;

import cn.hz.domain.Permission;
import cn.hz.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    Role findById(String id) throws Exception;

    List<Permission> findOtherPermission(String id) throws Exception;
}

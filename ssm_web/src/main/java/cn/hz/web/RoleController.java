package cn.hz.web;

import cn.hz.domain.Permission;
import cn.hz.domain.Role;
import cn.hz.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Role> list = roleService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("roleList", list);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    // 查看角色详情
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        Role role = roleService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    // 根据roleId查询role，并查询出可以添加的权限
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(String id) throws Exception {
        Role role = roleService.findById(id);
        List<Permission> permissionList = roleService.findOtherPermission(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("permissionList",permissionList);
        mv.addObject("role",role);
        mv.setViewName("role-permission-add");
        return mv;
    }
}

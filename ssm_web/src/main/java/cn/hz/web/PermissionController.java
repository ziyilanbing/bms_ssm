package cn.hz.web;

import cn.hz.domain.Permission;
import cn.hz.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Permission> list = permissionService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("permissionList",list);
        mv.setViewName("permission-list");
        return mv;
    }
    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }
}

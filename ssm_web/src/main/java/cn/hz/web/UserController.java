package cn.hz.web;

import cn.hz.domain.Role;
import cn.hz.domain.UserInfo;
import cn.hz.service.IUserServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;
// 只允许ADMIN角色访问
@RolesAllowed("ADMIN")
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserServie userServie;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<UserInfo> list = userServie.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userList",list);
        mv.setViewName("user-list");
        return mv;
    }
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception {
        userServie.save(userInfo);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id) throws Exception {
        UserInfo user = userServie.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",user);
        mv.setViewName("user-show");
        return mv;
    }
    //查询用户以及用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id) throws Exception {
        UserInfo userInfo = userServie.findById(id);
        List<Role> roleList = userServie.findOtherRoles(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("roleList",roleList);
        mv.addObject("user",userInfo);
        mv.setViewName("user-role-add");
        return mv;
    }

}

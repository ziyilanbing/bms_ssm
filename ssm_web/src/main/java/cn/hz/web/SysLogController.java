package cn.hz.web;

import cn.hz.domain.Syslog;
import cn.hz.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Syslog> syslogList = sysLogService.findAll();
        mv.addObject("sysLogs", syslogList);
        mv.setViewName("syslog-list");
        return mv;
    }
}

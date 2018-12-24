package cn.hz.web;

import cn.hz.domain.Syslog;
import cn.hz.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime; //开始时间
    private Class clazz; //访问的类
    private Method method;//访问的方法

    @Before("execution(* cn.hz.web.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//访问时间
        clazz = jp.getTarget().getClass();// 具体访问的类
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        if (args.length == 0 || args == null) {
            method = clazz.getMethod(methodName);
        } else {
            method = clazz.getMethod(methodName, args.getClass());
        }
    }

    @After("execution(* cn.hz.web.*.*(..))")
    public void doAfter() throws Exception {
        long time = System.currentTimeMillis() - visitTime.getTime();
        String url = "";
        if (clazz != null && method != null && clazz != LogAop.class && !clazz.getName().contains("SysLogController")) {
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0]+methodValue[0];
                    Syslog syslog = new Syslog();
                    syslog.setUrl(url);
                    //获取访问的ip
                    String ip = request.getRemoteAddr();
                    syslog.setIp(ip);
                    // 本服务器的ip
                    String localAddr = request.getLocalAddr();
                    System.out.println(localAddr+"--------------------------");
                    // 获取当前操作的用户
                    // 可以通过securityContext获取，也可以从request.getSession中获取
                    //request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")
                    SecurityContext context = SecurityContextHolder.getContext();
                    String username = ((User) context.getAuthentication().getPrincipal()).getUsername();
                    syslog.setUsername(username);

                    syslog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
                    syslog.setVisitTime(visitTime);
                    syslog.setExecutionTime(time);
                    sysLogService.save(syslog);
                }
            }
        }
    }
}

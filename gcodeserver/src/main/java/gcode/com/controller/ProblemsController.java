package gcode.com.controller;

import gcode.com.config.JwtUtils;
import gcode.com.model.User;
import gcode.com.service.UserService;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Classname ProblemsController
 * @Description TODO
 * @Date 2021/2/2 下午10:50
 * @Created by gandehua
 */
@RestController
@CrossOrigin
public class ProblemsController {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;
    @GetMapping("/problems")
    public boolean problems (HttpServletRequest request) {
        return isLogin(request);

    }

    public boolean isLogin(HttpServletRequest request){
        String token = request.getHeader("token");
        Claims claims = jwtUtils.parseJwt(token);
        long uid = Long.valueOf(claims.getId());
        System.out.println("uid--->"+ uid);
        User user = userService.getUserByUid(uid);
        return user != null;
    }
}

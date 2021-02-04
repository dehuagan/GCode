package gcode.com.controller;

import gcode.com.config.JwtUtils;
import gcode.com.model.*;
import gcode.com.service.UserService;
import io.jsonwebtoken.Jwt;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AccountsController {
    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @param email
     * @return
     */
    @PostMapping("/register")
    public RespBean register(@RequestParam(value = "username") String username,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "email", required = false) String email) {
        if (email == null) email = new String();
        User user = new User(username, password, email, 0);
        if (userService.createUser(user) == 1)
            return RespBean.ok("register success");
        else
            return RespBean.error("register failure");
    }

    /**
     * 检测用户名是否存在
     *
     * @param username
     * @return
     */
    @PostMapping("/validate_username")
    public User validate_username(@RequestParam(value = "username") String username) {
        return userService.getUserByUsername(username);
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public RespBean login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {

        User user = userService.getUserByUsername(username);
        if(user!= null  && user.getPassword().equals(password)){
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("uid",user.getUid());
            dataMap.put("username",user.getUsername());
            String token = jwtUtils.createJwt(String.valueOf(user.getUid()), user.getUsername(),dataMap);
            return RespBean.ok("login success", token);
        }else{
            return RespBean.error("login fail");
        }
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//
//        try {
//            subject.login(token);
//            User user = (User) subject.getPrincipal();
//            subject.getSession().setAttribute("login_user", user);
//            return RespBean.ok("login succes s", subject.getSession().getId());
//        } catch (UnknownAccountException e) {
//            return RespBean.error("no username");
//        } catch (IncorrectCredentialsException e) {
//            return RespBean.error("wrong password");
//        } catch (AuthenticationException e) {
//            return RespBean.error("login failure");
//        }
    }

    /**
     * logout
     *
     * @return
     */
    @GetMapping("/logout")
    public RespBean logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return RespBean.ok("logout success");
    }

    @GetMapping("/reg_test")
    public String reg_test(@RequestParam(value = "username") String username) {
        return "your name is " + username;
    }



}

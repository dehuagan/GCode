package gcode.com.controller;

import gcode.com.config.JwtUtils;
import gcode.com.model.RespBean;
import gcode.com.model.User;
import gcode.com.service.SubmissionService;
import gcode.com.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Classname SubmissionController
 * @Description TODO
 * @Date 2021/2/16 下午9:18
 * @Created by gandehua
 */
@RestController
public class SubmissionController {
    @Autowired
    SubmissionService submissionService;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/createSubmission")
    public RespBean createSubmission(@RequestParam(value = "uid") long uid, @RequestParam(value = "pid") long pid, @RequestParam(value = "languageSlug") String languageSlug, @RequestParam(value = "code") String code, HttpServletRequest request){
        if(isLogin(request)){
            Map<String, Object> result = submissionService.createSubmission(uid,pid,languageSlug,code);
            boolean successful = (Boolean)result.get("isSuccessful");
            if(successful) return RespBean.ok("success",result);
            else return RespBean.ok("unsuccess",result);
        }else{
            return RespBean.error("no login");
        }
    }

    public boolean isLogin(HttpServletRequest request) {
        String token = request.getHeader("token");
        try {
            Claims claims = jwtUtils.parseJwt(token);
            long uid = Long.valueOf(claims.getId());
            System.out.println("uid--->" + uid);
            User user = userService.getUserByUid(uid);
            return user != null;
        } catch (Exception e) {
            return false;
        }

    }

}

package gcode.com.controller;

import gcode.com.config.JwtUtils;
import gcode.com.model.RespBean;
import gcode.com.model.User;
import gcode.com.service.SubmissionService;
import gcode.com.service.UserService;
import io.jsonwebtoken.Claims;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

    private static final Logger LOGGER = LogManager.getLogger(SubmissionController.class);
    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/createSubmission")
    public RespBean createSubmission(@RequestParam(value = "pid") long pid, @RequestParam(value = "languageName") String languageName, @RequestParam(value = "code") String code, HttpServletRequest request) {
        HashMap<String, Object> map = isLogin(request);
        if ((boolean) map.get("isLogin")) {
            long uid = (long) map.get("uid");
            Map<String, Object> result = submissionService.createSubmission(uid, pid, languageName, code);
            boolean successful = (Boolean) result.get("isSuccessful");
            if (successful) {
//                LOGGER.info();
                return RespBean.ok("success", result);
            }
            else return RespBean.ok("unsuccess", result);
        } else {
            return RespBean.error("no login");
        }
    }

    public HashMap<String, Object> isLogin(HttpServletRequest request) {
        String token = request.getHeader("token");
        HashMap<String, Object> map = new HashMap<>();

        try {
            Claims claims = jwtUtils.parseJwt(token);
            long uid = Long.valueOf(claims.getId());
            System.out.println("uid--->" + uid);
            User user = userService.getUserByUid(uid);
            map.put("isLogin", user != null);
            map.put("uid", uid);
            return map;
        } catch (Exception e) {
            map.put("isLogin", false);
            return map;
        }

    }

}

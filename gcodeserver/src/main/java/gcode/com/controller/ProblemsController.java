package gcode.com.controller;

import com.github.pagehelper.PageInfo;
import gcode.com.config.JwtUtils;
import gcode.com.model.Problem;
import gcode.com.model.RespBean;
import gcode.com.model.User;
import gcode.com.service.ProblemService;
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
import java.util.HashMap;
import java.util.List;

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
    ProblemService problemService;
    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/getAllProblems")
    public RespBean getAllProblems(@RequestParam(defaultValue = "1", value = "pageNum") int pageNum, HttpServletRequest request) {
        if (isLogin(request)) {
            PageInfo<Problem> allProblems = problemService.selectPage(pageNum, 20);

            return RespBean.ok("success", allProblems);
        } else {
            return RespBean.error("no login");
        }
    }


    @GetMapping("/getNumOfProblems")
    public RespBean getNumOfProblems(HttpServletRequest request) {
        if (isLogin(request)) {
            HashMap<String, Integer> map = new HashMap<>();
            map.put("totalOfProblems", problemService.getProblemsNum());
            map.put("numOfHard", problemService.getNumOfDifficulty("Hard"));
            map.put("numOfMedium", problemService.getNumOfDifficulty("Medium"));
            map.put("numOfEasy", problemService.getNumOfDifficulty("Easy"));
            return RespBean.ok("success", map);
        } else {
            return RespBean.error("no login");
        }
    }

    @GetMapping("/getProblemDetail")
    public RespBean getProblemDetail(@RequestParam(value = "pid") long pid, HttpServletRequest request) {
        if (isLogin(request)) {
            Problem problem = problemService.getProblemDetailByPid(pid);
            if (problem != null) {
                return RespBean.ok("success", problem);
            } else {
                return RespBean.error("failure");
            }
        } else {
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

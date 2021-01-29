package gcode.com.controller;
import gcode.com.model.*;
import gcode.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AccountsController {
    @Autowired
    UserService userService;

    /**
     * 用户注册
     * @param username
     * @param password
     * @param email
     * @return
     */
    @PostMapping("/register")
    public int register(@RequestParam(value="username") String username,
                           @RequestParam(value="password") String password,
                           @RequestParam(value="email",required=false) String email){
        if(email == null) email = new String();
        User user  = new User(username, password, email,0);
        return userService.createUser(user);
    }

    /**
     * 检测用户名是否存在
     * @param username
     * @return
     */
    @PostMapping("/validate_username")
    public User validate_username(@RequestParam(value="username") String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping("/reg_test")
    public String reg_test(@RequestParam(value = "username") String username){
        return "your name is "+ username;
    }
}

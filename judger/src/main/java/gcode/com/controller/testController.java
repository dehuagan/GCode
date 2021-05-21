package gcode.com.controller;

import gcode.com.messenger.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname testController
 * @Description TODO
 * @Date 2021/5/20 上午12:01
 * @Created by gandehua
 */
@RestController
public class testController {
    @Autowired
    MessageSender messageSender;
    @PostMapping("/hello")
    public String hello(@RequestParam(value = "msg") String msg){
        messageSender.sendMessage(msg);
        return "hi";
    }
}

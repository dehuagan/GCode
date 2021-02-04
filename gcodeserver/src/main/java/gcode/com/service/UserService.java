package gcode.com.service;
import gcode.com.model.User;
import gcode.com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public int createUser(User user){
        return userMapper.createUser(user);
    }

    public User getUserByUsername(String username){
        return userMapper.getUserByUsername(username);
    }
    public User getUserByUid(long uid){
        return userMapper.getUserByUid(uid);
    }
}

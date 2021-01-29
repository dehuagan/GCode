package gcode.com.mapper;
import gcode.com.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    int createUser(User user);
    User getUserByUsername(String name);
//    int updateUser(User user);
//    int deleteUser(User user);
//    User getUserByUid(long uid);
}

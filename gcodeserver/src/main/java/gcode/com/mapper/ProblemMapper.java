package gcode.com.mapper;

import gcode.com.model.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname ProblemMapper
 * @Description TODO
 * @Date 2021/2/6 下午3:15
 * @Created by gandehua
 */
@Mapper
@Component
public interface ProblemMapper {
    int getProblemsNum();
    int getNumOfDifficulty(String difficulty);
    Problem getProblemDetailByPid(long pid);
    List<Problem> selectPage();
}

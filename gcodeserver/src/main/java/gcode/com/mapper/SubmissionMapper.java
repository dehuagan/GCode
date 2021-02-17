package gcode.com.mapper;
import org.springframework.stereotype.Component;
import gcode.com.model.Submission;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname SubmissionMapper
 * @Description TODO
 * @Date 2021/2/15 下午10:33
 * @Created by gandehua
 */
@Mapper
@Component
public interface SubmissionMapper {
    int createSubmission(Submission submission);
    int updateSubmission(Submission submission);
}

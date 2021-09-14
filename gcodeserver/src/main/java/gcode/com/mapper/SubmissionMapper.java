package gcode.com.mapper;
import org.springframework.stereotype.Component;
import gcode.com.model.Submission;
import org.apache.ibatis.annotations.Mapper;

/**
 * @see SubmissionMapper
 *
 * @date 2021/2/15 下午10:33
 * @author gandehua
 */
@Mapper
@Component
public interface SubmissionMapper {
    int createSubmission(Submission submission);
    int updateSubmission(Submission submission);
}

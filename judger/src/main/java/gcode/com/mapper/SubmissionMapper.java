package gcode.com.mapper;

import gcode.com.model.Submission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @see SubmissionMapper
 *
 * @date 2021/5/20 下午7:49
 * @author gandehua
 */
@Mapper
@Component
public interface SubmissionMapper {
    Submission getSubmissionById(long submissionId);
    int updateSubmission(Submission submission);
}

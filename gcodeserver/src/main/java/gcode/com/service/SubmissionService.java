package gcode.com.service;

import gcode.com.mapper.LanguageMapper;
import gcode.com.mapper.ProblemMapper;
import gcode.com.mapper.SubmissionMapper;
import gcode.com.mapper.UserMapper;
import gcode.com.messenger.MessageSender;
import gcode.com.model.Language;
import gcode.com.model.Problem;
import gcode.com.model.Submission;
import gcode.com.model.User;
import javafx.scene.web.PromptData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @see SubmissionService
 * 
 * @date 2021/2/15 下午10:26
 * @author gandehua
 */
@Service
public class SubmissionService {
    @Autowired
    private SubmissionMapper submissionMapper;

    @Autowired
    ProblemMapper problemMapper;

    @Autowired
    MessageSender messageSender;

    @Autowired
    UserMapper userMapper;

    @Autowired
    LanguageMapper languageMapper;

    public Map<String, Object> createSubmission(long uid, long pid, String languageName, String code) {
        User user = userMapper.getUserByUid(uid);
        Problem problem = problemMapper.getProblemDetailByPid(pid);
        Language language = languageMapper.getLanguageByName(languageName);
        Submission submission = new Submission(user, problem, language, code);
        Map<String,Object> result = (Map<String, Object>) getSubmissionCreateResult(submission);
        boolean isSuccessful = (Boolean)result.get("isSuccessful");
        if(isSuccessful){
            submission.setSubmitTime(new Date());
            submissionMapper.createSubmission(submission);
            long submissionId = submission.getSubmissionId();
            createSubmissionTask(submissionId);
            result.put("submissionId",submissionId);
        }
        return result;
    }

    private Map<String, ? extends Object> getSubmissionCreateResult(Submission submission) {
        Map<String, Boolean> result = new HashMap<>(5, 1);
        String code = submission.getCode();
        result.put("isUerLogined", submission.getUser() != null);
        result.put("isProblemExists", submission.getProblem() != null);
        result.put("isLanguageExists", submission.getLanguage() != null);
        result.put("isCodeEmpty", code == null || code.length() == 0);
        boolean isSuccessful = result.get("isUerLogined") && result.get("isProblemExists") && result.get("isLanguageExists") && !result.get("isCodeEmpty");
        result.put("isSuccessful",isSuccessful);
        return result;
    }

    public void createSubmissionTask(long submissionId){
        Map<String,Object> map = new HashMap<>();
        map.put("event","SubmissionCreated");
        map.put("submissionId",submissionId);
        messageSender.sendMessage(map);
    }
}

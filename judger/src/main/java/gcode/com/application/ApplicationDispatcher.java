package gcode.com.application;
import gcode.com.core.Dispatcher;
import gcode.com.mapper.SubmissionMapper;
import gcode.com.messenger.MessageSender;
import gcode.com.model.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @Classname ApplicationDispatcher
 * @Description TODO
 * @Date 2021/5/19 下午11:42
 * @Created by gandehua
 */
@Component
public class ApplicationDispatcher {
    @Autowired
    private MessageSender messageSender;

    @Autowired
    private SubmissionMapper submissionMapper;

    @Autowired
    private Dispatcher judgerDispatcher;
    public void onSubmissionCreated(long submissionId) throws InterruptedException, IOException {
        judgerDispatcher.createNewTask(submissionId);
    }

    public void onErrorOccurred(long submissionId, String errorMsg){
        //todo
        updateSubmission(submissionId, 0, 0, 0, "SE", errorMsg);
        Map<String, Object> mapMsg = new HashMap<>();
        mapMsg.put("event",errorMsg);
        mapMsg.put("submissionId",submissionId);
        messageSender.sendMessage(mapMsg);
    }

    private void updateSubmission(long submissionId, int usedTime,
                                  int usedMemory, int score, String judgeResult, String log) {
        Submission submission = submissionMapper.getSubmissionById(submissionId);
        submission.setExecuteTime(new Date());
        submission.setUsedTime(usedTime);
        submission.setUsedMemory(usedMemory);
//        submission.setJudgeScore(score);
//        submission.setJudgeResultSlug(judgeResult);
//        submission.setJudgeLog(log);

        submissionMapper.updateSubmission(submission);
    }

}

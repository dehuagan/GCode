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
 * @see ApplicationDispatcher
 *
 * @date 2021/5/19 下午11:42
 * @author gandehua
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
        updateSubmission(submissionId, 0, 0, "SE", errorMsg);
        Map<String, Object> mapMsg = new HashMap<>();
        mapMsg.put("event","ErrorOccurred");
        mapMsg.put("submissionId",submissionId);
        messageSender.sendMessage(mapMsg);
    }

    public void onTestCaseOccurred(long submissionId, String input, String expected, String output ){
        String result = "Wrong Answer \n"+ "Input: "+input+"\nOUtput: "+output+"\nExpected: "+expected;
        updateSubmission(submissionId,0,0,result, "");
        Map<String, Object> mapMsg = new HashMap<>();
        mapMsg.put("event","Wrong Answer");
        mapMsg.put("submissionId",submissionId);
        mapMsg.put("log", result);
        messageSender.sendMessage(mapMsg);
    }

    public void onAllTestcaseAccepted(long submissionId, float usedTime, float usedMemory,  String msg){
        updateSubmission(submissionId, usedTime, usedMemory,  msg,"");
        Map<String, Object> mapMsg = new HashMap<>();
        mapMsg.put("event","Accepted");
        mapMsg.put("submissionId",submissionId);
        messageSender.sendMessage(mapMsg);
    }

    private void updateSubmission(long submissionId, float usedTime,
                                  float usedMemory, String judgeResult, String log) {
        Submission submission = submissionMapper.getSubmissionById(submissionId);
        submission.setExecuteTime(new Date());
        submission.setUsedTime(usedTime);
        submission.setUsedMemory(usedMemory);

        submission.setResult(judgeResult);


        submissionMapper.updateSubmission(submission);
    }

}

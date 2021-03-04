package gcode.com.messenger;

import org.springframework.context.ApplicationEvent;

/**
 * @Classname SubmissionEvent
 * @Description TODO
 * @Date 2021/3/3 下午7:51
 * @Created by gandehua
 */
public class SubmissionEvent extends ApplicationEvent {
    private final long submissionId;
    private final String judgeResult;
    private final String message;
    private final boolean isCompleted;

    public long getSubmissionId() {
        return submissionId;
    }

    public String getJudgeResult() {
        return judgeResult;
    }

    public String getMessage() {
        return message;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public SubmissionEvent(Object source, long submissionId, String judgeResult, String message, boolean isCompleted){
        super(source);
        this.submissionId = submissionId;
        this.judgeResult = judgeResult;
        this.message = message;
        this.isCompleted = isCompleted;
    }
}

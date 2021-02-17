package gcode.com.model;

import org.apache.ibatis.annotations.Lang;

import java.io.Serializable;
import java.security.PrivateKey;
import java.util.Date;

/**
 * @Classname Submission
 * @Description TODO
 * @Date 2021/2/15 下午4:00
 * @Created by gandehua
 */
public class Submission implements Serializable {
    private long submissionId;
    private Problem problem;
    private User user;
    private Language language;
    private Date submitTime;
    private Date executeTime;
    private int usedTime;
    private int usedMemory;
    private String code;
    private String judgeResult;

    public Submission(User user, Problem problem, Language language,String code){
        this.user = user;
        this.problem = problem;
        this.language = language;
        this.code = code;
    }

    public long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(long submissionId) {
        this.submissionId = submissionId;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public int getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(int usedTime) {
        this.usedTime = usedTime;
    }

    public int getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(int usedMemory) {
        this.usedMemory = usedMemory;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJudgeResult() {
        return judgeResult;
    }

    public void setJudgeResult(String judgeResult) {
        this.judgeResult = judgeResult;
    }
}

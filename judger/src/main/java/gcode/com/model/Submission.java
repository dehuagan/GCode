package gcode.com.model;

/**
 * @Classname Submission
 * @Description TODO
 * @Date 2021/5/20 下午7:47
 * @Created by gandehua
 */


import org.springframework.beans.factory.parsing.Problem;

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
    private long pid;
    private long uid;
//    private User user;
    private Language language;
    private Date submitTime;
    private Date executeTime;
    private float usedTime;
    private float usedMemory;
    private String code;
//    private String judgeResult;
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
//    public Submission(User user, Problem problem, Language language,String code){
//        this.user = user;
//        this.problem = problem;
//        this.language = language;
//        this.code = code;
//    }

    public long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(long submissionId) {
        this.submissionId = submissionId;
    }

    public long getProblemId() {
        return pid;
    }

    public void setProblemId(long pid) {
        this.pid = pid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
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

    public float getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(float usedTime) {
        this.usedTime = usedTime;
    }

    public float getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(float usedMemory) {
        this.usedMemory = usedMemory;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

//    public String getJudgeResult() {
//        return judgeResult;
//    }
//
//    public void setJudgeResult(String judgeResult) {
//        this.judgeResult = judgeResult;
//    }
}


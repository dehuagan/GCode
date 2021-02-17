package gcode.com.model;

import java.io.Serializable;

/**
 * @Classname TestCase
 * @Description TODO
 * @Date 2021/2/15 下午4:01
 * @Created by gandehua
 */
public class TestCase implements Serializable {
    private long pid;
    private long testCaseId;
    private String input;
    private String output;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}

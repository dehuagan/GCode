package gcode.com.model;

/**
 * @Classname TestCase
 * @Description TODO
 * @Date 2021/5/21 下午4:22
 * @Created by gandehua
 */
public class TestCase {
    long testCaseId;
    long pid;
    String input;
    String output;

    public long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
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

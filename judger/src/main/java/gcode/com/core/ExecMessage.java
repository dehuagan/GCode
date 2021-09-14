package gcode.com.core;

/**
 * @see ExecMessage
 *
 * @date 2021/5/20 下午10:15
 * @author gandehua
 */
public class ExecMessage {

    private String error;

    private String stdout;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }
}

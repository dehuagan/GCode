package gcode.com.messenger;
import java.util.Date;
import org.springframework.context.ApplicationEvent;

/**
 * @see KeepAliveEvent
 *
 * @date 2021/3/3 下午9:23
 * @author gandehua
 */
public class KeepAliveEvent extends ApplicationEvent{
    private final String judgeUsername;
    private final String judgerDescription;
    private final Date heartbeatTime;

    public String getJudgerUsername() {
        return judgeUsername;
    }

    public String getJudgerDescription() {
        return judgerDescription;
    }

    public Date getHeartbeatTime() {
        return heartbeatTime;
    }

    public KeepAliveEvent(Object source, String judgeUsername, String judgerDescription, Date heartbeatTime){
        super(source);
        this.judgeUsername = judgeUsername;
        this.judgerDescription = judgerDescription;
        this.heartbeatTime = heartbeatTime;
    }

}

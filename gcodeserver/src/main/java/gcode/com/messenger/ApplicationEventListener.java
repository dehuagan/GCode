package gcode.com.messenger;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.*;
import java.util.concurrent.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @see ApplicationEventListener
 *
 * @date 2021/3/3 下午2:46
 * @author gandehua
 */
@Component
public class ApplicationEventListener {
    /**
     * SseEmitter对象的列表.
     * SseEmitter推送实时测评信息
     */
    private static Map<Long, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();


    /**
     * 在线测评机列表
     */
    private static Map<String, Map<String,Object>> onlineJudgers = new ConcurrentHashMap<>();
    /**
     * 定期移除离线的测评机
     */
    private static ScheduledExecutorService scheduler = null;


    /**
     * 日志记录器.
     */
    private static final Logger LOGGER = LogManager.getLogger(ApplicationEventListener.class);

    /**
     * 将消息队列中的信息转发至控制器
     */
    public ApplicationEventListener(){
        synchronized (this){
            if(scheduler == null){
                final int INITIAL_DELAY = 0;
                final int PERIOD = 30;
                scheduler = new ScheduledThreadPoolExecutor(1);
                scheduler.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.MINUTE,-PERIOD);
                        Date heartbeatTimeDeadline = calendar.getTime();
                        for(Iterator<Entry<String, Map<String,Object>>> itr = onlineJudgers.entrySet().iterator();itr.hasNext();){
                            Entry<String, Map<String, Object>> entry = itr.next();
                            Date lastHeartbeatTime = (Date)entry.getValue().get("heartbeatTime");
                            if(!lastHeartbeatTime.after(heartbeatTimeDeadline)){
                                itr.remove();
                            }
                        }
                    }
                },INITIAL_DELAY,PERIOD, TimeUnit.MINUTES);
            }
        }
    }


    @EventListener
    public void submissionEventHandler(SubmissionEvent event) throws IOException{
        long submissionId = event.getSubmissionId();
        String judgeResult = event.getJudgeResult();
        String message = event.getMessage();
        boolean isCompleted = event.isCompleted();
        SseEmitter sseEmitter = sseEmitterMap.get(submissionId);
        if(sseEmitter == null){
            LOGGER.warn(String.format("Cannot get sseEmitter for submission #%d.", submissionId));
            return;
        }
        Map<String,String> mapMessage = new HashMap<>(3,1);
        mapMessage.put("judgeResult",judgeResult);
        mapMessage.put("message",message);
        sseEmitter.send(mapMessage);
        if (isCompleted){
            sseEmitter.complete();

        }
    }

    public void addSseEmitters(long submissionId, SseEmitter sseEmitter){
        sseEmitterMap.put(submissionId,sseEmitter);
    }

    public void removeSseEmitters(long submissionId){
        sseEmitterMap.remove(submissionId);
        for(Entry<Long,SseEmitter>mapEntry:sseEmitterMap.entrySet()){
            long currentSubmissionId = mapEntry.getKey();
            if(currentSubmissionId<submissionId){
                sseEmitterMap.remove(currentSubmissionId);
            }
        }
    }

    @EventListener
    public void keepAliveEventHandler(KeepAliveEvent event){
        String judgerUsername = event.getJudgerUsername();
        String judgerDescription = event.getJudgerDescription();
        Date heartbeatTime = event.getHeartbeatTime();
        Map<String,Object> judgerInformation = new HashMap<>();
        judgerInformation.put("description",judgerDescription);
        judgerInformation.put("heartbeatTime",heartbeatTime);
        onlineJudgers.put(judgerUsername,judgerInformation);
    }

    public String getJudgerDescription(String judgerUsername){
        String judgerDescription = "[Offline]";
        if(onlineJudgers.containsKey(judgerUsername)){
            String description = (String) onlineJudgers.get(judgerUsername).get("description");
            judgerDescription = "[Online]"+description;
        }
        return judgerDescription;
    }

    public long getOnlineJudgers(){
        return onlineJudgers.size();
    }

}

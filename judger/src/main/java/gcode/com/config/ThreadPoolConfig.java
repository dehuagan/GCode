package gcode.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author gandehua
 * @date 2021/9/14 下午10:17
 * @see ThreadPoolConfig
 */
@Configuration
public class ThreadPoolConfig {
    // 获取CPU核数
    final static int availableProsser = Runtime.getRuntime().availableProcessors();

    @Bean("judgeExecutor")
    public Executor judgeExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 单位时间内处理更多的任务，也就是吞吐量优先
        taskExecutor.setCorePoolSize(availableProsser);
        taskExecutor.setMaxPoolSize(availableProsser * 2);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadNamePrefix("judgeExecutor --->");
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60);
        // 使用当前线程执行
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }
}

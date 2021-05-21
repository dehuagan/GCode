package gcode.com.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Classname ExecutorUtil
 * @Description TODO
 * @Date 2021/5/20 下午10:13
 * @Created by gandehua
 */
public class ExecutorUtil {

    public static ExecMessage exec(String cmd) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process exec = runtime.exec(cmd);
        ExecMessage res = new ExecMessage();
        res.setError(message(exec.getErrorStream()));
        res.setStdout(message(exec.getInputStream()));
        return res;
    }

    private static String message(InputStream inputStream) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuilder message = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {
                message.append(str);
            }
            String result = message.toString();
            if (result.equals("")) {
                return null;
            }
            return result;
        } catch (IOException e) {
            return e.getMessage();
        } finally {
            try {
                inputStream.close();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

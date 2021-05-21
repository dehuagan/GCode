package gcode.com.core;

import gcode.com.application.ApplicationDispatcher;
import gcode.com.mapper.SubmissionMapper;
import gcode.com.mapper.TestCaseMapper;
import gcode.com.model.Language;
import gcode.com.model.Submission;
import gcode.com.model.TestCase;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

/**
 * @Classname Dispatcher
 * @Description TODO
 * @Date 2021/5/20 上午9:43
 * @Created by gandehua
 */
@Component
public class Dispatcher {
    static String projectDir = "/Users/gandehua/GCode/judger/src/main/java/gcode/com";
    static String baseDir = "workspace";
    @Autowired
    private ApplicationDispatcher applicationDispatcher;

    @Autowired
    private TestCaseMapper testCaseMapper;
    @Autowired
    SubmissionMapper submissionMapper;
    public void createNewTask(long submissionId) throws InterruptedException, IOException {
        synchronized (this){
//            String baseDirectory = String
            int tryTimes = 0;
            Submission submission = null;
            while(submission==null&&tryTimes++<3){
                Thread.sleep(1000);
                submission = submissionMapper.getSubmissionById(submissionId);
            }
            if(submission == null){
                System.out.println("illegal submission "+ submissionId);
                return;
            }
            String path = baseDir+"/"+submission.getSubmissionId();
            //生成对应判题目录
            String filename = preprocess(submission,path);
            //在目录下编译文件
            String msg = complie(submission,path);
            if(msg!=null){
                applicationDispatcher.onErrorOccurred(submissionId,msg);
                return;
            }
            ExecutorUtil.exec("chmod -R 755 " + path);

            runProgram(submission,filename,path);

        }

    }
    private String preprocess(Submission submission, String path) throws IOException {
        File file = new File(path);
        file.mkdirs();
        return createFile(submission,path);
    }
    private String createFile(Submission submission, String path) throws IOException {
        String filename = "";
//        Language language = submission.getLanguage();
        String language = submission.getLanguage().getLanguageName();
        switch (language){
            case "Java":
                filename = "Main.java";
                break;
            case "C":
                filename = "main.c";
                break;
            case "C++":
                filename = "main.cpp";
                break;
            case "Pascal":
                filename = "main.pas";
                break;
            case "Python3":
                filename = "main.py";
                break;
        }

        File file = new File(path+"/"+filename);
        file.createNewFile();
        OutputStream output = new FileOutputStream(file);
        PrintWriter writer = new PrintWriter(output);
        writer.print(submission.getCode());
        writer.close();
        output.close();
        return filename;
    }

    private String complie(Submission submission, String path) throws IOException {
        String language = submission.getLanguage().getLanguageName();
        String cmd = "";
        switch (language){
            case "Java":
                cmd = "javac " + path + "/Main.java";
                break;
            case "C":
                cmd = "gcc " + path + "/main.c -o " + path + "/main " + "-w -lm";
                break;
            case "C++":
                cmd = "g++ " + path + "/main.cpp -o " + path + "/main "+"-w -lm";
                break;
            case "Pascal":
                cmd = "fpc " + path + "/main.pas -O2 -Co -Ct -Ci";
                break;
            case "Python3":
                cmd = "python3 -m py_compile " + path + "/main.py";
                break;
        }
        return ExecutorUtil.exec(cmd).getError();
    }

    private void runProgram(Submission submission,String filename, String path) throws IOException {

        long submissionId = submission.getSubmissionId();
        long problemId = submission.getProblemId();
        List<TestCase> testCases = testCaseMapper.getTestCaseByPid(problemId);
        for(TestCase testCase:testCases){
            String cmd = getRunCmd(submission.getLanguage().getLanguageName(),path,testCase.getInput());
            ExecMessage exec = ExecutorUtil.exec(cmd);
            if(exec.getError()!=null){

            }
        }


    }
    private String getRunCmd(String language, String path,String param){
        String cmd = "";
        switch (language){
            case "Java":
                cmd = "java -cp " + path + "Main.java";
                break;
            case "C":
                cmd = path + "/main";
                break;
            case "C++":
                cmd = path + "/main";
                break;
            case "Pascal":
                cmd = path + "/main";
                break;
            case "Python3":
                cmd = "python3" + path + "/main.py";
                break;
        }
        return cmd;
    }
}

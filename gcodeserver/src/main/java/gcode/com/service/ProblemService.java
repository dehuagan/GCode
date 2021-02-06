package gcode.com.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import gcode.com.model.Problem;
import gcode.com.mapper.ProblemMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname ProblemService
 * @Description TODO
 * @Date 2021/2/6 下午3:13
 * @Created by gandehua
 */
@Service
public class ProblemService {
    @Autowired
    private ProblemMapper problemMapper;

    public int getProblemsNum(){
        return problemMapper.getProblemsNum();
    }
    public int getNumOfDifficulty(String difficulty){
        return problemMapper.getNumOfDifficulty(difficulty);
    }

    public Problem getProblemDetailByPid(long pid){
        return problemMapper.getProblemDetailByPid(pid);
    }

    public PageInfo<Problem> selectPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Problem> list = problemMapper.selectPage();
        PageInfo<Problem> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}

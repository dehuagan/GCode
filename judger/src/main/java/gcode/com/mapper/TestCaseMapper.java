package gcode.com.mapper;

import gcode.com.model.TestCase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @see TestCaseMapper
 *
 * @date 2021/5/21 下午4:24
 * @author gandehua
 */
@Mapper
@Component
public interface TestCaseMapper {
    List<TestCase> getTestCaseByPid(long pid);
}

package gcode.com.mapper;

import gcode.com.model.Language;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Classname LanguageMapper
 * @Description TODO
 * @Date 2021/2/15 下午11:25
 * @Created by gandehua
 */
@Mapper
@Component
public interface LanguageMapper {
    Language getLanguageById(int languageId);
    Language getLanguageBySlug(String languageSlug);
    Language getLanguageByName(String name);
}

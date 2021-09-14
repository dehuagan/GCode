package gcode.com.mapper;

import gcode.com.model.Language;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @see LanguageMapper
 * 
 * @date 2021/2/15 下午11:25
 * @author gandehua
 */
@Mapper
@Component
public interface LanguageMapper {
    Language getLanguageById(int languageId);
    Language getLanguageBySlug(String languageSlug);
    Language getLanguageByName(String name);
}

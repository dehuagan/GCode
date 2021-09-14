package gcode.com.mapper;


import gcode.com.model.Language;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @see LanguageMapper
 *
 * @date 2021/5/20 下午8:07
 * @author gandehua
 */
@Mapper
@Component
public interface LanguageMapper {
    Language getLanguageById(int languageId);
    Language getLanguageBySlug(String languageSlug);
}

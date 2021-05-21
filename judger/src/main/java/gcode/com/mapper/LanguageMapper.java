package gcode.com.mapper;


import gcode.com.model.Language;

/**
 * @Classname LanguageMapper
 * @Description TODO
 * @Date 2021/5/20 下午8:07
 * @Created by gandehua
 */
public interface LanguageMapper {
    Language getLanguageById(int languageId);
    Language getLanguageBySlug(String languageSlug);
}

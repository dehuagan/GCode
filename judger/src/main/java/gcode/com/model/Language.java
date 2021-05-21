package gcode.com.model;

import java.io.Serializable;

/**
 * @Classname Language
 * @Description TODO
 * @Date 2021/5/20 下午8:08
 * @Created by gandehua
 */
public class Language implements Serializable {
    private int languageId;
    private String languageSlug;
    private String languageName;
    private String compileCommand;
    private String runCommand;

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getLanguageSlug() {
        return languageSlug;
    }

    public void setLanguageSlug(String languageSlug) {
        this.languageSlug = languageSlug;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getCompileCommand() {
        return compileCommand;
    }

    public void setCompileCommand(String compileCommand) {
        this.compileCommand = compileCommand;
    }

    public String getRunCommand() {
        return runCommand;
    }

    public void setRunCommand(String runCommand) {
        this.runCommand = runCommand;
    }
}


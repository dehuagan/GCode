package gcode.com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class User implements Serializable {
    /**
     * 用户标识符
     */
    private long uid;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * email
     */
    private String email;

    /**
     * 完成题目数
     */
    private int problem_count;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getProblem_count() {
        return problem_count;
    }

    public void setProblem_count(int problem_count) {
        this.problem_count = problem_count;
    }

    public User(String username, String password, String email, int problem_count) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.problem_count = problem_count;
    }
}

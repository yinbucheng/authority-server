package cn.bucheng.model.vo;

import java.io.Serializable;

/**
 * @ClassName UserVo
 * @Author buchengyin
 * @Date 2019/5/20 16:17
 **/
public class UserVo implements Serializable {
    private String name;
    private String gander;
    private String nickName;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGander() {
        return gander;
    }

    public void setGander(String gander) {
        this.gander = gander;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}

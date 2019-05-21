package cn.bucheng.model.dto;

import java.io.Serializable;

/**
 * @ClassName UserMappingDto
 * @Author buchengyin
 * @Date 2019/5/21 10:58
 **/
public class UserMappingDto  implements Serializable {
    private String url;
    private String webId;
    private String method;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "UserMappingDto{" +
                "url='" + url + '\'' +
                ", webId='" + webId + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}

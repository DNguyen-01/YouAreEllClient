package models;

/* 
 * POJO for an Id object
 */

//import com.fasterxml.jackson.annotation.JsonProperty;

public class Id {
//    @JsonProperty
    private String uid = "";
    private String name = "";
    private String github = "";
    private String idToRegister;
    private String githubName;

    public Id() {
    }

    public Id (String userid, String name, String githubId) {
        this.uid = userid;
        this.name = name;
        this.github = githubId;

    }

    public Id(String userid, String github) {
        this.idToRegister = userid;
        this.githubName = github;
    }

    public String getUid() {
        return uid;
    }

    public void setUserid(String userid) {
       this.uid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public String toString() {
        return "Id{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", github='" + github + '\'' +
                ", idToRegister='" + idToRegister + '\'' +
                ", githubName='" + githubName + '\'' +
                '}';
    }
}
package models;

/* 
 * POJO for an Id object
 */


public class Id {

    private String uid = "";
    private String name = "";
    private String github = "";
    private String idToRegister;
    private String githubName;

    public Id() {
    }

    public Id (String uid, String name, String githubId) {
        this.uid = uid;
        this.name = name;
        this.github = githubId;

    }

    public Id(String uid, String github) {
        this.idToRegister = uid;
        this.githubName = github;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
       this.uid = uid;
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
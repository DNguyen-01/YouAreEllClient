package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javaax.peristence.Id;
import javax.persistence.Table;


/* 
 * POJO for an Id object
 */


public class Id {

    private String uid = "";
    private String name = "";
    private String github = "";

    public Id() {
    }

    public Id (String uid, String name, String githubId) {
        this.uid = uid;
        this.name = name;
        this.github = githubId;
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
        return this.name + " (" + this.github + ") ";
    }
}
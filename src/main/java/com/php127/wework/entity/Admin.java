package com.php127.wework.entity;

import org.springframework.data.relational.core.mapping.Table;



import javax.persistence.*;

@Entity
@Table("wework_admin")
public class Admin {

    @Id
    @GeneratedValue

    private long id;
    private String username;
    private int state;

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

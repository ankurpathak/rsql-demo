package com.github.ankurpathak.rsql.rsqldemo;


import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Document(collection = "usersDemo")
@QueryEntity
public class User implements Serializable {


    @CreatedDate
    private LocalDateTime created;


    @LastModifiedDate
    private LocalDateTime updated;

    @Id
    private String id;

    private String firstName;

    private String lastName;


    public static User getInstance(){
        return new User();
    }


    private BigInteger age;

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public BigInteger getAge() {
        return age;
    }

    public void setAge(BigInteger age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User created(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public User updated(LocalDateTime updated) {
        this.updated = updated;
        return this;
    }

    public User id(String id) {
        this.id = id;
        return this;
    }

    public User firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User age(BigInteger age) {
        this.age = age;
        return this;
    }
}

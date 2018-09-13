package com.github.ankurpathak.rsql.rsqldemo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Date;
import java.util.List;

public interface IUserRepository extends MongoRepository<User, String>, QuerydslPredicateExecutor<User> {
    List<User> findByCreatedLessThan(Date date);
}

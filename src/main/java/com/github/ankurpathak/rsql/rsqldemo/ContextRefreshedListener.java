package com.github.ankurpathak.rsql.rsqldemo;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

//@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    private final IUserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    public ContextRefreshedListener(IUserRepository userRepository, MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    @SuppressWarnings("all")
    public void onApplicationEvent(ContextRefreshedEvent event) {
        mongoTemplate.dropCollection(User.class);
        try{
            userRepository.save(User.getInstance().age(BigInteger.valueOf(30)).firstName("Paul"));
        }catch (DuplicateKeyException ex){

        }


    }



}

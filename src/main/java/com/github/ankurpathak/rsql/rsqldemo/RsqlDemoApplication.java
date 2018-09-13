package com.github.ankurpathak.rsql.rsqldemo;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.RSQLParserException;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@SpringBootApplication
@EnableMongoAuditing
public class RsqlDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RsqlDemoApplication.class, args);
    }
}

@Component
class RsqlDemoCMD implements ApplicationRunner{

    private final  MongoTemplate mongoTemplate;
    private final IUserRepository userRepository;

    public RsqlDemoCMD(MongoTemplate mongoTemplate, IUserRepository userRepository) {
        this.mongoTemplate = mongoTemplate;
        this.userRepository = userRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();
        Condition<GeneralQueryBuilder> condition = pipeline.apply(, User.class);
        Criteria criteria = condition.query(new MongoVisitor());
        var query = new Query();
        query.addCriteria(criteria);
        List<User> result = mongoTemplate.find(query, User.class);
        System.out.println(result.size());*/


        //UriTemplate template = new UriTemplate("/{resource}/search");
       // System.out.println(template.match("http://localhost:8080/users/search"));

        String search = "firstName==A*;age==30;(created=lt=2018-09-10T13:25:12.228Z,firstName==*r)";

        Node rootNode = null;
        try{
            rootNode = new RSQLParser().parse(search);
            Criteria spec = rootNode.accept(new CustomRSQLVisitor("usersDemo"));

            Query query = new Query();
            query.addCriteria(spec);



            List<User> users =  mongoTemplate.find(query, User.class);


            users = userRepository.findByCreatedLessThan(Calendar.getInstance().getTime());

        }catch (RSQLParserException ex){
            ex.printStackTrace();
        }







    }
}

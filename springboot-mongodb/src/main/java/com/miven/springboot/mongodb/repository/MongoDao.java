package com.miven.springboot.mongodb.repository;

import com.miven.entity.Student;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class MongoDao {

    @Resource
    private MongoTemplate mongoTemplate;

    public void save(Object objectToSave) {
        mongoTemplate.save(objectToSave);
    }

    public Student query(String key, Object value) {
        return mongoTemplate.findOne(Query.query(Criteria.where(key).is(value)), Student.class);
    }

    public void updateById(long id, String key, Object value) {
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = Update.update(key, value);
        mongoTemplate.updateFirst(query, update, Student.class);
    }
}

package cn.king.test;

import cn.king.controller.PersonController;
import cn.king.dao.PersonDao;
import cn.king.domain.entity.Person;
import cn.king.service.PersonService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author: wjl@king.cn
 * @time: 2020/4/5 15:02
 * @version: 1.0.0
 * @description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {

    @Autowired
    private PersonController personController;
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonDao personDao;

    @Test
    public void test01(){
        Person person = new Person();
        person.setId(100L);
        person.setName("tom");
        person.setGender((byte)1);
        person.setBirthday(new Date());
        person.setSalary(1000.0);
        person.setIsMarried(true);
        person.setSortOrder(1);
        person.setStatus(1);
        person.setCreatedTime(new Date());
        person.setCreatedBy("admin");
        person.setUpdatedTime(new Date());
        person.setUpdatedBy("admin");
        log.info(JSONObject.toJSONString(person));
    }

}

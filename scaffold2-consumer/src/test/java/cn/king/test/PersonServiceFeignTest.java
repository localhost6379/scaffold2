package cn.king.test;

import cn.king.domain.data.DataResult;
import cn.king.domain.data.PageBean;
import cn.king.domain.entity.Person;
import cn.king.feign.service.PersonServiceFeign;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author: wjl@king.cn
 * @time: 2020/4/30 16:26
 * @version: 1.0.0
 * @description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceFeignTest {

    @Resource
    private PersonServiceFeign personServiceFeign;

    @Test
    public void test01() {
        DataResult<List<Person>> dataResult = personServiceFeign.findAll();
        log.info(JSONObject.toJSONString(dataResult));
        List<Person> personList = dataResult.getData();
        personList.forEach(System.out::println);
    }

    @Test
    public void test02() {
        Person person = new Person();
        person.setId(10L);
        person.setName("王五");
        person.setGender((byte) 1);
        person.setBirthday(new Date());
        person.setSalary(1000.0);
        person.setIsMarried(true);
        person.setSortOrder(1);
        person.setStatus(1);
        person.setCreatedTime(new Date());
        person.setCreatedBy("admin");
        person.setUpdatedTime(new Date());
        person.setUpdatedBy("admin");
        DataResult dataResult = personServiceFeign.save(person);
        log.info("dataResult：{}", dataResult);
    }

    @Test
    public void test03() {
        Person person = new Person();
        person.setId(9L);
        person.setName("王五2");
        person.setGender((byte) 1);
        person.setBirthday(new Date());
        person.setSalary(1000.0);
        person.setIsMarried(true);
        person.setSortOrder(1);
        person.setStatus(1);
        person.setCreatedTime(new Date());
        person.setCreatedBy("admin");
        person.setUpdatedTime(new Date());
        person.setUpdatedBy("admin");
        DataResult dataResult = personServiceFeign.update(person);
        log.info("dataResult:{}", dataResult);
    }

    @Test
    public void test04() {
        Person person = new Person();
        person.setId(9L);
        person.setName("王五2");
        DataResult dataResult = personServiceFeign.delete(person);
        log.info("dataResult:{}", dataResult);
    }

    @Test
    public void test05() {
        DataResult dataResult = personServiceFeign.deleteById(10L);
        log.info("dataResult:{}", dataResult);
    }

    @Test
    public void test06() {
        DataResult<Person> dataResult = personServiceFeign.findById(1L);
        Person person = dataResult.getData();
        log.info("person:{}", person);
    }

    @Test
    public void test07() {
        DataResult<PageBean<Person>> dataResult = personServiceFeign.findByCondition(1, 3, new Person());
        PageBean<Person> pageBean = dataResult.getData();
        log.info("pageBean:{}", JSONObject.toJSONString(pageBean));

        DataResult<PageBean<Person>> dataResult2 = personServiceFeign.findByCondition(2, 3, new Person());
        PageBean<Person> pageBean2 = dataResult2.getData();
        log.info("pageBean2:{}", JSONObject.toJSONString(pageBean2));

        DataResult<PageBean<Person>> dataResult3 = personServiceFeign.findByCondition(3, 3, new Person());
        PageBean<Person> pageBean3 = dataResult3.getData();
        log.info("pageBean3:{}", JSONObject.toJSONString(pageBean3));

        Person person = new Person();
        person.setName("张");
        DataResult<PageBean<Person>> dataResult4 = personServiceFeign.findByCondition(1, 2, person);
        PageBean<Person> pageBean4 = dataResult4.getData();
        log.info("pageBean4:{}", JSONObject.toJSONString(pageBean4));

        Person person1 = new Person();
        person1.setStatus(1);
        DataResult<PageBean<Person>> dataResult5 = personServiceFeign.findByCondition(1, 2, person1);
        PageBean<Person> pageBean5 = dataResult5.getData();
        log.info("pageBean5:{}", JSONObject.toJSONString(pageBean5));

        Person person2 = new Person();
        person2.setName("张");
        person2.setStatus(1);
        DataResult<PageBean<Person>> dataResult6 = personServiceFeign.findByCondition(1, 2, person2);
        PageBean<Person> pageBean6 = dataResult6.getData();
        log.info("pageBean6:{}", JSONObject.toJSONString(pageBean6));
    }

    @Test
    public void test09() {
        Person person = new Person();
        person.setName("张");
        DataResult<PageBean<Person>> dataResult4 = personServiceFeign.findByCondition(1, 2, person);
        log.info("{}", JSONObject.toJSONString(dataResult4.getData()));
    }

    @Test
    public void test08() {
        DataResult<List<Person>> dataResult1 = personServiceFeign.findAllByNameLike("o");
        List<Person> personList1 = dataResult1.getData();
        personList1.forEach(System.out::println);

        DataResult<List<Person>> dataResult2 = personServiceFeign.findAllByNameLike("j");
        List<Person> personList2 = dataResult2.getData();
        personList2.forEach(System.out::println);
    }


}

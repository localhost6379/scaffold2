package cn.king.controller;

import cn.king.base.BaseController;
import cn.king.domain.entity.Person;
import cn.king.service.PersonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController extends BaseController<Person,Long> {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public PersonService getService() {
        return this.personService;
    }

}

package com.springapp.mvc.controller;

import com.springapp.mvc.entity.Person;
import com.springapp.mvc.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by LANGKHACH on 21/05/2014.
 */
@Controller
@RequestMapping(value = "person")
@SessionAttributes("person")
class PersonController {

    @Resource
    private PersonService personService;

    @RequestMapping( value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public Person findPersonById(@RequestParam(value = "id", required = true) Long id)
            throws Exception {
        return personService.findById(id);
    }

    @RequestMapping( value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Person> findAll()
            throws Exception {
        return personService.findAll();
    }

}

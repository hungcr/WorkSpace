package com.springapp.mvc.service;

import com.springapp.mvc.entity.Person;

import java.util.List;

/**
 * Created by LANGKHACH on 21/05/2014.
 */
public interface PersonService {
    public Person create(Person createdPerson);
    public Person delete(Long PersonId) throws PersonNotFoundException;
    public List<Person> findAll();
    public Person findById(Long PersonId);
    public Person update(Person updatedPerson) throws PersonNotFoundException;
}

package com.springapp.mvc.service;

import com.springapp.mvc.entity.Person;
import com.springapp.mvc.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by LANGKHACH on 21/05/2014.
 */
@Service
public class RepositoryPersonService implements PersonService{
    @Resource
    private PersonRepository personRepository;

    @Transactional
    @Override
    public Person create(Person createdPerson) {
        return personRepository.save(createdPerson);
    }

    @Transactional(rollbackFor = PersonNotFoundException.class)
    @Override
    public Person delete(Long PersonId) throws PersonNotFoundException{
        Person deletedPerson = personRepository.findOne(PersonId);
        if(deletedPerson == null){
            throw new PersonNotFoundException();
        }
        personRepository.delete(deletedPerson);
        return deletedPerson;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Person findById(Long PersonId) {
        return personRepository.findOne(PersonId);
    }

    @Transactional(rollbackFor = PersonNotFoundException.class)
    @Override
    public Person update(Person updatedPerson) throws PersonNotFoundException{
        Person newPerson = personRepository.findOne(updatedPerson.getId());
        if(newPerson == null){
            throw new PersonNotFoundException();
        }
        newPerson.setId(updatedPerson.getId());
        newPerson.setName(updatedPerson.getName());
        return newPerson;
    }
}

package com.springapp.mvc.repository;

import com.springapp.mvc.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by LANGKHACH on 21/05/2014.
 */

public interface PersonRepository extends JpaRepository<Person,Long> {
}

package com.mahdi.model.service;

import com.mahdi.model.entity.Person;
import com.mahdi.model.repository.PersonRepository;

import java.util.List;

public class PersonService {
    private PersonService() {}
    private static PersonService personService = new PersonService();
    public static PersonService getInstance(){
        return personService;
    }

    public String save(Person person) throws Exception {
        try(PersonRepository personRepository = new PersonRepository())
        {
            personRepository.insert(person);
            return personRepository.selectAll();
        }
    }

    public String findAll() throws Exception {
        try(PersonRepository personRepository = new PersonRepository())
        {
            return personRepository.selectAll();
        }
    }

    public String test(Person person, int x, int y) throws Exception {
        try(PersonRepository personRepository = new PersonRepository())
        {
            personRepository.insert(person);
           System.exit(0);
            personRepository.insert(new Person("11111", "11111", 0));
            return personRepository.selectAll();
        }
    }
}

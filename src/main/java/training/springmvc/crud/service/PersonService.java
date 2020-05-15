/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package training.springmvc.crud.service;

import training.springmvc.crud.model.Person;

import java.util.List;

/**
 * @author henri
 */
public interface PersonService {

    List<Person> getPersonList();

    Person getPerson(Long id);

    void savePerson(Person person);

    void deletePerson(Long id);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.springmvc.crud.dao;

import training.springmvc.crud.model.Person;

import java.util.List;

/**
 * @author henri
 */
public interface PersonDAO {

    List getPersonList();

    Person getPerson(Long id);

    void savePerson(Person person);

    void deletePerson(Long id);
}

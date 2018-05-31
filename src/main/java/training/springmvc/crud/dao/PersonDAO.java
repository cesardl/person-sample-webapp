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

    public List getPersonList();

    public Person getPerson(Long id);

    public void savePerson(Person person);

    public void deletePerson(Long id);
}

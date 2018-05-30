/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package training.springmvc.crud.service;

import java.util.List;
import training.springmvc.crud.model.Person;

/**
 *
 * @author henri
 */
public interface PersonService {

    List getPersonList();

    Person getPerson(Long id);

    void savePerson(Person person);

    void deletePerson(Long id);
}

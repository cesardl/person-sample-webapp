/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.springmvc.crud.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import training.springmvc.crud.model.Person;

import java.util.List;

/**
 * @author henri
 */
public class PersonDAOHib {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Person> getPersonList() {
        return sessionFactory.getCurrentSession().createSelectionQuery("from Person", Person.class)
                .getResultList();
    }

    public Person getPerson(Long id) {
        return sessionFactory.getCurrentSession().find(Person.class, id);
    }

    public void savePerson(Person person) {
        // merge() returns a separate managed instance and does not write the
        // generated id back onto `person` the way saveOrUpdate() used to --
        // copy it back so callers that redisplay `person` after an insert
        // (SavePersonController) see the real id instead of null.
        Person managed = this.sessionFactory.getCurrentSession().merge(person);
        person.setId(managed.getId());
    }

    public void deletePerson(Long id) {
        sessionFactory.getCurrentSession().remove(getPerson(id));
    }
}

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
public class PersonDAOHib implements PersonDAO {

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
        return sessionFactory.getCurrentSession().get(Person.class, id);
    }

    public void savePerson(Person person) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(person);
    }

    public void deletePerson(Long id) {
        sessionFactory.getCurrentSession().detach(getPerson(id));
    }
}

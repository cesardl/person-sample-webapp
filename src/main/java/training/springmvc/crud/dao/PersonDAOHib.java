/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.springmvc.crud.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import training.springmvc.crud.model.Person;

import java.util.List;

/**
 * @author henri
 */
public class PersonDAOHib extends HibernateDaoSupport implements PersonDAO {

    public List getPersonList() {
        return getHibernateTemplate().find("from Person");
    }

    public Person getPerson(Long id) {
        return getHibernateTemplate().get(Person.class, id);
    }

    public void savePerson(Person person) {
        this.getHibernateTemplate().saveOrUpdate(person);
    }

    public void deletePerson(Long id) {
        this.getHibernateTemplate().delete(getPerson(id));
    }
}

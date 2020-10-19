/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.springmvc.crud.model;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @author henri
 */
public class Person implements Serializable {

    private static final long serialVersionUID = -5058945734356777328L;

    private Long id;
    private String name;
    private Character gender;
    private Date dob;

    public Person() {
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setDob_(String dob_) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = simpleDateFormat.parse(dob_);
        this.dob = new Date(date.getTime());
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id = " + id + "\nname = " + name;
    }
}

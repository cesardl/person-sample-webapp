/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.springmvc.crud.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author henri
 */
@Entity
@Table(name = "tbl_person")
public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = -5058945734356777328L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "gender")
    private Character gender;
    @Column(name = "dob")
    private Date dob;

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setDobStr(String dob_) throws ParseException {
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

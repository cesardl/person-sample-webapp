/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package training.springmvc.crud.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import training.springmvc.crud.model.Person;
import training.springmvc.crud.service.PersonService;

import java.util.List;

/**
 * @author henri
 */
@Controller
public class DeletePersonController {

    private PersonService personService;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("delete.html")
    @SuppressWarnings("unchecked")
    public String handleRequest(@RequestParam("personId") Long personId, HttpSession session, Model model) {
        personService.deletePerson(personId);

        PagedListHolder<Person> pagedListHolder = (PagedListHolder<Person>) session.getAttribute("personList");

        List<Person> personList = pagedListHolder.getSource();
        for (Person person : personList) {
            if (person.getId().equals(personId)) {
                personList.remove(person);
                break;
            }
        }
        pagedListHolder.setSource(personList);

        session.setAttribute("personList", pagedListHolder);
        model.addAttribute("personList", pagedListHolder);
        return "index";
    }

}

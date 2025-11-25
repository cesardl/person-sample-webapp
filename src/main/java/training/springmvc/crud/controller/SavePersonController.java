/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package training.springmvc.crud.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import training.springmvc.crud.model.Person;
import training.springmvc.crud.service.PersonService;

/**
 * @author henri
 */
@Controller
public class SavePersonController {

    private PersonService personService;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("save.html")
    public String handleRequest(@ModelAttribute Person person, HttpSession session, Model model) {
        personService.savePerson(person);

        session.setAttribute("personList", null);

        model.addAttribute("title", "Update Person");
        model.addAttribute("person", person);
        return "personEdit";
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package training.springmvc.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import training.springmvc.crud.model.Person;
import training.springmvc.crud.service.PersonService;

/**
 * @author henri
 */
@Controller
public class ViewPersonController {

    private PersonService personService;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("view.html")
    public String handleRequest(@RequestParam("personId") Long personId, Model model) {
        Person person = personService.getPerson(personId);

        model.addAttribute("title", "Person Details");
        model.addAttribute("person", person);
        return "personView";
    }

}

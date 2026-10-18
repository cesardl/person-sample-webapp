/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package training.springmvc.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import training.springmvc.crud.service.PersonService;

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
    public String handleRequest(@RequestParam("personId") Long personId) {
        personService.deletePerson(personId);
        return "redirect:index.html";
    }

}

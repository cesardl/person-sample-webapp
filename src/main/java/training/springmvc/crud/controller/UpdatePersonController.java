/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package training.springmvc.crud.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import training.springmvc.crud.model.Person;
import training.springmvc.crud.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author henri
 */
public class UpdatePersonController implements Controller {

    private PersonService personService;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<>();

        Long id = Long.parseLong(request.getParameter("personId"));
        Person person = personService.getPerson(id);

        model.put("title", "Update Person");
        model.put("person", person);
        return new ModelAndView("personEdit", model);
    }

}

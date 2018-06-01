/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package training.springmvc.crud.controller;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import training.springmvc.crud.model.Person;
import training.springmvc.crud.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author henri
 */
public class DeletePersonController implements Controller {

    private PersonService personService;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map<String, PagedListHolder> model = new HashMap<>();

        Long id = Long.parseLong(request.getParameter("personId"));
        personService.deletePerson(id);

        PagedListHolder pagedListHolder = (PagedListHolder) request.getSession().getAttribute("personList");

        List personList = pagedListHolder.getSource();
        for (Object aPersonList : personList) {
            Person person = (Person) aPersonList;
            if (person.getId().equals(id)) {
                personList.remove(person);
                break;
            }
        }
        pagedListHolder.setSource(personList);

        request.getSession().setAttribute("personList", pagedListHolder);
        model.put("personList", pagedListHolder);
        return new ModelAndView("index", model);
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package training.springmvc.crud.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import training.springmvc.crud.model.Person;
import training.springmvc.crud.service.PersonService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author henri
 */
public class IndexController implements Controller {

    private PersonService personService;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map<String, PagedListHolder<Person>> model = new HashMap<>();
        PagedListHolder<Person> pagedListHolder = (PagedListHolder) request.getSession().getAttribute("personList");

        if (pagedListHolder == null) {
            pagedListHolder = new PagedListHolder<>(personService.getPersonList());
        } else {
            String page = request.getParameter("page");
            if ("next".equals(page)) {
                pagedListHolder.nextPage();
            } else if ("previous".equals(page)) {
                pagedListHolder.previousPage();
            }
        }

        request.getSession().setAttribute("personList", pagedListHolder);
        model.put("personList", pagedListHolder);

        return new ModelAndView("index", model);
    }
}

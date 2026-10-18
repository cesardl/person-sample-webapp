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

/**
 * @author henri
 */
@Controller
public class IndexController {

    private PersonService personService;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("index.html")
    public String handleRequest(@RequestParam(value = "page", required = false) String page, HttpSession session, Model model) {
        PagedListHolder<Person> pagedListHolder = new PagedListHolder<>(personService.getPersonList());

        Integer currentPage = (Integer) session.getAttribute("personListPage");
        if (currentPage != null) {
            pagedListHolder.setPage(currentPage);
        }
        if ("next".equals(page)) {
            pagedListHolder.nextPage();
        } else if ("previous".equals(page)) {
            pagedListHolder.previousPage();
        }

        session.setAttribute("personListPage", pagedListHolder.getPage());
        model.addAttribute("personList", pagedListHolder);

        return "index";
    }
}

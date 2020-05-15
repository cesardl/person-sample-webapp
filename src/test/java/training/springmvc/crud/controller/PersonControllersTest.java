package training.springmvc.crud.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import training.springmvc.crud.model.Person;

import static org.hamcrest.CoreMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created on 30/05/2018.
 *
 * @author Cesardl
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"
})
public class PersonControllersTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void indexTest() throws Exception {
        this.mockMvc.perform(get("/index.html"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"))
                .andExpect(model().attribute("personList", any(PagedListHolder.class)))
                .andExpect(view().name("index"));
    }

    @Test
    public void viewTest() throws Exception {
        this.mockMvc.perform(get("/view.html").param("personId", "1"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/personView.jsp"))
                .andExpect(model().attribute("title", "Person Details"))
                .andExpect(model().attribute("person", any(Person.class)))
                .andExpect(view().name("personView"));
    }

    @Test
    public void updateTest() throws Exception {
        this.mockMvc.perform(get("/update.html").param("personId", "1"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/personEdit.jsp"))
                .andExpect(model().attribute("title", "Update Person"))
                .andExpect(model().attribute("person", any(Person.class)))
                .andExpect(view().name("personEdit"));
    }
}

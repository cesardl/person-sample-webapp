package training.springmvc.crud.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.web.context.WebApplicationContext;
import training.springmvc.crud.model.Person;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 30/05/2018.
 *
 * @author Cesardl
 */
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"
})
public class PersonControllersTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvcTester mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcTester.from(this.webApplicationContext);
    }

    @Test
    public void indexTest() {
        assertThat(this.mockMvc.get().uri("/index.html"))
                .hasStatusOk()
                .hasForwardedUrl("/WEB-INF/jsp/index.jsp")
                .hasViewName("index")
                .model().extractingByKey("personList").isInstanceOf(PersonPage.class);
    }

    @Test
    public void viewTest() {
        assertThat(this.mockMvc.get().uri("/view.html").param("personId", "1"))
                .hasStatusOk()
                .hasForwardedUrl("/WEB-INF/jsp/personView.jsp")
                .hasViewName("personView")
                .model()
                .containsEntry("title", "Person Details")
                .extractingByKey("person").isInstanceOf(Person.class);
    }

    @Test
    public void updateTest() {
        assertThat(this.mockMvc.get().uri("/update.html").param("personId", "1"))
                .hasStatusOk()
                .hasForwardedUrl("/WEB-INF/jsp/personEdit.jsp")
                .hasViewName("personEdit")
                .model()
                .containsEntry("title", "Update Person")
                .extractingByKey("person").isInstanceOf(Person.class);
    }
}

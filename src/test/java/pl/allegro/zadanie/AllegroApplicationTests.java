package pl.allegro.zadanie;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import pl.allegro.zadanie.rest.AppRestController;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AllegroApplicationTests.class)
public class AllegroApplicationTests {


    @Autowired
    WebApplicationContext webCtx;


    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
       mockMvc = MockMvcBuilders.webAppContextSetup(webCtx).build();
    }


    //to nie podnosi tomcata
    @Test
    public void contextLoads() throws Exception {
//        mockMvc.perform(get("/")).andExpect(status().isOK());
        mockMvc.perform(get("/")).andExpect(status().is(403));
    }

    //to podnosi tomcata
    @Test
    public void test2(){
        HttpStatus status = new RestTemplate().getForEntity("http://localhost:8090/repositories/matius1/allegro", AllegroApplication.class).getStatusCode();
        assertTrue(status.is2xxSuccessful());

    }

}

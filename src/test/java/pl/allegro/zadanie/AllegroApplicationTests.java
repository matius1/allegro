package pl.allegro.zadanie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import pl.allegro.zadanie.model.RepositoryInstance;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mateusz Skocz
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AllegroApplicationTests {

    @LocalServerPort
    private int port;
    private static String url;

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private static HttpEntity<String> entity;

    private static String repository1Json;
    private static String repository2Json;

    @BeforeClass
    public static void setUp() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        entity = new HttpEntity<>(null, new HttpHeaders());

        RepositoryInstance repository1 = new RepositoryInstance("allegro/php-protobuf",
                "PHP Protobuf - Google's Protocol Buffers for PHP",
                "https://github.com/allegro/php-protobuf.git",
                560,
                "2013-03-13T12:22:49");


        RepositoryInstance repository2 = new RepositoryInstance("allegro/allegro.github.io",
                "Blog sources",
                "https://github.com/allegro/allegro.github.io.git",
                12,
                "2014-09-02T11:26:50");

        repository1Json = mapper.writeValueAsString(repository1);
        repository2Json = mapper.writeValueAsString(repository2);
    }

    @Before
    public void setUpUrl() throws Exception {
        url = "http://localhost:" + port + "/";
    }

    @Test
    public void checkStatusCode_noParametersTest() throws Exception {

        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void checkStatusCode_oneParameterTest() throws Exception {

        ResponseEntity<String> response = restTemplate.exchange(url + "/repositories",
                HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void checkStatusCode_twoParametersTest() throws Exception {

        ResponseEntity<String> response = restTemplate.exchange(url + "/repositories/owner",
                HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void checkStatusCode_threeParametersTest() throws Exception {

        ResponseEntity<String> response = restTemplate.exchange(url + "/repositories/owner/repositoryName",
                HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void checkStatusCode_fourParametersTest() throws Exception {

        ResponseEntity<String> response = restTemplate.exchange(url + "/repositories/owner/repName/someString",
                HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void checkJSON_correctDataTest() throws Exception {

        ResponseEntity<String> response = restTemplate.exchange(url + "/repositories/allegro/php-protobuf",
                HttpMethod.GET, entity, String.class);

        JSONAssert.assertEquals(repository1Json, response.getBody(), false);
    }

    @Test
    public void checkParamWithDotsTest() throws Exception {

        ResponseEntity<String> response = restTemplate.exchange(url + "/repositories/allegro/allegro.github.io",
                HttpMethod.GET, entity, String.class);

        JSONAssert.assertEquals(repository2Json, response.getBody(), false);
    }

    @Test
    public void checkJSON_incorrectDataTest() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(url + "/repositories/allegro/errorBadRepoName",
                HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        RepositoryInstance tempRepo = mapper.readValue(response.getBody(), RepositoryInstance.class);

        assertEquals(null, tempRepo.getFullName());
        assertEquals(null, tempRepo.getDescription());
        assertEquals(null, tempRepo.getCloneUrl());
        assertEquals(0, tempRepo.getStars());
        assertEquals(null, tempRepo.getCloneUrl());
    }

}

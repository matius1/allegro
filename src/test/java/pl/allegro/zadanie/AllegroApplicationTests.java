package pl.allegro.zadanie;

import org.junit.*;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.allegro.zadanie.model.RepositoryInstance;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mateusz Skocz
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AllegroApplicationTests {

    private String url = "http://localhost:8090";
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private static HttpEntity<String> entity;
    private static RepositoryInstance repository1;
    private static RepositoryInstance repository2;
    private static RepositoryInstance emptyRepository;

    @BeforeClass
    public static void setUp() {
        entity = new HttpEntity<>(null, new HttpHeaders());

        repository1 = new RepositoryInstance("allegro/php-protobuf",
                "PHP Protobuf - Google's Protocol Buffers for PHP",
                "https://github.com/allegro/php-protobuf.git",
                560,
                "2013-03-13T12:22:49");

        repository2 = new RepositoryInstance("allegro/allegro.github.io",
                "Blog sources",
                "https://github.com/allegro/allegro.github.io.git",
                12,
                "2014-09-02T11:26:50");

        emptyRepository = new RepositoryInstance();
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

        JSONAssert.assertEquals(repository1.toJSON(), response.getBody(), false);
    }

    @Test
    public void checkParamWithDotsTest() throws Exception {

        ResponseEntity<String> response = restTemplate.exchange(url + "/repositories/allegro/allegro.github.io",
                HttpMethod.GET, entity, String.class);

        JSONAssert.assertEquals(repository2.toJSON(), response.getBody(), false);
    }

    @Test
    public void checkJSON_incorrectDataTest() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(url + "/repositories/allegro/errorBadRepoName",
                HttpMethod.GET, entity, String.class);

        JSONAssert.assertEquals(emptyRepository.toJSON(), response.getBody(), false);
    }


}

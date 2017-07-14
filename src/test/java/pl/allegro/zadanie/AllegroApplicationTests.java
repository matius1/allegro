//package pl.allegro.zadanie;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.*;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AllegroApplicationTests.class)
//public class AllegroApplicationTests {
//
//
//    //parsowanie informacji json z obiektu i odwrotnie
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//
//    private static final RestTemplate restTemplate = new RestTemplate();
//
//    @BeforeClass
//    public static void setUpBefore() throws Exception {
//        //raz przed wszystkimi testami
//    }
//
//    @AfterClass
//    public static void afterTest() throws Exception {
//        //po wykonaniu testow
//    }
//
//    @Before
//    public void setUp() throws Exception {
//        //przed kazdym testem
//    }
//
//    @After
//    public void after() throws Exception {
//        //po kazdym tescie
//    }
//
//    @Test
//    public void getEmptyRepo(){
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("fullName", "Tytul");
//        requestBody.put("description", "Tresc");
//        requestBody.put("cloneUrl", "11.11.11");
//        requestBody.put("stars", "1");
//        requestBody.put("createdAt", "11123");
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//        System.out.println("---------\n\n\n-------");
//
//        try{
//            HttpEntity<String> httpEntity = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);
//            System.out.println(httpEntity.toString());
//
//            Map<String, Object> apiResponse =
//                    restTemplate.getForEntity("http://localhost:8090/repositories/matius1/allegro", httpEntity, Map.class, Collections.emptyMap());
//
//            System.out.println(apiResponse.toString());
//            assertNotNull(apiResponse);
//
//            String message = apiResponse.get("message").toString();
//            assertEquals("News created successfully", message);
//
//            String newsId = ((Map<String, Object>) apiResponse.get("news")).get("id").toString();
//            assertNotNull(newsId);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//    }
//
//    @Test
//    public void contextLoads() {
//    }
//
//}

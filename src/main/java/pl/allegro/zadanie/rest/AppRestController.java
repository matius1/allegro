package pl.allegro.zadanie.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.allegro.zadanie.model.RepositoryInstance;
import pl.allegro.zadanie.service.RepositoryServiceI;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Mateusz Skocz on 13.07.2017.
 */
@RestController
@RequestMapping("/repositories")
public class AppRestController {

    @Autowired
    private RepositoryServiceI repositoryService;

    @Autowired
    public AppRestController(RepositoryServiceI repositoryService) {
        this.repositoryService = repositoryService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    @ResponseBody
    public String getNoParameters() {
        return "Prosze podac nazwe uzytkownika i jego repozytorium w adresie URL: /repositories/{owner}/{repository-name}";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{owner}")
    @ResponseBody
    public String getNoParameters(@PathVariable String owner) {
        return "Prosze podac nazwe repozytorium w adresie URL: /repositories/{owner}/{repository-name}";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{owner}/{repositoryName}")
    @ResponseBody
    public List<RepositoryInstance> getRepositoryDetails(HttpServletResponse response, @PathVariable String owner, @PathVariable String repositoryName) {
        List<RepositoryInstance> list = repositoryService.getRepoDetails(owner, repositoryName);

        if(list == null) try {
            response.sendRedirect("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

}

package pl.allegro.zadanie.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.allegro.zadanie.model.RepositoryInstance;
import pl.allegro.zadanie.service.RepositoryServiceI;

import java.util.TimeZone;

/**
 * Created by Mateusz Skocz on 13.07.2017.
 */
@RestController
@RequestMapping("")
public class AppRestController {

    @Autowired
    private RepositoryServiceI repositoryService;

    @Autowired
    public AppRestController(RepositoryServiceI repositoryService) {
        this.repositoryService = repositoryService;
    }

    @RequestMapping(value = {"", "/repositories/"}, method = {RequestMethod.POST, RequestMethod.PUT})
    public RepositoryInstance getNoParameters() {
        return new RepositoryInstance();
    }

    @GetMapping(value = "/repositories/{owner}/{repositoryName:.+}")
    public RepositoryInstance getRepositoryDetails(@PathVariable String owner, @PathVariable String repositoryName, TimeZone timezone) {
        return repositoryService.getRepoDetails(owner, repositoryName, timezone);
    }

}

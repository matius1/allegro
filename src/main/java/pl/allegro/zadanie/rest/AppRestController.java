package pl.allegro.zadanie.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.allegro.zadanie.model.RepositoryInstance;
import pl.allegro.zadanie.service.RepositoryServiceI;

import java.util.TimeZone;

/**
 * Created by Mateusz Skocz
 */
@RestController
@RequestMapping("/repositories")
@EnableWebMvc
public class AppRestController {

    @Autowired
    private RepositoryServiceI repositoryService;

    @Autowired
    public AppRestController(RepositoryServiceI repositoryService) {
        this.repositoryService = repositoryService;
    }

    @GetMapping(value = "/{owner:.+}/{repositoryName:.+}")
    public RepositoryInstance getRepositoryDetails(
            @PathVariable String owner,
            @PathVariable String repositoryName,
            TimeZone timezone) {
        return repositoryService.getRepoDetails(owner, repositoryName, timezone);
    }

}

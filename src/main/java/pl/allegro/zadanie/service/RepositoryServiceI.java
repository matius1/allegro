package pl.allegro.zadanie.service;

import pl.allegro.zadanie.model.RepositoryInstance;

import java.util.List;
import java.util.TimeZone;

/**
 * Created by Mateusz Skocz on 13.07.2017.
 */
public interface RepositoryServiceI {
    RepositoryInstance getRepoDetails(String owner, String repositoryName, TimeZone timezone);
}

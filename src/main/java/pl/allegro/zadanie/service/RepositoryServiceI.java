package pl.allegro.zadanie.service;

import pl.allegro.zadanie.model.RepositoryInstance;

import java.util.TimeZone;

/**
 * Created by Mateusz Skocz
 */
public interface RepositoryServiceI {
    RepositoryInstance getRepoDetails(String owner, String repositoryName, TimeZone timezone);
}

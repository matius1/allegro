package pl.allegro.zadanie.service;

import pl.allegro.zadanie.model.RepositoryInstance;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Created by Mateusz Skocz
 */
@Service
public class GitService implements RepositoryServiceI {

    @Override
    public RepositoryInstance getRepoDetails(String owner, String repositoryName, TimeZone clientTimezone) {

        ObjectMapper mapper = new ObjectMapper();
        RepositoryInstance repo = new RepositoryInstance();
        try {
            URL url = new URL("https://api.github.com/repos/" + owner + "/" + repositoryName);
            JsonNode json = mapper.readTree(url);

            if (json.findPath("name").toString().replaceAll("\"", "").equals(repositoryName))
                repo = new RepositoryInstance(
                        json.findPath("full_name").toString(),
                        json.findPath("description").toString(),
                        json.findPath("clone_url").toString(),
                        json.findPath("stargazers_count").intValue(),
                        json.findPath("created_at").toString()
                );

            repo.setCreatedAt(changeTimeZone(repo.getCreatedAt(), clientTimezone));

        } catch (IOException e) {
            System.err.println("Nie znaleziono repozytorium '" + repositoryName + "' u uzytkownika '" + owner + "'.");
        }

        return repo;
    }

    private String changeTimeZone(String date, TimeZone timezone) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime localDateTime = ZonedDateTime
                .parse(date, formatter)
                .withZoneSameInstant(timezone.toZoneId())
                .toLocalDateTime();
        return localDateTime.toString();
    }

}

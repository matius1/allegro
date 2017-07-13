package pl.allegro.zadanie.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.allegro.zadanie.model.RepositoryInstance;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Skocz on 13.07.2017.
 */
@Service
public class GitService implements RepositoryServiceI {

    @Override
    public List<RepositoryInstance> getRepoDetails(String owner, String repositoryName) {

        List<RepositoryInstance> repoList = null;
        ObjectMapper mapper = new ObjectMapper();
        URL url = null;
        JsonNode rootNode = null;

        try {
            url = new URL("https://api.github.com/users/" + owner + "/repos");
        } catch (MalformedURLException e) {
            System.err.println("Nie znaleziono uzytkownika " + owner + "na Github");
//            e.printStackTrace();
        }

        if (url != null) {
            try {
                rootNode = mapper.readTree(url);
            } catch (IOException e) {
                System.err.println("Blad przy pobieraniu JSON'a dla uzytkownika " + owner);
//                e.printStackTrace();
            }
        }

        if (rootNode != null) {
            repoList = new ArrayList<>();

            for (int i = 0; i < rootNode.size(); i++) {
                if (rootNode.get(i).findPath("name")
                        .toString()
                        .replaceAll("\"", "")
                        .equals(repositoryName)) {
                    RepositoryInstance repo = new RepositoryInstance(
                            rootNode.get(i).findPath("full_name").toString(),
                            rootNode.get(i).findPath("description").toString(),
                            rootNode.get(i).findPath("clone_url").toString(),
                            rootNode.get(i).findPath("stargazers_count").intValue(),
                            rootNode.get(i).findPath("created_at").toString()
                    );
                    repoList.add(repo);
                }
            }
        }

        return repoList;
    }


}

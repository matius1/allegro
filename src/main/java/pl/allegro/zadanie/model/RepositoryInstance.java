package pl.allegro.zadanie.model;

/**
 * Created by Mateusz Skocz
 */
public class RepositoryInstance {

    private String fullName;
    private String description;
    private String cloneUrl;
    private int stars;
    private String createdAt;

    public RepositoryInstance() {
    }

    public RepositoryInstance(String fullName, String description, String cloneUrl, int stars, String createdAt) {
        this.fullName = fullName.replaceAll("\"", "");
        this.description = description.replaceAll("\"", "");
        this.cloneUrl = cloneUrl.replaceAll("\"", "");
        this.stars = stars;
        this.createdAt = createdAt.replaceAll("\"", "");
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "RepositoryInstance{" +
                "fullName='" + fullName + '\'' +
                ", description='" + description + '\'' +
                ", cloneUrl='" + cloneUrl + '\'' +
                ", stars='" + stars + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}

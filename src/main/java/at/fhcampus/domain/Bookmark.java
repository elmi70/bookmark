package at.fhcampus.domain;

import java.time.LocalDateTime;
import java.util.*;

public class Bookmark {
    private String url;
    private List<String> tags = new ArrayList<>();
    private int rating = 1;


    public Bookmark(String url) {
        this.url = url;
    }

    public Bookmark(String url, String tag) {
        this.url = url;
        this.tags.add(tag);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if(url != null){
            this.url = url;
        }
    }

    public void addTag(String tag){
        this.tags.add(tag);
    }

    public void deleteTag(String tag){
        this.tags.remove(tag);
    }

    public List<String> getTags() {
        return new ArrayList<>(tags);
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookmark bookmark = (Bookmark) o;
        return rating == bookmark.rating &&
                Objects.equals(url, bookmark.url) &&
                Objects.equals(tags, bookmark.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, tags, rating);
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "url='" + url + '\'' +
                ", tags=" + tags +
                ", rating=" + rating +
                '}';
    }
}
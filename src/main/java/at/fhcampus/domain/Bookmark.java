package at.fhcampus.domain;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.*;

public class Bookmark {
    private String url;
    private List<String> tags = new ArrayList<>();
    private int rating = 1;
    private boolean secure;
    private Set<Bookmark> associates = new LinkedHashSet<>();// same domain


    public Bookmark(String url) {
        this.url = url;
        secure = url.startsWith("https");
    }

    public Bookmark(String url, String tag) {
        this(url);
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

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public void addAssociate(Bookmark bookmark){
        this.associates.add(bookmark);
    }

    public void deleteAssociate(Bookmark bookmark){
        this.associates.remove(bookmark);
    }

    public Set<Bookmark> getAssociates() {
        return new LinkedHashSet<>( associates);
    }

    public void setAssociates(Set<Bookmark> associates) {
        this.associates = associates;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookmark bookmark = (Bookmark) o;
        return rating == bookmark.rating &&
                secure == bookmark.secure &&
                Objects.equals(url, bookmark.url) &&
                Objects.equals(tags, bookmark.tags) &&
                Objects.equals(associates, bookmark.associates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, tags, rating, secure);
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "url='" + url + '\'' +
                ", tags=" + tags +
                ", rating=" + rating +
                ", secure=" + secure +
                '}';
    }
}

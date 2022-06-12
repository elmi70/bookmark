package at.fhcampus.domain;

import java.time.LocalDateTime;
import java.util.*;

public class Bookmark {
    private String url;

    public Bookmark() {
    }

    public Bookmark(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if(url != null){
            this.url = url;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookmark bookmark = (Bookmark) o;
        return Objects.equals(url, bookmark.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}

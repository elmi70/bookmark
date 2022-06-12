package at.fhcampus.domain;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookmarkManager {
    //list that holds all bookmarks
    private List<Bookmark> bookmarks = new ArrayList<>();

    public void addBookmark(String url) {
        if (url == null)
            throw new IllegalArgumentException("Url can't be null"); //to test
        if (validateURL(url)) {
            Bookmark bookmark = new Bookmark(url);
            bookmarks.add(bookmark);
        } else
            throw new IllegalArgumentException("URL is not valid!");
    }

    public List<Bookmark> getBookmarks() {
        return new ArrayList<>(bookmarks);
    }

    //check whether the url is valid
    private static boolean validateURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }
}

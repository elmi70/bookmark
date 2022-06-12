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
            for (Bookmark bookmark : bookmarks) {
                if (bookmark.getUrl().equalsIgnoreCase(url)) {
                    bookmark.setRating(bookmark.getRating() + 1);
                    return;
                }
            }
            Bookmark bookmark = new Bookmark(url);
            bookmarks.add(bookmark);
        } else
            throw new IllegalArgumentException("URL is not valid!");
    }

    public void addTagToBookmark(String url, String tag) {
        bookmarks.forEach(bookmark -> {
            if (bookmark.getUrl().equals(url)) {
                bookmark.addTag(tag);
            }
        });
    }

    public List<Bookmark> getBookmarks() {
        return new ArrayList<>(bookmarks);
    }

    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
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

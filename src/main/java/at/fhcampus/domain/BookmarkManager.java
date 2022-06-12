package at.fhcampus.domain;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
            addAssociates(bookmark);
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

    public int getNumbersOfSecureURL() {
        return (int) bookmarks.stream()
                .filter(Bookmark::isSecure)
                .count();
    }

    public List<Bookmark> filterByTag(String ... tags) {
        List<Bookmark> filteredResult = new ArrayList<>();
        for (Bookmark bookmark : bookmarks){
            for(String tag : bookmark.getTags()){
                if (Arrays.asList(tags).contains(tag)){
                    filteredResult.add(bookmark);
                    break;
                }
            }
        }
        return filteredResult;
    }

    public void removeTagFromBookmark(String url, String tag) {
        for (Bookmark bookmark : bookmarks) {
            if (bookmark.getUrl().equals(url)) {
                bookmark.deleteTag(tag);
            }
        }
    }

    public void removeBookmark(String url) {
        bookmarks.removeIf(currentBookmark -> currentBookmark.getUrl().equals(url));
    }

    private void addAssociates(Bookmark bookmark) {
        String bookmarkDomain = getDomainName(bookmark.getUrl()); //domain name von unserem Objekt
        if (bookmarkDomain != null)
            /*
            - www.test.com --> DN: test --> objekt --> weg aus dem stream
            - www.test.org --> DN: test --> erste element --> dieses element bleibt im stream
            - www.test.at   --> DN: test
            - www.test.uk  --> DN: test
            * */
            bookmarks.stream()
                    .filter(element -> Objects.requireNonNull(getDomainName(element.getUrl())).equals(bookmarkDomain)
                            && !bookmark.equals(element))
                    .forEach(element -> {
                        element.addAssociate(bookmark);
                        bookmark.addAssociate(element);
                    });
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

    private static String getDomainName(String url) {
        try {
            URI uri = new URI(url);
            //get domain name --> can be www.test or test
            String domain = uri.getHost();
            return domain.startsWith("www.") ? domain.substring(4) : domain;
        } catch (Exception e) {
            return null;
        }
    }
}

package at.fhcampus.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class BookmarkManagerTest {

    @Test
    public void ensureThatUserCanAddBookmark() {
        // Arrange
        BookmarkManager bookmarkManager = new BookmarkManager();
        List<Bookmark> expectedResult = new ArrayList<>();
        String url = "http://test.com/Test";
        expectedResult.add(new Bookmark(url));
        // Act
        bookmarkManager.addBookmark(url);
        List<Bookmark> actualResult = bookmarkManager.getBookmarks();
        // Assert
        assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureThatUserCantAddBookmarkWithNullURL() {
        // Arrange
        BookmarkManager bookmarkManager = new BookmarkManager();
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            bookmarkManager.addBookmark(null);
        });
    }

    @Test
    public void ensureThatUserCantAddBookmarkWithInValidURL() {
        // Arrange
        BookmarkManager bookmarkManager = new BookmarkManager();
        String url = "http://t est.com/Test";
        // Act
        assertThrows(IllegalArgumentException.class, () -> bookmarkManager.addBookmark(url));
    }


    @Test
    public void ensureThatUserCanAddOneTagToBookmark() {
        // Arrange
        BookmarkManager bookmarkManager = new BookmarkManager();
        String url = "http://test.com/Test";
        String tag = "test";
        List<Bookmark> bookmarkArrayList = bookmarkManager.getBookmarks();
        Bookmark bookmark = new Bookmark(url);
        bookmarkArrayList.add(bookmark);
        bookmarkManager.setBookmarks(bookmarkArrayList);

        List<Bookmark> expectedResult = new ArrayList<>();
        expectedResult.add(new Bookmark(url, tag));

        // Act
        bookmarkManager.addTagToBookmark(url, tag);
        List<Bookmark> actualResult = bookmarkManager.getBookmarks();

        // Assert
        assertIterableEquals(expectedResult, actualResult);
    }


    @Test
    public void ensureRatingIsIncreased() {
        // Arrange
        int expectedResult = 2;
        BookmarkManager bookmarkManager = new BookmarkManager();
        ArrayList<Bookmark> list = new ArrayList<>();
        String url = "http://test.com/Test";
        list.add(new Bookmark(url));
        bookmarkManager.setBookmarks(list);
        // Act
        bookmarkManager.addBookmark(url);
        int actualResult = bookmarkManager.getBookmarks().get(0).getRating();

        // Assert
        assertEquals(expectedResult, actualResult);

    }
}

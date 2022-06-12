package at.fhcampus.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
}

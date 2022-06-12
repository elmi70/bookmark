package at.fhcampus.domain;

import org.junit.jupiter.api.Test;

import java.util.*;

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

    @Test
    public void ensureSecureUrlsAreNoticed() {
        // Arrange
        BookmarkManager bookmarkManager = new BookmarkManager();
        String url1 = "http://test.com/Test1";
        String url2 = "https://test1.com/Test2";
        String url3 = "https://test1.com/Test3";
        String url4 = "https://test1.com/Test4";
        String url5 = "https://test1.com/Test5";
        bookmarkManager.addBookmark(url1);
        bookmarkManager.addBookmark(url2);
        bookmarkManager.addBookmark(url3);
        bookmarkManager.addBookmark(url4);
        bookmarkManager.addBookmark(url5);
        int expectedResult = 4;
        // Act
        int actualResult = bookmarkManager.getNumbersOfSecureURL();
        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureBookmarkHasAssociates() {
        // Arrange
        BookmarkManager bookmarkManager = new BookmarkManager();
        String url1 = "http://test.com/Test/java";
        String url2 = "https://test.com/Test/programming";
        String url3 = "https://test.com/Test";
        // Act
        bookmarkManager.addBookmark(url1);
        bookmarkManager.addBookmark(url2);
        bookmarkManager.addBookmark(url3);
        // Assert
        for(Bookmark bookmark : bookmarkManager.getBookmarks()){
            assertEquals(2, bookmark.getAssociates().size());
            bookmark.getAssociates().forEach(element -> assertNotEquals(bookmark, element));
        }
    }

    @Test
    public void ensureBookmarkHasNoAssociates() {
        // Arrange
        BookmarkManager bookmarkManager = new BookmarkManager();
        String url1 = "http://test1.com/Test/java";
        String url2 = "https://test2.com/Test/programming";
        String url3 = "https://test3.com/Test";
        // Act
        bookmarkManager.addBookmark(url1);
        bookmarkManager.addBookmark(url2);
        bookmarkManager.addBookmark(url3);
        // Assert
        for(Bookmark bookmark : bookmarkManager.getBookmarks()){
            System.out.println(bookmark);
            assertEquals(0, bookmark.getAssociates().size());
        }
    }
}

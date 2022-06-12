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

    @Test
    public void ensureFilteringByKeyword() {
        //Arrange
        BookmarkManager bookmarkManager = new BookmarkManager();
        String tag = "health";
        String url1 = "https://url1test.com";
        String url2 = "https://url2health.com";
        String url3 = "https://url3sport.com";
        String url4 = "https://url4java.com";
        String url5 = "https://url5prog.com";
        Bookmark bookmark1 = new Bookmark(url1, "test");
        Bookmark bookmark2 = new Bookmark(url2, "health");
        Bookmark bookmark3 = new Bookmark(url3, "sport");
        Bookmark bookmark4 = new Bookmark(url4, "java");
        Bookmark bookmark5 = new Bookmark(url5, "prog");
        List<Bookmark> bookmarks = Arrays.asList(bookmark1, bookmark2,
                bookmark3, bookmark4, bookmark5);
        bookmarkManager.setBookmarks(bookmarks);
        //Act
        List<Bookmark> actualResult = bookmarkManager.filterByTag(tag);

        //Assert
        assertEquals(bookmark2, actualResult.get(0));
    }

    @Test
    public void ensureFilteringByKeyword2() {
        //Arrange
        BookmarkManager bookmarkManager = new BookmarkManager();
        String tag = "health";
        String url1 = "https://url1test.com";
        String url2 = "https://url2health.com";
        String url3 = "https://url3sport.com";
        String url4 = "https://url4java.com";
        String url5 = "https://url5prog.com";
        Bookmark bookmark1 = new Bookmark(url1, "health");
        Bookmark bookmark2 = new Bookmark(url2, "health");
        Bookmark bookmark3 = new Bookmark(url3, "health");
        Bookmark bookmark4 = new Bookmark(url4, "java");
        Bookmark bookmark5 = new Bookmark(url5, "prog");
        List<Bookmark> bookmarks = Arrays.asList(bookmark1, bookmark2,
                bookmark3, bookmark4, bookmark5);
        bookmarkManager.setBookmarks(bookmarks);
        List<Bookmark> expectedResult = Arrays.asList(bookmark1, bookmark2,
                bookmark3);
        //Act
        List<Bookmark> actualResult = bookmarkManager.filterByTag(tag);
        //ignore order
        actualResult.sort(Comparator.comparing(Bookmark::getUrl));
        expectedResult.sort(Comparator.comparing(Bookmark::getUrl));

        //Assert
        assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureFilteringByMultipleKeywords() {
        //Arrange
        BookmarkManager bookmarkManager = new BookmarkManager();
        String url1 = "https://url1test.com";
        String url2 = "https://url2health.com";
        String url3 = "https://url3sport.com";
        String url4 = "https://url4java.com";
        String url5 = "https://url5prog.com";
        Bookmark bookmark1 = new Bookmark(url1, "test");
        Bookmark bookmark2 = new Bookmark(url2, "health");
        Bookmark bookmark3 = new Bookmark(url3, "sport");
        Bookmark bookmark4 = new Bookmark(url4, "java");
        Bookmark bookmark5 = new Bookmark(url5, "prog");
        List<Bookmark> bookmarks = Arrays.asList(bookmark1, bookmark2,
                bookmark3, bookmark4, bookmark5);
        bookmarkManager.setBookmarks(bookmarks);
        List<Bookmark> expectedResult = Arrays.asList(bookmark1, bookmark2,
                bookmark3);
        //Act
        List<Bookmark> actualResult = bookmarkManager
                .filterByTag("test", "health", "sport");
        //ignore order
        actualResult.sort(Comparator.comparing(Bookmark::getUrl));
        expectedResult.sort(Comparator.comparing(Bookmark::getUrl));

        //Assert
        assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureThatTakCanBeRemoved(){
        // Arrange
        BookmarkManager bookmarkManager = new BookmarkManager();
        String url = "http://test.com/Test";
        String tag = "test";
        List<Bookmark> bookmarkArrayList = bookmarkManager.getBookmarks();
        Bookmark bookmark = new Bookmark(url,tag);
        bookmark.addTag("sport");

        bookmarkArrayList.add(bookmark);
        bookmarkManager.setBookmarks(bookmarkArrayList);

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("sport");
        // Act
        bookmarkManager.removeTagFromBookmark(url,tag);
        List<String> actualResult = bookmarkManager.getBookmarks().get(0).getTags();
        // Assert
        assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureThatBookmarkCanBeRemoved(){
        // Arrange
        BookmarkManager bookmarkManager = new BookmarkManager();
        String url = "http://test.com/Test";
        List<Bookmark> bookmarkArrayList = bookmarkManager.getBookmarks();
        Bookmark bookmark = new Bookmark(url);
        bookmarkArrayList.add(bookmark);
        bookmarkManager.setBookmarks(bookmarkArrayList);
        List<Bookmark> expectedResult = new ArrayList<>();
        // Act
        bookmarkManager.removeBookmark(url);
        List<Bookmark> actualResult = bookmarkManager.getBookmarks();
        // Assert
        assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureThatBookmarkCanBeRemoved2(){
        // Arrange
        BookmarkManager bookmarkManager = new BookmarkManager();
        String url = "http://test.com/Test";
        String url2 = "http://TEST.com/TEST";
        List<Bookmark> bookmarkArrayList = bookmarkManager.getBookmarks();
        Bookmark bookmark = new Bookmark(url);
        Bookmark bookmark2 = new Bookmark(url2);
        bookmarkArrayList.add(bookmark);
        bookmarkArrayList.add(bookmark2);
        bookmarkManager.setBookmarks(bookmarkArrayList);

        List<Bookmark> expectedResult = new ArrayList<>();
        expectedResult.add(bookmark);
        // Act
        bookmarkManager.removeBookmark(url2);
        List<Bookmark> actualResult = bookmarkManager.getBookmarks();
        // Assert
        assertIterableEquals(expectedResult, actualResult);
    }
}

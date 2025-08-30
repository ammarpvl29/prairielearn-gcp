import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

public class BookTest {
    
    @Test
    @DisplayName("Test Book constructor and getTitle")
    @Tag("points=2")
    public void testBookConstructorAndGetTitle() {
        Book book = new Book("1984");
        assertEquals("1984", book.getTitle());
    }
    
    @Test
    @DisplayName("Test getAuthor method")
    @Tag("points=2")
    public void testGetAuthor() {
        Book book = new Book("Animal Farm");
        assertEquals("George Orwell", book.getAuthor());
    }
    
    @Test
    @DisplayName("Test getPages method")
    @Tag("points=2")
    public void testGetPages() {
        Book book = new Book("Nineteen Eighty-Four");
        assertEquals(328, book.getPages());
    }
    
    @Test
    @DisplayName("Test getDescription method")
    @Tag("points=4")
    public void testGetDescription() {
        Book book = new Book("1984");
        String expected = "Book: 1984 by George Orwell, 328 pages";
        assertEquals(expected, book.getDescription());
    }
}

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

public class ProductTest {
    
    @Test
    @DisplayName("Test Product constructor and getName")
    @Tag("points=2")
    public void testProductConstructorAndGetName() {
        Product product = new Product("Wireless Mouse");
        assertEquals("Wireless Mouse", product.getName());
    }
    
    @Test
    @DisplayName("Test getCategory method")
    @Tag("points=2")
    public void testGetCategory() {
        Product product = new Product("Keyboard");
        assertEquals("Electronics", product.getCategory());
    }
    
    @Test
    @DisplayName("Test getPrice method")
    @Tag("points=2")
    public void testGetPrice() {
        Product product = new Product("Monitor");
        assertEquals(45.99, product.getPrice(), 0.01);
    }
    
    @Test
    @DisplayName("Test getSummary method")
    @Tag("points=4")
    public void testGetSummary() {
        Product product = new Product("Wireless Mouse");
        String expected = "Product: Wireless Mouse (Electronics) - $45.99";
        assertEquals(expected, product.getSummary());
    }
}

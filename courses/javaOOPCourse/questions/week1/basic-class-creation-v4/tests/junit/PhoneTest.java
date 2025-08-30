import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

public class PhoneTest {
    
    @Test
    @DisplayName("Test Phone constructor and getModel")
    @Tag("points=2")
    public void testPhoneConstructorAndGetModel() {
        Phone phone = new Phone("iPhone 15");
        assertEquals("iPhone 15", phone.getModel());
    }
    
    @Test
    @DisplayName("Test getBrand method")
    @Tag("points=2")
    public void testGetBrand() {
        Phone phone = new Phone("iPhone 14");
        assertEquals("Apple", phone.getBrand());
    }
    
    @Test
    @DisplayName("Test getPrice method")
    @Tag("points=2")
    public void testGetPrice() {
        Phone phone = new Phone("iPhone 13");
        assertEquals(999.99, phone.getPrice(), 0.01);
    }
    
    @Test
    @DisplayName("Test getInfo method")
    @Tag("points=4")
    public void testGetInfo() {
        Phone phone = new Phone("iPhone 15");
        String expected = "Phone: Apple iPhone 15 - $999.99";
        assertEquals(expected, phone.getInfo());
    }
}

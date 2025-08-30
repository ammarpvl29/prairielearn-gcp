import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

public class CarTest {
    
    @Test
    @DisplayName("Test Car constructor and getModel")
    @Tag("points=2")
    public void testCarConstructorAndGetModel() {
        Car car = new Car("Camry");
        assertEquals("Camry", car.getModel());
    }
    
    @Test
    @DisplayName("Test getBrand method")
    @Tag("points=2")
    public void testGetBrand() {
        Car car = new Car("Corolla");
        assertEquals("Toyota", car.getBrand());
    }
    
    @Test
    @DisplayName("Test getYear method")
    @Tag("points=2")
    public void testGetYear() {
        Car car = new Car("Prius");
        assertEquals(2023, car.getYear());
    }
    
    @Test
    @DisplayName("Test getDetails method")
    @Tag("points=4")
    public void testGetDetails() {
        Car car = new Car("Camry");
        String expected = "Car: 2023 Toyota Camry";
        assertEquals(expected, car.getDetails());
    }
}

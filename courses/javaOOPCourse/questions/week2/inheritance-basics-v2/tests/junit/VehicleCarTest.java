import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

public class VehicleCarTest {
    
    @Test
    @DisplayName("Test Vehicle constructor and getBrand")
    @Tag("points=2")
    public void testVehicleConstructorAndGetBrand() {
        Vehicle vehicle = new Vehicle("Toyota", 2023);
        assertEquals("Toyota", vehicle.getBrand());
    }
    
    @Test
    @DisplayName("Test Vehicle getYear method")
    @Tag("points=2")
    public void testVehicleGetYear() {
        Vehicle vehicle = new Vehicle("Honda", 2022);
        assertEquals(2022, vehicle.getYear());
    }
    
    @Test
    @DisplayName("Test Car extends Vehicle")
    @Tag("points=2")
    public void testCarExtendsVehicle() {
        Car car = new Car("Toyota", 2023, 4);
        assertTrue(car instanceof Vehicle);
        assertEquals("Toyota", car.getBrand());
        assertEquals(2023, car.getYear());
    }
    
    @Test
    @DisplayName("Test Car getInfo method")
    @Tag("points=4")
    public void testCarGetInfo() {
        Car car = new Car("Toyota", 2023, 4);
        String expected = "2023 Toyota with 4 doors";
        assertEquals(expected, car.getInfo());
    }
}

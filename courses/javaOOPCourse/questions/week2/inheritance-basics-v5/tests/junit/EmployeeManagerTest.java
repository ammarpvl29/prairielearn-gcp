import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

public class EmployeeManagerTest {
    
    @Test
    @DisplayName("Test Employee constructor and getName")
    @Tag("points=2")
    public void testEmployeeConstructorAndGetName() {
        Employee employee = new Employee("Jane", 45000.0);
        assertEquals("Jane", employee.getName());
    }
    
    @Test
    @DisplayName("Test Employee calculatePay method")
    @Tag("points=2")
    public void testEmployeeCalculatePay() {
        Employee employee = new Employee("Bob", 50000.0);
        assertEquals(50000.0, employee.calculatePay(), 0.01);
    }
    
    @Test
    @DisplayName("Test Manager extends Employee")
    @Tag("points=2")
    public void testManagerExtendsEmployee() {
        Manager manager = new Manager("John", 50000.0, 10000.0);
        assertTrue(manager instanceof Employee);
        assertEquals("John", manager.getName());
    }
    
    @Test
    @DisplayName("Test Manager overridden calculatePay method")
    @Tag("points=4")
    public void testManagerOverriddenCalculatePay() {
        Manager manager = new Manager("John", 50000.0, 10000.0);
        assertEquals(60000.0, manager.calculatePay(), 0.01);
        
        Manager manager2 = new Manager("Sarah", 60000.0, 15000.0);
        assertEquals(75000.0, manager2.calculatePay(), 0.01);
    }
}

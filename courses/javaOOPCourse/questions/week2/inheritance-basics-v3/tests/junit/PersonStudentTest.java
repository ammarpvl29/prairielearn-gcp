import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

public class PersonStudentTest {
    
    @Test
    @DisplayName("Test Person constructor and introduce")
    @Tag("points=2")
    public void testPersonConstructorAndIntroduce() {
        Person person = new Person("John", 25);
        String expected = "Hi, I'm John and I'm 25 years old";
        assertEquals(expected, person.introduce());
    }
    
    @Test
    @DisplayName("Test Student extends Person")
    @Tag("points=2")
    public void testStudentExtendsPerson() {
        Student student = new Student("Alice", 20, "Computer Science");
        assertTrue(student instanceof Person);
    }
    
    @Test
    @DisplayName("Test Student constructor")
    @Tag("points=2")
    public void testStudentConstructor() {
        Student student = new Student("Alice", 20, "Computer Science");
        // Should be able to access inherited methods without errors
        assertNotNull(student);
    }
    
    @Test
    @DisplayName("Test Student overridden introduce method")
    @Tag("points=4")
    public void testStudentOverriddenIntroduce() {
        Student student = new Student("Alice", 20, "Computer Science");
        String expected = "Hi, I'm Alice, I'm 20 years old, and I study Computer Science";
        assertEquals(expected, student.introduce());
    }
}

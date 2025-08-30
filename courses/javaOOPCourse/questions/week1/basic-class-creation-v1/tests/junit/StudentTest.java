import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

public class StudentTest {
    
    @Test
    @DisplayName("Test Student constructor and getName")
    @Tag("points=2")
    public void testStudentConstructorAndGetName() {
        Student student = new Student("John Smith");
        assertEquals("John Smith", student.getName());
    }
    
    @Test
    @DisplayName("Test getStudentId method")
    @Tag("points=2")
    public void testGetStudentId() {
        Student student = new Student("Jane Doe");
        assertEquals(12345, student.getStudentId());
    }
    
    @Test
    @DisplayName("Test getGpa method")
    @Tag("points=2")
    public void testGetGpa() {
        Student student = new Student("Bob Johnson");
        assertEquals(3.85, student.getGpa(), 0.01);
    }
    
    @Test
    @DisplayName("Test getInfo method")
    @Tag("points=4")
    public void testGetInfo() {
        Student student = new Student("Alice Brown");
        String expected = "Student: Alice Brown, ID: 12345, GPA: 3.85";
        assertEquals(expected, student.getInfo());
    }
}
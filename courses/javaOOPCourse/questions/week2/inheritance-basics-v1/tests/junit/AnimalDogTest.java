import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

public class AnimalDogTest {
    
    @Test
    @DisplayName("Test Animal constructor and getName")
    @Tag("points=2")
    public void testAnimalConstructorAndGetName() {
        Animal animal = new Animal("Buddy");
        assertEquals("Buddy", animal.getName());
    }
    
    @Test
    @DisplayName("Test Dog extends Animal")
    @Tag("points=2")
    public void testDogExtendsAnimal() {
        Dog dog = new Dog("Max");
        assertTrue(dog instanceof Animal);
        assertEquals("Max", dog.getName());
    }
    
    @Test
    @DisplayName("Test Dog bark method")
    @Tag("points=3")
    public void testDogBark() {
        Dog dog = new Dog("Buddy");
        String expected = "Woof! My name is Buddy";
        assertEquals(expected, dog.bark());
    }
    
    @Test
    @DisplayName("Test main method output")
    @Tag("points=3")
    public void testMainMethodOutput() {
        Dog dog = new Dog("Buddy");
        assertEquals("Buddy", dog.getName());
        assertEquals("Woof! My name is Buddy", dog.bark());
    }
}

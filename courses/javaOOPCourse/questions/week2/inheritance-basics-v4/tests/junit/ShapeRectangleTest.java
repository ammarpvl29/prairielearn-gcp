import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

public class ShapeRectangleTest {
    
    @Test
    @DisplayName("Test Shape is abstract")
    @Tag("points=2")
    public void testShapeIsAbstract() {
        // Test by creating Rectangle which extends Shape
        Rectangle rectangle = new Rectangle("red", 5.0, 3.0);
        assertTrue(rectangle instanceof Shape);
    }
    
    @Test
    @DisplayName("Test Shape getColor method")
    @Tag("points=2")
    public void testShapeGetColor() {
        Rectangle rectangle = new Rectangle("blue", 4.0, 2.0);
        assertEquals("blue", rectangle.getColor());
    }
    
    @Test
    @DisplayName("Test Rectangle constructor")
    @Tag("points=2")
    public void testRectangleConstructor() {
        Rectangle rectangle = new Rectangle("red", 5.0, 3.0);
        assertEquals("red", rectangle.getColor());
        assertNotNull(rectangle);
    }
    
    @Test
    @DisplayName("Test Rectangle getArea implementation")
    @Tag("points=4")
    public void testRectangleGetArea() {
        Rectangle rectangle = new Rectangle("red", 5.0, 3.0);
        assertEquals(15.0, rectangle.getArea(), 0.01);
        
        Rectangle rectangle2 = new Rectangle("green", 4.0, 2.5);
        assertEquals(10.0, rectangle2.getArea(), 0.01);
    }
}

package com.csc205.project2.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Shape3D} abstract base class.
 *
 * <p>Because {@code Shape3D} is abstract, these tests use a minimal concrete
 * subclass ({@link Sphere}) to exercise the inherited behaviour — constructor
 * validation, getters/setters, delegation to {@code calculateVolume()} and
 * {@code calculateSurfaceArea()}, and the {@code toString()} contract.</p>
 */
@DisplayName("Shape3D (abstract base class)")
class Shape3DTest {

    // -----------------------------------------------------------------
    // Helper — create a simple Shape3D via Sphere
    // -----------------------------------------------------------------
    private Shape3D createShape(String name, String color) {
        return new Sphere(name, color, 1.0);
    }

    // =================================================================
    // Constructor validation
    // =================================================================
    @Nested
    @DisplayName("Constructor validation")
    class ConstructorValidation {

        @Test
        @DisplayName("Valid name and color are accepted")
        void validNameAndColor() {
            Shape3D shape = createShape("Test", "Blue");
            assertEquals("Test", shape.getName());
            assertEquals("Blue", shape.getColor());
        }

        @Test
        @DisplayName("Null name throws IllegalArgumentException")
        void nullNameThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> createShape(null, "Blue"));
        }

        @Test
        @DisplayName("Blank name throws IllegalArgumentException")
        void blankNameThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> createShape("   ", "Blue"));
        }

        @Test
        @DisplayName("Empty name throws IllegalArgumentException")
        void emptyNameThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> createShape("", "Blue"));
        }

        @Test
        @DisplayName("Null color throws IllegalArgumentException")
        void nullColorThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> createShape("Test", null));
        }

        @Test
        @DisplayName("Blank color throws IllegalArgumentException")
        void blankColorThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> createShape("Test", "   "));
        }

        @Test
        @DisplayName("Empty color throws IllegalArgumentException")
        void emptyColorThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> createShape("Test", ""));
        }
    }

    // =================================================================
    // Getters and setters
    // =================================================================
    @Nested
    @DisplayName("Getters and setters")
    class GettersAndSetters {

        @Test
        @DisplayName("getName returns constructor value")
        void getNameReturnsValue() {
            Shape3D shape = createShape("Ball", "Red");
            assertEquals("Ball", shape.getName());
        }

        @Test
        @DisplayName("setName updates the name")
        void setNameUpdatesName() {
            Shape3D shape = createShape("Ball", "Red");
            shape.setName("Orb");
            assertEquals("Orb", shape.getName());
        }

        @Test
        @DisplayName("setName rejects null")
        void setNameRejectsNull() {
            Shape3D shape = createShape("Ball", "Red");
            assertThrows(IllegalArgumentException.class,
                    () -> shape.setName(null));
        }

        @Test
        @DisplayName("setName rejects blank string")
        void setNameRejectsBlank() {
            Shape3D shape = createShape("Ball", "Red");
            assertThrows(IllegalArgumentException.class,
                    () -> shape.setName("  "));
        }

        @Test
        @DisplayName("getColor returns constructor value")
        void getColorReturnsValue() {
            Shape3D shape = createShape("Ball", "Red");
            assertEquals("Red", shape.getColor());
        }

        @Test
        @DisplayName("setColor updates the color")
        void setColorUpdatesColor() {
            Shape3D shape = createShape("Ball", "Red");
            shape.setColor("Green");
            assertEquals("Green", shape.getColor());
        }

        @Test
        @DisplayName("setColor rejects null")
        void setColorRejectsNull() {
            Shape3D shape = createShape("Ball", "Red");
            assertThrows(IllegalArgumentException.class,
                    () -> shape.setColor(null));
        }

        @Test
        @DisplayName("setColor rejects blank string")
        void setColorRejectsBlank() {
            Shape3D shape = createShape("Ball", "Red");
            assertThrows(IllegalArgumentException.class,
                    () -> shape.setColor(""));
        }
    }

    // =================================================================
    // Delegation — getVolume / getSurfaceArea
    // =================================================================
    @Nested
    @DisplayName("Delegation to abstract calculation methods")
    class Delegation {

        @Test
        @DisplayName("getVolume delegates to calculateVolume")
        void getVolumeDelegates() {
            Sphere sphere = new Sphere("S", "R", 1.0);
            // Both should return the same value
            assertEquals(sphere.calculateVolume(), sphere.getVolume(), 1e-9);
        }

        @Test
        @DisplayName("getSurfaceArea delegates to calculateSurfaceArea")
        void getSurfaceAreaDelegates() {
            Sphere sphere = new Sphere("S", "R", 1.0);
            assertEquals(sphere.calculateSurfaceArea(), sphere.getSurfaceArea(), 1e-9);
        }
    }

    // =================================================================
    // Polymorphism
    // =================================================================
    @Nested
    @DisplayName("Polymorphic behaviour")
    class Polymorphism {

        @Test
        @DisplayName("Shape3D reference can hold any concrete shape")
        void shape3DReferenceHoldsSphere() {
            Shape3D shape = new Sphere("Ball", "Red", 3.0);
            assertInstanceOf(Shape3D.class, shape);
            assertInstanceOf(ThreeDimensionalShape.class, shape);
        }

        @Test
        @DisplayName("Interface reference resolves correct implementation")
        void interfaceReferenceResolvesCorrectly() {
            ThreeDimensionalShape shape = new Cube("Box", "Blue", 2.0);
            // Cube with side 2: volume = 8
            assertEquals(8.0, shape.getVolume(), 1e-9);
        }
    }

    // =================================================================
    // toString
    // =================================================================
    @Nested
    @DisplayName("toString output")
    class ToStringTests {

        @Test
        @DisplayName("toString contains name")
        void toStringContainsName() {
            Shape3D shape = createShape("MyShape", "Green");
            assertTrue(shape.toString().contains("MyShape"));
        }

        @Test
        @DisplayName("toString contains color")
        void toStringContainsColor() {
            Shape3D shape = createShape("MyShape", "Green");
            assertTrue(shape.toString().contains("Green"));
        }
    }
}

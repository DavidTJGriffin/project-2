package com.csc205.project2.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Comprehensive unit tests for the {@link Cylinder} class.
 *
 * <p>Covers basic functionality, calculation accuracy with known values,
 * boundary / input-validation testing, and polymorphic behaviour.</p>
 */
@DisplayName("Cylinder")
class CylinderTest {

    private Cylinder cylinder;

    @BeforeEach
    void setUp() {
        cylinder = new Cylinder("Green Pipe", "Green", 3.0, 7.0);
    }

    // =================================================================
    // Constructor & basic functionality
    // =================================================================
    @Nested
    @DisplayName("Constructor and basic getters")
    class ConstructorTests {

        @Test
        @DisplayName("Constructor sets name correctly")
        void constructorSetsName() {
            assertEquals("Green Pipe", cylinder.getName());
        }

        @Test
        @DisplayName("Constructor sets color correctly")
        void constructorSetsColor() {
            assertEquals("Green", cylinder.getColor());
        }

        @Test
        @DisplayName("Constructor sets radius correctly")
        void constructorSetsRadius() {
            assertEquals(3.0, cylinder.getRadius(), 1e-9);
        }

        @Test
        @DisplayName("Constructor sets height correctly")
        void constructorSetsHeight() {
            assertEquals(7.0, cylinder.getHeight(), 1e-9);
        }
    }

    // =================================================================
    // Calculation accuracy
    // =================================================================
    @Nested
    @DisplayName("Calculation accuracy")
    class CalculationTests {

        @Test
        @DisplayName("Volume of cylinder r=3, h=7 ≈ 197.9203")
        void volumeR3H7() {
            double expected = Math.PI * 9.0 * 7.0; // π × 9 × 7
            assertEquals(expected, cylinder.getVolume(), 1e-4);
        }

        @Test
        @DisplayName("Volume of cylinder r=1, h=1 = π")
        void volumeUnitCylinder() {
            Cylinder c = new Cylinder("C", "R", 1.0, 1.0);
            assertEquals(Math.PI, c.getVolume(), 1e-9);
        }

        @Test
        @DisplayName("Volume of cylinder r=5, h=10 ≈ 785.3982")
        void volumeR5H10() {
            Cylinder c = new Cylinder("C", "R", 5.0, 10.0);
            double expected = Math.PI * 25.0 * 10.0;
            assertEquals(expected, c.getVolume(), 1e-4);
        }

        @Test
        @DisplayName("Surface area of cylinder r=3, h=7 ≈ 188.4956")
        void surfaceAreaR3H7() {
            double expected = 2.0 * Math.PI * 3.0 * (3.0 + 7.0); // 2π×3×10
            assertEquals(expected, cylinder.getSurfaceArea(), 1e-4);
        }

        @Test
        @DisplayName("Surface area of unit cylinder = 4π")
        void surfaceAreaUnitCylinder() {
            Cylinder c = new Cylinder("C", "R", 1.0, 1.0);
            double expected = 2.0 * Math.PI * 1.0 * (1.0 + 1.0);
            assertEquals(expected, c.getSurfaceArea(), 1e-9);
        }

        @Test
        @DisplayName("Volume recalculates after radius and height change")
        void volumeRecalculatesAfterSet() {
            cylinder.setRadius(2.0);
            cylinder.setHeight(5.0);
            double expected = Math.PI * 4.0 * 5.0;
            assertEquals(expected, cylinder.getVolume(), 1e-4);
        }
    }

    // =================================================================
    // Boundary & input validation
    // =================================================================
    @Nested
    @DisplayName("Input validation")
    class ValidationTests {

        @Test
        @DisplayName("Zero radius in constructor throws")
        void zeroRadiusThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Cylinder("C", "R", 0.0, 5.0));
        }

        @Test
        @DisplayName("Negative radius in constructor throws")
        void negativeRadiusThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Cylinder("C", "R", -1.0, 5.0));
        }

        @Test
        @DisplayName("Zero height in constructor throws")
        void zeroHeightThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Cylinder("C", "R", 3.0, 0.0));
        }

        @Test
        @DisplayName("Negative height in constructor throws")
        void negativeHeightThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Cylinder("C", "R", 3.0, -2.0));
        }

        @Test
        @DisplayName("Zero radius in setter throws")
        void setZeroRadiusThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> cylinder.setRadius(0.0));
        }

        @Test
        @DisplayName("Negative radius in setter throws")
        void setNegativeRadiusThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> cylinder.setRadius(-1.0));
        }

        @Test
        @DisplayName("Zero height in setter throws")
        void setZeroHeightThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> cylinder.setHeight(0.0));
        }

        @Test
        @DisplayName("Negative height in setter throws")
        void setNegativeHeightThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> cylinder.setHeight(-3.0));
        }

        @Test
        @DisplayName("Very small dimensions are accepted")
        void verySmallDimensions() {
            Cylinder c = new Cylinder("Tiny", "Y", 0.001, 0.001);
            assertTrue(c.getVolume() > 0);
        }

        @Test
        @DisplayName("Very large dimensions are accepted")
        void veryLargeDimensions() {
            Cylinder c = new Cylinder("Huge", "Z", 100000.0, 100000.0);
            assertTrue(c.getVolume() > 0);
            assertTrue(Double.isFinite(c.getVolume()));
        }
    }

    // =================================================================
    // Setter behaviour
    // =================================================================
    @Nested
    @DisplayName("Setter behaviour")
    class SetterTests {

        @Test
        @DisplayName("setRadius updates the radius")
        void setRadiusUpdates() {
            cylinder.setRadius(8.0);
            assertEquals(8.0, cylinder.getRadius(), 1e-9);
        }

        @Test
        @DisplayName("setHeight updates the height")
        void setHeightUpdates() {
            cylinder.setHeight(12.0);
            assertEquals(12.0, cylinder.getHeight(), 1e-9);
        }
    }

    // =================================================================
    // Polymorphism & inheritance
    // =================================================================
    @Nested
    @DisplayName("Polymorphism and inheritance")
    class PolymorphismTests {

        @Test
        @DisplayName("Cylinder is instance of Shape3D")
        void isShape3D() {
            assertInstanceOf(Shape3D.class, cylinder);
        }

        @Test
        @DisplayName("Cylinder is instance of ThreeDimensionalShape")
        void isThreeDimensionalShape() {
            assertInstanceOf(ThreeDimensionalShape.class, cylinder);
        }

        @Test
        @DisplayName("Shape3D reference calls Cylinder's volume")
        void polymorphicVolume() {
            Shape3D ref = cylinder;
            double expected = Math.PI * 9.0 * 7.0;
            assertEquals(expected, ref.getVolume(), 1e-4);
        }
    }

    // =================================================================
    // toString
    // =================================================================
    @Nested
    @DisplayName("toString output")
    class ToStringTests {

        @Test
        @DisplayName("toString contains 'Cylinder'")
        void containsTypeName() {
            assertTrue(cylinder.toString().contains("Cylinder"));
        }

        @Test
        @DisplayName("toString contains name, radius, and height")
        void containsFields() {
            String s = cylinder.toString();
            assertTrue(s.contains("Green Pipe"));
            assertTrue(s.contains("3.0"));
            assertTrue(s.contains("7.0"));
        }
    }
}

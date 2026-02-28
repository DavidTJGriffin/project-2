package com.csc205.project2.shapes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for the {@link Cone} class.
 *
 * <p>Covers basic functionality, calculation accuracy with known values,
 * boundary / input-validation testing, slant height, and polymorphic
 * behaviour.</p>
 */
@DisplayName("Cone")
class ConeTest {

    private Cone cone;

    @BeforeEach
    void setUp() {
        cone = new Cone("Purple Party Hat", "Purple", 4.0, 9.0);
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
            assertEquals("Purple Party Hat", cone.getName());
        }

        @Test
        @DisplayName("Constructor sets color correctly")
        void constructorSetsColor() {
            assertEquals("Purple", cone.getColor());
        }

        @Test
        @DisplayName("Constructor sets radius correctly")
        void constructorSetsRadius() {
            assertEquals(4.0, cone.getRadius(), 1e-9);
        }

        @Test
        @DisplayName("Constructor sets height correctly")
        void constructorSetsHeight() {
            assertEquals(9.0, cone.getHeight(), 1e-9);
        }
    }

    // =================================================================
    // Calculation accuracy
    // =================================================================
    @Nested
    @DisplayName("Calculation accuracy")
    class CalculationTests {

        @Test
        @DisplayName("Volume of cone r=4, h=9 ≈ 150.7964")
        void volumeR4H9() {
            double expected = (1.0 / 3.0) * Math.PI * 16.0 * 9.0;
            assertEquals(expected, cone.getVolume(), 1e-4);
        }

        @Test
        @DisplayName("Volume of cone r=1, h=1 = π/3")
        void volumeUnitCone() {
            Cone c = new Cone("C", "R", 1.0, 1.0);
            assertEquals(Math.PI / 3.0, c.getVolume(), 1e-9);
        }

        @Test
        @DisplayName("Volume of cone r=3, h=4 ≈ 37.6991")
        void volumeR3H4() {
            Cone c = new Cone("C", "R", 3.0, 4.0);
            double expected = (1.0 / 3.0) * Math.PI * 9.0 * 4.0;
            assertEquals(expected, c.getVolume(), 1e-4);
        }

        @Test
        @DisplayName("Surface area of cone r=4, h=9 ≈ 174.0310")
        void surfaceAreaR4H9() {
            double slant = Math.sqrt(81.0 + 16.0); // √97 ≈ 9.8489
            double expected = Math.PI * 4.0 * (4.0 + slant);
            assertEquals(expected, cone.getSurfaceArea(), 1e-4);
        }

        @Test
        @DisplayName("Surface area of cone r=3, h=4 (slant=5) ≈ 75.3982")
        void surfaceAreaR3H4() {
            Cone c = new Cone("C", "R", 3.0, 4.0);
            // slant = √(16+9) = 5
            double expected = Math.PI * 3.0 * (3.0 + 5.0); // 24π
            assertEquals(expected, c.getSurfaceArea(), 1e-4);
        }

        @Test
        @DisplayName("Slant height of cone r=3, h=4 = 5 (3-4-5 triangle)")
        void slantHeight345() {
            Cone c = new Cone("C", "R", 3.0, 4.0);
            assertEquals(5.0, c.getSlantHeight(), 1e-9);
        }

        @Test
        @DisplayName("Volume recalculates after dimension changes")
        void volumeRecalculatesAfterSet() {
            cone.setRadius(3.0);
            cone.setHeight(4.0);
            double expected = (1.0 / 3.0) * Math.PI * 9.0 * 4.0;
            assertEquals(expected, cone.getVolume(), 1e-4);
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
                    () -> new Cone("C", "R", 0.0, 5.0));
        }

        @Test
        @DisplayName("Negative radius in constructor throws")
        void negativeRadiusThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Cone("C", "R", -1.0, 5.0));
        }

        @Test
        @DisplayName("Zero height in constructor throws")
        void zeroHeightThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Cone("C", "R", 4.0, 0.0));
        }

        @Test
        @DisplayName("Negative height in constructor throws")
        void negativeHeightThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Cone("C", "R", 4.0, -3.0));
        }

        @Test
        @DisplayName("Zero radius in setter throws")
        void setZeroRadiusThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> cone.setRadius(0.0));
        }

        @Test
        @DisplayName("Negative radius in setter throws")
        void setNegativeRadiusThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> cone.setRadius(-1.0));
        }

        @Test
        @DisplayName("Zero height in setter throws")
        void setZeroHeightThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> cone.setHeight(0.0));
        }

        @Test
        @DisplayName("Negative height in setter throws")
        void setNegativeHeightThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> cone.setHeight(-5.0));
        }

        @Test
        @DisplayName("Very small dimensions are accepted")
        void verySmallDimensions() {
            Cone c = new Cone("Tiny", "Y", 0.001, 0.001);
            assertTrue(c.getVolume() > 0);
        }

        @Test
        @DisplayName("Very large dimensions are accepted")
        void veryLargeDimensions() {
            Cone c = new Cone("Huge", "Z", 100000.0, 100000.0);
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
            cone.setRadius(6.0);
            assertEquals(6.0, cone.getRadius(), 1e-9);
        }

        @Test
        @DisplayName("setHeight updates the height")
        void setHeightUpdates() {
            cone.setHeight(12.0);
            assertEquals(12.0, cone.getHeight(), 1e-9);
        }
    }

    // =================================================================
    // Polymorphism & inheritance
    // =================================================================
    @Nested
    @DisplayName("Polymorphism and inheritance")
    class PolymorphismTests {

        @Test
        @DisplayName("Cone is instance of Shape3D")
        void isShape3D() {
            assertInstanceOf(Shape3D.class, cone);
        }

        @Test
        @DisplayName("Cone is instance of ThreeDimensionalShape")
        void isThreeDimensionalShape() {
            assertInstanceOf(ThreeDimensionalShape.class, cone);
        }

        @Test
        @DisplayName("Shape3D reference calls Cone's volume")
        void polymorphicVolume() {
            Shape3D ref = cone;
            double expected = (1.0 / 3.0) * Math.PI * 16.0 * 9.0;
            assertEquals(expected, ref.getVolume(), 1e-4);
        }

        @Test
        @DisplayName("ThreeDimensionalShape reference calls Cone's surface area")
        void polymorphicSurfaceArea() {
            ThreeDimensionalShape ref = cone;
            double slant = Math.sqrt(81.0 + 16.0);
            double expected = Math.PI * 4.0 * (4.0 + slant);
            assertEquals(expected, ref.getSurfaceArea(), 1e-4);
        }
    }

    // =================================================================
    // toString
    // =================================================================
    @Nested
    @DisplayName("toString output")
    class ToStringTests {

        @Test
        @DisplayName("toString contains 'Cone'")
        void containsTypeName() {
            assertTrue(cone.toString().contains("Cone"));
        }

        @Test
        @DisplayName("toString contains name, radius, and height")
        void containsFields() {
            String s = cone.toString();
            assertTrue(s.contains("Purple Party Hat"));
            assertTrue(s.contains("4.0"));
            assertTrue(s.contains("9.0"));
        }
    }
}

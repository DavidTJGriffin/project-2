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
 * Comprehensive unit tests for the {@link Sphere} class.
 *
 * <p>Covers basic functionality, calculation accuracy with known values,
 * boundary / input-validation testing, and polymorphic behaviour.</p>
 */
@DisplayName("Sphere")
class SphereTest {

    private Sphere sphere;

    @BeforeEach
    void setUp() {
        sphere = new Sphere("Red Ball", "Red", 5.0);
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
            assertEquals("Red Ball", sphere.getName());
        }

        @Test
        @DisplayName("Constructor sets color correctly")
        void constructorSetsColor() {
            assertEquals("Red", sphere.getColor());
        }

        @Test
        @DisplayName("Constructor sets radius correctly")
        void constructorSetsRadius() {
            assertEquals(5.0, sphere.getRadius(), 1e-9);
        }
    }

    // =================================================================
    // Calculation accuracy
    // =================================================================
    @Nested
    @DisplayName("Calculation accuracy")
    class CalculationTests {

        @Test
        @DisplayName("Volume of sphere with radius 3 ≈ 113.0973")
        void volumeWithRadius3() {
            Sphere s = new Sphere("S", "R", 3.0);
            double expected = (4.0 / 3.0) * Math.PI * 27.0; // 113.0973...
            assertEquals(expected, s.getVolume(), 1e-4);
        }

        @Test
        @DisplayName("Volume of sphere with radius 5 ≈ 523.5988")
        void volumeWithRadius5() {
            double expected = (4.0 / 3.0) * Math.PI * 125.0;
            assertEquals(expected, sphere.getVolume(), 1e-4);
        }

        @Test
        @DisplayName("Surface area of sphere with radius 3 ≈ 113.0973")
        void surfaceAreaWithRadius3() {
            Sphere s = new Sphere("S", "R", 3.0);
            double expected = 4.0 * Math.PI * 9.0; // 113.0973...
            assertEquals(expected, s.getSurfaceArea(), 1e-4);
        }

        @Test
        @DisplayName("Surface area of sphere with radius 5 ≈ 314.1593")
        void surfaceAreaWithRadius5() {
            double expected = 4.0 * Math.PI * 25.0;
            assertEquals(expected, sphere.getSurfaceArea(), 1e-4);
        }

        @Test
        @DisplayName("Unit sphere volume ≈ 4.1888")
        void unitSphereVolume() {
            Sphere s = new Sphere("Unit", "W", 1.0);
            assertEquals((4.0 / 3.0) * Math.PI, s.getVolume(), 1e-4);
        }

        @Test
        @DisplayName("Unit sphere surface area ≈ 12.5664")
        void unitSphereSurfaceArea() {
            Sphere s = new Sphere("Unit", "W", 1.0);
            assertEquals(4.0 * Math.PI, s.getSurfaceArea(), 1e-4);
        }

        @Test
        @DisplayName("Volume recalculates after radius change")
        void volumeRecalculatesAfterSetRadius() {
            sphere.setRadius(10.0);
            double expected = (4.0 / 3.0) * Math.PI * 1000.0;
            assertEquals(expected, sphere.getVolume(), 1e-4);
        }
    }

    // =================================================================
    // Boundary & input validation
    // =================================================================
    @Nested
    @DisplayName("Input validation")
    class ValidationTests {

        @Test
        @DisplayName("Zero radius in constructor throws IllegalArgumentException")
        void zeroRadiusThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Sphere("S", "R", 0.0));
        }

        @Test
        @DisplayName("Negative radius in constructor throws IllegalArgumentException")
        void negativeRadiusThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Sphere("S", "R", -5.0));
        }

        @Test
        @DisplayName("Zero radius in setter throws IllegalArgumentException")
        void setZeroRadiusThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> sphere.setRadius(0.0));
        }

        @Test
        @DisplayName("Negative radius in setter throws IllegalArgumentException")
        void setNegativeRadiusThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> sphere.setRadius(-1.0));
        }

        @Test
        @DisplayName("Very small radius is accepted")
        void verySmallRadius() {
            Sphere s = new Sphere("Tiny", "Y", 0.001);
            assertTrue(s.getVolume() > 0);
        }

        @Test
        @DisplayName("Very large radius is accepted")
        void veryLargeRadius() {
            Sphere s = new Sphere("Huge", "Z", 1_000_000.0);
            assertTrue(s.getVolume() > 0);
            assertTrue(Double.isFinite(s.getVolume()));
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
            sphere.setRadius(7.5);
            assertEquals(7.5, sphere.getRadius(), 1e-9);
        }
    }

    // =================================================================
    // Polymorphism & inheritance
    // =================================================================
    @Nested
    @DisplayName("Polymorphism and inheritance")
    class PolymorphismTests {

        @Test
        @DisplayName("Sphere is instance of Shape3D")
        void isShape3D() {
            assertInstanceOf(Shape3D.class, sphere);
        }

        @Test
        @DisplayName("Sphere is instance of ThreeDimensionalShape")
        void isThreeDimensionalShape() {
            assertInstanceOf(ThreeDimensionalShape.class, sphere);
        }

        @Test
        @DisplayName("Shape3D reference calls Sphere's volume")
        void polymorphicVolume() {
            Shape3D ref = sphere;
            double expected = (4.0 / 3.0) * Math.PI * Math.pow(5.0, 3);
            assertEquals(expected, ref.getVolume(), 1e-4);
        }

        @Test
        @DisplayName("Shape3D reference calls Sphere's surface area")
        void polymorphicSurfaceArea() {
            Shape3D ref = sphere;
            double expected = 4.0 * Math.PI * Math.pow(5.0, 2);
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
        @DisplayName("toString contains 'Sphere'")
        void containsTypeName() {
            assertTrue(sphere.toString().contains("Sphere"));
        }

        @Test
        @DisplayName("toString contains name value")
        void containsName() {
            assertTrue(sphere.toString().contains("Red Ball"));
        }

        @Test
        @DisplayName("toString contains radius value")
        void containsRadius() {
            assertTrue(sphere.toString().contains("5.0"));
        }
    }
}

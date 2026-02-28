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
 * Comprehensive unit tests for the {@link Cube} class.
 *
 * <p>Covers basic functionality, calculation accuracy with known values,
 * boundary / input-validation testing, and polymorphic behaviour.</p>
 */
@DisplayName("Cube")
class CubeTest {

    private Cube cube;

    @BeforeEach
    void setUp() {
        cube = new Cube("Blue Box", "Blue", 4.0);
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
            assertEquals("Blue Box", cube.getName());
        }

        @Test
        @DisplayName("Constructor sets color correctly")
        void constructorSetsColor() {
            assertEquals("Blue", cube.getColor());
        }

        @Test
        @DisplayName("Constructor sets sideLength correctly")
        void constructorSetsSideLength() {
            assertEquals(4.0, cube.getSideLength(), 1e-9);
        }
    }

    // =================================================================
    // Calculation accuracy
    // =================================================================
    @Nested
    @DisplayName("Calculation accuracy")
    class CalculationTests {

        @Test
        @DisplayName("Volume of cube with side 4 = 64")
        void volumeWithSide4() {
            assertEquals(64.0, cube.getVolume(), 1e-9);
        }

        @Test
        @DisplayName("Volume of cube with side 1 = 1")
        void volumeUnitCube() {
            Cube c = new Cube("Unit", "W", 1.0);
            assertEquals(1.0, c.getVolume(), 1e-9);
        }

        @Test
        @DisplayName("Volume of cube with side 10 = 1000")
        void volumeWithSide10() {
            Cube c = new Cube("Big", "G", 10.0);
            assertEquals(1000.0, c.getVolume(), 1e-9);
        }

        @Test
        @DisplayName("Surface area of cube with side 4 = 96")
        void surfaceAreaWithSide4() {
            assertEquals(96.0, cube.getSurfaceArea(), 1e-9);
        }

        @Test
        @DisplayName("Surface area of unit cube = 6")
        void surfaceAreaUnitCube() {
            Cube c = new Cube("Unit", "W", 1.0);
            assertEquals(6.0, c.getSurfaceArea(), 1e-9);
        }

        @Test
        @DisplayName("Surface area of cube with side 3 = 54")
        void surfaceAreaWithSide3() {
            Cube c = new Cube("C", "R", 3.0);
            assertEquals(54.0, c.getSurfaceArea(), 1e-9);
        }

        @Test
        @DisplayName("Volume recalculates after sideLength change")
        void volumeRecalculatesAfterSet() {
            cube.setSideLength(5.0);
            assertEquals(125.0, cube.getVolume(), 1e-9);
        }
    }

    // =================================================================
    // Boundary & input validation
    // =================================================================
    @Nested
    @DisplayName("Input validation")
    class ValidationTests {

        @Test
        @DisplayName("Zero side length in constructor throws IllegalArgumentException")
        void zeroSideLengthThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Cube("C", "R", 0.0));
        }

        @Test
        @DisplayName("Negative side length in constructor throws IllegalArgumentException")
        void negativeSideLengthThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Cube("C", "R", -3.0));
        }

        @Test
        @DisplayName("Zero side length in setter throws IllegalArgumentException")
        void setZeroSideLengthThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> cube.setSideLength(0.0));
        }

        @Test
        @DisplayName("Negative side length in setter throws IllegalArgumentException")
        void setNegativeSideLengthThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> cube.setSideLength(-2.0));
        }

        @Test
        @DisplayName("Very small side length is accepted")
        void verySmallSideLength() {
            Cube c = new Cube("Tiny", "Y", 0.001);
            assertTrue(c.getVolume() > 0);
        }

        @Test
        @DisplayName("Very large side length is accepted")
        void veryLargeSideLength() {
            Cube c = new Cube("Huge", "Z", 1_000_000.0);
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
        @DisplayName("setSideLength updates the side length")
        void setSideLengthUpdates() {
            cube.setSideLength(7.0);
            assertEquals(7.0, cube.getSideLength(), 1e-9);
        }
    }

    // =================================================================
    // Polymorphism & inheritance
    // =================================================================
    @Nested
    @DisplayName("Polymorphism and inheritance")
    class PolymorphismTests {

        @Test
        @DisplayName("Cube is instance of Shape3D")
        void isShape3D() {
            assertInstanceOf(Shape3D.class, cube);
        }

        @Test
        @DisplayName("Cube is instance of ThreeDimensionalShape")
        void isThreeDimensionalShape() {
            assertInstanceOf(ThreeDimensionalShape.class, cube);
        }

        @Test
        @DisplayName("Shape3D reference calls Cube's volume")
        void polymorphicVolume() {
            Shape3D ref = cube;
            assertEquals(64.0, ref.getVolume(), 1e-9);
        }

        @Test
        @DisplayName("Shape3D reference calls Cube's surface area")
        void polymorphicSurfaceArea() {
            Shape3D ref = cube;
            assertEquals(96.0, ref.getSurfaceArea(), 1e-9);
        }
    }

    // =================================================================
    // toString
    // =================================================================
    @Nested
    @DisplayName("toString output")
    class ToStringTests {

        @Test
        @DisplayName("toString contains 'Cube'")
        void containsTypeName() {
            assertTrue(cube.toString().contains("Cube"));
        }

        @Test
        @DisplayName("toString contains name value")
        void containsName() {
            assertTrue(cube.toString().contains("Blue Box"));
        }

        @Test
        @DisplayName("toString contains sideLength value")
        void containsSideLength() {
            assertTrue(cube.toString().contains("4.0"));
        }
    }
}

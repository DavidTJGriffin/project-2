package com.csc205.project2.shapes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for the {@link RectangularPrism} class.
 *
 * <p>Covers basic functionality, calculation accuracy with known values,
 * boundary / input-validation testing, and polymorphic behaviour.</p>
 */
@DisplayName("RectangularPrism")
class RectangularPrismTest {

    private RectangularPrism prism;

    @BeforeEach
    void setUp() {
        prism = new RectangularPrism("Yellow Brick", "Yellow", 6.0, 3.0, 2.0);
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
            assertEquals("Yellow Brick", prism.getName());
        }

        @Test
        @DisplayName("Constructor sets color correctly")
        void constructorSetsColor() {
            assertEquals("Yellow", prism.getColor());
        }

        @Test
        @DisplayName("Constructor sets length correctly")
        void constructorSetsLength() {
            assertEquals(6.0, prism.getLength(), 1e-9);
        }

        @Test
        @DisplayName("Constructor sets width correctly")
        void constructorSetsWidth() {
            assertEquals(3.0, prism.getWidth(), 1e-9);
        }

        @Test
        @DisplayName("Constructor sets height correctly")
        void constructorSetsHeight() {
            assertEquals(2.0, prism.getHeight(), 1e-9);
        }
    }

    // =================================================================
    // Calculation accuracy
    // =================================================================
    @Nested
    @DisplayName("Calculation accuracy")
    class CalculationTests {

        @Test
        @DisplayName("Volume of 6×3×2 = 36")
        void volume6x3x2() {
            assertEquals(36.0, prism.getVolume(), 1e-9);
        }

        @Test
        @DisplayName("Volume of unit cube (1×1×1) = 1")
        void volumeUnitCube() {
            RectangularPrism rp = new RectangularPrism("U", "W", 1.0, 1.0, 1.0);
            assertEquals(1.0, rp.getVolume(), 1e-9);
        }

        @Test
        @DisplayName("Volume of 10×5×3 = 150")
        void volume10x5x3() {
            RectangularPrism rp = new RectangularPrism("R", "B", 10.0, 5.0, 3.0);
            assertEquals(150.0, rp.getVolume(), 1e-9);
        }

        @Test
        @DisplayName("Surface area of 6×3×2 = 72")
        void surfaceArea6x3x2() {
            // 2(6×3 + 6×2 + 3×2) = 2(18 + 12 + 6) = 72
            assertEquals(72.0, prism.getSurfaceArea(), 1e-9);
        }

        @Test
        @DisplayName("Surface area of unit cube = 6")
        void surfaceAreaUnitCube() {
            RectangularPrism rp = new RectangularPrism("U", "W", 1.0, 1.0, 1.0);
            assertEquals(6.0, rp.getSurfaceArea(), 1e-9);
        }

        @Test
        @DisplayName("Surface area of 10×5×3 = 190")
        void surfaceArea10x5x3() {
            RectangularPrism rp = new RectangularPrism("R", "B", 10.0, 5.0, 3.0);
            // 2(50 + 30 + 15) = 190
            assertEquals(190.0, rp.getSurfaceArea(), 1e-9);
        }

        @Test
        @DisplayName("Volume recalculates after dimension changes")
        void volumeRecalculatesAfterSet() {
            prism.setLength(4.0);
            prism.setWidth(4.0);
            prism.setHeight(4.0);
            assertEquals(64.0, prism.getVolume(), 1e-9);
        }
    }

    // =================================================================
    // Boundary & input validation
    // =================================================================
    @Nested
    @DisplayName("Input validation")
    class ValidationTests {

        @Test
        @DisplayName("Zero length in constructor throws")
        void zeroLengthThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RectangularPrism("R", "B", 0.0, 3.0, 2.0));
        }

        @Test
        @DisplayName("Negative length in constructor throws")
        void negativeLengthThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RectangularPrism("R", "B", -1.0, 3.0, 2.0));
        }

        @Test
        @DisplayName("Zero width in constructor throws")
        void zeroWidthThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RectangularPrism("R", "B", 6.0, 0.0, 2.0));
        }

        @Test
        @DisplayName("Negative width in constructor throws")
        void negativeWidthThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RectangularPrism("R", "B", 6.0, -3.0, 2.0));
        }

        @Test
        @DisplayName("Zero height in constructor throws")
        void zeroHeightThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RectangularPrism("R", "B", 6.0, 3.0, 0.0));
        }

        @Test
        @DisplayName("Negative height in constructor throws")
        void negativeHeightThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RectangularPrism("R", "B", 6.0, 3.0, -2.0));
        }

        @Test
        @DisplayName("Zero length in setter throws")
        void setZeroLengthThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> prism.setLength(0.0));
        }

        @Test
        @DisplayName("Negative length in setter throws")
        void setNegativeLengthThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> prism.setLength(-1.0));
        }

        @Test
        @DisplayName("Zero width in setter throws")
        void setZeroWidthThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> prism.setWidth(0.0));
        }

        @Test
        @DisplayName("Negative width in setter throws")
        void setNegativeWidthThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> prism.setWidth(-1.0));
        }

        @Test
        @DisplayName("Zero height in setter throws")
        void setZeroHeightThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> prism.setHeight(0.0));
        }

        @Test
        @DisplayName("Negative height in setter throws")
        void setNegativeHeightThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> prism.setHeight(-1.0));
        }

        @Test
        @DisplayName("Very small dimensions are accepted")
        void verySmallDimensions() {
            RectangularPrism rp = new RectangularPrism("T", "Y", 0.001, 0.001, 0.001);
            assertTrue(rp.getVolume() > 0);
        }

        @Test
        @DisplayName("Very large dimensions are accepted")
        void veryLargeDimensions() {
            RectangularPrism rp = new RectangularPrism("H", "Z", 100000.0, 100000.0, 100000.0);
            assertTrue(rp.getVolume() > 0);
            assertTrue(Double.isFinite(rp.getVolume()));
        }
    }

    // =================================================================
    // Setter behaviour
    // =================================================================
    @Nested
    @DisplayName("Setter behaviour")
    class SetterTests {

        @Test
        @DisplayName("setLength updates the length")
        void setLengthUpdates() {
            prism.setLength(10.0);
            assertEquals(10.0, prism.getLength(), 1e-9);
        }

        @Test
        @DisplayName("setWidth updates the width")
        void setWidthUpdates() {
            prism.setWidth(8.0);
            assertEquals(8.0, prism.getWidth(), 1e-9);
        }

        @Test
        @DisplayName("setHeight updates the height")
        void setHeightUpdates() {
            prism.setHeight(5.0);
            assertEquals(5.0, prism.getHeight(), 1e-9);
        }
    }

    // =================================================================
    // Polymorphism & inheritance
    // =================================================================
    @Nested
    @DisplayName("Polymorphism and inheritance")
    class PolymorphismTests {

        @Test
        @DisplayName("RectangularPrism is instance of Shape3D")
        void isShape3D() {
            assertInstanceOf(Shape3D.class, prism);
        }

        @Test
        @DisplayName("RectangularPrism is instance of ThreeDimensionalShape")
        void isThreeDimensionalShape() {
            assertInstanceOf(ThreeDimensionalShape.class, prism);
        }

        @Test
        @DisplayName("Shape3D reference calls RectangularPrism's volume")
        void polymorphicVolume() {
            Shape3D ref = prism;
            assertEquals(36.0, ref.getVolume(), 1e-9);
        }

        @Test
        @DisplayName("Shape3D reference calls RectangularPrism's surface area")
        void polymorphicSurfaceArea() {
            Shape3D ref = prism;
            assertEquals(72.0, ref.getSurfaceArea(), 1e-9);
        }
    }

    // =================================================================
    // toString
    // =================================================================
    @Nested
    @DisplayName("toString output")
    class ToStringTests {

        @Test
        @DisplayName("toString contains 'RectangularPrism'")
        void containsTypeName() {
            assertTrue(prism.toString().contains("RectangularPrism"));
        }

        @Test
        @DisplayName("toString contains name and dimensions")
        void containsFields() {
            String s = prism.toString();
            assertTrue(s.contains("Yellow Brick"));
            assertTrue(s.contains("6.0"));
            assertTrue(s.contains("3.0"));
            assertTrue(s.contains("2.0"));
        }
    }
}

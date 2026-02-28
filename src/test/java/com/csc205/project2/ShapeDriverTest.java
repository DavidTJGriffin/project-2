package com.csc205.project2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.csc205.project2.shapes.Cone;
import com.csc205.project2.shapes.Cube;
import com.csc205.project2.shapes.Cylinder;
import com.csc205.project2.shapes.RectangularPrism;
import com.csc205.project2.shapes.Shape3D;
import com.csc205.project2.shapes.Sphere;

/**
 * Tests for the {@link ShapeDriver} class.
 *
 * <p>Verifies that the pre-built demo output is correct, the interactive
 * menu operates as expected, and the comparative analysis identifies the
 * right shapes.</p>
 */
@DisplayName("ShapeDriver")
class ShapeDriverTest {

    // =================================================================
    // Helpers
    // =================================================================

    /**
     * Runs {@code ShapeDriver.main} while feeding the given string as
     * {@code System.in} and capturing {@code System.out}.
     */
    private String runMainWithInput(String simulatedInput) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        System.setOut(new PrintStream(baos));
        try {
            ShapeDriver.main(new String[]{});
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
        return baos.toString();
    }

    /**
     * Runs {@code ShapeDriver.displayShapesAndAnalysis} directly and
     * captures its output — no interactive menu involved.
     */
    private String captureDisplayOutput(List<Shape3D> shapes) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        try {
            ShapeDriver.displayShapesAndAnalysis(shapes);
        } finally {
            System.setOut(originalOut);
        }
        return baos.toString();
    }

    /**
     * Builds the same five shapes that the driver uses by default.
     */
    private List<Shape3D> buildDefaultShapes() {
        List<Shape3D> shapes = new ArrayList<>();
        shapes.add(new Sphere("Red Ball", "Red", 5.0));
        shapes.add(new Cube("Blue Box", "Blue", 4.0));
        shapes.add(new Cylinder("Green Pipe", "Green", 3.0, 7.0));
        shapes.add(new RectangularPrism("Yellow Brick", "Yellow", 6.0, 3.0, 2.0));
        shapes.add(new Cone("Purple Party Hat", "Purple", 4.0, 9.0));
        return shapes;
    }

    // =================================================================
    // Demo output tests (via displayShapesAndAnalysis)
    // =================================================================
    @Nested
    @DisplayName("Display and analysis output")
    class DisplayTests {

        @Test
        @DisplayName("Output contains header when run via main")
        void outputContainsHeader() {
            String output = runMainWithInput("3\n");
            assertTrue(output.contains("=== 3D Shape Analysis System ==="));
        }

        @Test
        @DisplayName("Output contains 'Created Shapes' section")
        void outputContainsCreatedShapes() {
            String output = runMainWithInput("3\n");
            assertTrue(output.contains("Created Shapes:"));
        }

        @Test
        @DisplayName("Output lists all five shapes")
        void outputListsAllFiveShapes() {
            String output = captureDisplayOutput(buildDefaultShapes());
            assertTrue(output.contains("1."));
            assertTrue(output.contains("2."));
            assertTrue(output.contains("3."));
            assertTrue(output.contains("4."));
            assertTrue(output.contains("5."));
        }

        @Test
        @DisplayName("Output contains Surface Area and Volume labels")
        void outputContainsLabels() {
            String output = captureDisplayOutput(buildDefaultShapes());
            assertTrue(output.contains("Surface Area:"));
            assertTrue(output.contains("Volume:"));
        }

        @Test
        @DisplayName("Output contains Analysis Results section")
        void outputContainsAnalysisResults() {
            String output = captureDisplayOutput(buildDefaultShapes());
            assertTrue(output.contains("Analysis Results:"));
            assertTrue(output.contains("Largest Volume:"));
            assertTrue(output.contains("Largest Surface Area:"));
            assertTrue(output.contains("Most Efficient"));
        }

        @Test
        @DisplayName("Largest volume is Red Ball (sphere r=5)")
        void largestVolumeIsRedBall() {
            String output = captureDisplayOutput(buildDefaultShapes());
            assertTrue(output.contains("Largest Volume: Red Ball"));
        }

        @Test
        @DisplayName("Largest surface area is Red Ball (sphere r=5)")
        void largestSurfaceAreaIsRedBall() {
            String output = captureDisplayOutput(buildDefaultShapes());
            assertTrue(output.contains("Largest Surface Area: Red Ball"));
        }

        @Test
        @DisplayName("Output contains efficiency values")
        void outputContainsEfficiency() {
            String output = captureDisplayOutput(buildDefaultShapes());
            assertTrue(output.contains("Efficiency"));
        }

        @Test
        @DisplayName("Empty list prints no-shapes message")
        void emptyListShowsMessage() {
            String output = captureDisplayOutput(new ArrayList<>());
            assertTrue(output.contains("No shapes to display"));
        }
    }

    // =================================================================
    // Interactive menu tests
    // =================================================================
    @Nested
    @DisplayName("Interactive menu")
    class InteractiveMenuTests {

        @Test
        @DisplayName("Quit option exits cleanly")
        void quitExitsCleanly() {
            String output = runMainWithInput("3\n");
            assertTrue(output.contains("Goodbye!"));
        }

        @Test
        @DisplayName("Create a Sphere interactively")
        void createSphereInteractively() {
            // Menu: 1 (create) → 1 (Sphere) → name → color → radius → 3 (quit)
            String input = "1\n1\nMy Sphere\nBlue\n7.5\n3\n";
            String output = runMainWithInput(input);
            assertTrue(output.contains("Shape created successfully"));
            assertTrue(output.contains("My Sphere"));
        }

        @Test
        @DisplayName("Create a Cube interactively")
        void createCubeInteractively() {
            String input = "1\n2\nMy Cube\nGreen\n3.0\n3\n";
            String output = runMainWithInput(input);
            assertTrue(output.contains("Shape created successfully"));
            assertTrue(output.contains("My Cube"));
        }

        @Test
        @DisplayName("Create a Cylinder interactively")
        void createCylinderInteractively() {
            String input = "1\n3\nMy Cylinder\nRed\n2.0\n5.0\n3\n";
            String output = runMainWithInput(input);
            assertTrue(output.contains("Shape created successfully"));
            assertTrue(output.contains("My Cylinder"));
        }

        @Test
        @DisplayName("Create a Rectangular Prism interactively")
        void createRectangularPrismInteractively() {
            String input = "1\n4\nMy Prism\nYellow\n4.0\n3.0\n2.0\n3\n";
            String output = runMainWithInput(input);
            assertTrue(output.contains("Shape created successfully"));
            assertTrue(output.contains("My Prism"));
        }

        @Test
        @DisplayName("Create a Cone interactively")
        void createConeInteractively() {
            String input = "1\n5\nMy Cone\nPurple\n3.0\n6.0\n3\n";
            String output = runMainWithInput(input);
            assertTrue(output.contains("Shape created successfully"));
            assertTrue(output.contains("My Cone"));
        }

        @Test
        @DisplayName("Invalid dimension shows error message")
        void invalidDimensionShowsError() {
            String input = "1\n1\nBad Sphere\nRed\n-5\n3\n";
            String output = runMainWithInput(input);
            assertTrue(output.contains("Invalid input:"));
        }

        @Test
        @DisplayName("Non-numeric dimension shows error message")
        void nonNumericDimensionShowsError() {
            String input = "1\n1\nBad Sphere\nRed\nabc\n3\n";
            String output = runMainWithInput(input);
            assertTrue(output.contains("Invalid number format"));
        }

        @Test
        @DisplayName("View all shapes option re-displays analysis")
        void viewAllShapesRedisplays() {
            String input = "2\n3\n";
            String output = runMainWithInput(input);
            // The "All Shapes" header should appear from option 2
            assertTrue(output.contains("=== All Shapes ==="));
            assertTrue(output.contains("Analysis Results:"));
        }

        @Test
        @DisplayName("Invalid menu option shows error")
        void invalidMenuOption() {
            String input = "9\n3\n";
            String output = runMainWithInput(input);
            assertTrue(output.contains("Invalid option"));
        }

        @Test
        @DisplayName("New shape appears in subsequent view-all")
        void newShapeAppearsInViewAll() {
            // Create a sphere, then view all, then quit
            String input = "1\n1\nExtra Ball\nOrange\n2.0\n2\n3\n";
            String output = runMainWithInput(input);
            // The view-all output should contain 6 shapes (5 default + 1 new)
            assertTrue(output.contains("6."));
            assertTrue(output.contains("Extra Ball"));
        }
    }
}

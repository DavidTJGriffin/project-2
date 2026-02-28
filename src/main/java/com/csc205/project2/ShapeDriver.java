package com.csc205.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.csc205.project2.shapes.Cone;
import com.csc205.project2.shapes.Cube;
import com.csc205.project2.shapes.Cylinder;
import com.csc205.project2.shapes.RectangularPrism;
import com.csc205.project2.shapes.Shape3D;
import com.csc205.project2.shapes.Sphere;

/**
 * Main demonstration class for the 3D Shape Analysis System.
 *
 * <p>{@code ShapeDriver} creates one instance of each concrete shape, stores
 * them in a polymorphic {@code List<Shape3D>}, and then performs a series of
 * analyses including formatted output of every shape's properties,
 * comparative analysis to find the largest volume and surface area, and
 * an efficiency calculation (Volume / Surface Area ratio).</p>
 *
 * <p>After the initial demonstration, the driver provides an interactive menu
 * that allows the user to create additional shapes with custom parameters,
 * view all shapes, and re-run the comparative analysis at any time.</p>
 *
 * <p><strong>Design patterns demonstrated:</strong></p>
 * <ul>
 *   <li><em>Polymorphism</em> — all shapes are accessed through the
 *       {@code Shape3D} base-class reference</li>
 *   <li><em>Inheritance</em> — each shape inherits common behavior from
 *       {@code Shape3D} while providing its own calculations</li>
 *   <li><em>Abstraction</em> — the driver does not need to know which
 *       concrete type it is working with</li>
 * </ul>
 *
 * @see Shape3D
 * @see Sphere
 * @see Cube
 * @see Cylinder
 * @see RectangularPrism
 * @see Cone
 */
public class ShapeDriver {

    private static final Logger LOGGER = Logger.getLogger(ShapeDriver.class.getName());

    /**
     * Entry point for the 3D Shape Analysis System.
     *
     * <p>Creates five shapes, prints their details, runs comparative
     * analysis, and then enters an interactive loop where the user can
     * create additional shapes with custom parameters.</p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        LOGGER.info("Starting 3D Shape Analysis System...");

        // -- Build the shape collection (polymorphism in action) ------
        List<Shape3D> shapes = new ArrayList<>();
        shapes.add(new Sphere("Red Ball", "Red", 5.0));
        shapes.add(new Cube("Blue Box", "Blue", 4.0));
        shapes.add(new Cylinder("Green Pipe", "Green", 3.0, 7.0));
        shapes.add(new RectangularPrism("Yellow Brick", "Yellow", 6.0, 3.0, 2.0));
        shapes.add(new Cone("Purple Party Hat", "Purple", 4.0, 9.0));

        LOGGER.info("Created " + shapes.size() + " shapes for analysis.");

        // -- Header ---------------------------------------------------
        System.out.println("=== 3D Shape Analysis System ===");
        System.out.println();
        System.out.println("Created Shapes:");

        // -- Display shapes and analysis ------------------------------
        displayShapesAndAnalysis(shapes);

        // -- Interactive menu -----------------------------------------
        runInteractiveMenu(shapes);
    }

    // =================================================================
    // Display & Analysis helpers
    // =================================================================

    /**
     * Iterates over the supplied list, prints each shape's details, and
     * outputs the comparative analysis (largest volume, largest surface
     * area, and best volume-to-surface-area efficiency).
     *
     * @param shapes the list of shapes to display and analyse
     */
    static void displayShapesAndAnalysis(List<Shape3D> shapes) {
        if (shapes.isEmpty()) {
            System.out.println("No shapes to display.");
            return;
        }

        Shape3D largestVolumeShape = null;
        double largestVolume = Double.NEGATIVE_INFINITY;

        Shape3D largestSurfaceAreaShape = null;
        double largestSurfaceArea = Double.NEGATIVE_INFINITY;

        Shape3D mostEfficientShape = null;
        double bestEfficiency = Double.NEGATIVE_INFINITY;

        int index = 1;
        for (Shape3D shape : shapes) {
            double volume = shape.getVolume();
            double surfaceArea = shape.getSurfaceArea();
            double efficiency = volume / surfaceArea;

            System.out.printf("%d. %s%n", index, shape);
            System.out.printf("   - Surface Area: %.2f square units%n", surfaceArea);
            System.out.printf("   - Volume: %.2f cubic units%n", volume);
            System.out.printf("   - Efficiency (V/SA): %.2f%n", efficiency);
            System.out.println();

            LOGGER.info("Displayed shape #" + index + ": " + shape.getName());

            if (volume > largestVolume) {
                largestVolume = volume;
                largestVolumeShape = shape;
            }
            if (surfaceArea > largestSurfaceArea) {
                largestSurfaceArea = surfaceArea;
                largestSurfaceAreaShape = shape;
            }
            if (efficiency > bestEfficiency) {
                bestEfficiency = efficiency;
                mostEfficientShape = shape;
            }

            index++;
        }

        System.out.println("Analysis Results:");
        System.out.printf("- Largest Volume: %s (%.2f)%n",
                largestVolumeShape.getName(), largestVolume);
        System.out.printf("- Largest Surface Area: %s (%.2f)%n",
                largestSurfaceAreaShape.getName(), largestSurfaceArea);
        System.out.printf("- Most Efficient (Volume/Surface): %s (%.2f)%n",
                mostEfficientShape.getName(), bestEfficiency);

        LOGGER.info("Analysis complete. Most efficient shape: " + mostEfficientShape.getName());
    }

    // =================================================================
    // Interactive menu
    // =================================================================

    /**
     * Presents an interactive menu that allows the user to create shapes
     * with custom parameters, view all shapes, re-run analysis, or quit.
     *
     * @param shapes the mutable list of shapes to operate on
     */
    static void runInteractiveMenu(List<Shape3D> shapes) {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("=== Interactive Shape Creator ===");

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("Menu:");
            System.out.println("  1. Create a new shape");
            System.out.println("  2. View all shapes & analysis");
            System.out.println("  3. Quit");
            System.out.print("Choose an option (1-3): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    createShapeInteractively(scanner, shapes);
                    break;
                case "2":
                    System.out.println();
                    System.out.println("=== All Shapes ===");
                    System.out.println();
                    displayShapesAndAnalysis(shapes);
                    break;
                case "3":
                    running = false;
                    System.out.println("Goodbye!");
                    LOGGER.info("User exited interactive menu.");
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, or 3.");
                    break;
            }
        }

        scanner.close();
        LOGGER.info("3D Shape Analysis System finished.");
    }

    /**
     * Prompts the user to select a shape type and enter custom dimensions,
     * then creates the shape and adds it to the list.
     *
     * @param scanner the {@code Scanner} used for console input
     * @param shapes  the list to which the new shape will be added
     */
    static void createShapeInteractively(Scanner scanner, List<Shape3D> shapes) {
        System.out.println();
        System.out.println("Select shape type:");
        System.out.println("  1. Sphere");
        System.out.println("  2. Cube");
        System.out.println("  3. Cylinder");
        System.out.println("  4. Rectangular Prism");
        System.out.println("  5. Cone");
        System.out.print("Enter choice (1-5): ");

        String typeChoice = scanner.nextLine().trim();

        System.out.print("Enter a name for the shape: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter a color for the shape: ");
        String color = scanner.nextLine().trim();

        try {
            Shape3D newShape;
            switch (typeChoice) {
                case "1":
                    System.out.print("Enter radius: ");
                    double radius = Double.parseDouble(scanner.nextLine().trim());
                    newShape = new Sphere(name, color, radius);
                    break;
                case "2":
                    System.out.print("Enter side length: ");
                    double side = Double.parseDouble(scanner.nextLine().trim());
                    newShape = new Cube(name, color, side);
                    break;
                case "3":
                    System.out.print("Enter radius: ");
                    double cylRadius = Double.parseDouble(scanner.nextLine().trim());
                    System.out.print("Enter height: ");
                    double cylHeight = Double.parseDouble(scanner.nextLine().trim());
                    newShape = new Cylinder(name, color, cylRadius, cylHeight);
                    break;
                case "4":
                    System.out.print("Enter length: ");
                    double length = Double.parseDouble(scanner.nextLine().trim());
                    System.out.print("Enter width: ");
                    double width = Double.parseDouble(scanner.nextLine().trim());
                    System.out.print("Enter height: ");
                    double height = Double.parseDouble(scanner.nextLine().trim());
                    newShape = new RectangularPrism(name, color, length, width, height);
                    break;
                case "5":
                    System.out.print("Enter radius: ");
                    double coneRadius = Double.parseDouble(scanner.nextLine().trim());
                    System.out.print("Enter height: ");
                    double coneHeight = Double.parseDouble(scanner.nextLine().trim());
                    newShape = new Cone(name, color, coneRadius, coneHeight);
                    break;
                default:
                    System.out.println("Invalid shape type.");
                    LOGGER.warning("User entered invalid shape type: " + typeChoice);
                    return;
            }

            shapes.add(newShape);
            System.out.println();
            System.out.println("Shape created successfully!");
            System.out.printf("  %s%n", newShape);
            System.out.printf("  - Surface Area: %.2f square units%n", newShape.getSurfaceArea());
            System.out.printf("  - Volume: %.2f cubic units%n", newShape.getVolume());
            LOGGER.info("User created shape: " + newShape.getName());

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Shape was not created.");
            LOGGER.warning("Invalid number input from user: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
            LOGGER.warning("Shape creation failed: " + e.getMessage());
        }
    }
}

package com.csc205.project2.shapes;

import java.util.logging.Logger;

/**
 * Represents a three-dimensional cube defined by the length of one side.
 *
 * <p>A cube is a regular hexahedron — all six faces are congruent squares.
 * This class extends {@link Shape3D} and provides concrete implementations
 * for volume and surface-area calculations:</p>
 * <ul>
 *   <li><strong>Volume</strong> = s&sup3;</li>
 *   <li><strong>Surface Area</strong> = 6 s&sup2;</li>
 * </ul>
 *
 * <p><strong>Design patterns demonstrated:</strong></p>
 * <ul>
 *   <li><em>Inheritance</em> — extends the abstract {@code Shape3D} base class</li>
 *   <li><em>Polymorphism</em> — usable wherever a {@code Shape3D} or
 *       {@code ThreeDimensionalShape} reference is expected</li>
 *   <li><em>Encapsulation</em> — the {@code sideLength} field is private with
 *       validated access through a getter and setter</li>
 * </ul>
 *
 * @see Shape3D
 * @see ThreeDimensionalShape
 */
public class Cube extends Shape3D {

    private static final Logger LOGGER = Logger.getLogger(Cube.class.getName());

    private double sideLength;

    /**
     * Constructs a new {@code Cube} with the specified name, color, and
     * side length.
     *
     * <p>The side length is validated to ensure it is a positive value.
     * If it is zero or negative, an {@link IllegalArgumentException} is
     * thrown and a {@code SEVERE} message is logged.</p>
     *
     * @param name       the descriptive name for this cube (e.g. "Blue Box")
     * @param color      the color of this cube (e.g. "Blue")
     * @param sideLength the length of one side; must be greater than zero
     * @throws IllegalArgumentException if {@code sideLength} is zero or
     *                                  negative, or if {@code name}/{@code color}
     *                                  is {@code null} or blank
     */
    public Cube(String name, String color, double sideLength) {
        super(name, color);
        if (sideLength <= 0) {
            LOGGER.severe("Invalid sideLength (" + sideLength + ") for Cube '" + name + "'.");
            throw new IllegalArgumentException("Side length must be greater than zero.");
        }
        this.sideLength = sideLength;
        LOGGER.info("Created Cube '" + name + "' with sideLength=" + sideLength);
    }

    // ---------------------------------------------------------------
    // Calculation implementations
    // ---------------------------------------------------------------

    /**
     * Calculates the volume of this cube using the formula s&sup3;.
     *
     * @return the volume in cubic units
     */
    @Override
    public double calculateVolume() {
        return Math.pow(sideLength, 3);
    }

    /**
     * Calculates the surface area of this cube using the formula
     * 6 s&sup2;.
     *
     * @return the surface area in square units
     */
    @Override
    public double calculateSurfaceArea() {
        return 6.0 * Math.pow(sideLength, 2);
    }

    // ---------------------------------------------------------------
    // Getters and Setters
    // ---------------------------------------------------------------

    /**
     * Returns the side length of this cube.
     *
     * @return the side length
     */
    public double getSideLength() {
        return sideLength;
    }

    /**
     * Sets the side length of this cube.
     *
     * @param sideLength the new side length; must be greater than zero
     * @throws IllegalArgumentException if {@code sideLength} is zero or negative
     */
    public void setSideLength(double sideLength) {
        if (sideLength <= 0) {
            LOGGER.warning("Attempted to set invalid sideLength (" + sideLength + ") on Cube '" + getName() + "'.");
            throw new IllegalArgumentException("Side length must be greater than zero.");
        }
        LOGGER.info("Updating sideLength of Cube '" + getName() + "' from " + this.sideLength + " to " + sideLength);
        this.sideLength = sideLength;
    }

    // ---------------------------------------------------------------
    // Object overrides
    // ---------------------------------------------------------------

    /**
     * Returns a formatted string representation of this cube, including
     * its name, color, and side length.
     *
     * @return a human-readable description of this cube
     */
    @Override
    public String toString() {
        return "Cube {name='" + getName() + "'"
                + ", color='" + getColor() + "'"
                + ", sideLength=" + sideLength + "}";
    }
}

package com.csc205.project2.shapes;

import java.util.logging.Logger;

/**
 * Represents a three-dimensional sphere defined by its radius.
 *
 * <p>A sphere is a perfectly round geometrical object in three-dimensional
 * space. This class extends {@link Shape3D} and provides concrete
 * implementations for volume and surface-area calculations using the
 * standard mathematical formulas:</p>
 * <ul>
 *   <li><strong>Volume</strong> = (4/3) &pi; r&sup3;</li>
 *   <li><strong>Surface Area</strong> = 4 &pi; r&sup2;</li>
 * </ul>
 *
 * <p><strong>Design patterns demonstrated:</strong></p>
 * <ul>
 *   <li><em>Inheritance</em> — extends the abstract {@code Shape3D} base class</li>
 *   <li><em>Polymorphism</em> — can be used anywhere a {@code Shape3D} or
 *       {@code ThreeDimensionalShape} reference is expected</li>
 *   <li><em>Encapsulation</em> — the {@code radius} field is private with
 *       validated access through a getter and setter</li>
 * </ul>
 *
 * @see Shape3D
 * @see ThreeDimensionalShape
 */
public class Sphere extends Shape3D {

    private static final Logger LOGGER = Logger.getLogger(Sphere.class.getName());

    private double radius;

    /**
     * Constructs a new {@code Sphere} with the specified name, color, and
     * radius.
     *
     * <p>The radius is validated to ensure it is a positive value. If it is
     * zero or negative, an {@link IllegalArgumentException} is thrown and a
     * {@code SEVERE} message is logged.</p>
     *
     * @param name   the descriptive name for this sphere (e.g. "Red Ball")
     * @param color  the color of this sphere (e.g. "Red")
     * @param radius the radius of the sphere; must be greater than zero
     * @throws IllegalArgumentException if {@code radius} is zero or negative,
     *                                  or if {@code name}/{@code color} is
     *                                  {@code null} or blank
     */
    public Sphere(String name, String color, double radius) {
        super(name, color);
        if (radius <= 0) {
            LOGGER.severe("Invalid radius (" + radius + ") for Sphere '" + name + "'.");
            throw new IllegalArgumentException("Radius must be greater than zero.");
        }
        this.radius = radius;
        LOGGER.info("Created Sphere '" + name + "' with radius=" + radius);
    }

    // ---------------------------------------------------------------
    // Calculation implementations
    // ---------------------------------------------------------------

    /**
     * Calculates the volume of this sphere using the formula
     * (4/3) &pi; r&sup3;.
     *
     * @return the volume in cubic units
     */
    @Override
    public double calculateVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    /**
     * Calculates the surface area of this sphere using the formula
     * 4 &pi; r&sup2;.
     *
     * @return the surface area in square units
     */
    @Override
    public double calculateSurfaceArea() {
        return 4.0 * Math.PI * Math.pow(radius, 2);
    }

    // ---------------------------------------------------------------
    // Getters and Setters
    // ---------------------------------------------------------------

    /**
     * Returns the radius of this sphere.
     *
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets the radius of this sphere.
     *
     * @param radius the new radius; must be greater than zero
     * @throws IllegalArgumentException if {@code radius} is zero or negative
     */
    public void setRadius(double radius) {
        if (radius <= 0) {
            LOGGER.warning("Attempted to set invalid radius (" + radius + ") on Sphere '" + getName() + "'.");
            throw new IllegalArgumentException("Radius must be greater than zero.");
        }
        LOGGER.info("Updating radius of Sphere '" + getName() + "' from " + this.radius + " to " + radius);
        this.radius = radius;
    }

    // ---------------------------------------------------------------
    // Object overrides
    // ---------------------------------------------------------------

    /**
     * Returns a formatted string representation of this sphere, including
     * its name, color, and radius.
     *
     * @return a human-readable description of this sphere
     */
    @Override
    public String toString() {
        return "Sphere {name='" + getName() + "'"
                + ", color='" + getColor() + "'"
                + ", radius=" + radius + "}";
    }
}

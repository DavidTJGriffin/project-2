package com.csc205.project2.shapes;

import java.util.logging.Logger;

/**
 * Represents a three-dimensional cone defined by its radius and height.
 *
 * <p>A cone has a circular base that tapers to a single apex. This class
 * extends {@link Shape3D} and provides concrete implementations for volume
 * and surface-area calculations:</p>
 * <ul>
 *   <li><strong>Volume</strong> = (1/3) &pi; r&sup2; h</li>
 *   <li><strong>Surface Area</strong> = &pi; r (r + &radic;(h&sup2; + r&sup2;))</li>
 * </ul>
 *
 * <p>The surface-area formula uses the <em>slant height</em>
 * (&radic;(h&sup2; + r&sup2;)) to account for the lateral surface.</p>
 *
 * <p><strong>Design patterns demonstrated:</strong></p>
 * <ul>
 *   <li><em>Inheritance</em> — extends the abstract {@code Shape3D} base class</li>
 *   <li><em>Polymorphism</em> — usable wherever a {@code Shape3D} or
 *       {@code ThreeDimensionalShape} reference is expected</li>
 *   <li><em>Encapsulation</em> — fields are private with validated getters
 *       and setters</li>
 * </ul>
 *
 * @see Shape3D
 * @see ThreeDimensionalShape
 */
public class Cone extends Shape3D {

    private static final Logger LOGGER = Logger.getLogger(Cone.class.getName());

    private double radius;
    private double height;

    /**
     * Constructs a new {@code Cone} with the specified name, color, radius,
     * and height.
     *
     * <p>Both dimensions are validated to ensure they are positive values.
     * If either is zero or negative, an {@link IllegalArgumentException} is
     * thrown and a {@code SEVERE} message is logged.</p>
     *
     * @param name   the descriptive name for this cone
     * @param color  the color of this cone
     * @param radius the radius of the circular base; must be greater than zero
     * @param height the height of the cone; must be greater than zero
     * @throws IllegalArgumentException if {@code radius} or {@code height}
     *                                  is zero or negative, or if
     *                                  {@code name}/{@code color} is
     *                                  {@code null} or blank
     */
    public Cone(String name, String color, double radius, double height) {
        super(name, color);
        if (radius <= 0) {
            LOGGER.severe("Invalid radius (" + radius + ") for Cone '" + name + "'.");
            throw new IllegalArgumentException("Radius must be greater than zero.");
        }
        if (height <= 0) {
            LOGGER.severe("Invalid height (" + height + ") for Cone '" + name + "'.");
            throw new IllegalArgumentException("Height must be greater than zero.");
        }
        this.radius = radius;
        this.height = height;
        LOGGER.info("Created Cone '" + name + "' with radius=" + radius + ", height=" + height);
    }

    // ---------------------------------------------------------------
    // Calculation implementations
    // ---------------------------------------------------------------

    /**
     * Calculates the volume of this cone using the formula
     * (1/3) &pi; r&sup2; h.
     *
     * @return the volume in cubic units
     */
    @Override
    public double calculateVolume() {
        return (1.0 / 3.0) * Math.PI * Math.pow(radius, 2) * height;
    }

    /**
     * Calculates the surface area of this cone using the formula
     * &pi; r (r + &radic;(h&sup2; + r&sup2;)).
     *
     * <p>The slant height is computed internally as
     * {@code Math.sqrt(height² + radius²)}.</p>
     *
     * @return the surface area in square units
     */
    @Override
    public double calculateSurfaceArea() {
        double slantHeight = Math.sqrt(Math.pow(height, 2) + Math.pow(radius, 2));
        return Math.PI * radius * (radius + slantHeight);
    }

    // ---------------------------------------------------------------
    // Getters and Setters
    // ---------------------------------------------------------------

    /**
     * Returns the radius of this cone's circular base.
     *
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets the radius of this cone's circular base.
     *
     * @param radius the new radius; must be greater than zero
     * @throws IllegalArgumentException if {@code radius} is zero or negative
     */
    public void setRadius(double radius) {
        if (radius <= 0) {
            LOGGER.warning("Attempted to set invalid radius (" + radius + ") on Cone '" + getName() + "'.");
            throw new IllegalArgumentException("Radius must be greater than zero.");
        }
        LOGGER.info("Updating radius of Cone '" + getName() + "' from " + this.radius + " to " + radius);
        this.radius = radius;
    }

    /**
     * Returns the height of this cone.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of this cone.
     *
     * @param height the new height; must be greater than zero
     * @throws IllegalArgumentException if {@code height} is zero or negative
     */
    public void setHeight(double height) {
        if (height <= 0) {
            LOGGER.warning("Attempted to set invalid height (" + height + ") on Cone '" + getName() + "'.");
            throw new IllegalArgumentException("Height must be greater than zero.");
        }
        LOGGER.info("Updating height of Cone '" + getName() + "' from " + this.height + " to " + height);
        this.height = height;
    }

    /**
     * Computes the slant height of this cone.
     *
     * <p>The slant height is the distance from the apex to any point on
     * the circumference of the base, calculated as
     * &radic;(h&sup2; + r&sup2;).</p>
     *
     * @return the slant height
     */
    public double getSlantHeight() {
        return Math.sqrt(Math.pow(height, 2) + Math.pow(radius, 2));
    }

    // ---------------------------------------------------------------
    // Object overrides
    // ---------------------------------------------------------------

    /**
     * Returns a formatted string representation of this cone, including
     * its name, color, radius, and height.
     *
     * @return a human-readable description of this cone
     */
    @Override
    public String toString() {
        return "Cone {name='" + getName() + "'"
                + ", color='" + getColor() + "'"
                + ", radius=" + radius
                + ", height=" + height + "}";
    }
}

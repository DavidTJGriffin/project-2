package com.csc205.project2.shapes;

import java.util.logging.Logger;

/**
 * Represents a three-dimensional cylinder defined by its radius and height.
 *
 * <p>A cylinder has two parallel circular bases connected by a curved
 * surface. This class extends {@link Shape3D} and provides concrete
 * implementations for volume and surface-area calculations:</p>
 * <ul>
 *   <li><strong>Volume</strong> = &pi; r&sup2; h</li>
 *   <li><strong>Surface Area</strong> = 2 &pi; r (r + h)</li>
 * </ul>
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
public class Cylinder extends Shape3D {

    private static final Logger LOGGER = Logger.getLogger(Cylinder.class.getName());

    private double radius;
    private double height;

    /**
     * Constructs a new {@code Cylinder} with the specified name, color,
     * radius, and height.
     *
     * <p>Both dimensions are validated to ensure they are positive values.
     * If either is zero or negative, an {@link IllegalArgumentException} is
     * thrown and a {@code SEVERE} message is logged.</p>
     *
     * @param name   the descriptive name for this cylinder
     * @param color  the color of this cylinder
     * @param radius the radius of the circular base; must be greater than zero
     * @param height the height of the cylinder; must be greater than zero
     * @throws IllegalArgumentException if {@code radius} or {@code height}
     *                                  is zero or negative, or if
     *                                  {@code name}/{@code color} is
     *                                  {@code null} or blank
     */
    public Cylinder(String name, String color, double radius, double height) {
        super(name, color);
        if (radius <= 0) {
            LOGGER.severe("Invalid radius (" + radius + ") for Cylinder '" + name + "'.");
            throw new IllegalArgumentException("Radius must be greater than zero.");
        }
        if (height <= 0) {
            LOGGER.severe("Invalid height (" + height + ") for Cylinder '" + name + "'.");
            throw new IllegalArgumentException("Height must be greater than zero.");
        }
        this.radius = radius;
        this.height = height;
        LOGGER.info("Created Cylinder '" + name + "' with radius=" + radius + ", height=" + height);
    }

    // ---------------------------------------------------------------
    // Calculation implementations
    // ---------------------------------------------------------------

    /**
     * Calculates the volume of this cylinder using the formula
     * &pi; r&sup2; h.
     *
     * @return the volume in cubic units
     */
    @Override
    public double calculateVolume() {
        return Math.PI * Math.pow(radius, 2) * height;
    }

    /**
     * Calculates the surface area of this cylinder using the formula
     * 2 &pi; r (r + h).
     *
     * @return the surface area in square units
     */
    @Override
    public double calculateSurfaceArea() {
        return 2.0 * Math.PI * radius * (radius + height);
    }

    // ---------------------------------------------------------------
    // Getters and Setters
    // ---------------------------------------------------------------

    /**
     * Returns the radius of this cylinder's circular base.
     *
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets the radius of this cylinder's circular base.
     *
     * @param radius the new radius; must be greater than zero
     * @throws IllegalArgumentException if {@code radius} is zero or negative
     */
    public void setRadius(double radius) {
        if (radius <= 0) {
            LOGGER.warning("Attempted to set invalid radius (" + radius + ") on Cylinder '" + getName() + "'.");
            throw new IllegalArgumentException("Radius must be greater than zero.");
        }
        LOGGER.info("Updating radius of Cylinder '" + getName() + "' from " + this.radius + " to " + radius);
        this.radius = radius;
    }

    /**
     * Returns the height of this cylinder.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of this cylinder.
     *
     * @param height the new height; must be greater than zero
     * @throws IllegalArgumentException if {@code height} is zero or negative
     */
    public void setHeight(double height) {
        if (height <= 0) {
            LOGGER.warning("Attempted to set invalid height (" + height + ") on Cylinder '" + getName() + "'.");
            throw new IllegalArgumentException("Height must be greater than zero.");
        }
        LOGGER.info("Updating height of Cylinder '" + getName() + "' from " + this.height + " to " + height);
        this.height = height;
    }

    // ---------------------------------------------------------------
    // Object overrides
    // ---------------------------------------------------------------

    /**
     * Returns a formatted string representation of this cylinder, including
     * its name, color, radius, and height.
     *
     * @return a human-readable description of this cylinder
     */
    @Override
    public String toString() {
        return "Cylinder {name='" + getName() + "'"
                + ", color='" + getColor() + "'"
                + ", radius=" + radius
                + ", height=" + height + "}";
    }
}

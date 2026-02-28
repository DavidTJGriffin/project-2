package com.csc205.project2.shapes;

import java.util.logging.Logger;

/**
 * Represents a three-dimensional rectangular prism (box) defined by its
 * length, width, and height.
 *
 * <p>A rectangular prism has six rectangular faces where opposite faces
 * are congruent. This class extends {@link Shape3D} and provides concrete
 * implementations for volume and surface-area calculations:</p>
 * <ul>
 *   <li><strong>Volume</strong> = l &times; w &times; h</li>
 *   <li><strong>Surface Area</strong> = 2 (lw + lh + wh)</li>
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
public class RectangularPrism extends Shape3D {

    private static final Logger LOGGER = Logger.getLogger(RectangularPrism.class.getName());

    private double length;
    private double width;
    private double height;

    /**
     * Constructs a new {@code RectangularPrism} with the specified name,
     * color, length, width, and height.
     *
     * <p>All three dimensions are validated to ensure they are positive
     * values. If any dimension is zero or negative, an
     * {@link IllegalArgumentException} is thrown and a {@code SEVERE}
     * message is logged.</p>
     *
     * @param name   the descriptive name for this prism
     * @param color  the color of this prism
     * @param length the length of the prism; must be greater than zero
     * @param width  the width of the prism; must be greater than zero
     * @param height the height of the prism; must be greater than zero
     * @throws IllegalArgumentException if any dimension is zero or negative,
     *                                  or if {@code name}/{@code color} is
     *                                  {@code null} or blank
     */
    public RectangularPrism(String name, String color, double length, double width, double height) {
        super(name, color);
        if (length <= 0) {
            LOGGER.severe("Invalid length (" + length + ") for RectangularPrism '" + name + "'.");
            throw new IllegalArgumentException("Length must be greater than zero.");
        }
        if (width <= 0) {
            LOGGER.severe("Invalid width (" + width + ") for RectangularPrism '" + name + "'.");
            throw new IllegalArgumentException("Width must be greater than zero.");
        }
        if (height <= 0) {
            LOGGER.severe("Invalid height (" + height + ") for RectangularPrism '" + name + "'.");
            throw new IllegalArgumentException("Height must be greater than zero.");
        }
        this.length = length;
        this.width = width;
        this.height = height;
        LOGGER.info("Created RectangularPrism '" + name + "' with length=" + length
                + ", width=" + width + ", height=" + height);
    }

    // ---------------------------------------------------------------
    // Calculation implementations
    // ---------------------------------------------------------------

    /**
     * Calculates the volume of this rectangular prism using the formula
     * l &times; w &times; h.
     *
     * @return the volume in cubic units
     */
    @Override
    public double calculateVolume() {
        return length * width * height;
    }

    /**
     * Calculates the surface area of this rectangular prism using the
     * formula 2 (lw + lh + wh).
     *
     * @return the surface area in square units
     */
    @Override
    public double calculateSurfaceArea() {
        return 2.0 * (length * width + length * height + width * height);
    }

    // ---------------------------------------------------------------
    // Getters and Setters
    // ---------------------------------------------------------------

    /**
     * Returns the length of this rectangular prism.
     *
     * @return the length
     */
    public double getLength() {
        return length;
    }

    /**
     * Sets the length of this rectangular prism.
     *
     * @param length the new length; must be greater than zero
     * @throws IllegalArgumentException if {@code length} is zero or negative
     */
    public void setLength(double length) {
        if (length <= 0) {
            LOGGER.warning("Attempted to set invalid length (" + length + ") on RectangularPrism '" + getName() + "'.");
            throw new IllegalArgumentException("Length must be greater than zero.");
        }
        LOGGER.info("Updating length of RectangularPrism '" + getName() + "' from " + this.length + " to " + length);
        this.length = length;
    }

    /**
     * Returns the width of this rectangular prism.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the width of this rectangular prism.
     *
     * @param width the new width; must be greater than zero
     * @throws IllegalArgumentException if {@code width} is zero or negative
     */
    public void setWidth(double width) {
        if (width <= 0) {
            LOGGER.warning("Attempted to set invalid width (" + width + ") on RectangularPrism '" + getName() + "'.");
            throw new IllegalArgumentException("Width must be greater than zero.");
        }
        LOGGER.info("Updating width of RectangularPrism '" + getName() + "' from " + this.width + " to " + width);
        this.width = width;
    }

    /**
     * Returns the height of this rectangular prism.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of this rectangular prism.
     *
     * @param height the new height; must be greater than zero
     * @throws IllegalArgumentException if {@code height} is zero or negative
     */
    public void setHeight(double height) {
        if (height <= 0) {
            LOGGER.warning("Attempted to set invalid height (" + height + ") on RectangularPrism '" + getName() + "'.");
            throw new IllegalArgumentException("Height must be greater than zero.");
        }
        LOGGER.info("Updating height of RectangularPrism '" + getName() + "' from " + this.height + " to " + height);
        this.height = height;
    }

    // ---------------------------------------------------------------
    // Object overrides
    // ---------------------------------------------------------------

    /**
     * Returns a formatted string representation of this rectangular prism,
     * including its name, color, length, width, and height.
     *
     * @return a human-readable description of this rectangular prism
     */
    @Override
    public String toString() {
        return "RectangularPrism {name='" + getName() + "'"
                + ", color='" + getColor() + "'"
                + ", length=" + length
                + ", width=" + width
                + ", height=" + height + "}";
    }
}

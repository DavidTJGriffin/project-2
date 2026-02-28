package com.csc205.project2.shapes;

import java.util.logging.Logger;

/**
 * Abstract base class for all three-dimensional geometric shapes.
 *
 * <p>{@code Shape3D} sits in the middle of the inheritance hierarchy: it
 * implements the {@link ThreeDimensionalShape} interface and provides
 * concrete {@link #getVolume()} and {@link #getSurfaceArea()} methods that
 * delegate to protected abstract calculation methods. Concrete subclasses
 * (such as {@link Sphere}, {@link Cube}, and {@link Cylinder}) supply the
 * actual formulas by overriding {@link #calculateVolume()} and
 * {@link #calculateSurfaceArea()}.</p>
 *
 * <p>This class also holds common properties shared by every shape —
 * {@code name} and {@code color} — and enforces that they are never
 * {@code null} or blank at construction time.</p>
 *
 * <p><strong>Design patterns demonstrated:</strong></p>
 * <ul>
 *   <li><em>Inheritance</em> — subclasses extend this class to reuse
 *       common state and behavior</li>
 *   <li><em>Abstraction</em> — calculation details are deferred to subclasses
 *       via the Template Method pattern</li>
 *   <li><em>Encapsulation</em> — fields are private with validated getters
 *       and setters</li>
 *   <li><em>Polymorphism</em> — any {@code Shape3D} reference can hold a
 *       concrete shape and invoke its unique calculations</li>
 * </ul>
 *
 * @see ThreeDimensionalShape
 * @see Sphere
 * @see Cube
 * @see Cylinder
 * @see RectangularPrism
 * @see Cone
 */
public abstract class Shape3D implements ThreeDimensionalShape {

    private static final Logger LOGGER = Logger.getLogger(Shape3D.class.getName());

    private String name;
    private String color;

    /**
     * Constructs a new {@code Shape3D} with the given name and color.
     *
     * <p>Both parameters are validated to ensure they are neither {@code null}
     * nor blank. If validation fails, an {@link IllegalArgumentException} is
     * thrown and a {@code SEVERE} message is logged.</p>
     *
     * @param name  the descriptive name for this shape (e.g. "Red Ball")
     * @param color the color of this shape (e.g. "Red")
     * @throws IllegalArgumentException if {@code name} or {@code color} is
     *                                  {@code null} or blank
     */
    public Shape3D(String name, String color) {
        if (name == null || name.isBlank()) {
            LOGGER.severe("Attempted to create a Shape3D with a null or blank name.");
            throw new IllegalArgumentException("Name must not be null or blank.");
        }
        if (color == null || color.isBlank()) {
            LOGGER.severe("Attempted to create a Shape3D with a null or blank color.");
            throw new IllegalArgumentException("Color must not be null or blank.");
        }
        this.name = name;
        this.color = color;
        LOGGER.info("Created Shape3D: name='" + name + "', color='" + color + "'");
    }

    // ---------------------------------------------------------------
    // ThreeDimensionalShape contract — delegates to abstract methods
    // ---------------------------------------------------------------

    /**
     * Returns the volume of this shape by delegating to the subclass-specific
     * {@link #calculateVolume()} implementation.
     *
     * @return the volume in cubic units
     */
    @Override
    public double getVolume() {
        double volume = calculateVolume();
        LOGGER.info("Calculated volume for '" + name + "': " + volume);
        return volume;
    }

    /**
     * Returns the surface area of this shape by delegating to the
     * subclass-specific {@link #calculateSurfaceArea()} implementation.
     *
     * @return the surface area in square units
     */
    @Override
    public double getSurfaceArea() {
        double surfaceArea = calculateSurfaceArea();
        LOGGER.info("Calculated surface area for '" + name + "': " + surfaceArea);
        return surfaceArea;
    }

    // ---------------------------------------------------------------
    // Abstract methods for subclasses
    // ---------------------------------------------------------------

    /**
     * Calculates the volume of this specific shape.
     *
     * <p>Subclasses must provide the mathematical formula appropriate to
     * their geometry.</p>
     *
     * @return the volume in cubic units
     */
    public abstract double calculateVolume();

    /**
     * Calculates the surface area of this specific shape.
     *
     * <p>Subclasses must provide the mathematical formula appropriate to
     * their geometry.</p>
     *
     * @return the surface area in square units
     */
    public abstract double calculateSurfaceArea();

    // ---------------------------------------------------------------
    // Getters and Setters
    // ---------------------------------------------------------------

    /**
     * Returns the name of this shape.
     *
     * @return the shape name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this shape.
     *
     * @param name the new name; must not be {@code null} or blank
     * @throws IllegalArgumentException if {@code name} is {@code null} or
     *                                  blank
     */
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            LOGGER.warning("Attempted to set a null or blank name on '" + this.name + "'.");
            throw new IllegalArgumentException("Name must not be null or blank.");
        }
        LOGGER.info("Updating name from '" + this.name + "' to '" + name + "'");
        this.name = name;
    }

    /**
     * Returns the color of this shape.
     *
     * @return the shape color
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color of this shape.
     *
     * @param color the new color; must not be {@code null} or blank
     * @throws IllegalArgumentException if {@code color} is {@code null} or
     *                                  blank
     */
    public void setColor(String color) {
        if (color == null || color.isBlank()) {
            LOGGER.warning("Attempted to set a null or blank color on '" + name + "'.");
            throw new IllegalArgumentException("Color must not be null or blank.");
        }
        LOGGER.info("Updating color from '" + this.color + "' to '" + color + "'");
        this.color = color;
    }

    // ---------------------------------------------------------------
    // Object overrides
    // ---------------------------------------------------------------

    /**
     * Returns a formatted string representation of this shape, including its
     * type, name, and color. Subclasses should override this method and
     * append shape-specific dimensions.
     *
     * @return a human-readable description of this shape
     */
    @Override
    public String toString() {
        return getClass().getSimpleName()
                + " {name='" + name + "'"
                + ", color='" + color + "'}";
    }
}

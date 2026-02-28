package com.csc205.project2.shapes;

/**
 * Defines the contract for all three-dimensional geometric shapes.
 *
 * <p>This interface establishes the required behaviors that every 3D shape
 * must support: calculating surface area and volume. It serves as the top
 * of the inheritance hierarchy and demonstrates the <strong>abstraction</strong>
 * principle of object-oriented design by specifying <em>what</em> a shape
 * can do without prescribing <em>how</em> it does it.</p>
 *
 * <p><strong>Design patterns demonstrated:</strong></p>
 * <ul>
 *   <li><em>Abstraction</em> — hides implementation details behind a common API</li>
 *   <li><em>Polymorphism</em> — allows any implementing class to be used
 *       interchangeably through this interface type</li>
 * </ul>
 *
 * @see Shape3D
 */
public interface ThreeDimensionalShape {

    /**
     * Calculates and returns the surface area of this three-dimensional shape.
     *
     * @return the surface area in square units
     */
    double getSurfaceArea();

    /**
     * Calculates and returns the volume of this three-dimensional shape.
     *
     * @return the volume in cubic units
     */
    double getVolume();
}

package org.opengis.cite.pipelineml10;

/**
 * An enumerated type defining all recognized test run arguments.
 */
public enum TestRunArg {

  /**
   * An absolute URI that refers to a representation of the test subject or
   * metadata about it.
   */
  IUT,
  /**
   * An absolute URI referring to a GML application schema (XML Schema)
   * resource.
   */
  XSD,
  /**
   * An absolute URI referring to a Schematron schema resource.
   */
  SCH;

  @Override
  public String toString() {
    return name().toLowerCase();
  }
}

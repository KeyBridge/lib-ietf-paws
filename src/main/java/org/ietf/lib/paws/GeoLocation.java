/*
 * Copyright 2017 Key Bridge LLC. All rights reserved.
 * Use is subject to license terms.
 *
 * Software Code is protected by Copyrights. Author hereby reserves all rights
 * in and to Copyrights and no license is granted under Copyrights in this
 * Software License Agreement.
 *
 * Key Bridge LLC generally licenses Copyrights for commercialization pursuant to
 * the terms of either a Standard Software Source Code License Agreement or a
 * Standard Product License Agreement. A copy of either Agreement can be
 * obtained upon request from: info@keybridgewireless.com
 */
package org.ietf.lib.paws;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.ietf.lib.paws.adapter.XmlGeometryAdapter;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

/**
 * 5.1. GeoLocation
 * <p>
 * <img alt="clazz" src="doc-files/geoLocation.png">
 * <p>
 * GeoLocation is used to specify a single point with optional uncertainty or a
 * region described by a polygon.
 * <p>
 * Key Bridge implementation: GeoLocation values are encoded using SFA Well
 * Known Text.
 * <p>
 * Deprecated: <em> GeoLocation is represented using geometric shapes defined in
 * Section 5 of "GEOPRIV Presence Information Data Format Location Object"
 * [RFC5491], where a "point" with uncertainty is represented using the Ellipse
 * shape; and a region is represented using the Polygon shape.</em>
 * <p>
 * The coordinates are expressed using the WGS84 datum [WGS-84], and units are
 * degrees or meters. GeoLocation MAY also include a confidence level, expressed
 * as a percentage. The confidence and uncertainty parameters may be required by
 * some rulesets (see also [RFC7459]).
 *
 * @author Key Bridge LLC
 * @see <a href="http://www.opengeospatial.org/standards/sfa">Simple Feature
 * Access</a>
 * @see
 * <a href="https://en.wikipedia.org/wiki/Well-known_text_representation_of_geometry">Well-known
 * text</a>
 * @see <a href="https://tools.ietf.org/html/rfc5491">RFC5491 (not used</a>
 */
@XmlRootElement(name = "GeoLocation")
@XmlType(name = "GeoLocation")
@XmlAccessorType(XmlAccessType.FIELD)
public class GeoLocation {

  /**
   * The GeoLocation as a point.
   * <p>
   * Key Bridge: This represents the device instant geographic position and is
   * always REQUIRED.
   */
  @XmlJavaTypeAdapter(XmlGeometryAdapter.class)
  @XmlElement(required = true)
  private Point point;
  /**
   * The GeoLocation as a region.
   * <p>
   * Key Bridge: This represents the device operation area (/region). Valid
   * geometry is a POLYGON to represent a contained operating area or region.
   */
  @XmlJavaTypeAdapter(XmlGeometryAdapter.class)
  private Polygon region;
  /**
   * The location confidence level, as a percentage, MAY be provided. When this
   * parameter is not provided, the default value is 95. Valid values range from
   * 0 to 100, but, in practice, 100% confidence is not achievable. The
   * confidence value is meaningful only when GeoLocation refers to a point with
   * uncertainty.
   *
   * @deprecated use {@code uncertainty} instead
   */
  @Deprecated
  private Integer confidence;
  /**
   * Key Bridge extension. (Optional)
   * <p>
   * The horizontal position uncertainty / error margin, in meters. For most GPS
   * read positions this will be up to but typically not more than +- 50 meters.
   */
  private Integer uncertainty;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  /**
   * Get the instant device position.
   *
   * @return the device position
   */
  public Point getPoint() {
    return point;
  }

  /**
   * Set the instant device position. This represents the device instant
   * geographic position.
   *
   * @param point the device position
   */
  public void setPoint(Point point) {
    this.point = point;
  }

  /**
   * Get the device operation area (/region).
   *
   * @return the device operation area
   */
  public Polygon getRegion() {
    return region;
  }

  /**
   * Set the device operation area (/region). Valid geometry is a POLYGON to
   * represent a contained operating area or region.
   *
   * @param region the device operation area
   */
  public void setRegion(Polygon region) {
    this.region = region;
  }

  public Integer getConfidence() {
    return confidence;
  }

  public void setConfidence(Integer confidence) {
    this.confidence = confidence;
  }

  /**
   * Get the horizontal position uncertainty / error margin, in meters.
   *
   * @return the horizontal position uncertainty (m)
   */
  public Integer getUncertainty() {
    return uncertainty;
  }

  /**
   * Set the horizontal position uncertainty / error margin, in meters. For most
   * GPS read positions this will be up to but typically not more than +- 50
   * meters.
   *
   * @param uncertainty the horizontal position uncertainty (m)
   */
  public void setUncertainty(Integer uncertainty) {
    this.uncertainty = uncertainty;
  }//</editor-fold>

  /**
   * Determine if a point or region is specified.
   *
   * @return TRUE if either are specified.
   */
  public boolean isValid() {
    return point != null || region != null;
  }

  /**
   * Validate this instance.
   *
   * @throws Exception describing the invalid configuration
   */
  public void validate() throws Exception {
    if (point == null) {
      throw new Exception("GeoLocation::point is required");
    }
  }

  @Override
  public String toString() {
    return "GeoLocation{" + "point=" + point + ", region=" + region + ", confidence=" + confidence + ", uncertainty=" + uncertainty + '}';
  }

}

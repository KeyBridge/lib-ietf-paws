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
package org.ietf.paws;

import ch.keybridge.lib.xml.adapter.XmlGeometryAdapter;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * GeoLocation is used to specify a single point with optional uncertainty or a
 * region described by a polygon.
 * <p>
 * These are represented using geometric shapes defined in Section 5 of "GEOPRIV
 * Presence Information Data Format Location Object" [RFC5491], where a "point"
 * with uncertainty is represented using the Ellipse shape; and a region is
 * represented using the Polygon shape.
 * <p>
 * The coordinates are expressed using the WGS84 datum [WGS-84], and units are
 * degrees or meters. GeoLocation MAY also include a confidence level, expressed
 * as a percentage. The confidence and uncertainty parameters may be required by
 * some rulesets (see also [RFC7459]).
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "GeoLocation")
@XmlType(name = "GeoLocation")
@XmlAccessorType(XmlAccessType.FIELD)
public class GeoLocation {

  /**
   * The GeoLocation as a point. Paradoxically, a "point" is parameterized using
   * an Ellipse, where the center represents the location of the point and the
   * distances along the major and minor axes represent the uncertainty. The
   * uncertainty values may be required, depending on the ruleset. Exactly one
   * of "point" or "region" MUST be present.
   */
  @XmlJavaTypeAdapter(XmlGeometryAdapter.class)
  @XmlElement(required = true)
  private Point point;
  /**
   * The GeoLocation as a region. Exactly one of "point" or "region" MUST be
   * present.
   */
  @XmlJavaTypeAdapter(XmlGeometryAdapter.class)
  private Polygon region;
  /**
   * The location confidence level, as a percentage, MAY be provided. When this
   * parameter is not provided, the default value is 95. Valid values range from
   * 0 to 100, but, in practice, 100% confidence is not achievable. The
   * confidence value is meaningful only when GeoLocation refers to a point with
   * uncertainty.
   */
  private Integer confidence;

  public Point getPoint() {
    return point;
  }

  public void setPoint(Point point) {
    this.point = point;
  }

  public Polygon getRegion() {
    return region;
  }

  public void setRegion(Polygon region) {
    this.region = region;
  }

  public Integer getConfidence() {
    return confidence;
  }

  public void setConfidence(Integer confidence) {
    this.confidence = confidence;
  }

}

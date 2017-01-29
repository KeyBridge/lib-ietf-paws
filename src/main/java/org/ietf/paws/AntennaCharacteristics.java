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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 5.3. AntennaCharacteristics
 * <p>
 * Antenna characteristics provide additional information, such as the antenna
 * height, antenna type, etc. Whether antenna characteristics must be provided
 * in a request depends on the device type and ruleset. Additionally, a
 * parameter marked as optional may be required by some rulesets.
 * <pre>
 * +------------------------------------+
 * |AntennaCharacteristics              |
 * +-------------------------+----------+
 * |height:float             | OPTIONAL |
 * |heightType:enum          | OPTIONAL |
 * |heightUncertainty:float  | OPTIONAL |
 * |.........................|..........|
 * |*characteristics:        | OPTIONAL |
 * |   various               |          |
 * +-------------------------+----------+
 * </pre>
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "AntennaCharacteristics")
@XmlType(name = "AntennaCharacteristics")
@XmlAccessorType(XmlAccessType.FIELD)
public class AntennaCharacteristics {

  /**
   * The antenna height in meters. Note that the height may be negative.
   */
  public Double height;
  /**
   * Valid values are:
   * <p>
   * AGL - Above Ground Level (default)
   * <p>
   * AMSL - Above Mean Sea Level
   */
  public String heightType;
  /**
   * The height uncertainty in meters.
   */
  public Double heightUncertainty;

//      direction
//    radiation pattern
//    gain
//    polarization
  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

  public String getHeightType() {
    return heightType;
  }

  public void setHeightType(String heightType) {
    this.heightType = heightType;
  }

  public Double getHeightUncertainty() {
    return heightUncertainty;
  }

  public void setHeightUncertainty(Double heightUncertainty) {
    this.heightUncertainty = heightUncertainty;
  }

}

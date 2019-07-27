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

import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.ietf.lib.paws.adapter.XmlRadiationPatternAdapter;
import org.ietf.lib.paws.type.AntennaHeightType;
import org.ietf.lib.paws.type.AntennaPolarizationType;

/**
 * 5.3. AntennaCharacteristics
 * <p>
 * <img src="doc-files/antennaCharacteristics.png">
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
 * <p>
 * Key Bridge: The following fields are expected for FIXED device types: antenna
 * rotation, radiation pattern, gain, polarization.
 *
 * @author Key Bridge LLC
 * @since v0.7.0 add fields to enhance FIXED type transmitter support
 */
@XmlRootElement(name = "AntennaCharacteristics")
@XmlType(name = "AntennaCharacteristics")
@XmlAccessorType(XmlAccessType.FIELD)
public class AntennaCharacteristics {

  /**
   * The antenna height in meters. <strike>Note that the height may be
   * negative.</strike>
   * <p>
   * Key Bridge: Antenna height must be zero or a positive value. For negative
   * heights (e.g. when operating in a mine) use zero meters above ground level.
   */
  private Double height;
  /**
   * Antenna radiation center height (meters). Valid values are: AGL - Above
   * Ground Level (default); AMSL - Above Mean Sea Level
   */
  private AntennaHeightType heightType = AntennaHeightType.AGL;
  /**
   * The height uncertainty in meters.
   */
  private Double heightUncertainty;

  /**
   * The antenna polarization;
   *
   * @since v0.7.0 added to enhance FIXED type transmitter support
   */
  private AntennaPolarizationType polarization;

  /**
   * Key Bridge: The antenna radiation pattern.
   * <p>
   * Gain of an antenna is defined (IEEE 1983) as "the ratio of the radiation
   * intensity, in a given direction, to the radiation intensity that would be
   * obtained if the power accepted by the antenna were radiated isotropically."
   * <p>
   * If the direction is not specified, the direction of maximum radiation
   * intensity is implied.
   * <p>
   * Maximum/Mininum value = 0 dBi, -90dBi
   * <p>
   * Developer note: Some NSMA files that use a dimensionless ratio (between [0
   * and 1] vs. dBi) include the value of zero. This creates negative infinities
   * when translating to deciBel (dB) as 10 * log(value/1). Check and correct
   * for this by pegging the minimum input to the default minimum value,
   * typically at -90 dB (equal 1/1,000,000 power ratio).
   *
   * @since v0.7.0 added to enhance FIXED type transmitter support
   */
  @XmlJavaTypeAdapter(value = XmlRadiationPatternAdapter.class)
  private Map<Double, Double> radiationPattern;

  /**
   * Key Bridge: The antenna maximum gain (dBi relative to an isotropic
   * radiator). Default value is zero.
   * <p>
   * Gain is the ratio (in decibels: dBi) of the power required at the input of
   * a loss-free reference antenna to the power supplied to the input of the
   * given antenna to produce, in a given direction, the same field strength or
   * the same power-flux density at the same distance. This information should
   * be available from the specification sheet included with the antenna at the
   * time of purchase.
   * <p>
   * This is the gain of the antenna at the maximum pattern value. {@code gain}
   * is really only needed if a pattern is not provided, wherein a standard
   * DIPOLE antenna with the indicated gain should be assumed.
   *
   * @since v0.7.0 added to enhance FIXED type transmitter support
   */
  private Double gain;

  /**
   * Key Bridge: The offset in degrees azimuth [0, 360] from true North that the
   * antenna radiation pattern should be rotated.
   *
   * @since v0.7.0 added to enhance FIXED type transmitter support
   */
  private Double rotation;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

  public AntennaHeightType getHeightType() {
    return heightType;
  }

  public void setHeightType(AntennaHeightType heightType) {
    this.heightType = heightType;
  }

  public Double getHeightUncertainty() {
    return heightUncertainty;
  }

  public void setHeightUncertainty(Double heightUncertainty) {
    this.heightUncertainty = heightUncertainty;
  }

  public AntennaPolarizationType getPolarization() {
    return polarization;
  }

  public void setPolarization(AntennaPolarizationType polarization) {
    this.polarization = polarization;
  }

  public Map<Double, Double> getRadiationPattern() {
    return radiationPattern;
  }

  public void setRadiationPattern(Map<Double, Double> radiationPattern) {
    this.radiationPattern = radiationPattern;
  }

  public Double getGain() {
    return gain;
  }

  public void setGain(Double gain) {
    this.gain = gain;
  }

  public Double getRotation() {
    return rotation;
  }

  public void setRotation(Double rotation) {
    this.rotation = rotation;
  }//</editor-fold>

}

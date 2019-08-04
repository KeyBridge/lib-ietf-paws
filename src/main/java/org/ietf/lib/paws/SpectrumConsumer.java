/*
 * Copyright 2019 Key Bridge. All rights reserved. Use is subject to license
 * terms.
 *
 * This software code is protected by Copyrights and remains the property of
 * Key Bridge and its suppliers, if any. Key Bridge reserves all rights in and to
 * Copyrights and no license is granted under Copyrights in this Software
 * License Agreement.
 *
 * Key Bridge generally licenses Copyrights for commercialization pursuant to
 * the terms of either a Standard Software Source Code License Agreement or a
 * Standard Product License Agreement. A copy of either Agreement can be
 * obtained upon request by sending an email to info@keybridgewireless.com.
 *
 * All information contained herein is the property of Key Bridge and its
 * suppliers, if any. The intellectual and technical concepts contained herein
 * are proprietary.
 */
package org.ietf.lib.paws;

/**
 * Key Bridge proprietary white space spectrum information data transfer object.
 * <p>
 * <p>
 * A {@code SpectrumConsumer} conveys information about a wireless service,
 * which may be a transmitter or receiver. This provides detail and context
 * about incumbent / blocking services in a SpectrumInfo response
 *
 * @author Key Bridge
 * @since v0.20.0 created 08/04/19 in rewrite of SpectrumInfo class.
 */
public class SpectrumConsumer implements Comparable<SpectrumConsumer> {

  /**
   * The referenced service assigned UUID. This may be used to identify the
   * service in the Key Bridge database if further details are required.
   */
  private String uuid;
  /**
   * The service authorization. This is typically a call sign or license
   * authorization.
   */
  private String authorization;
  /**
   * The service type code.
   */
  private String type;
  /**
   * The offset in degrees azimuth [0, 360] from true North from the inquiring
   * position to the referenced service. (degrees)
   */
  private double heading;
  /**
   * The distance from the inquiring position to the referenced service. (km)
   */
  private double distance;
  /**
   * The transmitting device’s current effective radiated power (ERP), measured
   * in dBW (decibel Watt). For receiving stations this value should be set to
   * NULL.
   */
  private Double erp;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getAuthorization() {
    return authorization;
  }

  public void setAuthorization(String authorization) {
    this.authorization = authorization;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getHeading() {
    return heading;
  }

  public void setHeading(double heading) {
    this.heading = heading;
  }

  public double getDistance() {
    return distance;
  }

  public void setDistance(double distance) {
    this.distance = distance;
  }

  public Double getErp() {
    return erp;
  }

  public void setErp(Double erp) {
    this.erp = erp;
  }//</editor-fold>

  /**
   * Compare by distance. If equidistant, then compare by authorization.
   *
   * @param o the other instance
   * @return the sort order
   */
  @Override
  public int compareTo(SpectrumConsumer o) {
    return distance == o.getDistance()
           ? authorization.compareTo(o.getAuthorization())
           : Double.compare(distance, o.getDistance());
  }

  @Override
  public String toString() {
    return "SpectrumConsumer{" + "uuid=" + uuid + ", authorization=" + authorization + ", type=" + type + ", heading=" + heading + ", distance=" + distance + ", erp=" + erp + '}';
  }

}

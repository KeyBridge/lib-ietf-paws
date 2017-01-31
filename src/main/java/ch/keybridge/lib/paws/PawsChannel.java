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
package ch.keybridge.lib.paws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Key Bridge LLC
 */
@XmlType(name = "PawsChannel")
@XmlRootElement(name = "PawsChannel")
@XmlAccessorType(XmlAccessType.FIELD)
public class PawsChannel {

  /**
   * The colloquial channel name. This corresponds to the channel number
   * identified in rule or regulation. e.g. "VHF4"
   */
  @XmlAttribute(name = "name", required = true)
  private String name;

  /**
   * The maximum (or end) frequency of the indicated name in MHz.
   */
  @XmlElement(required = true)
  private double frequencyMax;
  /**
   * The minimum (or start) frequency of the indicated name in MHz.
   */
  @XmlElement(required = true)
  private double frequencyMin;
  /**
   * Indicator that the device operation is allowed or forbidden.
   */
  @XmlElement(required = true)
  private boolean allowed;
  /**
   * The maximum allowable EIRP value on this channel. (dBW)
   */
  @XmlElement(required = true)
  private double maxPowerDBW;

  /**
   * A list of co-channel services.
   */
  @XmlList
  private List<String> servicesCo;
  /**
   * A list of blocking adjacent channel services.
   */
  @XmlList
  private List<String> servicesAdj;
  /**
   * A list of blocking taboo-channel services.
   */
  @XmlList
  private List<String> servicesTaboo;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getFrequencyMax() {
    return frequencyMax;
  }

  public void setFrequencyMax(double frequencyMax) {
    this.frequencyMax = frequencyMax;
  }

  public double getFrequencyMin() {
    return frequencyMin;
  }

  public void setFrequencyMin(double frequencyMin) {
    this.frequencyMin = frequencyMin;
  }

  public boolean isAllowed() {
    return allowed;
  }

  public void setAllowed(boolean allowed) {
    this.allowed = allowed;
  }

  public double getMaxPowerDBW() {
    return maxPowerDBW;
  }

  public void setMaxPowerDBW(double maxPowerDBW) {
    this.maxPowerDBW = maxPowerDBW;
  }

  public List<String> getServicesCo() {
    if (servicesCo == null) {
      servicesCo = new ArrayList<>();
    }
    return servicesCo;
  }

  public void setServicesCo(List<String> servicesCo) {
    this.servicesCo = servicesCo == null || servicesCo.isEmpty() ? null : servicesCo;
  }

  public List<String> getServicesAdj() {
    if (servicesAdj == null) {
      servicesAdj = new ArrayList<>();
    }
    return servicesAdj;
  }

  public void setServicesAdj(List<String> servicesAdj) {
    this.servicesAdj = servicesAdj == null || servicesAdj.isEmpty() ? null : servicesAdj;
  }

  public List<String> getServicesTaboo() {
    if (servicesTaboo == null) {
      servicesTaboo = new ArrayList<>();
    }
    return servicesTaboo;
  }

  public void setServicesTaboo(List<String> servicesTaboo) {
    this.servicesTaboo = servicesTaboo == null || servicesTaboo.isEmpty() ? null : servicesTaboo;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + (int) (Double.doubleToLongBits(this.frequencyMax) ^ (Double.doubleToLongBits(this.frequencyMax) >>> 32));
    hash = 97 * hash + (int) (Double.doubleToLongBits(this.frequencyMin) ^ (Double.doubleToLongBits(this.frequencyMin) >>> 32));
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final PawsChannel other = (PawsChannel) obj;
    if (Double.doubleToLongBits(this.frequencyMax) != Double.doubleToLongBits(other.frequencyMax)) {
      return false;
    }
    return Double.doubleToLongBits(this.frequencyMin) == Double.doubleToLongBits(other.frequencyMin);
  }

}

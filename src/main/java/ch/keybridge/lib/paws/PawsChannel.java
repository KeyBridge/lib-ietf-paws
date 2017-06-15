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

import ch.keybridge.lib.xml.adapter.XmlPrecisionAdapter02;
import ch.keybridge.lib.xml.adapter.XmlPrecisionAdapter06;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.ietf.paws.EventTime;

/**
 * <img src="doc-files/pawsChannel.png">
 * <p>
 * A PAWS channel description.
 * <p>
 * The {@code PawsChannel} is used in the {@code AvailableSpectrumResponse}
 * object to convey channel availability information.
 * <p>
 * Notes: This is a Key Bridge custom component replacing the twice-buried list
 * of {@code SpectrumSchedule / Spectrum}, and then also the subsequent set of
 * buried lists under {@code SpectrumProfile / SpectrumProfilePoint}.
 * (Seriously, who designed that garbage?) This presents a simple container with
 * all the frequency information you need in a simple package. Neat and easy.
 *
 * @author Key Bridge LLC
 * @since v0.2.0 added 01/31/17
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
  @XmlJavaTypeAdapter(XmlPrecisionAdapter06.class)
  private Double frequencyMax;
  /**
   * The minimum (or start) frequency of the indicated name in MHz.
   */
  @XmlElement(required = true)
  @XmlJavaTypeAdapter(XmlPrecisionAdapter06.class)
  private Double frequencyMin;
  /**
   * Indicator that the device operation is allowed or forbidden.
   */
  @XmlAttribute(name = "allowed", required = true)
  private Boolean allowed;
  /**
   * The maximum allowable EIRP value on this channel. (dBW)
   */
  @XmlElement(required = true)
  @XmlJavaTypeAdapter(XmlPrecisionAdapter02.class)
  private Double maxPowerDBW;

  /**
   * The time range for which this specific channel is available.
   * <p>
   * When specified, any gaps in time intervals within the element that overlap
   * with the range specified by "timeRange" are interpreted by the device as
   * time intervals in which there is no available spectrum.
   */
  @XmlElement(name = "maxTimeRange", required = true)
  private EventTime timeRange;

  /**
   * Indicator that this channel is subject to an Enforcement record and should
   * NOT be included in a channel availability list. When this is configured the
   * enforcement message may be found in the messages list.
   */
  @XmlAttribute(name = "enforcement")
  private Boolean enforcement;

  /**
   * Indicator that an exception occurred when calculating the availability of
   * this channel and it is therefore disabled. Inspect the messages for more
   * information. Default is FALSE.
   */
  @XmlAttribute(name = "exception")
  private Boolean exception;

  /**
   * A list of co-channel services.
   */
  @XmlElementWrapper(name = "servicesCo")
  @XmlElement(name = "uuid")
  private List<String> servicesCo;
  /**
   * A list of blocking adjacent channel services.
   */
  @XmlElementWrapper(name = "servicesAdj")
  @XmlElement(name = "uuid")
  private List<String> servicesAdj;
  /**
   * A list of blocking taboo-channel services.
   */
  @XmlElementWrapper(name = "servicesTaboo")
  @XmlElement(name = "uuid")
  private List<String> servicesTaboo;
  /**
   * A list of blocking second adjacent channel services. (RRBS only)
   */
  @XmlElementWrapper(name = "services2ndAdj")
  @XmlElement(name = "uuid")
  private List<String> services2ndAdj;

  /**
   * Messages provides information about the white space channel build process.
   */
  @XmlElementWrapper(name = "messages")
  @XmlElement(name = "message")
  private List<String> messages;

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

  public EventTime getTimeRange() {
    return timeRange;
  }

  public void setTimeRange(EventTime timeRange) {
    this.timeRange = timeRange;
  }

  public Boolean getEnforcement() {
    return enforcement;
  }

  public void setEnforcement(Boolean enforcement) {
    this.enforcement = enforcement;
  }

  public Boolean getException() {
    return exception;
  }

  public void setException(Boolean exception) {
    this.exception = exception;
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

  public List<String> getServices2ndAdj() {
    if (services2ndAdj == null) {
      services2ndAdj = new ArrayList<>();
    }
    return services2ndAdj;
  }

  public void setServices2ndAdj(List<String> services2ndAdj) {
    this.services2ndAdj = services2ndAdj;
  }

  public List<String> getMessages() {
    if (messages == null) {
      messages = new ArrayList<>();
    }
    return messages;
  }

  public void setMessages(List<String> messages) {
    this.messages = messages;
  }

  public void addMessage(String message) {
    getMessages().add(message);
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

  @Override
  public String toString() {
    return name;
  }

}

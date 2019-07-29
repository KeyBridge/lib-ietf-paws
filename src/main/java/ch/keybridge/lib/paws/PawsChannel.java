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

import ch.keybridge.lib.xml.adapter.XmlDouble02PrecisionAdapter;
import ch.keybridge.lib.xml.adapter.XmlDouble06PrecisionAdapter;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.ietf.lib.paws.EventTime;

/**
 * Key Bridge PAWS channel description.
 * <p>
 * <img alt="clazz" src="doc-files/pawsChannel.png">
 * <p>
 * The {@code PawsChannel} is used in the {@code AvailableSpectrumResponse}
 * object to convey channel availability information.
 * <p>
 * Notes: This is a Key Bridge custom component replacing the twice-buried list
 * of {@code SpectrumSchedule / Spectrum}, and then also the subsequent set of
 * buried lists under {@code SpectrumProfile / SpectrumProfilePoint}.
 * (Seriously, who designed that garbage?) This implementation presents a simple
 * container with all the frequency information you need in a simple package.
 * Neat and easy.
 *
 * @author Key Bridge LLC
 * @since v0.2.0 added 01/31/17
 * @since v0.7.0 move informational fields to `PawsInfo` class
 */
@XmlType(name = "PawsChannel")
@XmlRootElement(name = "PawsChannel")
@XmlAccessorType(XmlAccessType.FIELD)
public class PawsChannel implements Comparable<PawsChannel> {

  /**
   * The colloquial channel name. This corresponds to the channel number
   * identified in rule or regulation. e.g. "VHF4"
   */
  @XmlElement(required = true)
  private String name;
  /**
   * The maximum (or end) frequency of the indicated name in MHz.
   */
  @XmlElement(required = true)
  @XmlJavaTypeAdapter(XmlDouble06PrecisionAdapter.class)
  private Double frequencyMax;
  /**
   * The minimum (or start) frequency of the indicated name in MHz.
   */
  @XmlElement(required = true)
  @XmlJavaTypeAdapter(XmlDouble06PrecisionAdapter.class)
  private Double frequencyMin;

  /**
   * Indicator that the device operation is allowed or forbidden.
   */
  @XmlElement(required = true)
  private Boolean allowed;

  /**
   * The maximum allowable EIRP value on this channel. (dBW)
   */
  @XmlElement(required = true)
  @XmlJavaTypeAdapter(XmlDouble02PrecisionAdapter.class)
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
   * Make the empty constructor protected to encourage use of the
   * {@code getInstance} constructor.
   */
  protected PawsChannel() {
  }

  /**
   * PAWS Channel constructor with minimum required configuration.
   *
   * @param name         The colloquial channel name. This corresponds to the
   *                     channel number identified in rule or regulation. e.g.
   *                     "VHF4"
   * @param frequencyMin The minimum (or start) frequency of the indicated name
   *                     in MHz.
   * @param frequencyMax The maximum (or end) frequency of the indicated name in
   *                     MHz.
   * @return a new PawsChannel instance
   */
  public static PawsChannel getInstance(String name, double frequencyMin, double frequencyMax) {
    PawsChannel pc = new PawsChannel();
    pc.setName(name);
    pc.setFrequencyMax(frequencyMax);
    pc.setFrequencyMin(frequencyMin);
    return pc;
  }

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

  /**
   * Return the channel name
   *
   * @return the name
   */
  @Override
  public String toString() {
    return name;
  }

  /**
   * Sort on the min frequency.
   *
   * @param o the other instance
   * @return the sort order
   */
  @Override
  public int compareTo(PawsChannel o) {
    return frequencyMin.compareTo(o.getFrequencyMin());
  }

}

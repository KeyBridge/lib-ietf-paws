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

import ch.keybridge.lib.xml.adapter.XmlDouble02PrecisionAdapter;
import ch.keybridge.lib.xml.adapter.XmlDouble06PrecisionAdapter;
import java.util.Objects;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Key Bridge proprietary white space spectrum channel data transfer object.
 * <p>
 * <img alt="clazz" src="doc-files/spectrumChannel.png">
 * <p>
 * The {@code SpectrumChannel} is used in the {@code AvailableSpectrumResponse}
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
 * @since v0.13.0 rename from PawsChannel to SpectrumChannel
 */
@XmlType(name = "SpectrumChannel")
@XmlRootElement(name = "SpectrumChannel")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpectrumChannel implements Comparable<SpectrumChannel> {

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
   * Indicator that the device operation is allowed (TRUE) or forbidden (FALSE).
   */
  @XmlElement(required = true)
  private boolean allowed;

  /**
   * The maximum allowable EIRP value on this channel. (dBW)
   */
  @XmlElement(required = true)
  @XmlJavaTypeAdapter(XmlDouble02PrecisionAdapter.class)
  private Double maxPowerDBW;

  /**
   * The time range for which this specific channel is available.
   * <p>
   * When specified, this is the only time range when the specified channel may
   * be used.
   */
  @XmlElement(name = "maxTimeRange")
  private EventTime timeRange;

  /**
   * Make the empty constructor protected to encourage use of the
   * {@code getInstance} constructor.
   */
  protected SpectrumChannel() {
  }

  /**
   * PAWS Channel constructor with the minimum required configuration. Sets
   * `allowed` to TRUE and the `timeRange` to null.
   * <p>
   * Developer note: To finalize the returned channel the `maxPowerDBW` field
   * must be set.
   *
   * @param name         The colloquial channel name. This corresponds to the
   *                     channel number identified in rule or regulation. e.g.
   *                     "VHF4"
   * @param frequencyMin The minimum (or start) frequency of the indicated name
   *                     in MHz.
   * @param frequencyMax The maximum (or end) frequency of the indicated name in
   *                     MHz.
   */
  public SpectrumChannel(String name, Double frequencyMax, Double frequencyMin) {
    this.name = name;
    this.frequencyMax = frequencyMax;
    this.frequencyMin = frequencyMin;
    this.allowed = true;
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
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

  public Double getMaxPowerDBW() {
    return maxPowerDBW;
  }

  public void setMaxPowerDBW(Double maxPowerDBW) {
    this.maxPowerDBW = maxPowerDBW;
  }

  public EventTime getTimeRange() {
    return timeRange;
  }

  public void setTimeRange(EventTime timeRange) {
    this.timeRange = timeRange;
  }//</editor-fold>

  /**
   * Sort on the min frequency.
   *
   * @param o the other instance
   * @return the sort order
   */
  @Override
  public int compareTo(SpectrumChannel o) {
    return frequencyMin.compareTo(o.getFrequencyMin());
  }

  /**
   * Channels are uniquely identified by the max and min frequencies.
   *
   * @return a hashcode
   */
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 43 * hash + Objects.hashCode(this.frequencyMax);
    hash = 43 * hash + Objects.hashCode(this.frequencyMin);
    return hash;
  }

  /**
   * Channels are uniquely identified by the max and min frequencies.
   *
   * @param obj the other instance
   * @return equality status
   */
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
    final SpectrumChannel other = (SpectrumChannel) obj;
    if (!Objects.equals(this.frequencyMax, other.frequencyMax)) {
      return false;
    }
    return Objects.equals(this.frequencyMin, other.frequencyMin);
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

}

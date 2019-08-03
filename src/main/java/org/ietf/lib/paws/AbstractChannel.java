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

import java.util.Objects;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.ietf.lib.paws.adapter.XmlDouble06PrecisionAdapter;

/**
 * Key Bridge proprietary white space spectrum channel data transfer object.
 * <p>
 * <img alt="clazz" src="doc-files/abstractChannel.png">
 * <p>
 * The {@code AbstractChannel} provides based spectrum information.
 *
 * @author Key Bridge LLC
 * @since v0.2.0 added 01/31/17
 * @since v0.7.0 move informational fields to `PawsInfo` class
 * @since v0.13.0 rename from PawsChannel to SpectrumChannel
 * @since v0.18.0 added 08/03/19 to support SpectrumInfo and SpectrumChannel use
 */
@XmlType(name = "AbstractChannel")
@XmlRootElement(name = "AbstractChannel")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({SpectrumChannel.class, SpectrumInfo.class})
public abstract class AbstractChannel implements Comparable<AbstractChannel> {

  /**
   * The colloquial channel name. This corresponds to the channel number
   * identified in rule or regulation. e.g. "VHF4"
   */
  @XmlElement(required = true)
  protected String name;
  /**
   * The maximum (or end) frequency of the indicated name in MHz.
   */
  @XmlElement(required = true)
  @XmlJavaTypeAdapter(XmlDouble06PrecisionAdapter.class)
  protected Double frequencyMax;
  /**
   * The minimum (or start) frequency of the indicated name in MHz.
   */
  @XmlElement(required = true)
  @XmlJavaTypeAdapter(XmlDouble06PrecisionAdapter.class)
  protected Double frequencyMin;

  /**
   * Make the empty constructor protected to encourage use of the
   * {@code getInstance} constructor.
   */
  protected AbstractChannel() {
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
  public AbstractChannel(String name, Double frequencyMin, Double frequencyMax) {
    this.name = name;
    this.frequencyMin = frequencyMin;
    this.frequencyMax = frequencyMax;
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
  }//</editor-fold>

  /**
   * Sort on the min frequency.
   *
   * @param o the other instance
   * @return the sort order
   */
  @Override
  public int compareTo(AbstractChannel o) {
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
    final AbstractChannel other = (AbstractChannel) obj;
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

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
import org.ietf.lib.paws.adapter.XmlDouble02PrecisionAdapter;

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
public class SpectrumChannel extends AbstractSpectrum {

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
  public SpectrumChannel(String name, double frequencyMin, double frequencyMax) {
    super(name, frequencyMin, frequencyMax);
    this.allowed = true;
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
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

}

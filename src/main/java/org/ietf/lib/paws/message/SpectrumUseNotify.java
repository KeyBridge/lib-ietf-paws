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
package org.ietf.lib.paws.message;

import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.*;
import org.ietf.lib.paws.*;

/**
 * 4.5.5. SPECTRUM_USE_NOTIFY
 * <p>
 * The spectrum-use notification message indicates the spectrum anticipated to
 * be used by the device.
 * <pre>
 *   +---------------------------------------------------+
 *   |SPECTRUM_USE_NOTIFY                                |
 *   +---------------------------------+-----------------+
 *   |deviceDesc:DeviceDescriptor      | REQUIRED        |
 *   |location:GeoLocation             | see description |
 *   |masterDeviceDesc:DeviceDescriptor| OPTIONAL        |
 *   |masterDeviceLocation:GeoLocation | see description |
 *   |spectra:list                     | REQUIRED        |--+
 *   |...................................................|  |
 *   |*other:any                       | OPTIONAL        |  |
 *   +---------------------------------+-----------------+  | 0..*
 *                                                          V
 *                                 +--------------------------------+
 *                                 |Spectrum                        |
 *                                 +---------------------+----------+
 *                                 |resolutionBwHz:float | REQUIRED |
 *                                 |profiles:list        | REQUIRED |
 *                                 +---------------------+----------+
 * </pre> other: Depending on the ruleset, other parameters may be required. To
 * simplify its logic, the device MAY include the union of all parameters
 * required by all supported rulesets. The Database MUST ignore all parameters
 * it does not understand.
 *
 * @author Key Bridge
 */
@XmlRootElement(name = "SpectrumUseNotify")
@XmlType(name = "SpectrumUseNotify")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpectrumUseNotify {

  /**
   * The DeviceDescriptor (Section 5.2) for the device is REQUIRED.
   */
  @XmlElement(required = true)
  private DeviceDescriptor deviceDesc;
  /**
   * The GeoLocation (Section 5.1) for the reporting device. When the
   * notification is made by a Master Device on its own behalf, the location is
   * that of the Master Device and is REQUIRED. When the notification is made by
   * a Master Device on behalf of a Slave Device, the location is that of the
   * Slave Device and is OPTIONAL but may be required by some rulesets.
   */
  @XmlElement(required = true)
  private GeoLocation location;

  /**
   * When the notification is made by the Master Device on behalf of a Slave
   * Device, the Master Device MAY provide its own descriptor.
   */
  private DeviceDescriptor masterDeviceDesc;
  /**
   * When the notification is made by the Master Device on behalf of a Slave
   * Device, the Master Device MUST include its own GeoLocation (Section 5.1).
   */
  private GeoLocation masterDeviceLocation;

  /**
   * The Spectrum (Section 5.11) list is REQUIRED and specifies the spectrum
   * anticipated to be used by the device; this includes profiles of frequencies
   * and power levels. The list MAY be empty, if the device decides not to use
   * any spectrum. For consistency, the resolution bandwidth value,
   * "resolutionBwHz", MUST match that from one of the Spectrum (Section 5.11)
   * elements in the corresponding AVAIL_SPECTRUM_RESP message, and the maximum
   * power levels in the Spectrum element MUST be expressed as power (EIRP) over
   * the specified "resolutionBwHz" value. The actual bandwidth to be used (as
   * computed from the start and stop frequencies) MAY be different from the
   * "resolutionBwHz" value. As an example, when the ruleset expresses maximum
   * power spectral density in terms of maximum power over any 100 kHz band,
   * then the "resolutionBwHz" value should be set to 100 kHz, even though the
   * actual bandwidth used can be 20 kHz.
   *
   * @deprecated Use the {@code transmitChannel} and {@code receiveChannel}
   * instead.
   */
  @Deprecated
  private Collection<Spectrum> spectra;

  /**
   * Key Bridge Modification. Specifies the selected transmit channel
   * configuration.
   * <p>
   * This is a custom component replacing the list of {@code Spectrum}. In the
   * response only the channel name, frequency and max power are required.
   *
   * @since v0.22.0
   */
  @XmlElement(required = true)
  private SpectrumChannel transmitChannel;
  /**
   * Key Bridge Modification. Specifies the selected receive channel
   * configuration.
   * <p>
   * This is a custom component replacing the list of {@code Spectrum}. In the
   * response only the channel name, frequency and max power are required.
   *
   * @since v0.22.0
   */
  @XmlElement(required = true)
  private SpectrumChannel receiveChannel;

  /**
   * Key Bridge addition: Added to support automated LPA spectrum registration.
   * When a licensed LPA user wishes to register for interference protection
   * they must indicate a usage schedule. When specified, this is the time range
   * when the specified channels will be used.
   */
  @XmlElement(name = "maxTimeRange")
  private EventTime timeRange;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public DeviceDescriptor getDeviceDesc() {
    return deviceDesc;
  }

  public void setDeviceDesc(DeviceDescriptor deviceDesc) {
    this.deviceDesc = deviceDesc;
  }

  public GeoLocation getLocation() {
    return location;
  }

  public void setLocation(GeoLocation location) {
    this.location = location;
  }

  public DeviceDescriptor getMasterDeviceDesc() {
    return masterDeviceDesc;
  }

  public void setMasterDeviceDesc(DeviceDescriptor masterDeviceDesc) {
    this.masterDeviceDesc = masterDeviceDesc;
  }

  public GeoLocation getMasterDeviceLocation() {
    return masterDeviceLocation;
  }

  public void setMasterDeviceLocation(GeoLocation masterDeviceLocation) {
    this.masterDeviceLocation = masterDeviceLocation;
  }

  public Collection<Spectrum> getSpectra() {
    if (spectra == null) {
      spectra = new ArrayList<>();
    }
    return spectra;
  }

  public void setSpectra(Collection<Spectrum> spectra) {
    this.spectra = spectra;
  }

  public EventTime getTimeRange() {
    return timeRange;
  }

  public void setTimeRange(EventTime timeRange) {
    this.timeRange = timeRange;
  }

  public SpectrumChannel getTransmitChannel() {
    return transmitChannel;
  }

  public void setTransmitChannel(SpectrumChannel transmitChannel) {
    this.transmitChannel = transmitChannel;
  }

  public SpectrumChannel getReceiveChannel() {
    return receiveChannel;
  }

  public void setReceiveChannel(SpectrumChannel receiveChannel) {
    this.receiveChannel = receiveChannel;
  }//</editor-fold>

  /**
   * Validate a spectrum use notify message. If a transmit channel is indicated,
   * the transmit channel power must also be declared.
   *
   * @return true if value
   * @throws Exception if the transmit channel power is not provided
   * @since v0.21.0 added 09/14/19
   */
  public boolean validate() throws Exception {
    if (transmitChannel != null && transmitChannel.getPower() == null) {
      throw new Exception("Transmit channel power (dBW) is required.");
    }
    return true;
  }

}

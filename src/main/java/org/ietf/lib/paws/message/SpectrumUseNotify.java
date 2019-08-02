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

import org.ietf.lib.paws.SpectrumChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import javax.xml.bind.annotation.*;
import org.ietf.lib.paws.DeviceDescriptor;
import org.ietf.lib.paws.GeoLocation;
import org.ietf.lib.paws.Spectrum;

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
@XmlRootElement(name = "SPECTRUM_USE_NOTIFY")
@XmlType(name = "SPECTRUM_USE_NOTIFY")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpectrumUseNotify {

  /**
   * The DeviceDescriptor (Section 5.2) for the device is REQUIRED.
   */
  @XmlElement(required = true)
  private DeviceDescriptor deviceDesc;
  /**
   * The GeoLocation (Section 5.1) for the device. When the notification is made
   * by a Master Device on its own behalf, the location is that of the Master
   * Device and is REQUIRED. When the notification is made by a Master Device on
   * behalf of a Slave Device, the location is that of the Slave Device and is
   * OPTIONAL but may be required by some rulesets.
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
   * @deprecated Use the {@code channels} list instead.
   */
  @XmlElement(required = true)
  private Collection<Spectrum> spectra;

  /**
   * Key Bridge Modification.
   * <p>
   * This is a custom component replacing the list of {@code Spectrum}. In the
   * response only the channel name, frequency and max power are required.
   */
  @XmlElement(name = "channels", required = true)
  private Collection<SpectrumChannel> channels;

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

  public Collection<SpectrumChannel> getChannels() {
    if (channels == null) {
      channels = new TreeSet<>();
    }
    return channels;
  }

  public void setChannels(Collection<SpectrumChannel> channels) {
    this.channels = channels;
  }

  public void addChannel(SpectrumChannel channel) {
    getChannels().add(channel);
  }//</editor-fold>

}

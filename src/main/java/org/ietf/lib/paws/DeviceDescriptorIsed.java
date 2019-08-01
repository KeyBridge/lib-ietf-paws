/*
 * Copyright 2019 Key Bridge LLC. All rights reserved.
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
import org.ietf.lib.paws.type.PawsRulesetType;

/**
 * 5.2. DeviceDescriptor for {@code ISED-DBS01-2015} PAWS Ruleset.
 * <p>
 * <img alt="clazz" src="doc-files/deviceDescriptorIsed.png">
 * <p>
 * The device descriptor contains parameters that identify the specific device,
 * such as its manufacturer serial number, manufacturer's ID, and any other
 * device characteristics required by ruleset.
 * <pre>
 * +--------------------------------+
 * |DeviceDescriptor                |
 * +---------------------+----------+
 * |serialNumber:string  | OPTIONAL |
 * |manufacturerId:string| OPTIONAL |
 * |modelId:string       | OPTIONAL |  1..*
 * |rulesetIds:list      | OPTIONAL |------=string
 * |.....................|..........|
 * |*other:any           | OPTIONAL |
 * +---------------------+----------+
 * </pre>
 *
 * @see
 * <a href="https://www.ic.gc.ca/eic/site/smt-gst.nsf/eng/sf10930.html">RSS-222</a>
 * @author Key Bridge LLC
 * @since v0.9.0 created 07/27/19 to support operation in Canada
 * @deprecated fields in this class are consolidated in `DeviceDescriptor`
 */
@XmlRootElement(name = "DeviceDescriptorIsed")
@XmlType(name = "DeviceDescriptorIsed")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceDescriptorIsed extends DeviceDescriptor {

  /**
   * "DBS-01". The default rule set for this type.
   */
  private static final PawsRulesetType RULESET_TYPE = PawsRulesetType.ISED_DBS01_2015;

  /**
   * Specifies a device's government certification ID (Section 9.2.2.1).
   * <p>
   * Specifies the device's Industry Canada Identification Number (IC ID). For
   * the purposes of the PAWS protocol, the maximum length of the fccId value is
   * 32 octets.
   *
   * @deprecated consolidated in `DeviceDescriptor::deviceType` field
   */
  @XmlElement(required = true)
  private String icId;
  /**
   * Specifies the Device Type (Section 9.2.2.2) of TV-band white-space device,
   * as defined by rule.
   * <p>
   * Key Bridge: Valid values are "FIXED", "MODE2_HP" (for high power),
   * "MODE2_LP" (for low power), "MODE1".
   *
   * @deprecated consolidated in `DeviceDescriptor::deviceType` field
   */
  @XmlElement(required = true)
  private String isedTvbdDeviceType;

  /**
   * Default no-arg constructor. Sets the rule set type.
   */
  public DeviceDescriptorIsed() {
    super(RULESET_TYPE);
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  /**
   * Get the device's government certification ID (Section 9.2.2.1).
   *
   * @return the device's Industry Canada Identification Number (IC ID).
   */
  public String getIcId() {
    return icId;
  }

  /**
   * Set the device's government certification ID (Section 9.2.2.1).
   *
   * @param icId the device's Industry Canada Identification Number (IC ID).
   */
  public void setIcId(String icId) {
    this.icId = icId;
  }

  /**
   * Get the Device Type (Section 9.2.2.2) of TV-band white-space device, as
   * defined by rule.
   * <p>
   * Key Bridge: Valid values are "FIXED", "MODE2_HP" (for high power),
   * "MODE2_LP" (for low power), "MODE1".
   *
   * @return the TV-band white-space Device Type
   */
  public String getIsedTvbdDeviceType() {
    return isedTvbdDeviceType;
  }

  /**
   * Set the Device Type (Section 9.2.2.2) of TV-band white-space device, as
   * defined by rule.
   * <p>
   * Key Bridge: Valid values are "FIXED", "MODE2_HP" (for high power),
   * "MODE2_LP" (for low power), "MODE1".
   *
   * @param isedTvbdDeviceType the TV-band white-space Device Type
   */
  public void setIsedTvbdDeviceType(String isedTvbdDeviceType) {
    this.isedTvbdDeviceType = isedTvbdDeviceType;
  }//</editor-fold>

  @Override
  public String toString() {
    return "DeviceDescriptorIsed{" + "icId=" + icId + ", isedTvbdDeviceType=" + isedTvbdDeviceType + super.toString() + '}';
  }

}

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
 * 5.2. DeviceDescriptor for {@code FCC_Part15H_2019} PAWS Ruleset.
 * <p>
 * <img alt="clazz" src="doc-files/deviceDescriptorFcc.png">
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
 * @author Key Bridge LLC
 * @since v0.6.0 created 07/26/19 to distinguish FCC vs. ETSI handling
 */
@XmlRootElement(name = "DeviceDescriptorFcc")
@XmlType(name = "DeviceDescriptorFcc")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceDescriptorFcc extends DeviceDescriptor {

  /**
   * "Part 15-H". The default rule set for this type.
   */
  private static final PawsRulesetType RULESET_TYPE = PawsRulesetType.FCC_Part15H_2019;

  /**
   * Specifies a device's government certification ID (Section 9.2.2.1).
   * <p>
   * Specifies the device's FCC certification identifier. A valid FCC ID is
   * limited to 19 characters in the ASCII value range, as proposed in FCC
   * Administration Topics Review [FCC-Review-2012-10]. For the purposes of the
   * PAWS protocol, the maximum length of the fccId value is 32 octets.
   */
  @XmlElement(required = true)
  private String fccId;
  /**
   * Specifies the Device Type (Section 9.2.2.2) of TV-band white-space device,
   * as defined by rule.
   * <p>
   * Key Bridge: Valid values are "FIXED", "MODE2_HP" (for high power),
   * "MODE2_LP" (for low power), "MODE1".
   */
  @XmlElement(required = true)
  private String fccTvbdDeviceType;

  /**
   * Default no-arg constructor. Sets the rule set type.
   */
  public DeviceDescriptorFcc() {
    super(RULESET_TYPE);
  }

  /**
   * Get the device's government certification ID (Section 9.2.2.1).
   *
   * @return the device's Industry Canada Identification Number (IC ID).
   */
  public String getFccId() {
    return fccId;
  }

  /**
   * Set the device's government certification ID (Section 9.2.2.1).
   *
   * @param icId the device's Industry Canada Identification Number (IC ID).
   */
  public void setFccId(String fccId) {
    this.fccId = fccId;
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
  public String getFccTvbdDeviceType() {
    return fccTvbdDeviceType;
  }

  /**
   * Set the Device Type (Section 9.2.2.2) of TV-band white-space device, as
   * defined by rule.
   * <p>
   * Key Bridge: Valid values are "FIXED", "MODE2_HP" (for high power),
   * "MODE2_LP" (for low power), "MODE1".
   *
   * @return the TV-band white-space Device Type
   */
  public void setFccTvbdDeviceType(String fccTvbdDeviceType) {
    this.fccTvbdDeviceType = fccTvbdDeviceType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isValid() {
    return isSet(fccId) && isSet(fccTvbdDeviceType);
  }

  @Override
  public String toString() {
    return "DeviceDescriptorFcc{" + "fccId=" + fccId + ", fccTvbdDeviceType=" + fccTvbdDeviceType + super.toString() + '}';
  }

}

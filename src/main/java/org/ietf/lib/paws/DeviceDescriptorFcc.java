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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import org.ietf.lib.paws.type.PawsRulesetType;

/**
 * <img src="doc-files/deviceDescriptor.png">
 * <p>
 * 5.2. DeviceDescriptor for {@code FccTvBandWhiteSpace-2010} PAWS Ruleset.
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
 * |rulesetIds:list      | OPTIONAL |------>string
 * |.....................|..........|
 * |*other:any           | OPTIONAL |
 * +---------------------+----------+
 * </pre>
 *
 * @author Key Bridge LLC
 * @since v0.6.0 created 07/26/19 to distinguish FCC vs. ETSI handling
 */
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
   * as defined by rule. See 9.2.2.2. FCC Device Type:
   * {@code fccTvbdDeviceType}: Specifies the TV-band white-space device type.
   * <p>
   * Deprecated: <i>FCC Valid values are "FIXED", "MODE_1", and for
   * "MODE_2".</i>
   * <p>
   * See also 9.2.2.3. ETSI Device Type: Specification document(s): Specifies
   * the white-space device type, as defined by the ETSI Harmonised Standard
   * [ETSI-EN-301-598]. Valid values are single-letter strings, such as "A",
   * "B", etc. Consult the documentation for details about the device types.
   * <p>
   * Key Bridge: Valid values are "FIXED", "MODE2_HP" for high power, "MODE2_LP"
   * for low power, "MODE1".
   */
  @XmlElement(required = true)
  private String fccTvbdDeviceType;

  /**
   * Default no-arg constructor. Sets the rule set type.
   */
  public DeviceDescriptorFcc() {
    super(RULESET_TYPE);
  }

  public String getFccId() {
    return fccId;
  }

  public void setFccId(String fccId) {
    this.fccId = fccId;
  }

  public String getFccTvbdDeviceType() {
    return fccTvbdDeviceType;
  }

  public void setFccTvbdDeviceType(String fccTvbdDeviceType) {
    this.fccTvbdDeviceType = fccTvbdDeviceType;
  }

}

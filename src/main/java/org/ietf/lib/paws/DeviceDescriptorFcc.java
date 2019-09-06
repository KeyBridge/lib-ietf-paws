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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.ietf.lib.paws.type.FccDeviceType;
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
//@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceDescriptorFcc extends DeviceDescriptor {

  /**
   * "Part 15-H". The default rule set for this type.
   */
  private static final PawsRulesetType RULESET_TYPE = PawsRulesetType.FCC_Part15H_2019;

  /**
   * Default no-arg constructor. Sets the rule set type.
   */
  public DeviceDescriptorFcc() {
    super(RULESET_TYPE);
  }

  /**
   * Get the Device Type (Section 9.2.2.2) of TV-band white-space device, as
   * defined by rule.
   * <p>
   * Key Bridge: Valid values are "FIXED", "MODE2", "MODE1".
   *
   * @return The Part 15(H) device type of TV-band white-space device.
   */
  @XmlElement(name = "FccDeviceType")
  public FccDeviceType getFccDeviceType() {
    return super.getDeviceMode() == null ? null : FccDeviceType.valueOf(super.getDeviceMode());
  }

  /**
   * Set the Device Type (Section 9.2.2.2) of TV-band white-space device, as
   * defined by rule.
   * <p>
   * Key Bridge: Valid values are "FIXED", "MODE2", "MODE1".
   *
   * @param deviceType the TV-band white-space Device Type
   */
  public void setFccDeviceType(FccDeviceType deviceType) {
    super.setDeviceMode(deviceType.name());
  }

}

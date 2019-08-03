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
import org.ietf.lib.paws.type.IsedDeviceType;
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
   * Default no-arg constructor. Sets the rule set type.
   */
  public DeviceDescriptorIsed() {
    super(RULESET_TYPE);
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
  @XmlElement(name = "IsedDeviceType")
  public IsedDeviceType getIsedTvbdDeviceType() {
    return super.getDeviceType() == null ? null : IsedDeviceType.valueOf(super.getDeviceType());
  }

  /**
   * Set the Device Type (Section 9.2.2.2) of TV-band white-space device, as
   * defined by rule.
   * <p>
   * Key Bridge: Valid values are "FIXED", "MODE2_HP" (for high power),
   * "MODE2_LP" (for low power), "MODE1".
   *
   * @param deviceType the TV-band white-space Device Type
   */
  public void setIsedTvbdDeviceType(IsedDeviceType deviceType) {
    super.setDeviceType(deviceType == null ? null : deviceType.name());
  }

}

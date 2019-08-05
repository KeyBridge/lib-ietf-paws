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
import org.ietf.lib.paws.type.EtsiDeviceCategory;
import org.ietf.lib.paws.type.EtsiEmissionClassType;
import org.ietf.lib.paws.type.EtsiEquipmentType;
import org.ietf.lib.paws.type.PawsRulesetType;

/**
 * 5.2. DeviceDescriptor for {@code ETSI_EN301_598_211} PAWS Ruleset.
 * <p>
 * <img alt="clazz" src="doc-files/deviceDescriptorEtsi.png">
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
@XmlRootElement(name = "DeviceDescriptorEtsi")
@XmlType(name = "DeviceDescriptorEtsi")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceDescriptorEtsi extends DeviceDescriptor {

  /**
   * "ETSI-EN-301-598-2.1.1". The default rule set for this type.
   */
  private static final PawsRulesetType RULESET_TYPE = PawsRulesetType.ETSI_EN301_598_211;

  /**
   * Specifies the white-space device type, as defined by the ETSI Harmonised
   * Standard [ETSI-EN-301-598]. Valid values are single-letter strings, such as
   * "A", "B", etc. Consult the documentation for details about the device
   * types.
   * <p>
   * Reference 4.2.1 Equipment types
   *
   * @deprecated consolidated into the `DeviceDescriptor::deviceType` field
   */
  @XmlElement(required = true)
  private EtsiEquipmentType etsiEnDeviceType;
  /**
   * Specifies the white-space device technology identifier, as defined by the
   * ETSI Harmonised Standard [ETSI-EN-301-598]. The maximum length of the
   * string value is 64 octets.
   * <p>
   * Table 3: Device Parameters. A set of characters representing that allows to
   * uniquely identify the technology. This may include: name of the
   * organization responsible for the technology specifications; specification
   * number, version and issue date.
   */
  @XmlElement(required = true)
  private String etsiEnTechnologyId;
  /**
   * Specifies the white-space device category, as defined by the ETSI
   * Harmonised Standard [ETSI-EN-301-598]. Valid values are the strings
   * "master" and "slave". It is case insensitive.
   */
  @XmlElement(required = true)
  private EtsiDeviceCategory etsiEnDeviceCategory;

  /**
   * Specifies the white-space device emissions class, as defined by the ETSI
   * Harmonised Standard [ETSI-EN-301-598], that characterizes the out-of-block
   * emissions of the device. The values are represented by numeric strings,
   * such as "1", "2", "3", etc.
   * <p>
   * Reference 4.2.4.2.2 Limits and Table 2: Adjacent Channel Leakage Ratios
   * (ACLR) for different Device Emission Classes.
   */
  @XmlElement(required = true)
  private Integer etsiEnDeviceEmissionsClass;

  /**
   * Default no-arg constructor. Sets the rule set type.
   */
  public DeviceDescriptorEtsi() {
    super(RULESET_TYPE);
  }

  /**
   * Get the white-space device type
   *
   * @return the white-space device type
   */
  public EtsiEquipmentType getEtsiEnDeviceType() {
    return etsiEnDeviceType;
  }

  /**
   * Set the white-space device type
   *
   * @param etsiEnDeviceType the white-space device type
   */
  public void setEtsiEnDeviceType(EtsiEquipmentType etsiEnDeviceType) {
    this.etsiEnDeviceType = etsiEnDeviceType;
  }

  /**
   * Get a string to uniquely identify the technology. This may include: name of
   * the organization responsible for the technology specifications;
   * specification number, version and issue date.
   *
   * @return the Technology identifier
   */
  public String getEtsiEnTechnologyId() {
    return etsiEnTechnologyId;
  }

  /**
   * Set a string to uniquely identify the technology. This may include: name of
   * the organization responsible for the technology specifications;
   * specification number, version and issue date.
   *
   * @param etsiEnTechnologyId the Technology identifier
   */
  public void setEtsiEnTechnologyId(String etsiEnTechnologyId) {
    this.etsiEnTechnologyId = etsiEnTechnologyId;
  }

  /**
   * Get the white-space device category. Valid values are "master" and "slave".
   *
   * @return the white-space device category.
   */
  public EtsiDeviceCategory getEtsiEnDeviceCategory() {
    return etsiEnDeviceCategory;
  }

  /**
   * Set the white-space device category. Valid values are "master" and "slave".
   *
   * @param etsiEnDeviceCategory the white-space device category.
   */
  public void setEtsiEnDeviceCategory(EtsiDeviceCategory etsiEnDeviceCategory) {
    this.etsiEnDeviceCategory = etsiEnDeviceCategory;
  }

  /**
   * Get the white-space device emissions class that characterizes the
   * out-of-block emissions of the device.
   *
   * @return the white-space device emissions class
   */
  public EtsiEmissionClassType getEtsiEnDeviceEmissionsClass() {
    return etsiEnDeviceEmissionsClass == null ? null : EtsiEmissionClassType.fromCode(etsiEnDeviceEmissionsClass);
  }

  /**
   * Set the white-space device emissions class that characterizes the
   * out-of-block emissions of the device.
   *
   * @param etsiEnDeviceEmissionsClass the white-space device emissions class
   */
  public void setEtsiEnDeviceEmissionsClass(EtsiEmissionClassType etsiEnDeviceEmissionsClass) {
    this.etsiEnDeviceEmissionsClass = etsiEnDeviceEmissionsClass == null ? null : etsiEnDeviceEmissionsClass.getCode();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isValid() {
    return super.isValid() && etsiEnDeviceCategory != null && isSet(etsiEnTechnologyId) && etsiEnDeviceEmissionsClass != null;
  }

  @Override
  public String toString() {
    return "DeviceDescriptorEtsi{" + "etsiEnDeviceType=" + etsiEnDeviceType + ", etsiEnTechnologyId=" + etsiEnTechnologyId + ", etsiEnDeviceCategory=" + etsiEnDeviceCategory + ", etsiEnDeviceEmissionsClass=" + etsiEnDeviceEmissionsClass + super.toString() + '}';
  }

}

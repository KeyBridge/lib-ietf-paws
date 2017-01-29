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
package org.ietf.paws;

import java.util.List;
import javax.xml.bind.annotation.*;

/**
 * 5.2. DeviceDescriptor
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
 */
@XmlRootElement(name = "DeviceDescriptor")
@XmlType(name = "DeviceDescriptor")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceDescriptor {

  /**
   * The manufacturer’s device serial number is OPTIONAL, although rulesets
   * typically require it. Its maximum length is 64 octets.
   */
  private String serialNumber;
  /**
   * The manufacturer’s ID is OPTIONAL but may be required by some rulesets.
   * This represents the name of the device manufacturer, and therefore ought to
   * be consistent across all devices from the same manufacturer and distinct
   * from that of other manufacturers. Its maximum length is 64 octets.
   */
  private String manufacturerId;
  /**
   * The device’s model ID is OPTIONAL but may be required by some rulesets. Its
   * maximum length is 64 octets.
   */
  private String modelId;
  /**
   * The list of identifiers for rulesets supported by the device (see Ruleset
   * ID Registry (Section 9.1)). A Database MAY require that the device provides
   * this list before servicing the device requests. If the Database supports
   * none of the rulesets specified in the list, the Database MAY refuse to
   * service the device requests. See RulesetInfo (Section 5.6) for discussion
   * on ruleset identifiers. If present, the list MUST contain at least one
   * entry.
   */
  @XmlList
  private List<String> rulesetIds;

  /**
   * Specifies a device's government certification ID (Section 9.2.2.1).
   */
  @XmlElement(required = true)
  private String id;
  /**
   * Specifies the Device Type (Section 9.2.2.2) of TV-band white-space device,
   * as defined by rule.
   */
  @XmlElement(required = true)
  private String tvbdDeviceType;

  /**
   * 9.2.2.3. ETSI Device Type
   * <p>
   * Specification document(s): Specifies the white-space device type, as
   * defined by the ETSI Harmonised Standard [ETSI-EN-301-598]. Valid values are
   * single-letter strings, such as "A", "B", etc. Consult the documentation for
   * details about the device types.
   */
  private String etsiEnDeviceType;
  /**
   * 9.2.2.4. ETSI Device Emissions Class
   * <p>
   * Specification document(s): Specifies the white-space device emissions
   * class, as defined by the ETSI Harmonised Standard [ETSI-EN-301-598], that
   * characterizes the out-of-block emissions of the device. The values are
   * represented by numeric strings, such as "1", "2", "3", etc. Consult the
   * documentation for details about emissions classes.
   */
  private String etsiEnDeviceEmissionsClass;
  /**
   * 9.2.2.5. ETSI Technology Identifier
   * <p>
   * Specification document(s): Specifies the white-space device technology
   * identifier, as defined by the ETSI Harmonised Standard [ETSI-EN-301-598].
   * The maximum length of the string value is 64 octets. Consult the
   * documentation for valid values.
   */
  private String etsiEnTechnologyId;
  /**
   * 9.2.2.6. ETSI Device Category
   * <p>
   * Specification document(s): Specifies the white-space device category, as
   * defined by the ETSI Harmonised Standard [ETSI-EN-301-598]. Valid values are
   * the strings "master" and "slave". It is case insensitive.
   */
  private String etsiEnDeviceCategory;

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public String getManufacturerId() {
    return manufacturerId;
  }

  public void setManufacturerId(String manufacturerId) {
    this.manufacturerId = manufacturerId;
  }

  public String getModelId() {
    return modelId;
  }

  public void setModelId(String modelId) {
    this.modelId = modelId;
  }

  public List<String> getRulesetIds() {
    return rulesetIds;
  }

  public void setRulesetIds(List<String> rulesetIds) {
    this.rulesetIds = rulesetIds;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTvbdDeviceType() {
    return tvbdDeviceType;
  }

  public void setTvbdDeviceType(String tvbdDeviceType) {
    this.tvbdDeviceType = tvbdDeviceType;
  }

  public String getEtsiEnDeviceType() {
    return etsiEnDeviceType;
  }

  public void setEtsiEnDeviceType(String etsiEnDeviceType) {
    this.etsiEnDeviceType = etsiEnDeviceType;
  }

  public String getEtsiEnDeviceEmissionsClass() {
    return etsiEnDeviceEmissionsClass;
  }

  public void setEtsiEnDeviceEmissionsClass(String etsiEnDeviceEmissionsClass) {
    this.etsiEnDeviceEmissionsClass = etsiEnDeviceEmissionsClass;
  }

  public String getEtsiEnTechnologyId() {
    return etsiEnTechnologyId;
  }

  public void setEtsiEnTechnologyId(String etsiEnTechnologyId) {
    this.etsiEnTechnologyId = etsiEnTechnologyId;
  }

  public String getEtsiEnDeviceCategory() {
    return etsiEnDeviceCategory;
  }

  public void setEtsiEnDeviceCategory(String etsiEnDeviceCategory) {
    this.etsiEnDeviceCategory = etsiEnDeviceCategory;
  }

}

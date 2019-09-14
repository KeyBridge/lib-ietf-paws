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

import java.util.Objects;
import javax.xml.bind.annotation.*;
import org.ietf.lib.paws.type.EtsiDeviceCategory;
import org.ietf.lib.paws.type.EtsiDeviceMode;
import org.ietf.lib.paws.type.EtsiEmissionClassType;
import org.ietf.lib.paws.type.PawsRulesetType;

/**
 * 5.2. DeviceDescriptor
 * <p>
 * <img alt="clazz" src="doc-files/deviceDescriptor.png">
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
 */
@XmlRootElement(name = "DeviceDescriptor")
@XmlType(name = "DeviceDescriptor")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceDescriptor {

  /**
   * Key Bridge modification. REQUIRED. Change from List to single instance. A
   * device may only register and operate under ONE jurisdiction at a time.
   * <p>
   * The list of identifiers for rulesets supported by the device (see Ruleset
   * ID Registry (Section 9.1)). A Database MAY require that the device provides
   * this list before servicing the device requests. If the Database supports
   * none of the rulesets specified in the list, the Database MAY refuse to
   * service the device requests. See RulesetInfo (Section 5.6) for discussion
   * on ruleset identifiers. If present, the list MUST contain at least one
   * entry.
   */
  @XmlElement(required = true)
  private PawsRulesetType rulesetId;
  /**
   * Key Bridge extension. REQUIRED. Consolidates FCC, ISED and ETSI fields.
   * <p>
   * Specifies the Device operating mode (Section 9.2.2.2) of TV-band
   * white-space device, as defined by rule. Valid values for US and Canada are
   * "FIXED", "MODE2", "MODE1", "LPA".
   * <p>
   * For ETSI regions, valid values are "A", "B", as defined by the ETSI
   * Harmonised Standard [ETSI-EN-301-598]. Consult the documentation for
   * details about the device types.
   * <p>
   * Reference 4.2.1 Equipment types
   *
   * @see FccDeviceMode
   * @see IsedDeviceMode
   * @see EtsiDeviceMode
   */
  @XmlElement(required = true)
  private String deviceMode;

  /**
   * Key Bridge extension. REQUIRED. Consolidates FCC, ISED and ETSI fields.
   * <p>
   * Specifies a device's government certification ID (Section 9.2.2.1). This is
   * the device's FCC certification identifier, Industry Canada Identification
   * Number (IC ID), or other certification number for the operating
   * jurisdiction.
   */
  @XmlElement(required = true)
  private String deviceId;

  /**
   * The manufacturer’s device serial number is <strike>OPTIONAL</string>
   * REQUIRED, although rulesets typically require it. Its maximum length is 64
   * octets.
   * <p>
   * Key Bridge always requires the device serial number to be provided.
   */
  @XmlElement(required = true)
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
   * Key Bridge extension. OPTIONAL.
   * <p>
   * This transmitter's wireless emission designator.
   * <p>
   * Each type of radio emission is classified according to its bandwidth,
   * method of modulation, nature of the modulating signal, and type of
   * information transmitted on the carrier signal. It is based on
   * characteristics of the signal, not on the transmitter used.
   * <p>
   * This value is typically associated with licensed services, and is used to
   * encode the type of modulation of the main carrier, the nature of the
   * modulating signals, and the type of information to be transmitted.
   * <p>
   * An emission designation is of the form 'BBBB 123 45', where BBBB is the
   * bandwidth of the signal, 1 is a letter indicating the type of modulation
   * used of the main carrier (not including any subcarriers. 2 is a digit
   * representing the type of modulating signal again of the main carrier, 3 is
   * a letter corresponding to the type of information transmitted, 4 is a
   * letter indicating the practical details of the transmitted information, and
   * 5 is a letter that represents the method of multiplexing. The 4 and 5
   * fields are optional.
   */
  private String emissionDesignator;

  //
  // ETSI only fields
  //
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
   * Default no-arg constructor.
   */
  public DeviceDescriptor() {
  }

  /**
   * Constructor declaring the PawsRulesetType of this device descriptor.
   *
   * @param rulesetId the device descriptor PawsRulesetType
   */
  public DeviceDescriptor(PawsRulesetType rulesetId) {
    this.rulesetId = rulesetId;
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  /**
   * Get the manufacturer’s device serial number
   *
   * @return the manufacturer’s device serial number
   */
  public String getSerialNumber() {
    return serialNumber;
  }

  /**
   * Set the manufacturer’s device serial number
   *
   * @param serialNumber the manufacturer’s device serial number
   */
  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  /**
   * Get the manufacturer’s ID. This represents the name of the device
   * manufacturer, and therefore ought to be consistent across all devices from
   * the same manufacturer and distinct from that of other manufacturers.
   *
   *
   * @return the manufacturer’s ID
   */
  public String getManufacturerId() {
    return manufacturerId;
  }

  /**
   * Set the manufacturer’s ID. This represents the name of the device
   * manufacturer, and therefore ought to be consistent across all devices from
   * the same manufacturer and distinct from that of other manufacturers.
   *
   * @param manufacturerId the manufacturer’s ID
   */
  public void setManufacturerId(String manufacturerId) {
    this.manufacturerId = manufacturerId;
  }

  /**
   * Get the device’s model ID.
   *
   * @return the device’s model ID.
   */
  public String getModelId() {
    return modelId;
  }

  /**
   * Set the device’s model ID.
   *
   * @param modelId the device’s model ID.
   */
  public void setModelId(String modelId) {
    this.modelId = modelId;
  }

  /**
   * Get the PAWS Ruleset ID Registry value.
   *
   * @return the PAWS Ruleset ID
   */
  public PawsRulesetType getRulesetId() {
    return rulesetId;
  }

  /**
   * Set the PAWS Ruleset ID Registry values.
   *
   * @param rulesetId the PAWS Ruleset ID
   */
  public void setRulesetIds(PawsRulesetType rulesetId) {
    this.rulesetId = rulesetId;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public String getDeviceMode() {
    return deviceMode;
  }

  public void setDeviceMode(String deviceMode) {
    this.deviceMode = deviceMode;
  }

  public String getEmissionDesignator() {
    return emissionDesignator;
  }

  public void setEmissionDesignator(String emissionDesignator) {
    this.emissionDesignator = emissionDesignator;
  }//</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="ETSI Getter and Setter">
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
  }//</editor-fold>

  /**
   * Inspect this configuration and determine if it is value.
   *
   * @return TRUE if valid.
   */
  public boolean isValid() {
    return rulesetId != null && isSet(deviceId) && deviceMode != null;
  }

  /**
   * Validate this instance.
   *
   * @throws Exception describing the invalid configuration
   */
  public void validate() throws Exception {
    if (rulesetId == null) {
      throw new Exception("rulesetId is required");
    }
    if (deviceId == null) {
      throw new Exception("deviceId is required");
    }
    if (deviceMode == null) {
      throw new Exception("deviceType is required");
    }
  }

  /**
   * Inspect a string a determine it is not null and not empty.
   *
   * @param value the string value
   * @return TRUE if not null and not empty
   */
  protected boolean isSet(String value) {
    return value != null && !value.isEmpty();
  }

  /**
   * A DeviceDescriptor configuration is uniquely identified by its deviceId and
   * serialNumber.
   *
   * @return the hash code
   */
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 47 * hash + Objects.hashCode(this.serialNumber);
    hash = 47 * hash + Objects.hashCode(this.deviceId);
    return hash;
  }

  /**
   * A DeviceDescriptor configuration is uniquely identified by its deviceId and
   * serialNumber.
   *
   * @param obj the other instance
   * @return equality status
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final DeviceDescriptor other = (DeviceDescriptor) obj;
    if (!Objects.equals(this.serialNumber, other.serialNumber)) {
      return false;
    }
    return Objects.equals(this.deviceId, other.deviceId);
  }

  @Override
  public String toString() {
    return rulesetId + " " + deviceMode + " (" + deviceId + ", " + serialNumber + ')';
  }

}

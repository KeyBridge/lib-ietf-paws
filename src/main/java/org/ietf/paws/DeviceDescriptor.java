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
 * <img src="doc-files/deviceDescriptor.png">
 * <p>
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
   * <p>
   * Specifies the device's FCC certification identifier. A valid FCC ID is
   * limited to 19 characters in the ASCII value range, as proposed in FCC
   * Administration Topics Review [FCC-Review-2012-10]. For the purposes of the
   * PAWS protocol, the maximum length of the fccId value is 32 octets.
   */
  @XmlElement(required = true)
  private String id;
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
  private String tvbdDeviceType;

  /**
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
  protected String emissionDesignator;

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

  public String getEmissionDesignator() {
    return emissionDesignator;
  }

  public void setEmissionDesignator(String emissionDesignator) {
    this.emissionDesignator = emissionDesignator;
  }

}

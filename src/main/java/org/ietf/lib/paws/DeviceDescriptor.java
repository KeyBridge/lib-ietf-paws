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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.*;
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
@XmlSeeAlso({DeviceDescriptorEtsi.class, DeviceDescriptorFcc.class, DeviceDescriptorIsed.class})
public abstract class DeviceDescriptor {

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
   * Key Bridge extension.
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

  public DeviceDescriptor() {
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  /**
   * Constructor declaring the PawsRulesetType of this device descriptor.
   *
   * @param rulesetId the device descriptor PawsRulesetType
   */
  public DeviceDescriptor(PawsRulesetType rulesetId) {
    this.rulesetIds = Arrays.asList(rulesetId.getId());
  }

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
   * Get the PAWS Ruleset ID Registry values.
   *
   * @return a non-null ArrayList
   */
  public List<String> getRulesetIds() {
    if (rulesetIds == null) {
      rulesetIds = new ArrayList<>();
    }
    return rulesetIds;
  }

  /**
   * Set the PAWS Ruleset ID Registry values.
   *
   * @param rulesetIds one or more IDs
   */
  public void setRulesetIds(List<String> rulesetIds) {
    this.rulesetIds = rulesetIds;
  }

  /**
   * Convenience method to set the PAWS Ruleset ID Registry values to a single
   * enumerated type.
   *
   * @param rulesetId the enumerated PawsRulesetType
   */
  public void setRulesetId(PawsRulesetType rulesetId) {
    this.rulesetIds = Arrays.asList(rulesetId.getId());
  }

  public String getEmissionDesignator() {
    return emissionDesignator;
  }

  public void setEmissionDesignator(String emissionDesignator) {
    this.emissionDesignator = emissionDesignator;
  }//</editor-fold>

  /**
   * Inspect this configuration and determine if it is value.
   *
   * @return TRUE if valid.
   */
  public abstract boolean isValid();

  /**
   * Inspect a string a determine it is not null and not empty.
   *
   * @param value the string value
   * @return TRUE if not null and not empty
   */
  protected boolean isSet(String value) {
    return value != null && !value.isEmpty();
  }

  @Override
  public String toString() {
    return ",serialNumber=" + serialNumber + ", manufacturerId=" + manufacturerId + ", modelId=" + modelId + ", rulesetIds=" + rulesetIds + ", emissionDesignator=" + emissionDesignator;
  }

}

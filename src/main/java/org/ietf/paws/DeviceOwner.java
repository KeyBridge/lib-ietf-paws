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

import ch.keybridge.lib.paws.Contact;
import javax.xml.bind.annotation.*;

/**
 * <img src="doc-files/deviceOwner.png">
 * <p>
 * 5.5. DeviceOwner
 * <p>
 * DeviceOwner contains information on device ownership that is provided as part
 * of device registration. Some rulesets may require additional parameters.
 * <pre>
 * +-----------------------------+
 * |DeviceOwner                  |
 * +------------------+----------+
 * |owner:vcard       | REQUIRED |
 * |operator:vcard    | OPTIONAL |
 * +------------------+----------+
 * </pre> See PAWS Ruleset ID Registry (Section 9.1) for ruleset-specific
 * requirements on mandatory vCard properties. Depending on the ruleset, the
 * Database may be required to validate the device-owner information. In these
 * cases, the Database MUST respond with an INVALID_VALUE error (see "Error
 * Codes" (Section 5.17)) if validation fails.
 * <p>
 * All contact information MUST be expressed using the structure defined by the
 * "vCard Format Specification" [RFC6350], encoded in JSON [RFC7095]. Note that
 * the vCard specification defines maximum lengths for each parameter.
 *
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "DeviceOwner")
@XmlType(name = "DeviceOwner")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceOwner {

  /**
   * The vCard contact information for the individual or business that owns the
   * device is REQUIRED.
   * <p>
   * The owner is required to contain the formatted name of an individual or
   * organization using the "fn" property. When the name is that of an
   * organization, the entry also is required to contain the "kind" property,
   * with a value of "org".
   */
  @XmlElement(required = true)
  public Contact owner;
  /**
   * The vCard contact information for the device operator is OPTIONAL but may
   * be required by specific rulesets.
   * <p>
   * The operator entry is required to contain the following properties for the
   * contact person responsible for the device's operation: "fn", "adr", "tel",
   * and "email".
   */
  public Contact operator;

  public Contact getOwner() {
    return owner;
  }

  public void setOwner(Contact owner) {
    this.owner = owner;
  }

  public Contact getOperator() {
    return operator;
  }

  public void setOperator(Contact operator) {
    this.operator = operator;
  }

}

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

import javax.xml.bind.annotation.*;

/**
 * 5.5. DeviceOwner
 * <p>
 * <img alt="clazz" src="doc-files/deviceOwner.png">
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
 * <p>
 * 9.1.2.1. Federal Communications Commission (FCC)
 *
 * @author Key Bridge LLC
 * @since v0.8.0 update fields to contain LDAP distinguished names instead of
 * VCARD format.
 */
@XmlRootElement(name = "DeviceOwner")
@XmlType(name = "DeviceOwner")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceOwner {

  /**
   * The vCard contact information for the individual or business that owns the
   * device is REQUIRED.
   * <p>
   * 9.1.2.1. Federal Communications Commission (FCC):
   * <p>
   * The owner is required to contain the formatted name of an individual or
   * organization using the "fn" property. When the name is that of an
   * organization, the entry also is required to contain the "kind" property,
   * with a value of "org".
   * <p>
   * Key Bridge: This is an LDAP distinguished name ("DN").
   */
  @XmlElement(required = true)
  public String owner;
  /**
   * The vCard contact information for the device operator is OPTIONAL but may
   * be required by specific rulesets.
   * <p>
   * 9.1.2.1. Federal Communications Commission (FCC):
   * <p>
   * The operator entry is required to contain the following properties for the
   * contact person responsible for the device's operation: "fn", "adr", "tel",
   * and "email".
   * <p>
   * Key Bridge: This is an LDAP distinguished name ("DN"). For FIXED type
   * devices the DN should indicate the professional installer ID at the
   * attribute `installerId`.
   */
  public String operator;

  /**
   * Get the owner information.
   * <p>
   * Key Bridge: This is an LDAP distinguished name ("DN").
   *
   * @return the owner information
   */
  public String getOwner() {
    return owner;
  }

  /**
   * Set the owner information.
   * <p>
   * Key Bridge: This is an LDAP distinguished name ("DN").
   *
   * @param owner the owner information
   */
  public void setOwner(String owner) {
    this.owner = owner;
  }

  /**
   * Get the device operator information.
   * <p>
   * Key Bridge: This is an LDAP distinguished name ("DN").
   *
   * @return the device operator information
   */
  public String getOperator() {
    return operator;
  }

  /**
   * Set the device operator information. Key Bridge: This is an LDAP
   * distinguished name ("DN"). For FIXED type devices the DN should indicate
   * the professional installer ID at the attribute `installerId`.
   *
   * @param operator the device operator information
   */
  public void setOperator(String operator) {
    this.operator = operator;
  }

}

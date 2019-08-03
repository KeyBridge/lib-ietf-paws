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
package org.ietf.lib.paws.message;

import javax.xml.bind.annotation.*;
import org.ietf.lib.paws.DbUpdateSpec;
import org.ietf.lib.paws.type.PawsRulesetType;

/**
 * 4.4.2. REGISTRATION_RESP
 * <p>
 * <img alt="clazz" src="doc-files/registration_resp.png">
 * <p>
 * The registration response message acknowledges successful registration by
 * including a RulesetInfo message for each ruleset in which the registration is
 * accepted. If the Database accepts the registration for none of the specified
 * rulesets, the Database MUST return the NOT_REGISTERED error (see "Error
 * Codes" (Section 5.17)).
 * <pre>
 *   +---------------------------------------+
 *   |REGISTRATION_RESP                      |
 *   +----------------------------+----------+   1..* +-------------+
 *   |rulesetInfos:list           | REQUIRED |-------=| RulesetInfo |
 *   |databaseChange:DbUpdateSpec | OPTIONAL |        +-------------+
 *   |............................|..........|
 *   |*other:any                  | OPTIONAL |
 *   +----------------------------+----------+
 * </pre> other: Database implementations MAY return additional parameters in
 * the registration response. The Master Device MUST ignore any parameters it
 * does not understand. Consult the PAWS Parameters Registry (Section 9.2) for
 * possible additional parameters.
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "RegistrationResponse")
@XmlType(name = "RegistrationResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegistrationResponse {

  /**
   * Key Bridge modification. REQUIRED. Change from List to single instance. A
   * device may only register and operate under ONE jurisdiction at a time.
   * <p>
   * A RulesetInfo (Section 5.6) list MUST be included in the response. Each
   * entry corresponds to a ruleset for which the registration was accepted. The
   * list MUST contain at least one entry.
   * <p>
   * Each RulesetInfo in the response MUST match one of the ruleset IDs
   * specified in the DeviceDescriptor of REGISTRATION_REQ.
   * <p>
   * If the Database does not support the device or supports none of the
   * rulesets specified in the DeviceDescriptor, it MUST instead return an error
   * with the UNSUPPORTED code (see Table 1) in the error response.
   */
  @XmlElement(required = true)
  private PawsRulesetType rulesetId;
  /**
   * The Database MAY include a DbUpdateSpec (Section 5.7) to notify the Master
   * Device of a change to the database URI, providing one or more alternate
   * database URIs. The device needs to update its preconfigured entry for the
   * responding Database with the alternate Databases listed in the
   * DbUpdateSpec.
   */
  private DbUpdateSpec databaseChange;

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

  public DbUpdateSpec getDatabaseChange() {
    return databaseChange;
  }

  public void setDatabaseChange(DbUpdateSpec databaseChange) {
    this.databaseChange = databaseChange;
  }

  @Override
  public String toString() {
    return "RegistrationResponse{" + "rulesetId=" + rulesetId + ", databaseChange=" + databaseChange + '}';
  }

}

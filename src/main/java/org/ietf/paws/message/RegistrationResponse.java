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
package org.ietf.paws.message;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;
import org.ietf.paws.DbUpdateSpec;
import org.ietf.paws.RulesetInfo;

/**
 * Current: Key Bridge protocol {@code version} and {@code message} UUID fields
 * attributes added.
 * <p>
 * <img src="doc-files/registration_resp_1.png">
 * <p>
 * Original:
 * <p>
 * <img src="doc-files/registration_resp.png">
 * <p>
 * 4.4.2. REGISTRATION_RESP
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
 *   |rulesetInfos:list           | REQUIRED |------->| RulesetInfo |
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
@XmlRootElement(name = "REGISTRATION_RESP")
@XmlType(name = "REGISTRATION_RESP")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegistrationResponse extends AbstractResponse {

  /**
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
  private List<RulesetInfo> rulesetInfos;
  /**
   * The Database MAY include a DbUpdateSpec (Section 5.7) to notify the Master
   * Device of a change to the database URI, providing one or more alternate
   * database URIs. The device needs to update its preconfigured entry for the
   * responding Database with the alternate Databases listed in the
   * DbUpdateSpec.
   */
  private DbUpdateSpec databaseChange;

  public List<RulesetInfo> getRulesetInfos() {
    if (rulesetInfos == null) {
      rulesetInfos = new ArrayList<>();
    }
    return rulesetInfos;
  }

  public void setRulesetInfos(List<RulesetInfo> rulesetInfos) {
    this.rulesetInfos = rulesetInfos;
  }

  public DbUpdateSpec getDatabaseChange() {
    return databaseChange;
  }

  public void setDatabaseChange(DbUpdateSpec databaseChange) {
    this.databaseChange = databaseChange;
  }
}

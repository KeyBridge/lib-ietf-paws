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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.*;
import org.ietf.lib.paws.DbUpdateSpec;
import org.ietf.lib.paws.RulesetInfo;

/**
 * 4.3.2. INIT_RESP
 * <p>
 * <img alt="clazz" src="doc-files/init_resp.png">
 * <p>
 * The initialization response message communicates database parameters to the
 * requesting device. This response is returned only when there is at least one
 * ruleset. Otherwise, the Database returns an error response, as described in
 * INIT_REQ (Section 4.3.1).
 * <pre>
 * +---------------------------------------+
 * |INIT_RESP                              |
 * +----------------------------+----------+   1..* +-------------+
 * |rulesetInfos:list           | REQUIRED |-------=| RulesetInfo |
 * |databaseChange:DbUpdateSpec | OPTIONAL |        +-------------+
 * |.......................................|
 * |*other:any                  | OPTIONAL |
 * +----------------------------+----------+
 * </pre> other: The Database MAY include additional handshake parameters in the
 * INIT_RESP (Section 4.3.2) message. The Master Device MUST ignore all
 * parameters it does not understand. Consult the PAWS Parameters Registry
 * (Section 9.2) for possible additional parameters.
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "INIT_RESP")
@XmlType(name = "INIT_RESP")
@XmlAccessorType(XmlAccessType.FIELD)
public class InitializationResponse {

  /**
   * A RulesetInfo (Section 5.6) list MUST be included in the response. Each
   * RulesetInfo corresponds to a ruleset supported by the Database and is
   * applicable to the location specified in the INIT_REQ (Section 4.3.1)
   * message.
   * <p>
   * If the device included a list of ruleset IDs in the DeviceDescriptor of its
   * INIT_REQ message, each RulesetInfo in the response MUST match one of the
   * specified ruleset IDs.
   * <p>
   * If the DeviceDescriptor did not contain any ruleset IDs, the Database
   * SHOULD include in the rulesetInfos list a RulesetInfo for each ruleset it
   * supports at the specified location.
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

  /**
   * Make default no-arg constructor protected to encourage ruleset info
   * setting.
   */
  protected InitializationResponse() {
  }

  public InitializationResponse(RulesetInfo rulesetInfo) {
    this.rulesetInfos = rulesetInfo == null ? null : Arrays.asList(rulesetInfo);
  }

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

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
import org.ietf.lib.paws.type.PawsRulesetType;

/**
 * 5.6. RulesetInfo
 * <p>
 * <img alt="clazz" src="doc-files/rulesetInfo.png">
 * <p>
 * RulesetInfo contains parameters for the ruleset of a regulatory domain that
 * is communicated using the Initialization (Section 4.3), Device Registration
 * (Section 4.4), and Available Spectrum Query (Section 4.5) components.
 * <pre>
 * +------------------------------------------+
 * |RulesetInfo                               |
 * +------------------------------------------+
 * |authority:string        | REQUIRED        |
 * |rulesetId:string        | REQUIRED        |
 * |maxLocationChange:float | see description |
 * |maxPollingSecs:int      | see description |
 * |..........................................|
 * |*other:any              | OPTIONAL        |
 * +------------------------+-----------------+
 * </pre>
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "RulesetInfo")
@XmlType(name = "RulesetInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class RulesetInfo {

  /**
   * 600 seconds = 5 minutes. The default maximum duration, in seconds, between
   * requests for available spectrum.
   */
  private static final int MAX_POLLING_SECS = 600;
  /**
   * A string that indicates the regulatory domain to which the ruleset applies
   * is REQUIRED. It will normally be a 2-letter country code defined by Country
   * Codes - ISO 3166 [ISO3166-1].
   */
  @XmlElement(required = true)
  private String authority;
  /**
   * The ID of a ruleset for the specified authority (see Ruleset ID Registry
   * (Section 9.1)). The device can use this to determine additional device
   * behavior required by the associated ruleset. To define new ruleset IDs, see
   * "Defining Ruleset Identifiers" (Section 8.1).
   */
  @XmlElement(required = true)
  private PawsRulesetType rulesetId;
  /**
   * The maximum location change in meters is REQUIRED for the Initialization
   * Response (Section 4.3.2), but OPTIONAL otherwise. Some regulatory domains
   * mandate that, when the device changes location by more than this specified
   * distance, it contact the Database to get the available spectrum for the new
   * location. If this value is provided by the Database within the context of
   * an Available Spectrum Response (Section 4.5.2), it takes precedence over
   * the value within the Initialization Response (Section 4.3.2)
   */
  @XmlElement(required = true)
  private Double maxLocationChange;
  /**
   * The maximum duration, in seconds, between requests for available spectrum
   * is REQUIRED for the Initialization Response (Section 4.3.2), but OPTIONAL
   * otherwise. The device MUST contact the Database to get available spectrum
   * no less frequently than this duration. If this value is provided within the
   * context of an Available Spectrum Response (Section 4.5.2), it takes
   * precedence over the value within the Initialization Response (Section
   * 4.3.2).
   */
  @XmlElement(required = true)
  private Integer maxPollingSecs;

  /**
   * Build a RulesetInfo configuration. This created a complete configuration
   * for all types except ETSI, which do not have country specific details.
   *
   * @param rulesetType the rule set type
   * @return a new RulesetInfo configuration
   */
  public static RulesetInfo getInstance(PawsRulesetType rulesetType) {
    RulesetInfo info = new RulesetInfo();
    info.setAuthority(rulesetType.getIso2());
    info.setRulesetId(rulesetType);
    info.setMaxLocationChange(rulesetType.getMaxLocationChange());
    info.setMaxPollingSecs(MAX_POLLING_SECS);
    return info;
  }

  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  /**
   * Get the PAWS Ruleset ID Registry values.
   *
   * @return the enumerated PawsRulesetType
   */
  public PawsRulesetType getRulesetId() {
    return rulesetId;
  }

  /**
   * Set the PAWS Ruleset ID Registry values.
   *
   * @param rulesetId the enumerated PawsRulesetType
   */
  public void setRulesetId(PawsRulesetType rulesetId) {
    this.rulesetId = rulesetId;
  }

  public Double getMaxLocationChange() {
    return maxLocationChange;
  }

  public void setMaxLocationChange(Double maxLocationChange) {
    this.maxLocationChange = maxLocationChange;
  }

  public Integer getMaxPollingSecs() {
    return maxPollingSecs;
  }

  public void setMaxPollingSecs(Integer maxPollingSecs) {
    this.maxPollingSecs = maxPollingSecs;
  }
}

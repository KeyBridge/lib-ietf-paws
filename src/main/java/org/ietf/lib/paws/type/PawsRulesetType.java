/*
 * Copyright 2019 Key Bridge. All rights reserved. Use is subject to license
 * terms.
 *
 * This software code is protected by Copyrights and remains the property of
 * Key Bridge and its suppliers, if any. Key Bridge reserves all rights in and to
 * Copyrights and no license is granted under Copyrights in this Software
 * License Agreement.
 *
 * Key Bridge generally licenses Copyrights for commercialization pursuant to
 * the terms of either a Standard Software Source Code License Agreement or a
 * Standard Product License Agreement. A copy of either Agreement can be
 * obtained upon request by sending an email to info@keybridgewireless.com.
 *
 * All information contained herein is the property of Key Bridge and its
 * suppliers, if any. The intellectual and technical concepts contained herein
 * are proprietary.
 */
package org.ietf.lib.paws.type;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * PAWS Ruleset ID Registry
 * <p>
 * Enumerated Ruleset IDs for the DeviceDescriptor::rulesetIds list.
 * <p>
 * The list of identifiers for rulesets supported by the device (see Ruleset ID
 * Registry (Section 9.1)). A Database MAY require that the device provides this
 * list before servicing the device requests. If the Database supports none of
 * the rulesets specified in the list, the Database MAY refuse to service the
 * device requests. See RulesetInfo (Section 5.6) for discussion on ruleset
 * identifiers. If present, the list MUST contain at least one entry.
 *
 * @see
 * <a href="https://www.iana.org/assignments/paws/paws.xhtml#parameters">PAWS
 * Ruleset ID Registry</a>
 * @author Key Bridge
 * @since v0.5.0 created 07/26/19
 * @since v0.6.0 add FCC_Part15H_2019, ISED_DBS01_2015
 */
@XmlEnum
public enum PawsRulesetType {

  /**
   * Title 47: Telecommunication PART 15—RADIO FREQUENCY DEVICES Subpart H—White
   * Space Devices
   * <p>
   * Original rules ca. 2010.
   *
   * @see
   * <a href="http://www.ecfr.gov/cgi-bin/text-idx?rgn=div6&view=text&node=47:1.0.1.1.16.8">Subpart
   * H—White Space Devices</a>
   * @deprecated Part 15H updated ca. 2015-2019 fromm the 600 MHz repack.
   */
  @Deprecated
  @XmlEnumValue("FccTvBandWhiteSpace-2010")
  FCC_Part15H_2010("FccTvBandWhiteSpace-2010"),

  /**
   * Title 47: Telecommunication PART 15—RADIO FREQUENCY DEVICES Subpart H—White
   * Space Devices
   *
   * @see
   * <a href="http://www.ecfr.gov/cgi-bin/text-idx?rgn=div6&view=text&node=47:1.0.1.1.16.8">Subpart
   * H—White Space Devices</a>
   */
  @XmlEnumValue("FCC-Part15H-2019")
  FCC_Part15H_2019("FCC-Part15H-2019"),

  /**
   * DBS-01 — White Space Database Specifications
   *
   * @see
   * <a href="https://www.ic.gc.ca/eic/site/smt-gst.nsf/eng/sf10928.html">DBS-01</a>
   */
  @XmlEnumValue("ISED-DBS01-2015")
  ISED_DBS01_2015("ISED-DBS01-2015"),

  /**
   * ETSI EN 301 598 V1.1.1 (2014-04)
   * <p>
   * White Space Devices (WSD); Wireless Access Systems operating in the 470 MHz
   * to 790 MHz TV broadcast band; Harmonized EN covering the essential
   * requirements of article 3.2 of the R&TTE Directive
   *
   * @see
   * <a href="http://www.etsi.org/deliver/etsi_en/301500_301599/301598/01.01.01_60/en_301598v010101p.pdf">ETSI-EN-301-598</a>
   * @deprecated superceded by "ETSI-EN-301-598-2.1.1"
   */
  @Deprecated
  @XmlEnumValue("ETSI-EN-301-598-1.1.1")
  ETSI_EN301_598_111("ETSI-EN-301-598-1.1.1"),

  /**
   * ETSI EN 301 598 V2.1.1 (2018-01)
   * <p>
   * White Space Devices (WSD); Wireless Access Systems operating in the 470 MHz
   * to 790 MHz TV broadcast band; Harmonised Standard covering the essential
   * requirements of article 3.2 of Directive 2014/53/EU
   *
   * @see
   * <a href="https://www.etsi.org/deliver/etsi_en/301500_301599/301598/02.01.01_60/en_301598v020101p.pdf">ETSI-EN-301-598</a>
   */
  @XmlEnumValue("ETSI-EN-301-598-2.1.1")
  ETSI_EN301_598_211("ETSI-EN-301-598-2.1.1");

  private final String id;

  private PawsRulesetType(String id) {
    this.id = id;
  }

  /**
   * Get the id.
   *
   * @return the id String
   */
  public String getId() {
    return id;
  }

  /**
   * Get the PawsRulesetType from its ID
   *
   * @param id the id
   * @return the corresponding type
   */
  public static PawsRulesetType fromId(String id) {
    for (PawsRulesetType type : PawsRulesetType.values()) {
      if (type.getId().equalsIgnoreCase(id)) {
        return type;
      }
    }
    throw new IllegalArgumentException("RFC7545 unrecognized ruleset type: " + id);
  }

  /**
   * Returns the ID value.
   *
   * @return the id string value
   */
  @Override
  public String toString() {
    return id;
  }

}

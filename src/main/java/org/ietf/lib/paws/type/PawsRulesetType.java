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

import java.time.Duration;
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
   * <p>
   * Max movement before re-registration is 50 meters. <br>
   * Max lease time is 48 hours (== 172800 seconds)
   *
   * @see
   * <a href="http://www.ecfr.gov/cgi-bin/text-idx?rgn=div6&view=text&node=47:1.0.1.1.16.8">Subpart
   * H—White Space Devices</a>
   * @deprecated Part 15H updated ca. 2015-2019 fromm the 600 MHz repack.
   */
  @Deprecated
  FCC_Part_15_H_2010("USA", "US", 50, Duration.ofHours(48), Duration.ofDays(365)),

  /**
   * Title 47: Telecommunication PART 15—RADIO FREQUENCY DEVICES Subpart H—White
   * Space Devices
   * <p>
   * Max movement before re-registration is 50 meters. <br>
   * Max lease time is 48 hours (== 172800 seconds)
   *
   * @see
   * <a href="http://www.ecfr.gov/cgi-bin/text-idx?rgn=div6&view=text&node=47:1.0.1.1.16.8">Subpart
   * H—White Space Devices</a>
   */
  FCC_Part_15_H_2019("USA", "US", 50, Duration.ofHours(48), Duration.ofDays(365)),

  /**
   * DBS-01 — White Space Database Specifications
   * <p>
   * Max movement before re-registration is 50 meters. <br>
   * Max lease time is 48 hours (== 172800 seconds)
   *
   * @see
   * <a href="https://www.ic.gc.ca/eic/site/smt-gst.nsf/eng/sf10928.html">DBS-01</a>
   */
  ISED_DBS_01_2015("CAN", "CA", 50, Duration.ofHours(48), Duration.ofDays(365)),

  /**
   * ETSI EN 301 598 V1.1.1 (2014-04)
   * <p>
   * White Space Devices (WSD); Wireless Access Systems operating in the 470 MHz
   * to 790 MHz TV broadcast band; Harmonized EN covering the essential
   * requirements of article 3.2 of the R&amp;TTE Directive
   *
   * @see
   * <a href="http://www.etsi.org/deliver/etsi_en/301500_301599/301598/01.01.01_60/en_301598v010101p.pdf">ETSI-EN-301-598</a>
   * @deprecated superceded by "ETSI-EN-301-598-2.1.1"
   */
  @Deprecated
  @XmlEnumValue("ETSI-EN-301-598-1.1.1")
  ETSI_EN_301_598_111,

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
  ETSI_EN_301_598_211;

  /**
   * The ISO3 country code.
   */
  private final String iso3;
  /**
   * The ISO2 country code.
   */
  private final String iso2;
  /**
   * The maximum location change in meters is REQUIRED for the Initialization
   * Response (Section 4.3.2), but OPTIONAL otherwise. Some regulatory domains
   * mandate that, when the device changes location by more than this specified
   * distance, it contact the Database to get the available spectrum for the new
   * location. If this value is provided by the Database within the context of
   * an Available Spectrum Response (Section 4.5.2), it takes precedence over
   * the value within the Initialization Response (Section 4.3.2)
   */
  private final double maxLocationChange;

  /**
   * WSD (white space device) maximum channel lease time. Defaults to 1 hour if
   * not set.
   */
  private final Duration wsdLeaseTime;
  /**
   * LPA (wireless microphone) maximum channel lease time. Defaults to 1 hour if
   * not set.
   */
  private final Duration lpaLeaseTime;

  private PawsRulesetType() {
    this.iso3 = null;
    this.iso2 = null;
    this.maxLocationChange = 0;
    this.wsdLeaseTime = Duration.ofHours(1); // Defaults to 1 hour.
    this.lpaLeaseTime = Duration.ofHours(48); // Defaults to 48 hour.
  }

  private PawsRulesetType(String iso3, String iso2, double maxLocationChange, Duration wsdLeaseTime, Duration lpaLeaseTime) {
    this.iso3 = iso3;
    this.iso2 = iso2;
    this.maxLocationChange = maxLocationChange;
    this.wsdLeaseTime = wsdLeaseTime;
    this.lpaLeaseTime = lpaLeaseTime;
  }

  /**
   * Get the ISO3 country code
   *
   * @return the ISO3 country code
   */
  public String getIso3() {
    return iso3;
  }

  /**
   * Get the ISO2 country code
   *
   * @return the ISO2 country code
   */
  public String getIso2() {
    return iso2;
  }

  /**
   * Get the maximum location change in meters
   *
   * @return the maximum location change in meters
   */
  public double getMaxLocationChange() {
    return maxLocationChange;
  }

  /**
   * Get the WSD (white space device) maximum channel lease time, in hours.
   * Typical value is 48 hours. Defaults to 1 hour if not set.
   *
   * @return the WSD maximum channel lease time.
   */
  public Duration getWsdLeaseTime() {
    return wsdLeaseTime;
  }

  /**
   * Get the LPA (wireless microphone) maximum channel lease time, in hours.
   * Typical value is 1 year. Defaults to 48 hour if not set.
   *
   * @return the WSD maximum channel lease time.
   */
  public Duration getLpaLeaseTime() {
    return lpaLeaseTime;
  }

}

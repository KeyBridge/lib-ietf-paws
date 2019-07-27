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
 * ETSI EN 301 598 V2.1.1 (2018-01)
 * <p>
 * 4.2.5 Transmitter unwanted emissions <br>
 * 4.2.5.2.2 Limits
 * <p>
 * The manufacturer shall declare which Device Emission Class from table 2 that
 * the TVWSD complies with.
 * <p>
 * Reference Table 2: Adjacent Channel Leakage Ratios (ACLR) for different
 * Device Emission Classes
 *
 * @author Key Bridge
 * @since v0.6.0 created 07/26/19 to support ETSI operation
 */
@XmlEnum
public enum EtsiEmissionClassType {

  @XmlEnumValue("1")
  CLASS_1(1),
  @XmlEnumValue("2")
  CLASS_2(2),
  @XmlEnumValue("3")
  CLASS_3(3),
  @XmlEnumValue("4")
  CLASS_4(4),
  @XmlEnumValue("5")
  CLASS_5(5);

  /**
   * The class "code" value.
   */
  private final int code;

  private EtsiEmissionClassType(int code) {
    this.code = code;
  }

  /**
   * Get the code value, from table 2.
   *
   * @return the code value
   */
  public int getCode() {
    return code;
  }

  /**
   * Get an instance from a code
   *
   * @param code the code
   * @return the instance
   */
  public static EtsiEmissionClassType fromCode(int code) {
    return EtsiEmissionClassType.valueOf("CLASS_" + code);
  }

  /**
   * Returns the code value.
   *
   * @return the code string value
   */
  @Override
  public String toString() {
    return String.valueOf(code);
  }

}

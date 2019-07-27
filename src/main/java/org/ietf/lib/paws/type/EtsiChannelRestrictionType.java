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
 * Table 4: Operational parameters
 * <p>
 * Simultaneous channel operation power restriction. If the simultaneous channel
 * operation power restriction parameter is not provided, the device shall use
 * the default value of 0.
 * <p>
 * Can take values of 0 or 1. A value of 1 indicates the device that the power
 * restriction in clause 4.2.4.2 applies, a value of 0 indicates that the power
 * restriction does not apply. The default value is 0.
 * <p>
 * 4.2.4.2 Limits. The RF power and RF power spectral density radiated from a
 * TVWSD shall not exceed the levels stated below irrespective of the number of
 * transmit antennas.
 *
 * @author Key Bridge
 * @since v0.6.0 created 07/26/19 to support ETSI operation
 */
@XmlEnum
public enum EtsiChannelRestrictionType {

  /**
   * "0" indicates that the power restriction does not apply. The default value
   * is 0.
   */
  @XmlEnumValue("0")
  TYPE_0(0),
  /**
   * "1" indicates the device that the power restriction in clause 4.2.4.2
   * applies.
   * <p>
   * 4.2.4.2 Limits: "In the case of simultaneous operation in multiple DTT
   * channels, the TVWSDB may direct a device to restrict its maximum total RF
   * power as to {@code min{P 1,i}} dBm, where {@code P 1,i} is the RF power
   * level specified by the TVWSD for DTT channel i."
   */
  @XmlEnumValue("1")
  TYPE_1(1);

  /**
   * The class "code" value.
   */
  private final int code;

  private EtsiChannelRestrictionType(int code) {
    this.code = code;
  }

  /**
   * Get the code value, from Table 4: Operational parameters.
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
  public static EtsiChannelRestrictionType fromCode(int code) {
    return EtsiChannelRestrictionType.valueOf("TYPE_" + code);
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

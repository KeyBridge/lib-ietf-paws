/*
 * Copyright 2017 Key Bridge. All rights reserved.
 * Use is subject to license terms.
 *
 * Software Code is protected by Copyrights. Author hereby reserves all rights
 * in and to Copyrights and no license is granted under Copyrights in this
 * Software License Agreement.
 *
 * Key Bridge generally licenses Copyrights for commercialization pursuant to
 * the terms of either a Standard Software Source Code License Agreement or a
 * Standard Product License Agreement. A copy of either Agreement can be
 * obtained upon request from: info@keybridgewireless.com
 */
package org.ietf.lib.paws.type;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * Enumerated white space device access point operating modes for operation in
 * Canada under ISED jurisdiction.
 *
 * @since v0.6.3 added 01/28/17 copied from lib-whitespace-messaging
 * @since v1.0.0 rename 07/22/19 DeviceModeType to TvwsDeviceModeType
 * @since v0.14.0 copy 08/03/19 to ietf-paws library
 */
@XmlEnum
@XmlType(name = "IsedDeviceMode")
public enum IsedDeviceMode {

  /**
   * White Space Access Point (Fixed) operating at full power.
   * <p>
   * Device is FIXED standard classification (professionally installed,
   * non-transportable).
   */
  FIXED,
  /**
   * White Space Access Point (Unlicensed, Mode II).
   * <p>
   * Device is Mode 2 standard classification (transportable, not in-motion).
   */
  MODE_2,
  /**
   * White Space Client Device (Unlicensed, Mode I) operating at low power.
   * <p>
   * Device is Mode 1 classification.
   */
  MODE_1,
  /**
   * Development license; similar to low power auxiliary.
   */
  DEV,
  /**
   * Low power auxiliary, wireless microphone.
   */
  LPA;

}

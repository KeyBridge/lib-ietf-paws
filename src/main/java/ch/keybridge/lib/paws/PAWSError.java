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
package ch.keybridge.lib.paws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * Protocol to Access White-Space (PAWS) Error Code Registry.
 *
 * @author Key Bridge LLC
 * @since v0.1.0 added 01/30/17
 * @see
 * <a href="https://www.iana.org/assignments/paws/paws.xhtml#parameters">PAWS
 * Ruleset ID Registry</a>
 */
@XmlType(name = "PAWSError")
@XmlEnum
public enum PAWSError {

  /**
   * The Database does not support the specified version of the message. This
   * error does not use any additional data.
   */
  VERSION(-101, "The Database does not support the specified version of the message."),
  /**
   * The Database does not support the device. For example, it supports none of
   * the ruleset specified in the request or does not support the device, based
   * on its device type, model, etc. This error does not use any additional
   * data.
   */
  UNSUPPORTED(-102, "The Database does not support the device."),
  /**
   * The Database does not implement the optional request or optional feature.
   * This error does not use any additional data.
   */
  UNIMPLEMENTED(-103, "The Database does not implement the optional request or optional feature."),
  /**
   * The specified geolocation is outside the coverage area of the Database. The
   * Database MAY include a DbUpdateSpec (Section 5.7 of [RFC7545]) to provide a
   * list of alternate databases that might be appropriate for the requested
   * location. See OUTSIDE_COVERAGE Error (Section 5.17.1 of [RFC7545]) for more
   * details.
   */
  OUTSIDE_COVERAGE(-104, "The specified geolocation is outside the coverage area of the Database. See OUTSIDE_COVERAGE Error (Section 5.17.1 of [RFC7545]) for more details."),
  /**
   * The Database has changed its URI. The Database MAY include a DbUpdateSpec
   * (Section 5.7 of [RFC7545]) in the error response to provide devices with
   * one or more alternate database URIs. The device needs to update its
   * preconfigured entry for the responding database with the alternate
   * databases listed in the DbUpdateSpec. See DATABASE_CHANGE Error (Section
   * 5.17.2 of [RFC7545]) for more details.
   */
  DATABASE_CHANGE(-105, "The Database has changed its URI. See DATABASE_CHANGE Error (Section 5.17.2 of [RFC7545]) for more details."),
  /**
   * A required parameter is missing. The Database MUST include a list of the
   * required parameter names. The Database MAY include only names of parameters
   * that are missing, but MAY include a full list. Including the full list of
   * missing parameters may reduce the number of re-queries from the device. See
   * MISSING Error (Section 5.17.3 of [RFC7545]) for more details.
   */
  MISSING(-201, "A required parameter is missing. See MISSING Error (Section 5.17.3 of [RFC7545]) for more details."),
  /**
   * A parameter value is invalid in some way. The Database SHOULD include a
   * message indicating which parameter and why its value is invalid. This error
   * does not use any additional data.
   */
  INVALID_VALUE(-202, "A parameter value is invalid in some way."),
  /**
   * The device is not authorized to used the Database. Authorization may be
   * determined by the ruleset or be dependent on prior arrangement between the
   * device and Database. This error does not use any additional data.
   */
  UNAUTHORIZED(-301, "The device is not authorized to used the Database."),
  /**
   * Device registration required, but the device is not registered. This error
   * does not use any additional data.
   */
  NOT_REGISTERED(-302, "Device registration required, but the device is not registered.");

  private final int code;
  private final String description;

  private PAWSError(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public int getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  public static PAWSError fromCode(int code) {
    for (PAWSError error : PAWSError.values()) {
      if (error.getCode() == code) {
        return error;
      }
    }
    throw new IllegalArgumentException("Reserved or Unassigned code: " + code);
  }

}

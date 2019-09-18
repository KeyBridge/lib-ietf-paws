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
package org.ietf.lib.paws.type;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * Protocol to Access White-Space (PAWS) Error Code Registry.
 * <p>
 * Note: Error code values range from -32768 to 32767, inclusive. See Section
 * 5.17 of [RFC7545].
 *
 * @see
 * <a href="https://www.iana.org/assignments/paws/paws.xhtml#parameters">PAWS
 * Ruleset ID Registry</a>
 * @author Key Bridge LLC
 * @since v0.1.0 added 01/30/17
 * @since v0.5.0 rename 07/26/19 to PawsErrorCode, move to 'type' package
 */
@XmlEnum
public enum PawsErrorType {

  /**
   * The Database does not support the specified version of the message. This
   * error does not use any additional data.
   */
  @XmlEnumValue("-101")
  VERSION(-101, "The Database does not support the specified version of the message."),
  /**
   * The Database does not support the device. For example, it supports none of
   * the ruleset specified in the request or does not support the device, based
   * on its device type, model, etc. This error does not use any additional
   * data.
   */
  @XmlEnumValue("-102")
  UNSUPPORTED(-102, "The Database does not support the device."),
  /**
   * The Database does not implement the optional request or optional feature.
   * This error does not use any additional data.
   */
  @XmlEnumValue("-103")
  UNIMPLEMENTED(-103, "The Database does not implement the optional request or optional feature."),
  /**
   * The specified geolocation is outside the coverage area of the Database. The
   * Database MAY include a DbUpdateSpec (Section 5.7 of [RFC7545]) to provide a
   * list of alternate databases that might be appropriate for the requested
   * location. See OUTSIDE_COVERAGE Error (Section 5.17.1 of [RFC7545]) for more
   * details.
   */
  @XmlEnumValue("-104")
  OUTSIDE_COVERAGE(-104, "The specified geolocation is outside the coverage area of the Database."),
  /**
   * The Database has changed its URI. The Database MAY include a DbUpdateSpec
   * (Section 5.7 of [RFC7545]) in the error response to provide devices with
   * one or more alternate database URIs. The device needs to update its
   * preconfigured entry for the responding database with the alternate
   * databases listed in the DbUpdateSpec. See DATABASE_CHANGE Error (Section
   * 5.17.2 of [RFC7545]) for more details.
   */
  @XmlEnumValue("-105")
  DATABASE_CHANGE(-105, "The Database has changed its URI."),

  /**
   * A required parameter is missing. The Database MUST include a list of the
   * required parameter names. The Database MAY include only names of parameters
   * that are missing, but MAY include a full list. Including the full list of
   * missing parameters may reduce the number of re-queries from the device. See
   * MISSING Error (Section 5.17.3 of [RFC7545]) for more details.
   */
  @XmlEnumValue("-201")
  MISSING(-201, "A required parameter is missing."),
  /**
   * A parameter value is invalid in some way. The Database SHOULD include a
   * message indicating which parameter and why its value is invalid. This error
   * does not use any additional data.
   */
  @XmlEnumValue("-202")
  INVALID_VALUE(-202, "A parameter value is invalid in some way."),

  /**
   * The device is not authorized to used the Database. Authorization may be
   * determined by the ruleset or be dependent on prior arrangement between the
   * device and Database. This error does not use any additional data.
   */
  @XmlEnumValue("-301")
  UNAUTHORIZED(-301, "The device is not authorized to used the Database."),
  /**
   * Device registration required, but the device is not registered. This error
   * does not use any additional data.
   */
  @XmlEnumValue("-302")
  NOT_REGISTERED(-302, "Device registration required, but the device is not registered."),

  /**
   * Device initialization required prior to receiving service.
   * <p>
   * Key Bridge extension.
   */
  @XmlEnumValue("-303")
  NOT_INITIALIZED(-303, "Device initialization required prior to receiving service."),

  /**
   * The request was invalid for some other reason. For example, the API key was
   * invalid.
   */
  @XmlEnumValue("-400")
  BAD_REQUEST(-400, "The request was invalid for some other reason. For example, the API key was invalid."),
  /**
   * The number of queries has exceeded the quota allocated to the project
   * associated with the API key. This may also be triggered if the API key
   * wasn't present in the request.
   */
  @XmlEnumValue("-403")
  QUOTA(-403, "The number of queries has exceeded the API key quota. This may also be triggered if the API key wasn't present in the request. "),

  /**
   * The request failed due to an internal error in the database.
   */
  @XmlEnumValue("-500")
  INTERNAL_DATABASE_ERROR(-500, "The request failed due to an internal error in the database.");

  /**
   * The error code.
   */
  private final int code;
  /**
   * A human readable description.
   */
  private final String description;

  private PawsErrorType(int code, String description) {
    this.code = code;
    this.description = description;
  }

  /**
   * Get the error code number.
   *
   * @return the error code
   */
  public int getCode() {
    return code;
  }

  /**
   * Get the human readable description
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Get a PawsErrorCode from an integer code value
   *
   * @param code the code value
   * @return the corresponding PawsErrorCode instance
   */
  public static PawsErrorType fromCode(int code) {
    for (PawsErrorType error : PawsErrorType.values()) {
      if (error.getCode() == code) {
        return error;
      }
    }
    throw new IllegalArgumentException("RFC7545 reserved or unassigned code: " + code);
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

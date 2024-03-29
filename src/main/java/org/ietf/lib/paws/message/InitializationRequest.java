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

import javax.xml.bind.annotation.*;
import org.ietf.lib.paws.DeviceDescriptor;
import org.ietf.lib.paws.GeoLocation;

/**
 * 4.3.1. INIT_REQ
 * <p>
 * <img alt="clazz" src="doc-files/init_req.png">
 * <p>
 * The initialization request message allows the Master Device to initiate
 * exchange of capabilities with the Database.
 * <pre>
 * +---------------------------------------+
 * |INIT_REQ                               |
 * +----------------------------+----------|
 * |deviceDesc:DeviceDescriptor | REQUIRED |
 * |location:GeoLocation        | REQUIRED |
 * |.......................................|
 * |*other:any                  | OPTIONAL |
 * +----------------------------+----------+
 * </pre> Parameters:
 * <p>
 * other: The Master Device MAY specify additional handshake parameters in the
 * INIT_REQ message. The Database MUST ignore all parameters it does not
 * understand. To simplify its initialization logic, a Master Device that
 * supports multiple Databases and rulesets can include the union of all
 * required parameters for all its supported rulesets. Consult the PAWS
 * Parameters Registry (Section 9.2) for possible additional parameters.
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "InitializationRequest")
@XmlType(name = "InitializationRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class InitializationRequest {

  /**
   * The DeviceDescriptor (Section 5.2) for the device is REQUIRED. If the
   * device descriptor does not contain any ruleset IDs, the Master Device is
   * asking the Database to return a RulesetInfo (Section 5.6) list that
   * specifies the rulesets that it supports at the specified location.
   */
  @XmlElement(required = true)
  private DeviceDescriptor deviceDesc;
  /**
   * The GeoLocation (Section 5.1) of the device is REQUIRED. If the location is
   * outside all regulatory domain supported by the Database, the Database MUST
   * respond with an OUTSIDE_COVERAGE error (see Table 1).
   */
  @XmlElement(required = true)
  private GeoLocation location;

  public DeviceDescriptor getDeviceDesc() {
    return deviceDesc;
  }

  public void setDeviceDesc(DeviceDescriptor deviceDesc) {
    this.deviceDesc = deviceDesc;
  }

  public GeoLocation getLocation() {
    return location;
  }

  public void setLocation(GeoLocation location) {
    this.location = location;
  }

  /**
   * Validate a request message.
   *
   * @throws Exception if the transmit channel power is not provided. The
   *                   exception message will describe the invalid
   *                   configuration.
   * @since v0.21.0 added 09/14/19
   */
  public void validate() throws Exception {
    if (deviceDesc == null) {
      throw new Exception("deviceDesc is required.");
    }
    deviceDesc.validate();
    if (location == null) {
      throw new Exception("location is required");
    }
    location.isValid();
  }
}

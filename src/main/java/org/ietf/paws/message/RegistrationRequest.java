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
package org.ietf.paws.message;

import javax.xml.bind.annotation.*;
import org.ietf.paws.AntennaCharacteristics;
import org.ietf.paws.DeviceDescriptor;
import org.ietf.paws.DeviceOwner;
import org.ietf.paws.GeoLocation;

/**
 * <img src="doc-files/registration_req.png">
 * <p>
 * 4.4.1. REGISTRATION_REQ
 * <p>
 * The registration request message contains the required registration
 * parameters. A parameter marked as optional may be required by some rulesets.
 * <pre>
 * +-------------------------------------------+
 * |REGISTRATION_REQ                           |
 * +-------------------------------+-----------+
 * |deviceDesc:DeviceDescriptor    | REQUIRED  |
 * |location:GeoLocation           | REQUIRED  |
 * |deviceOwner:DeviceOwner        | OPTIONAL  |
 * |antenna:AntennaCharacteristics | OPTIONAL  |
 * |...........................................|
 * |*other:any                     | OPTIONAL  |
 * +-------------------------------+-----------+
 * </pre> other: Rulesets and database implementations may require additional
 * registration parameters. To simplify its registration logic, the Master
 * Device MAY send a union of the registration information required by all
 * supported rulesets. The Database MUST ignore all parameters it does not
 * understand. Consult the PAWS Parameters Registry (Section 9.2) for possible
 * additional parameters.
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "REGISTRATION_REQ")
@XmlType(name = "REGISTRATION_REQ")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegistrationRequest {

  /**
   * The DeviceDescriptor (Section 5.2) for the Master Device is REQUIRED. The
   * ruleset IDs included in the DeviceDescriptor indicate the rulesets for
   * which the device wishes to register.
   */
  @XmlElement(required = true)
  private DeviceDescriptor deviceDesc;
  /**
   * The GeoLocation (Section 5.1) for the device is REQUIRED. More precisely,
   * this is the location at which the device intends to operate. If the
   * location is outside all regulatory domains supported by the Database, the
   * Database MUST respond with an OUTSIDE_COVERAGE error (see Table 1).
   */
  @XmlElement(required = true)
  private GeoLocation location;
  /**
   * The DeviceOwner (Section 5.5) information is OPTIONAL. Some rulesets may
   * require deviceOwner information under certain conditions. See PAWS Ruleset
   * ID Registry (Section 9.1) for ruleset-specific requirements.
   */
  private DeviceOwner deviceOwner;
  /**
   * The AntennaCharacteristics (Section 5.3) is OPTIONAL.
   */
  private AntennaCharacteristics antenna;

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

  public DeviceOwner getDeviceOwner() {
    return deviceOwner;
  }

  public void setDeviceOwner(DeviceOwner deviceOwner) {
    this.deviceOwner = deviceOwner;
  }

  public AntennaCharacteristics getAntenna() {
    return antenna;
  }

  public void setAntenna(AntennaCharacteristics antenna) {
    this.antenna = antenna;
  }

}

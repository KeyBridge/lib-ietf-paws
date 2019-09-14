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
import org.ietf.lib.paws.AntennaCharacteristics;
import org.ietf.lib.paws.DeviceDescriptor;
import org.ietf.lib.paws.DeviceOwner;
import org.ietf.lib.paws.GeoLocation;

/**
 * 4.4.1. REGISTRATION_REQ
 * <p>
 * <img alt="clazz" src="doc-files/registration_req.png">
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
@XmlRootElement(name = "RegistrationRequest")
@XmlType(name = "RegistrationRequest")
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
   * The AntennaCharacteristics (Section 5.3) is OPTIONAL.
   * <p>
   * Key Bridge: AntennaCharacteristics are REQUIRED for FIXED devices to
   * provide the necessary antenna height above ground value.
   */
  @XmlElement(required = true)
  private AntennaCharacteristics antenna;

  /**
   * The DeviceOwner (Section 5.5) information is OPTIONAL. Some rulesets may
   * require deviceOwner information under certain conditions. See PAWS Ruleset
   * ID Registry (Section 9.1) for ruleset-specific requirements.
   * <p>
   * Key Bridge: DeviceOwner is REQUIRED for registration. It is used to
   * identify the responsible parties and bind with an account.
   */
  @XmlElement(required = true)
  private DeviceOwner deviceOwner;

  /**
   * Key Bridge extension. (Optional)
   * <p>
   * The user provided government authorization information. This is typically a
   * call sign or license number.
   * <p>
   * If not provided by the user then the registration request is interpreted to
   * represent an unlicensed service.
   *
   * @since v0.22.0
   */
  private String authorization;

  /**
   * /**
   * Key Bridge extension. (Optional)
   * <p>
   * The user provided service group identifier. A service group identifier is
   * specified by the registering user to inform the SAS that a collection of TV
   * white space devices should be considered as part of a one service and not
   * another.
   * <p>
   * For example, service group Id would be used when devices are installed to
   * service a campus at two different locations but administered by the same
   * parties. e.g. using service groups "campus-1" and "campus-2".
   * <p>
   * If not provided a service group ID will be automatically generated based
   * upon the Device Owner + Operator pair.
   */
  private String serviceGroupId;
  /**
   * Key Bridge extension. (Optional)
   * <p>
   * A brief service name. This is a human-readable name for this wireless
   * service. For example, “Concert in the Park.”
   */
  private String serviceName;
  /**
   * Key Bridge extension. (Optional)
   * <p>
   * A human-readable description of this wireless service. For example, “Simon
   * and Garfunkle Concert in New York's Central Park, Sept. 1981.”
   */
  private String serviceDescription;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
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

  public AntennaCharacteristics getAntenna() {
    return antenna;
  }

  public void setAntenna(AntennaCharacteristics antenna) {
    this.antenna = antenna;
  }

  public DeviceOwner getDeviceOwner() {
    return deviceOwner;
  }

  public void setDeviceOwner(DeviceOwner deviceOwner) {
    this.deviceOwner = deviceOwner;
  }

  public String getServiceGroupId() {
    return serviceGroupId;
  }

  public void setServiceGroupId(String serviceGroupId) {
    this.serviceGroupId = serviceGroupId;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getServiceDescription() {
    return serviceDescription;
  }

  public void setServiceDescription(String serviceDescription) {
    this.serviceDescription = serviceDescription;
  }

  public String getAuthorization() {
    return authorization;
  }

  public void setAuthorization(String authorization) {
    this.authorization = authorization;
  }//</editor-fold>

  /**
   * Affirm this is a valid registration request message.
   *
   * @return TRUE if all required fields are configured
   */
  public boolean isValid() {
    return deviceDesc.isValid() && location.isValid() && antenna.isValid() && deviceOwner.isValid();
  }

  /**
   * Validate this instance.
   *
   * @throws Exception describing the invalid configuration
   */
  public void validate() throws Exception {
    if (deviceDesc == null) {
      throw new Exception("RegistrationRequest::deviceDesc is required");
    }
    if (location == null) {
      throw new Exception("RegistrationRequest::location is required");
    }
    if (antenna == null) {
      throw new Exception("RegistrationRequest::antenna is required");
    }
    if (deviceOwner == null) {
      throw new Exception("RegistrationRequest::deviceOwner is required");
    }
    /**
     * Validate all my children.
     */
    deviceDesc.validate();
    location.validate();
    antenna.validate();
    deviceOwner.validate();
  }

}

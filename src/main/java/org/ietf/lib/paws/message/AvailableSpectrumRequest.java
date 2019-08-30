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
import org.ietf.lib.paws.*;
import org.ietf.lib.paws.type.SpectrumRequestType;

/**
 * 4.5.1. AVAIL_SPECTRUM_REQ
 * <p>
 * <img alt="clazz" src="doc-files/avail_spectrum_req.png">
 * <p>
 * The request message for the Available Spectrum Query protocol MUST include a
 * geolocation. Rulesets may mandate that it be the device's current location or
 * allow it to be an anticipated location. A parameter marked as optional may be
 * required by some rulesets.
 * <pre>
 * +----------------------------------------------------+
 * |AVAIL_SPECTRUM_REQ                                  |
 * +----------------------------------+-----------------+
 * |deviceDesc:DeviceDescriptor       | see description |
 * |location:GeoLocation              | see description |
 * |owner:DeviceOwner                 | OPTIONAL        |
 * |antenna:AntennaCharacteristics    | OPTIONAL        |
 * |capabilities:DeviceCapabilities   | OPTIONAL        |
 * |masterDeviceDesc:DeviceDescriptor | OPTIONAL        |
 * |masterDeviceLocation:GeoLocation  | see description |
 * |requestType:string                | OPTIONAL        |
 * |..................................|.................|
 * |*other:any                        | OPTIONAL        |
 * +----------------------------------+-----------------+
 * </pre> other: Rulesets and database implementations may require additional
 * request parameters. The Database MUST ignore all parameters it does not
 * understand. Consult the PAWS Parameters Registry (Section 9.2) for possible
 * additional parameters.
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "AvailableSpectrumRequest")
@XmlType(name = "AvailableSpectrumRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class AvailableSpectrumRequest {

  /**
   * The request type is <strike>OPTIONAL</strike> REQUIRED; it may be used to
   * modify the request, but its use depends on the applicable ruleset. The
   * request type may be used, for example, to indicate that the response should
   * include generic Slave Device parameters without having to specify the
   * device descriptor for a specific device. When requestType is missing, the
   * request is for a specific device (Master or Slave), so deviceDesc is
   * REQUIRED.
   * <p>
   * 9.1.2.2. European Telecommunications Standards Institute (ETSI)
   * <p>
   * Modifies the available-spectrum request type. If specified, the only valid
   * value is "Generic Slave" and the Database is required to respond with
   * generic operating parameters for any Slave Device.
   * <p>
   * Key Bridge: `requestType` is REQUIRED. Changes from String to enumerated
   * type.
   * <p>
   * Developer Note: For `LPA` type queries only the `location` field is
   * required.
   */
  @XmlElement(required = true)
  private SpectrumRequestType requestType;

  /**
   * The DeviceDescriptor (Section 5.2) for the device requesting available
   * spectrum. When the request is made by a Master Device on its own behalf,
   * the descriptor is that of the Master Device, and it is REQUIRED. When the
   * request is made on behalf of a Slave Device, the descriptor is that of the
   * Slave Device, and it is REQUIRED if the "requestType" parameter is not
   * specified. The deviceDesc parameter may be OPTIONAL for some values of
   * requestType.
   */
  @XmlElement(required = true)
  private DeviceDescriptor deviceDesc;
  /**
   * The GeoLocation (Section 5.1) for the device requesting available spectrum.
   * More precisely, this is the location at which the device intends to
   * operate. When the request is made by the Master Device on its own behalf,
   * the location is that of the Master Device, and it is REQUIRED. When the
   * request is made by the Master Device on behalf of a Slave Device, the
   * location is that of the Slave Device, and it is OPTIONAL (see also
   * masterDeviceLocation). The location may be an anticipated position of the
   * device to support mobile devices, but its use depends on the ruleset. If
   * the location specifies a region, rather than a point, the Database MAY
   * return an error with the UNIMPLEMENTED code (see Table 1), if it does not
   * implement query by region.
   * <p>
   * NOTE: Technically, this is the location of the radiation center of the
   * device's antenna, but that distinction may be relevant only for fixed
   * devices.
   */
  @XmlElement(required = true)
  private GeoLocation location;
  /**
   * The AntennaCharacteristics (Section 5.3) is OPTIONAL.
   * <p>
   * Key Bridge: AntennaCharacteristics is REQUIRED if submitted by a Master
   * Device. Also required if the antenna height or rotation have changed.
   */
  private AntennaCharacteristics antenna;

  /**
   * Key Bridge extension to support US Part 15 operation of Mode-2 devices.
   * <p>
   * The DeviceDescriptor for the downstream device that the requesting device
   * intends to communicate with. This is required for MODE-2 operation, as
   * different separation distances apply for communication with MODE-2 or
   * MODE-1 downstream devices.
   * <p>
   * In this entry only the `deviceMode` is REQUIRED; all other fields are
   * helpful if provided but optional.
   */
  private DeviceDescriptor communicatingWith;

  /**
   * The DeviceOwner (Section 5.5) information MAY be included to register the
   * device with the Database. This enables the device to register and get
   * spectrum-availability information in a single request. Some rulesets
   * mandate registration for specific device types.
   * <p>
   * Key Bridge: Rename from `owner` to `deviceOwner` for consistency with other
   * classes.
   *
   * @deprecated DeviceOwner is NOT accepted in AvailableSpectrumRequest.
   * DeviceOwner info for SLAVE devices is inherited from the Master.
   */
  private DeviceOwner deviceOwner;

  /**
   * When the request is made by a Master Device on behalf of a Slave Device,
   * the Master Device MAY provide its own descriptor.
   * <p>
   * Key Bridge: If submitted by a Master Device, the Master DeviceDescriptor is
   * REQUIRED, but only the `deviceId` and `serialNumber` fields are necessary
   * to identify the Master Device. Also note that the Master Device MUST be
   * already registered.
   */
  private DeviceDescriptor masterDeviceDesc;
  /**
   * When the request is made by a Master Device on behalf of a Slave Device,
   * the Master Device MUST provide its own GeoLocation (Section 5.1).
   * <p>
   * Key Bridge: This is used to affirm the registered location.
   */
  private GeoLocation masterDeviceLocation;
  /**
   * The Master Device MAY include its DeviceCapabilities (Section 5.4) to limit
   * the available-spectrum response to the spectrum that is compatible with its
   * capabilities. The Database SHOULD NOT return spectrum that is not
   * compatible with the specified capabilities.
   * <p>
   * Key Bridge: DeviceCapabilities is (optionally) used for LPA registrations
   * to specify the desired lease duration.
   */
  private DeviceCapabilities capabilities;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public DeviceDescriptor getDeviceDesc() {
    return deviceDesc;
  }

  public void setDeviceDesc(DeviceDescriptor deviceDesc) {
    this.deviceDesc = deviceDesc;
  }

  public DeviceDescriptor getCommunicatingWith() {
    return communicatingWith;
  }

  public void setCommunicatingWith(DeviceDescriptor communicatingWith) {
    this.communicatingWith = communicatingWith;
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

  public DeviceCapabilities getCapabilities() {
    return capabilities;
  }

  public void setCapabilities(DeviceCapabilities capabilities) {
    this.capabilities = capabilities;
  }

  public DeviceDescriptor getMasterDeviceDesc() {
    return masterDeviceDesc;
  }

  public void setMasterDeviceDesc(DeviceDescriptor masterDeviceDesc) {
    this.masterDeviceDesc = masterDeviceDesc;
  }

  public GeoLocation getMasterDeviceLocation() {
    return masterDeviceLocation;
  }

  public void setMasterDeviceLocation(GeoLocation masterDeviceLocation) {
    this.masterDeviceLocation = masterDeviceLocation;
  }

  public SpectrumRequestType getRequestType() {
    return requestType;
  }

  public void setRequestType(SpectrumRequestType requestType) {
    this.requestType = requestType;
  }//</editor-fold>

  /**
   * Validate this instance.
   *
   * @throws Exception describing the invalid configuration
   */
  public void validate() throws Exception {
    if (requestType == null) {
      throw new Exception("AvailableSpectrumRequest::requestType is required");
    }
    /**
     * All require [requestType]
     */
//    boolean valid = requestType != null;

    switch (requestType) {
      /**
       * SLAVE requires [masterDeviceDesc, masterDeviceLocation] + [deviceDesc,
       * antenna] + [location]
       */
      case SLAVE:
        if (masterDeviceDesc == null) {
          throw new Exception("AvailableSpectrumRequest::masterDeviceDesc is required");
        }
        masterDeviceDesc.isValid();
        if (masterDeviceLocation == null) {
          throw new Exception("AvailableSpectrumRequest::masterDeviceLocation is required");
        }
        masterDeviceLocation.isValid();
//        valid && valid && masterDeviceDesc != null && masterDeviceLocation != null;
      /**
       * SLAVE + MASTER requires [deviceDesc, antenna] + [location]
       */
      case MASTER:
        if (antenna == null) {
          throw new Exception("AvailableSpectrumRequest::antenna is required");
        }
        antenna.isValid();
        if (deviceDesc == null) {
          throw new Exception("AvailableSpectrumRequest::deviceDesc is required");
        }
        deviceDesc.isValid();
      //        valid = valid && && antenna != null && deviceDesc != null;

      /**
       * SLAVE + MASTER + LPA requires [location]
       */
      case LPA:
        if (location == null) {
          throw new Exception("AvailableSpectrumRequest::location is required");
        }
        location.isValid();
//        valid = valid && location != null;
        break;

      default:
        throw new AssertionError(requestType.name());

    }
//    return false;
  }

}

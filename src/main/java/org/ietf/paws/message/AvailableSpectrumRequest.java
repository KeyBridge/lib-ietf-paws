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
import org.ietf.paws.*;

/**
 * <img src="doc-files/avail_spectrum_req.png">
 * <p>
 * 4.5.1. AVAIL_SPECTRUM_REQ
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
@XmlRootElement(name = "AVAIL_SPECTRUM_REQ")
@XmlType(name = "AVAIL_SPECTRUM_REQ")
@XmlAccessorType(XmlAccessType.FIELD)
public class AvailableSpectrumRequest extends AbstractRequest {

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
   * The DeviceOwner (Section 5.5) information MAY be included to register the
   * device with the Database. This enables the device to register and get
   * spectrum-availability information in a single request. Some rulesets
   * mandate registration for specific device types.
   */
  private DeviceOwner owner;
  /**
   * The AntennaCharacteristics (Section 5.3) is OPTIONAL.
   */
  private AntennaCharacteristics antenna;
  /**
   * The Master Device MAY include its DeviceCapabilities (Section 5.4) to limit
   * the available-spectrum response to the spectrum that is compatible with its
   * capabilities. The Database SHOULD NOT return spectrum that is not
   * compatible with the specified capabilities.
   */
  private DeviceCapabilities capabilities;
  /**
   * When the request is made by the Master Device on behalf of a Slave Device,
   * the Master Device MAY provide its own descriptor.
   */
  private DeviceDescriptor masterDeviceDesc;
  /**
   * When the request is made by the Master Device on behalf of a Slave Device,
   * the Master Device MUST provide its own GeoLocation (Section 5.1).
   */
  private GeoLocation masterDeviceLocation;
  /**
   * The request type is OPTIONAL; it may be used to modify the request, but its
   * use depends on the applicable ruleset. The request type may be used, for
   * example, to indicate that the response should include generic Slave Device
   * parameters without having to specify the device descriptor for a specific
   * device. When requestType is missing, the request is for a specific device
   * (Master or Slave), so deviceDesc is REQUIRED. The maximum length of the
   * value is 64 octets. See the specifics in the Initial Registry Contents
   * (Section 9.1.2) for the Ruleset ID Registry.
   */
  private String requestType;

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

  public DeviceOwner getOwner() {
    return owner;
  }

  public void setOwner(DeviceOwner owner) {
    this.owner = owner;
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

  public String getRequestType() {
    return requestType;
  }

  public void setRequestType(String requestType) {
    this.requestType = requestType;
  }

}

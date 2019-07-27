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
package org.ietf.lib.paws;

import javax.xml.bind.annotation.*;

/**
 * 5.16. DeviceValidity
 * <p>
 * <img src="doc-files/deviceValidity.png">
 * <p>
 * The DeviceValidity element is used to indicate whether a device is valid. See
 * Section 4.6.2.
 * <pre>
 * +---------------------------------------+
 * |DeviceValidity                         |
 * +----------------------------+----------+
 * |deviceDesc:DeviceDescriptor | REQUIRED |
 * |isValid:boolean             | REQUIRED |
 * |reason:string               | OPTIONAL |
 * +----------------------------+----------+
 * </pre>
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "DeviceValidity")
@XmlType(name = "DeviceValidity")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceValidity {

  /**
   * The DeviceDescriptor (Section 5.2) that was used to check for validity is
   * REQUIRED.
   */
  @XmlElement(required = true)
  private DeviceDescriptor deviceDesc;
  /**
   * This is a REQUIRED boolean value that indicates whether the device is
   * valid.
   */
  @XmlElement(required = true)
  private Boolean isValid;
  /**
   * If the device identifier is not valid, the Database MAY include a reason.
   * The reason MAY be in any language. Its maximum length is 128 octets.
   */
  private String reason;

  public DeviceDescriptor getDeviceDesc() {
    return deviceDesc;
  }

  public void setDeviceDesc(DeviceDescriptor deviceDesc) {
    this.deviceDesc = deviceDesc;
  }

  public Boolean getIsValid() {
    return isValid;
  }

  public void setIsValid(Boolean isValid) {
    this.isValid = isValid;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}

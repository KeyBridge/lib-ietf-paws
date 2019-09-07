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
package org.ietf.lib.paws.message;

import javax.xml.bind.annotation.*;
import org.ietf.lib.paws.DeviceDescriptor;

/**
 * 4.6.1. DEV_VALID_REQ
 * <p>
 * This request is used by a Master Device to determine which Slave Devices are
 * permitted to operate.
 * <pre>
 *   +---------------------------------------------+
 *   |DEV_VALID_REQ                                |
 *   +----------------------------------+----------+
 *   |deviceDescs:list                  | REQUIRED |---+
 *   |masterDeviceDesc:DeviceDescriptor | OPTIONAL |   |
 *   +----------------------------------+----------+   |
 *                                                     V 1..*
 *                                    +----------------------+
 *                                    |DeviceDescriptor      |
 *                                    +----------------------+
 * </pre>
 *
 * @author Key Bridge
 */
@XmlRootElement(name = "DeviceValidationRequest")
@XmlType(name = "DeviceValidationRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceValidationRequest {

  /**
   * A DeviceDescriptor (Section 5.2) is REQUIRED; it describes a Slave Device
   * to be validated.
   * <p>
   * Key Bridge: Changed from a list to single entry. Master devices can request
   * device validation one device at a time.
   */
  @XmlElement(required = true)
  private DeviceDescriptor deviceDesc;
  /**
   * The Master Device MAY provide its own descriptor.
   * <p>
   * Key Bridge: The master device MUST identify itself.
   */
  @XmlElement(required = true)
  private DeviceDescriptor masterDeviceDesc;

  public DeviceDescriptor getDeviceDesc() {
    return deviceDesc;
  }

  public void setDeviceDesc(DeviceDescriptor deviceDesc) {
    this.deviceDesc = deviceDesc;
  }

  public DeviceDescriptor getMasterDeviceDesc() {
    return masterDeviceDesc;
  }

  public void setMasterDeviceDesc(DeviceDescriptor masterDeviceDesc) {
    this.masterDeviceDesc = masterDeviceDesc;
  }

}

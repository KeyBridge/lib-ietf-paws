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

import java.util.List;
import javax.xml.bind.annotation.*;
import org.ietf.lib.paws.DbUpdateSpec;
import org.ietf.lib.paws.DeviceValidity;
import org.ietf.lib.paws.Error;

/**
 * 4.6.2. DEV_VALID_RESP
 * <pre>
 *   +---------------------------------------+
 *   |DEV_VALID_RESP                         |
 *   +----------------------------+----------+
 *   |deviceValidities:list       | REQUIRED |----
 *   |databaseChange:DbUpdateSpec | OPTIONAL |   |
 *   +----------------------------+----------+   |
 *                                               V 1..*
 *                            +---------------------------------------+
 *                            |DeviceValidity                         |
 *                            +----------------------------+----------+
 *                            |deviceDesc:DeviceDescriptor | REQUIRED |
 *                            |isValid:boolean             | REQUIRED |
 *                            |reason:string               | OPTIONAL |
 *                            +----------------------------+----------+
 * </pre>
 *
 * @author Key Bridge
 */
@XmlRootElement(name = "DeviceValidationResponse")
@XmlType(name = "DeviceValidationResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceValidationResponse {

  /**
   * A DeviceValidities (Section 5.16) list is REQUIRED to report the list of
   * Slave Devices and whether each listed device is valid. The number of
   * entries MUST match the number of DeviceDescriptors (Section 5.2) listed in
   * the DEV_VALID_REQ message.
   * <p>
   * Key Bridge: Changed from a list to single entry. Master devices can request
   * device validation one device at a time.
   */
  @XmlElement(required = true)
  private List<DeviceValidity> deviceValidities;
  /**
   * The Database MAY include a DbUpdateSpec (Section 5.7) to notify the device
   * of a change to the database URI, providing one or more alternate database
   * URIs. The device needs to update its preconfigured entry for the responding
   * Database with the alternate Databases listed in the DbUpdateSpec.
   *
   * @deprecated not used by Key Bridge in this context.
   */
  private DbUpdateSpec databaseChange;

  /**
   * Error element describing any error encountered during processing.
   */
  private Error error;

  public List<DeviceValidity> getDeviceValidities() {
    return deviceValidities;
  }

  public void setDeviceValidities(List<DeviceValidity> deviceValidities) {
    this.deviceValidities = deviceValidities;
  }

  public DbUpdateSpec getDatabaseChange() {
    return databaseChange;
  }

  public void setDatabaseChange(DbUpdateSpec databaseChange) {
    this.databaseChange = databaseChange;
  }

  public Error getError() {
    return error;
  }

  public void setError(Error error) {
    this.error = error;
  }

}

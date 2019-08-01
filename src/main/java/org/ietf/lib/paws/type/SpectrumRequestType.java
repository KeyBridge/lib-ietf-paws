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
package org.ietf.lib.paws.type;

import javax.xml.bind.annotation.XmlEnum;

/**
 * 4.5.1. AVAIL_SPECTRUM_REQ
 * <p>
 * Enumerated list of supported `AvailableSpectrumRequest::requestType` values.
 * <p>
 * The request type is OPTIONAL; it may be used to modify the request, but its
 * use depends on the applicable ruleset. The request type may be used, for
 * example, to indicate that the response should include generic Slave Device
 * parameters without having to specify the device descriptor for a specific
 * device. When requestType is missing, the request is for a specific device
 * (Master or Slave), so deviceDesc is REQUIRED. The maximum length of the value
 * is 64 octets. See the specifics in the Initial Registry Contents (Section
 * 9.1.2) for the Ruleset ID Registry.
 * <p>
 * 9.1.2.2. European Telecommunications Standards Institute (ETSI)
 * <p>
 * Modifies the available-spectrum request type. If specified, the only valid
 * value is "Generic Slave" and the Database is required to respond with generic
 * operating parameters for any Slave Device.
 *
 * @author Key Bridge
 * @since v0.12.0 created 08/01/19 to describe supported spectrum request types
 */
@XmlEnum
public enum SpectrumRequestType {

  /**
   * Requesting a spectrum assignment for the inquiring device.
   */
  MASTER,
  /**
   * Requesting a spectrum assignment on behalf of a slaved (i.e. downstream)
   * device.
   */
  SLAVE,
  /**
   * Requesting spectrum availability for LPA type devices (e.g. wireless
   * microphone).
   */
  LPA,
  /**
   * Requesting detailed spectrum availability information for the purposes of
   * spectrum inquiry, planning and evaluation. Results may not be used for
   * service.
   */
  INFORMATION;

}

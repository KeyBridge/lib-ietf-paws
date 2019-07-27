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
 * ETSI EN 301 598 V2.1.1 (2018-01)
 * <p>
 * Table 3: Device Parameters
 * <p>
 * Device category.
 *
 * @author Key Bridge
 * @since v0.6.0 created 07/26/19 to support ETSI operation
 */
@XmlEnum
public enum EtsiDeviceCategory {

  MASTER,
  SLAVE;
}

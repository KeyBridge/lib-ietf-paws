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
 * 4.2.2 Equipment types
 * <p>
 * For the purpose of the present document, two equipment types have been
 * defined with equipment Type A (clause 4.2.2.2) and equipment Type B (clause
 * 4.2.2.3).
 * <p>
 * NOTE: It is envisaged that a TVWSD which is not fixed, will operate with an
 * integral or dedicated antenna.
 *
 * @author Key Bridge
 * @since v0.6.0 created 07/26/19 to support ETSI operation
 */
@XmlEnum
public enum EtsiEquipmentType {

  /**
   * 4.2.2.2 Equipment Type A
   * <p>
   * A Type A TVWSD is a device that is intended for fixed use only. This type
   * of equipment can have integral, dedicated or external antennas
   */
  A,
  /**
   * 4.2.2.3 Equipment Type B
   * <p>
   * A Type B TVWSD is a device that is not intended for fixed use and which has
   * an integral antenna or a dedicated antenna.
   * <p>
   * The equipment and the antenna shall be designed to ensure that no antenna
   * other than that furnished by the responsible party shall be used with the
   * device.
   * <p>
   * In the case of dedicated antennas, the manufacturer has to specify the
   * antennas that have been assessed together with the equipment against the
   * requirements of the present document. That information shall be included in
   * the user documentation. The use of other antennas is prohibited.
   */
  B;
}

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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <img src="doc-files/deviceCapabilities.png">
 * <p>
 * 5.4. DeviceCapabilities
 * <p>
 * Device capabilities provide additional information that may be used by the
 * device to provide additional information to the Database that can help it to
 * determine available spectrum. If the Database does not support device
 * capabilities, it MUST ignore the parameter altogether.
 * <pre>
 *  +-------------------------------+
 *  |DeviceCapabilities             |
 *  +---------------------+---------+
 *  |frequencyRanges:list |OPTIONAL |--+
 *  |.....................|.........|  |
 *  |*other:any           |OPTIONAL |  |
 *  +---------------------+---------+  | 0..*
 *                                     V
 *               +--------------------------------+
 *               |FrequencyRange                  |
 *               +----------------------+---------+
 *               |startHz:float         |REQUIRED |
 *               |stopHz:float          |REQUIRED |
 *               +----------------------+---------+
 * </pre>
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "DeviceCapabilities")
@XmlType(name = "DeviceCapabilities")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceCapabilities {

  private List<FrequencyRange> frequencyRanges;

  public List<FrequencyRange> getFrequencyRanges() {
    if (frequencyRanges == null) {
      frequencyRanges = new ArrayList<>();
    }
    return frequencyRanges;
  }

  public void setFrequencyRanges(List<FrequencyRange> frequencyRanges) {
    this.frequencyRanges = frequencyRanges;
  }

}
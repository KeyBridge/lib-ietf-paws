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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.ietf.lib.paws.adapter.XmlDouble06PrecisionAdapter;

/**
 * 5.13. FrequencyRange
 * <p>
 * <img alt="clazz" src="doc-files/frequencyRange.png">
 * <p>
 * FrequencyRange specifies a frequency range.
 * <pre>
 * +--------------------------------+
 * |FrequencyRange                  |
 * +----------------------+---------+
 * |startHz:float         |REQUIRED |
 * |stopHz:float          |REQUIRED |
 * +----------------------+---------+
 * </pre>
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "FrequencyRange")
@XmlType(name = "FrequencyRange")
@XmlAccessorType(XmlAccessType.FIELD)
public class FrequencyRange {

  /**
   * The inclusive start of the frequency range (in megahertz) is REQUIRED.
   * <p>
   * Key Bridge: change unit of measure from Hertz to MegaHertz.
   */
  @XmlElement(required = true)
  @XmlJavaTypeAdapter(XmlDouble06PrecisionAdapter.class)
  public Double startMHz;
  /**
   * The exclusive end of the frequency range (in megahertz) is REQUIRED.
   * <p>
   * Key Bridge: change unit of measure from Hertz to MegaHertz.
   */
  @XmlElement(required = true)
  @XmlJavaTypeAdapter(XmlDouble06PrecisionAdapter.class)
  public Double stopMHz;

  public Double getStartMHz() {
    return startMHz;
  }

  public void setStartMHz(Double startMHz) {
    this.startMHz = startMHz;
  }

  public Double getStopMHz() {
    return stopMHz;
  }

  public void setStopMHz(Double stopMHz) {
    this.stopMHz = stopMHz;
  }

  @Override
  public String toString() {
    return "FrequencyRange{" + "startMHz=" + startMHz + ", stopMHz=" + stopMHz + '}';
  }

}

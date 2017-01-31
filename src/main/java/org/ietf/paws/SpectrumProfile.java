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
package org.ietf.paws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

/**
 * <img src="doc-files/spectrumProfile.png">
 * <p>
 * 5.12. SpectrumProfile (Point)
 * <p>
 * A spectrum profile is characterized by an ordered list of (frequency, power)
 * points that represents the shape of maximum permissible power levels over a
 * range of frequencies as a piecewise linear curve.
 * <ul>
 * <li> It MUST contain a minimum of two entries.
 * </li>
 * <li> The entries in the list MUST be ordered in non-decreasing frequency
 * values.
 * </li>
 * <li> Two consecutive points MAY have the same frequency value to represent a
 * "step function".
 * </li>
 * <li> Three or more points MUST NOT share the same frequency value.
 * </li>
 * <li> The first frequency is inclusive; the last frequency is exclusive.
 * </ul>
 * NOTE: This encoding allows presentation of "ramps" where the slope of a line
 * segment may be finite and non-zero.
 * <p>
 * The following figure illustrates the SpectrumProfilePoint element.
 * <pre>
 * +-------------------------------+
 * |SpectrumProfilePoint                |
 * +---------------------+---------+
 * |list                 |REQUIRED |---+
 * +---------------------+---------+   |  2..*
 * V
 * +--------------------------+
 * |SpectrumProfilePoint      |
 * +----------------+---------+
 * |hz:float        |REQUIRED |
 * |dbm:float       |REQUIRED |
 * +----------------+---------+
 * </pre>
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "SpectrumProfile")
@XmlType(name = "SpectrumProfile")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpectrumProfile {

  @XmlElement(required = true)
  private List<SpectrumProfilePoint> list;

  public List<SpectrumProfilePoint> getList() {
    if (list == null) {
      list = new ArrayList<>();
    }
    return list;
  }

  public void setList(List<SpectrumProfilePoint> list) {
    this.list = list;
  }

}

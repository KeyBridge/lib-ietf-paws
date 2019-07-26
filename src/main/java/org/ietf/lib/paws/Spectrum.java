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
import javax.xml.bind.annotation.*;

/**
 * <img src="doc-files/spectrum.png">
 * <p>
 * 5.11. Spectrum
 * <p>
 * Available spectrum can be characterized by an ordered list of spectrum
 * profiles that defines permissible power levels over a set of frequency
 * ranges. Each Spectrum element defines permissible power levels as maximum
 * power spectral densities over a specified resolution bandwidth,
 * "resolutionBwHz". Note that the spectrum profiles represent the "availability
 * mask", as defined by the governing ruleset; they are not intended to encode
 * device-level transmission-mask requirements.
 * <p>
 * NOTE: Within the contexts of the AVAIL_SPECTRUM_RESP (Section 4.5.2),
 * AVAIL_SPECTRUM_BATCH_RESP (Section 4.5.4), and SPECTRUM_USE_NOTIFY (Section
 * 4.5.5) messages, the power levels expressed within the Spectrum messages
 * refer to EIRP. Future extensions of PAWS may use Spectrum in other contexts
 * for other definitions of power levels.
 * <ul>
 * <li> To support a ruleset that defines different "wide-band" and
 * "narrow-band" power levels, PAWS allows multiple Spectrum elements to be
 * included in the available-spectrum response, each with a different resolution
 * bandwidth.
 * </li>
 * <li> When multiple Spectrum elements are included in the response, each
 * represents a constraint that the device must satisfy (logical AND).
 * </li>
 * <li> Each Spectrum element covers the range of frequencies governed by a
 * ruleset, rather than splitting the frequencies across multiple Spectrum
 * elements for the same resolution bandwidth.
 * </li>
 * <li> Each spectrum profile represents the maximum permissible power spectral
 * density over a contiguous range of frequencies.
 * </li>
 * <li> When multiple spectrum profiles are included, they MUST be disjoint and
 * MUST be ordered in non-decreasing frequency value.
 * </li>
 * <li> Gaps in frequencies between consecutive spectrum profiles represent
 * unavailability for those frequencies.
 * </ul>
 * <pre>
 * +-------------------------------+
 * |Spectrum                       |
 * +---------------------+---------+
 * |resolutionBwHz:float |REQUIRED |
 * |profiles:list        |REQUIRED |---+
 * +---------------------+---------+   |  0..*
 * V
 * +-----------------------------+
 * |SpectrumProfilePoint              |
 * +-------------------+---------+
 * |list               |REQUIRED |
 * +-------------------+---------+
 * |
 * V 2..*
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
@XmlRootElement(name = "Spectrum")
@XmlType(name = "Spectrum")
@XmlAccessorType(XmlAccessType.FIELD)
public class Spectrum {

  /**
   * This parameter defines the resolution bandwidth (in hertz) over which
   * permissible power spectral density is defined. For example, FCC regulation
   * would require one spectrum specification at a bandwidth of 6 MHz, and ETSI
   * regulation would require two specifications, at 0.1 MHz and 8 MHz.
   */
  @XmlElement(required = true)
  private double resolutionBwHz;
  /**
   * A SpectrumProfilePoint (Section 5.12) list specifies permissible power
   * levels over a set of frequency ranges. The list MAY be empty if there is no
   * available spectrum.
   */
  @XmlElement(required = true)
  private List<SpectrumProfile> profiles;

  public double getResolutionBwHz() {
    return resolutionBwHz;
  }

  public void setResolutionBwHz(double resolutionBwHz) {
    this.resolutionBwHz = resolutionBwHz;
  }

  public List<SpectrumProfile> getProfiles() {
    if (profiles == null) {
      profiles = new ArrayList<>();
    }
    return profiles;
  }

  public void setProfiles(List<SpectrumProfile> profiles) {
    this.profiles = profiles;
  }
}

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
 * 5.15. GeoSpectrumSpec
 * <p>
 * <img src="doc-files/geoSpectrumSpec.png">
 * <p>
 * The GeoSpectrumSpec element encapsulates the available spectrum for a
 * location. It is returned within an AVAIL_SPECTRUM_BATCH_RESP (Section 4.5.4)
 * batch response that contains multiple GeoSpectrumSpec entries, each matching
 * a location provided in the batch request.
 * <pre>
 *   +----------------------------------+
 *   |GeoSpectrumSpec                   |
 *   +-----------------------+----------+
 *   |location:GeoLocation   | REQUIRED |
 *   |spectrumSpecs:list     | REQUIRED |-------+
 *   +-----------------------+----------+       |
 *                                              | 1..*
 *                                              V
 *                                      +--------------+
 *                                      | SpectrumSpec |
 *                                      +--------------+
 * </pre>
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "GeoSpectrumSpec")
@XmlType(name = "GeoSpectrumSpec")
@XmlAccessorType(XmlAccessType.FIELD)
public class GeoSpectrumSpec {

  /**
   * The GeoLocation (Section 5.1) identifies the location at which the spectrum
   * schedule applies.
   */
  @XmlElement(required = true)
  private GeoLocation location;
  /**
   * The SpectrumSpec (Section 5.9) list is REQUIRED. At least one entry MUST be
   * included. Each entry represents schedules of available spectrum for a
   * ruleset. More than one entry MAY be included to support multiple rulesets
   * at a location.
   */
  @XmlElement(required = true)
  private List<SpectrumSpec> spectrumSpecs;

  public GeoLocation getLocation() {
    return location;
  }

  public void setLocation(GeoLocation location) {
    this.location = location;
  }

  public List<SpectrumSpec> getSpectrumSpecs() {
    if (spectrumSpecs == null) {
      spectrumSpecs = new ArrayList<>();
    }
    return spectrumSpecs;
  }

  public void setSpectrumSpecs(List<SpectrumSpec> spectrumSpecs) {
    this.spectrumSpecs = spectrumSpecs;
  }
}

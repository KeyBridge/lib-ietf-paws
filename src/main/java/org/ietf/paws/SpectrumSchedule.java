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
 * <img src="doc-files/spectrumSchedule.png">
 * 5.10. SpectrumSchedule
 * <p>
 * The SpectrumSchedule element combines EventTime (Section 5.14) with Spectrum
 * (Section 5.11) to define a time period in which the spectrum is valid.
 * <pre>
 * +-------------------------------+
 * |SpectrumSchedule               |
 * +--------------------+----------+
 * |eventTime:EventTime | REQUIRED |        +--------------------+
 * |spectra:list        | REQUIRED |------->|Spectrum            |
 * +--------------------+----------+   0..* +--------------------+
 *                                          |resolutionBwHz:float|
 *                                          |profiles:list       |
 *                                          +--------------------+
 * </pre>
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "SpectrumSchedule")
@XmlType(name = "SpectrumSchedule")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpectrumSchedule {

  /**
   * The EventTime (Section 5.14) is REQUIRED to express "when" this
   * specification is valid.
   */
  @XmlElement(required = true)
  private EventTime eventTime;
  /**
   * The Spectrum (Section 5.11) list is REQUIRED to specify the available
   * spectrum and permissible power levels, one per resolutionBwHz. The list MAY
   * be empty when there is no available spectrum.
   */
  @XmlElement(required = true)
  private List<Spectrum> spectra;

  public EventTime getEventTime() {
    return eventTime;
  }

  public void setEventTime(EventTime eventTime) {
    this.eventTime = eventTime;
  }

  public List<Spectrum> getSpectra() {
    if (spectra == null) {
      spectra = new ArrayList<>();
    }
    return spectra;
  }

  public void setSpectra(List<Spectrum> spectra) {
    this.spectra = spectra;
  }

}

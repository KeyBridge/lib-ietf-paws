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
 * 5.9. SpectrumSpec
 * <p>
 * The SpectrumSpec element encapsulates the schedule of available spectrum for
 * a ruleset.
 * <pre>
 *   +---------------------------------------+
 *   |SpectrumSpec                           |
 *   +----------------------------+----------+
 *   |rulesetInfo:RulesetInfo     | REQUIRED |
 *   |spectrumSchedules:list      | REQUIRED |-----+
 *   |timeRange:EventTime         | OPTIONAL |     |
 *   |frequencyRanges:list        | OPTIONAL |     |
 *   |needsSpectrumReport:boolean | OPTIONAL |     |
 *   |maxTotalBwHz:float          | OPTIONAL |     |
 *   |maxContiguousBwHz:float     | OPTIONAL |     |
 *   +----------------------------+----------+     |
 *                                                 | 1..*
 *                                                 V
 *                                      +-------------------------------+
 *                                      |SpectrumSchedule               |
 *                                      +--------------------+----------+
 *                                      |eventTime:EventTime | REQUIRED |
 *                                      |spectra:list        | REQUIRED |
 *                                      +--------------------+----------+
 * </pre>
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "SpectrumSpec")
@XmlType(name = "SpectrumSpec")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpectrumSpec {

  /**
   * RulesetInfo (Section 5.6) is REQUIRED to identify the regulatory domain and
   * ruleset to which the spectrum schedule applies (see Ruleset ID Registry
   * (Section 9.1)). The device needs to use the corresponding ruleset to
   * interpret the response. Values provided within rulesetInfo, such as
   * maxLocationChange, take precedence over the values provided by the
   * Initialization Procedure (Section 4.3).
   */
  @XmlElement(required = true)
  private RulesetInfo rulesetInfo;
  /**
   * The SpectrumSchedule (Section 5.10) list is REQUIRED. At least one schedule
   * MUST be included. More than one schedule MAY be included to represent
   * future changes to the available spectrum. How far in advance a schedule may
   * be provided depends on the ruleset. If more than one schedule is included,
   * the eventTime intervals MUST be disjoint and MUST be sorted in increasing
   * time. A gap in the time schedule indicates no available spectrum during
   * that time-interval gap.
   */
  @XmlElement(required = true)
  private List<SpectrumSchedule> spectrumSchedules;
  /**
   * The time range for which the specification is comprehensive is OPTIONAL.
   * When specified, any gaps in time intervals within the spectrumSchedules
   * element that overlap with the range specified by "timeRange" are
   * interpreted by the device as time intervals in which there is no available
   * spectrum.
   */
  private EventTime timeRange;
  /**
   * Specifying the frequency ranges for which the specification is
   * comprehensive is OPTIONAL. It is a list of disjoint FrequencyRange (Section
   * 5.13) entries. When specified, it typically corresponds to the frequency
   * ranges governed by the ruleset, e.g., for TV white space, the frequency
   * ranges can correspond to the VHF and UHF bands of the associated regulatory
   * domain. A device can combine this information with the available- spectrum
   * specification within the spectrumSchedules element to distinguish between
   * "unavailable spectrum" and "spectrum for which no information has been
   * provided".
   */
  private List<FrequencyRange> frequencyRanges;
  /**
   * The Database MAY return true for this parameter if spectrumSchedules list
   * is non-empty; otherwise, the Database MAY omit this parameter altogether,
   * in which case, the default value is false. If this parameter is present and
   * its value is true, the device sends a SPECTRUM_USE_NOTIFY (Section 4.5.5)
   * message to the Database; otherwise, the device SHOULD NOT send the
   * SPECTRUM_USE_NOTIFY message. Some rulesets mandate this value be set to
   * true.
   */
  private Boolean needsSpectrumReport;
  /**
   * The Database MAY return a constraint on the maximum total bandwidth (in
   * hertz) allowed, which may or may not be contiguous. Some rulesets mandate
   * the Database to return this parameter. When present in the response, the
   * device needs to apply this constraint to its spectrum-selection logic to
   * ensure total bandwidth does not exceed this value.
   */
  private Double maxTotalBwHz;
  /**
   * The Database MAY return a constraint on the maximum contiguous bandwidth
   * (in hertz) allowed. Some rulesets mandate the Database to return this
   * parameter. When present in the response, the device needs to apply this
   * constraint to its spectrum-selection logic to ensure no single block of
   * spectrum has bandwidth that exceeds this value.
   */
  private Double maxContiguousBwHz;
  /**
   * 9.2.2.7. ETSI Simultaneous Channel Operation Restriction
   * <p>
   * Specification document(s): Specifies the constraint on the device maximum
   * total EIRP, as defined by the ETSI Harmonised Standard [ETSI-EN-301-598].
   * The values are represented by numeric strings, such as "0", "1", etc.
   * Consult the documentation for the specification of the power constraint
   * corresponding to each parameter value.
   * <p>
   */
  private String etsiEnSimultaneousChannelOperationRestriction;

  public RulesetInfo getRulesetInfo() {
    return rulesetInfo;
  }

  public void setRulesetInfo(RulesetInfo rulesetInfo) {
    this.rulesetInfo = rulesetInfo;
  }

  public List<SpectrumSchedule> getSpectrumSchedules() {
    if (spectrumSchedules == null) {
      spectrumSchedules = new ArrayList<>();
    }
    return spectrumSchedules;
  }

  public void setSpectrumSchedules(List<SpectrumSchedule> spectrumSchedules) {
    this.spectrumSchedules = spectrumSchedules;
  }

  public EventTime getTimeRange() {
    return timeRange;
  }

  public void setTimeRange(EventTime timeRange) {
    this.timeRange = timeRange;
  }

  public List<FrequencyRange> getFrequencyRanges() {
    if (frequencyRanges == null) {
      frequencyRanges = new ArrayList<>();
    }
    return frequencyRanges;
  }

  public void setFrequencyRanges(List<FrequencyRange> frequencyRanges) {
    this.frequencyRanges = frequencyRanges;
  }

  public Boolean getNeedsSpectrumReport() {
    return needsSpectrumReport;
  }

  public void setNeedsSpectrumReport(Boolean needsSpectrumReport) {
    this.needsSpectrumReport = needsSpectrumReport;
  }

  public Double getMaxTotalBwHz() {
    return maxTotalBwHz;
  }

  public void setMaxTotalBwHz(Double maxTotalBwHz) {
    this.maxTotalBwHz = maxTotalBwHz;
  }

  public Double getMaxContiguousBwHz() {
    return maxContiguousBwHz;
  }

  public void setMaxContiguousBwHz(Double maxContiguousBwHz) {
    this.maxContiguousBwHz = maxContiguousBwHz;
  }

  public String getEtsiEnSimultaneousChannelOperationRestriction() {
    return etsiEnSimultaneousChannelOperationRestriction;
  }

  public void setEtsiEnSimultaneousChannelOperationRestriction(String etsiEnSimultaneousChannelOperationRestriction) {
    this.etsiEnSimultaneousChannelOperationRestriction = etsiEnSimultaneousChannelOperationRestriction;
  }

}

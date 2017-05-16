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
package org.ietf.paws.message;

import ch.keybridge.lib.paws.PawsChannel;
import ch.keybridge.lib.xml.adapter.XmlDateTimeAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.ietf.paws.*;

/**
 * Current: {@code spectrumSpec} is deprecated and replaced by {@code channels}.
 * Other objects moved here. Key Bridge protocol {@code version} and
 * {@code message} UUID fields attributes added.
 * <p>
 * <img src="doc-files/avail_spectrum_resp_1.png">
 * <p>
 * Original:
 * <p>
 * <img src="doc-files/avail_spectrum_resp.png">
 * <p>
 * 4.5.2. AVAIL_SPECTRUM_RESP
 * <p>
 * The response message for the Available Spectrum Query contains one or more
 * SpectrumSpec (Section 5.9) elements, one for each ruleset supported at the
 * location specified in the corresponding AVAIL_SPECTRUM_REQ (Section 4.5.1)
 * message. Each SpectrumSpec element contains a list of one or more spectrum
 * schedules, representing permissible power levels over time:
 * <ul>
 * <li> Each spectrum schedule specifies the permissible power level for a
 * duration defined by a pair of start and stop times. The power levels refer to
 * permissible EIRP over a resolution bandwidth.
 * </li><li> Within each list of schedules, event-time intervals MUST be
 * disjoint and MUST be sorted in increasing time.
 * </li><li> A gap in the time schedule means no spectrum is available for that
 * time interval.</li></ul>
 * <p>
 * Consider a Database that provides a schedule of available spectrum for the
 * next 24 hours. If spectrum availability were to be different at different
 * times of day, the response would contain a list of schedules, each transition
 * representing some change to the spectrum availability. A device might use
 * different strategies to select which spectrum to use, e.g.:
 * <ul>
 * <li> Always use the frequencies that permit the highest power
 * </li><li> Use the frequencies that are available for the longest period of
 * time.
 * </li><li> Just use the first set of frequencies that matches its
 * needs.</li></ul>
 * <pre>
 *  +---------------------------------------+
 *  |AVAIL_SPECTRUM_RESP                    |
 *  +----------------------------+----------+
 *  |timestamp:string            | REQUIRED |
 *  |deviceDesc:DeviceDescriptor | REQUIRED |
 *  |spectrumSpecs:list          | REQUIRED |-------+
 *  |............................|..........|       |
 *  |databaseChange:DbUpdateSpec | OPTIONAL |       |
 *  |*other:any                  | OPTIONAL |       |
 *  +----------------------------+----------+       | 1..*
 *                                                  V
 *                             +-----------------------------------+
 *                             |SpectrumSpec                       |
 *                             +------------------------+----------+
 *                             |rulesetInfo:RulesetInfo | REQUIRED |
 *                             |spectrumSchedules:list  | REQUIRED |-+
 *                             |timeRange:EventTime     | OPTIONAL | |
 *                             |frequencyRanges:list    | OPTIONAL | |
 *                             |needsSpectrumReport:bool| OPTIONAL | |
 *                             |maxTotalBwHz:float      | OPTIONAL | |
 *                             |maxContiguousBwHz:float | OPTIONAL | |
 *                             +------------------------+----------+ |
 *                                              +--------------------+
 *                                              | 1..*
 *                                              V
 *                                 +-------------------------------+
 *                                 |SpectrumSchedule               |
 *                                 +--------------------+----------+
 *                                 |eventTime:EventTime | REQUIRED |
 *                                 |spectra:list        | REQUIRED |
 *                                 +--------------------+----------+
 * </pre> other: Database implementations MAY return additional parameters in
 * the response. The device MUST ignore any parameters that it does not
 * understand. Consult the PAWS Parameters Registry (Section 9.2) for possible
 * additional parameters and requirements they place on the device.
 *
 * @author Key Bridge LLC
 * @since v0.2.0 rewritten 01/31/17 to use a simple list of PawsChannel instead
 * of the SpectrumSpec list of lists of lists of lists of lists.
 */
@XmlRootElement(name = "AVAIL_SPECTRUM_RESP")
@XmlType(name = "AVAIL_SPECTRUM_RESP")
@XmlAccessorType(XmlAccessType.FIELD)
public class AvailableSpectrumResponse extends AbstractResponse {

  /**
   * Timestamp of the response is expressed in UTC using the form,
   * YYYY-MM-DDThh:mm:ssZ, as defined by "Date and Time on the Internet:
   * Timestamps" [RFC3339]. This can be used by the device as a reference for
   * the start and stop times in the spectrum schedules.
   */
  @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
  @XmlElement(required = true)
  private Date timestamp;
  /**
   * The Database MUST include the DeviceDescriptor (Section 5.2) specified in
   * the AVAIL_SPECTRUM_REQ message.
   */
  @XmlElement(required = true)
  private DeviceDescriptor deviceDesc;
  /**
   * @deprecated This silly list of lists is deprecated. The Response is
   * flattened.
   *
   * Deprecated: <em> The SpectrumSpec (Section 5.9) list MUST include at least
   * one entry. Each entry contains the schedules of available spectrum for a
   * ruleset. The Database MAY return more than one SpectrumSpec to represent
   * available spectrum for multiple rulesets at the specified location.</em>
   * <p>
   * Key Bridge: This is not used. Instead the components are FLATTENED into
   * this message.
   */
//  @XmlElement(required = true)
  private List<SpectrumSpec> spectrumSpecs;
  /**
   * The Database MAY include a DbUpdateSpec (Section 5.7) to notify the device
   * of a change to the database URI, providing one or more alternate database
   * URIs. The device needs to update its preconfigured entry for the responding
   * Database with the alternate Databases listed in the DbUpdateSpec.
   */
  private DbUpdateSpec databaseChange;

  // Key Bridge Modifications.
  /**
   * Key Bridge Modification. This is moved from {@code SpectrumSpec}.
   * <p>
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
   * Key Bridge Modification. This is moved from {@code SpectrumSpec}.
   * <p>
   * The time range for which the specification is comprehensive is OPTIONAL.
   * When specified, any gaps in time intervals within the spectrumSchedules
   * element that overlap with the range specified by "timeRange" are
   * interpreted by the device as time intervals in which there is no available
   * spectrum.
   */
  @XmlElement(required = true)
  private EventTime timeRange;
  /**
   * Key Bridge Modification. This is moved from {@code SpectrumSpec}.
   * <p>
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
   * Key Bridge Modification. This is a custom component replacing the
   * thrice-buried list of {@code SpectrumSpec / SpectrumSchedule / Spectrum},
   * and then on to another set of buried lists under
   * {@code SpectrumProfile / SpectrumProfilePoint}. Seriously, who designed
   * that garbage? Here we use a simple list to provide all the frequency
   * information. Neat and easy.
   * <p>
   * This replaces the Spectrum list silliness (Section 5.11).
   */
  @XmlElementWrapper(name = "channels", required = true)
  @XmlElement(name = "channel", required = true)
  private List<PawsChannel> channels;

  public AvailableSpectrumResponse() {
    super();
    this.timestamp = new Date();
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public DeviceDescriptor getDeviceDesc() {
    return deviceDesc;
  }

  public void setDeviceDesc(DeviceDescriptor deviceDesc) {
    this.deviceDesc = deviceDesc;
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

  public DbUpdateSpec getDatabaseChange() {
    return databaseChange;
  }

  public void setDatabaseChange(DbUpdateSpec databaseChange) {
    this.databaseChange = databaseChange;
  }

  public RulesetInfo getRulesetInfo() {
    return rulesetInfo;
  }

  public void setRulesetInfo(RulesetInfo rulesetInfo) {
    this.rulesetInfo = rulesetInfo;
  }

  public EventTime getTimeRange() {
    return timeRange;
  }

  public void setTimeRange(EventTime timeRange) {
    this.timeRange = timeRange;
  }

  public Boolean getNeedsSpectrumReport() {
    return needsSpectrumReport;
  }

  public void setNeedsSpectrumReport(Boolean needsSpectrumReport) {
    this.needsSpectrumReport = needsSpectrumReport;
  }

  public List<PawsChannel> getChannels() {
    if (channels == null) {
      channels = new ArrayList<>();
    }
    return channels;
  }

  public void setChannels(List<PawsChannel> channels) {
    this.channels = channels;
  }

  @Override
  public String toString() {
    List<PawsChannel> allowedChannels = new ArrayList<>();
    for (PawsChannel channel : getChannels()) {
      if (channel.isAllowed()) {
        allowedChannels.add(channel);
      }
    }
    return allowedChannels.toString();
  }

}

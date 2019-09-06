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
package org.ietf.lib.paws.message;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.ietf.lib.paws.*;
import org.ietf.lib.paws.adapter.XmlZonedDateTimeAdapter;
import org.ietf.lib.paws.type.SpectrumRequestType;

/**
 * 4.5.2. AVAIL_SPECTRUM_RESP
 * <p>
 * <img alt="clazz" src="doc-files/avail_spectrum_resp.png">
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
 * <p>
 * Note: {@code spectrumSpec} is deprecated and replaced by {@code channels}.
 *
 * @author Key Bridge LLC
 * @since v0.2.0 rewritten 01/31/17 to use a simple list of PawsChannel instead
 * of the SpectrumSpec list of lists of lists of lists of lists.
 */
@XmlRootElement(name = "AvailableSpectrumResponse")
@XmlType(name = "AvailableSpectrumResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class AvailableSpectrumResponse {

  /**
   * "UTC". The default time zone.
   */
  private static final ZoneId ZONE_ID = ZoneId.of("UTC");

  /**
   * Key Bridge. The response type is REQUIRED; it is be used to specify how the
   * provided spectrum information may be used.
   *
   * @since v0.16.0 - to specify how the spectrum information may be used
   */
  @XmlElement(required = true)
  private SpectrumRequestType responseType;

  /**
   * Timestamp of the response is expressed in UTC using the form,
   * YYYY-MM-DDThh:mm:ssZ, as defined by "Date and Time on the Internet:
   * Timestamps" [RFC3339]. This can be used by the device as a reference for
   * the start and stop times in the spectrum schedules.
   */
  @XmlJavaTypeAdapter(XmlZonedDateTimeAdapter.class)
  @XmlElement(required = true)
  private ZonedDateTime timestamp;
  /**
   * The Database MUST include the DeviceDescriptor (Section 5.2) specified in
   * the AVAIL_SPECTRUM_REQ message.
   */
  @XmlElement(required = true)
  private DeviceDescriptor deviceDesc;
  /**
   * Key Bridge Modification: Added to provide clarity in the response.
   * <p>
   * The response `location` field specifically describes the geographic
   * validity of the spectrum availability information.
   */
  @XmlElement(required = true)
  private GeoLocation location;

  /**
   * Deprecated: <em> The SpectrumSpec (Section 5.9) list MUST include at least
   * one entry. Each entry contains the schedules of available spectrum for a
   * ruleset. The Database MAY return more than one SpectrumSpec to represent
   * available spectrum for multiple rulesets at the specified location.</em>
   * <p>
   * Key Bridge: This is not used. Instead the various SpectrumSpec components
   * are FLATTENED into this message and frequency information is provided in
   * the {@code channels} collection.
   *
   * @deprecated This silly list of lists is deprecated. Reference instead the
   * {@code spectrum} collection of sorted {@code SpectrumChannel} or
   * {@code SpectrumInfo} instances.
   */
  private List<SpectrumSpec> spectrumSpecs;
  /**
   * The Database MAY include a DbUpdateSpec (Section 5.7) to notify the device
   * of a change to the database URI, providing one or more alternate database
   * URIs. The device needs to update its preconfigured entry for the responding
   * Database with the alternate Databases listed in the DbUpdateSpec.
   */
  private DbUpdateSpec databaseChange;

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
   * Describes the maximum time range for which the spectrum information in this
   * response is valid. Note that individual PawsChannel entries in the
   * `channels` list may indicate a _shorter_ but never a longer time range.
   * PawsChannel entries with no time range configuration must be interpreted as
   * having this (parent) time range.
   */
  @XmlElement(required = true)
  private EventTime timeRange;
  /**
   * Key Bridge Modification. This is moved from {@code SpectrumSpec} and is
   * ALWAYS TRUE.
   * <p>
   * The Database MAY return true for this parameter if spectrumSchedules list
   * is non-empty; otherwise, the Database MAY omit this parameter altogether,
   * in which case, the default value is false. If this parameter is present and
   * its value is true, the device sends a SPECTRUM_USE_NOTIFY (Section 4.5.5)
   * message to the Database; otherwise, the device SHOULD NOT send the
   * SPECTRUM_USE_NOTIFY message. Some rulesets mandate this value be set to
   * true.
   */
  @XmlElement(required = true)
  private boolean needsSpectrumReport;

  /**
   * Key Bridge Modification. Indicator that an exception occurred when
   * calculating the availability of the spectrum blocks and all channels are
   * therefore disabled. Inspect the messages for more information. Default is
   * FALSE.
   */
  private boolean exception = false;
  /**
   * Key Bridge Modification. A list of simple String messages to provide
   * information about the white space channel build process. This is typically
   * used only to explain exceptions encountered during processing and is
   * intended to help the client quickly identify and fix issues.
   */
  private Collection<String> messages;

  /**
   * Key Bridge Modification.
   * <p>
   * This is a custom component replacing the thrice-buried list of
   * {@code SpectrumSpec / SpectrumSchedule / Spectrum} (Section 5.11), and then
   * on to another set of buried lists under
   * {@code SpectrumProfile / SpectrumProfilePoint}. Seriously, who designed
   * that garbage? Here we use a simple list to provide all the frequency
   * information. Neat and easy.
   * <p>
   * This is a collection of {@code SpectrumChannel} or {@code SpectrumInfo}
   * configurations.
   */
  @XmlElement(required = true)
  private Collection<AbstractSpectrum> spectrum;

  public AvailableSpectrumResponse() {
    this.timestamp = ZonedDateTime.now(ZONE_ID);
    this.needsSpectrumReport = true;
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public SpectrumRequestType getResponseType() {
    return responseType;
  }

  public void setResponseType(SpectrumRequestType responseType) {
    this.responseType = responseType;
  }

  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(ZonedDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public DeviceDescriptor getDeviceDesc() {
    return deviceDesc;
  }

  public void setDeviceDesc(DeviceDescriptor deviceDesc) {
    this.deviceDesc = deviceDesc;
  }

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

  public boolean getNeedsSpectrumReport() {
    return needsSpectrumReport;
  }

  public void setNeedsSpectrumReport(boolean needsSpectrumReport) {
    this.needsSpectrumReport = needsSpectrumReport;
  }

  public boolean isException() {
    return exception;
  }

  public void setException(boolean exception) {
    this.exception = exception;
  }

  public Collection<String> getMessages() {
    return messages;
  }

  public void setMessages(Collection<String> messages) {
    this.messages = messages;
  }

  /**
   * Get the spectrum collection. Note that each entry must be inspected to
   * determine its implementation type.
   *
   * @return a non-null but possibly empty TreeSet
   */
  public Collection<AbstractSpectrum> getSpectrum() {
    if (spectrum == null) {
      spectrum = new TreeSet<>();
    }
    return spectrum;
  }

  /**
   * Helper method to clear the spectrum collection.
   */
  public void clearSpectrum() {
    this.spectrum = null;
  }

  /**
   * Set the spectrum as a collection of SpectrumChannel configurations.
   *
   * @param spectrum a collection of SpectrumChannel configurations.
   */
  public void setSpectrumChannels(Collection<SpectrumChannel> spectrum) {
    if (spectrum == null) {
      this.spectrum = null;
    } else {
      this.spectrum = null; // first clear the list
      spectrum.forEach(s -> addSpectrumEntry(s));
    }
  }

  /**
   * Set the spectrum as a collection of SpectrumInfo configurations.
   *
   * @param spectrum a collection of SpectrumInfo configurations.
   */
  public void setSpectrumInfos(Collection<SpectrumInfo> spectrum) {
    if (spectrum == null) {
      this.spectrum = null;
    } else {
      this.spectrum = null; // first clear the list
      spectrum.forEach(s -> addSpectrumEntry(s));
    }
  }

  /**
   * Add a single PawsChannel instance to the internal sorted set.
   *
   * @param channel the PawsChannel instance to add
   */
  public void addSpectrumEntry(AbstractSpectrum channel) {
    getSpectrum().add(channel);
  }//</editor-fold>

  /**
   * Return a list of channels.
   *
   * @return a list of channels.
   */
  @Override
  public String toString() {
    return getSpectrum().toString();
  }

}

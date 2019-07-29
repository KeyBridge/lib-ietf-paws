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
package ch.keybridge.lib.paws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Key Bridge White Space information data transfer object.
 * <p>
 * <img alt="clazz" src="doc-files/pawsInfo.png">
 * <p>
 * A {@code PawInfo} channel information message providing a complete
 * description of spectrum availability and incumbent occupancy.
 * <p>
 * The {@code PawsInfo} is provided in the {@code AvailableSpectrumResponse}
 * object to convey channel availability information for non-WSD consumers such
 * as spectrum planning applications.
 * <p>
 * Notes: This is a Key Bridge custom component.
 *
 * @author Key Bridge LLC
 * @since v0.7.0 created 07/27/19 to support LPA channel detail inquiries
 */
@XmlType(name = "PawsInfo")
@XmlRootElement(name = "PawsInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class PawsInfo extends PawsChannel {

  /**
   * Indicator that this channel is subject to a BLOCKING Enforcement record and
   * should NOT be included in a channel availability list. When not configured
   * this may be assumed to be FALSE.
   */
  private Boolean enforcementBlocking;
  /**
   * Indicator that this channel is subject to a FAST POLLING Enforcement record
   * and the default (48 hour maximum) schedule should be shortened. When not
   * configured this may be assumed to be FALSE.
   */
  private Boolean enforcementFastPoll;

  /**
   * Indicator that an exception occurred when calculating the availability of
   * this channel and it is therefore disabled. Inspect the messages for more
   * information. Default is FALSE.
   */
  private Boolean exception;

  /**
   * A list of co-channel services.
   */
  private List<String> servicesCo;
  /**
   * A list of blocking adjacent channel services.
   */
  private List<String> servicesAdj;
  /**
   * A list of blocking taboo-channel services.
   */
  private List<String> servicesTaboo;
  /**
   * A list of blocking second adjacent channel services. (RRBS only)
   */
  private List<String> services2ndAdj;

  /**
   * Messages provides information about the white space channel build process.
   */
  private List<String> messages;

  /**
   * Make the empty constructor protected directing users to the
   * {@code getInstance} constructor.
   */
  protected PawsInfo() {
  }

  /**
   * PAWS Channel constructor with minimum required configuration.
   *
   * @param name         The colloquial channel name. This corresponds to the
   *                     channel number identified in rule or regulation. e.g.
   *                     "VHF4"
   * @param frequencyMin The minimum (or start) frequency of the indicated name
   *                     in MHz.
   * @param frequencyMax The maximum (or end) frequency of the indicated name in
   *                     MHz.
   * @return a new PawsChannel instance
   */
  public static PawsInfo getInstance(String name, double frequencyMin, double frequencyMax) {
    PawsInfo pc = new PawsInfo();
    pc.setName(name);
    pc.setFrequencyMax(frequencyMax);
    pc.setFrequencyMin(frequencyMin);
    return pc;
  }

  public Boolean getEnforcementBlocking() {
    return enforcementBlocking;
  }

  /**
   * Remark if there is a BLOCKING enforcement action on this channel. If FALSE
   * set then leave empty.
   *
   * @param enforcementBlocking blocking enforcement flag
   */
  public void setEnforcementBlocking(boolean enforcementBlocking) {
    this.enforcementBlocking = enforcementBlocking ? enforcementBlocking : null;
  }

  public Boolean getEnforcementFastPoll() {
    return enforcementFastPoll;
  }

  /**
   * Remark if there is a FAST POLL enforcement action on this channel. If FALSE
   * set then leave empty.
   *
   * @param enforcementFastPoll blocking enforcement flag
   */
  public void setEnforcementFastPoll(boolean enforcementFastPoll) {
    this.enforcementFastPoll = enforcementFastPoll ? enforcementFastPoll : null;
  }

  public Boolean getException() {
    return exception;
  }

  /**
   * Remark if there is an exception error on this channel. If FALSE set then
   * leave empty.
   *
   * @param exception blocking enforcement flag
   */
  public void setException(boolean exception) {
    this.exception = exception ? exception : null;
  }

  public List<String> getServicesCo() {
    if (servicesCo == null) {
      servicesCo = new ArrayList<>();
    }
    return servicesCo;
  }

  public void setServicesCo(List<String> servicesCo) {
    /**
     * Only set the collection if the source is not empty. This produces a
     * cleaner XML output.
     */
    this.servicesCo = (servicesCo == null || servicesCo.isEmpty()) ? null : servicesCo;
  }

  public List<String> getServicesAdj() {
    if (servicesAdj == null) {
      servicesAdj = new ArrayList<>();
    }
    return servicesAdj;
  }

  public void setServicesAdj(List<String> servicesAdj) {
    /**
     * Only set the collection if the source is not empty. This produces a
     * cleaner XML output.
     */
    this.servicesAdj = (servicesAdj == null || servicesAdj.isEmpty()) ? null : servicesAdj;
  }

  public List<String> getServicesTaboo() {
    if (servicesTaboo == null) {
      servicesTaboo = new ArrayList<>();
    }
    return servicesTaboo;
  }

  public void setServicesTaboo(List<String> servicesTaboo) {
    /**
     * Only set the collection if the source is not empty. This produces a
     * cleaner XML output.
     */
    this.servicesTaboo = (servicesTaboo == null || servicesTaboo.isEmpty()) ? null : servicesTaboo;
  }

  public List<String> getServices2ndAdj() {
    if (services2ndAdj == null) {
      services2ndAdj = new ArrayList<>();
    }
    return services2ndAdj;
  }

  public void setServices2ndAdj(List<String> services2ndAdj) {
    /**
     * Only set the collection if the source is not empty. This produces a
     * cleaner XML output.
     */
    this.services2ndAdj = (services2ndAdj == null || services2ndAdj.isEmpty()) ? null : services2ndAdj;
  }

  public List<String> getMessages() {
    if (messages == null) {
      messages = new ArrayList<>();
    }
    return messages;
  }

  public void setMessages(List<String> messages) {
    /**
     * Only set the collection if the source is not empty. This produces a
     * cleaner XML output.
     */
    this.messages = (messages == null || messages.isEmpty()) ? null : messages;
  }

  public void addMessage(String message) {
    getMessages().add(message);
  }

}

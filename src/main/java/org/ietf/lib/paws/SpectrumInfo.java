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
import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Key Bridge proprietary white space spectrum information data transfer object.
 * <p>
 * <img alt="clazz" src="doc-files/spectrumInfo.png">
 * <p>
 * A {@code SpectrumInfo} channel information message providing a complete
 * description of spectrum availability and incumbent occupancy.
 * <p>
 * The {@code SpectrumInfo} is provided in the {@code AvailableSpectrumResponse}
 * object to convey channel availability information for non-WSD consumers such
 * as spectrum planning applications.
 *
 * @author Key Bridge LLC
 * @since v0.7.0 created 07/27/19 to support LPA channel detail inquiries
 * @since v0.13.0 rename from PawsInfo to SpectrumInfo
 */
@XmlType(name = "SpectrumInfo")
@XmlRootElement(name = "SpectrumInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpectrumInfo extends AbstractChannel {

  /**
   * Indicator that this channel is subject to a BLOCKING Enforcement record and
   * should NOT be included in a channel availability list. When not configured
   * this may be assumed to be FALSE.
   */
  private boolean enforcementBlocking;
  /**
   * Indicator that this channel is subject to a FAST POLLING Enforcement record
   * and the default (48 hour maximum) schedule should be shortened. When not
   * configured this may be assumed to be FALSE.
   */
  private boolean enforcementFastPoll;

  /**
   * Indicator that an exception occurred when calculating the availability of
   * this channel and it is therefore disabled. Inspect the messages for more
   * information. Default is FALSE.
   */
  private boolean exception;

  /**
   * A list of co-channel services.
   */
  private Collection<String> servicesCo;
  /**
   * A list of blocking adjacent channel services.
   */
  private Collection<String> servicesAdj;
  /**
   * A list of blocking second adjacent channel services. (RRBS only)
   */
  private Collection<String> servicesSecondAdjacent;
  /**
   * A list of blocking taboo-channel services.
   */
  private Collection<String> servicesTaboo;

  /**
   * Messages provides information about the white space channel build process.
   */
  private Collection<String> messages;

  /**
   * Make the empty constructor protected directing users to the
   * {@code getInstance} constructor.
   */
  protected SpectrumInfo() {
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
   */
  public SpectrumInfo(String name, double frequencyMin, double frequencyMax) {
    super(name, frequencyMin, frequencyMax);
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  /**
   * Get the indicator that this channel is subject to a BLOCKING Enforcement
   * record and should NOT be included in a channel availability list. When not
   * configured this may be assumed to be FALSE
   *
   * @return blocking enforcement flag
   */
  public boolean getEnforcementBlocking() {
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

  public boolean getEnforcementFastPoll() {
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

  public boolean getException() {
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

  public Collection<String> getServicesCo() {
    if (servicesCo == null) {
      servicesCo = new ArrayList<>();
    }
    return servicesCo;
  }

  public void setServicesCo(Collection<String> servicesCo) {
    /**
     * Only set the collection if the source is not empty. This produces a
     * cleaner XML output.
     */
    this.servicesCo = (servicesCo == null || servicesCo.isEmpty()) ? null : servicesCo;
  }

  public Collection<String> getServicesAdj() {
    if (servicesAdj == null) {
      servicesAdj = new ArrayList<>();
    }
    return servicesAdj;
  }

  public void setServicesAdj(Collection<String> servicesAdj) {
    /**
     * Only set the collection if the source is not empty. This produces a
     * cleaner XML output.
     */
    this.servicesAdj = (servicesAdj == null || servicesAdj.isEmpty()) ? null : servicesAdj;
  }

  public Collection<String> getServicesTaboo() {
    if (servicesTaboo == null) {
      servicesTaboo = new ArrayList<>();
    }
    return servicesTaboo;
  }

  public void setServicesTaboo(Collection<String> servicesTaboo) {
    /**
     * Only set the collection if the source is not empty. This produces a
     * cleaner XML output.
     */
    this.servicesTaboo = (servicesTaboo == null || servicesTaboo.isEmpty()) ? null : servicesTaboo;
  }

  public Collection<String> getServicesSecondAdjacent() {
    if (servicesSecondAdjacent == null) {
      servicesSecondAdjacent = new ArrayList<>();
    }
    return servicesSecondAdjacent;
  }

  public void setServicesSecondAdjacent(Collection<String> servicesSecondAdjacent) {
    /**
     * Only set the collection if the source is not empty. This produces a
     * cleaner XML output.
     */
    this.servicesSecondAdjacent = (servicesSecondAdjacent == null || servicesSecondAdjacent.isEmpty()) ? null : servicesSecondAdjacent;
  }

  /**
   * Get Messages to provide information about the white space channel build
   * process
   *
   * @return a collection of build messages
   */
  public Collection<String> getMessages() {
    if (messages == null) {
      messages = new ArrayList<>();
    }
    return messages;
  }

  /**
   * Set Messages to provide information about the white space channel build
   * process.
   *
   * @param messages a collection of build messages
   */
  public void setMessages(Collection<String> messages) {
    /**
     * Only set the collection if the source is not empty. This produces a
     * cleaner XML output.
     */
    this.messages = (messages == null || messages.isEmpty()) ? null : messages;
  }

  /**
   * Add a Messages to provide information about the white space channel build
   * process
   *
   * @param message a build message
   */
  public void addMessage(String message) {
    getMessages().add(message);
  }//</editor-fold>

}

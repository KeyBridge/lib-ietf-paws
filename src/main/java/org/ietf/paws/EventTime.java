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

import ch.keybridge.lib.xml.adapter.XmlDateTimeAdapter;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <img src="doc-files/eventTime.png">
 * <p>
 * 5.14. EventTime
 * <p>
 * The EventTime element specifies the start and stop times of an "event". This
 * is used to indicate the time period for which a Spectrum (Section 5.11) is
 * valid.
 * <pre>
 * +---------------------------+
 * |EventTime                  |
 * +-----------------+---------+
 * |startTime:string |REQUIRED |
 * |stopTime:string  |REQUIRED |
 * +-----------------+---------+
 * </pre> Both times are expressed using the format, YYYY-MM-DDThh:mm:ssZ, as
 * defined by "Date and Time on the Internet: Timestamps" [RFC3339]. The times
 * MUST be expressed using UTC.
 * <p>
 * A device that does not have access to the current date and time MUST use the
 * timestamp at the top level of the response message as a substitute for the
 * current time (see "Available Spectrum Response" (Section 4.5.2) and
 * "Available Spectrum Batch Response" (Section 4.5.4)). For example,
 * <ul>
 * <li>(startTime - timestamp) gives the duration that a device must wait before
 * the event becomes "active". If the value is zero or negative, the event is
 * already active.</li>
 * <li> If the event is already active, (stopTime - timestamp) is the duration
 * that the event remains active. If the value is zero or negative, the event is
 * no longer active and MUST be ignored.</li></ul>
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "EventTime")
@XmlType(name = "EventTime")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventTime {

  /**
   * The inclusive start of the event is REQUIRED.
   */
  @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
  @XmlElement(required = true)
  public Date startTime;
  /**
   * The exclusive end of the event is REQUIRED.
   */
  @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
  @XmlElement(required = true)
  public Date stopTime;

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getStopTime() {
    return stopTime;
  }

  public void setStopTime(Date stopTime) {
    this.stopTime = stopTime;
  }
}

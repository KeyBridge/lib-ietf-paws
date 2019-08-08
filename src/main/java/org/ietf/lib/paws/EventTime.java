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

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.ietf.lib.paws.adapter.XmlDurationAdapter;
import org.ietf.lib.paws.adapter.XmlZonedDateTimeAdapter;

/**
 * 5.14. EventTime
 * <p>
 * <img alt="clazz" src="doc-files/eventTime.png">
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
 * @see <a href="https://tools.ietf.org/html/rfc3339">RFC3339 Timestamps</a>
 */
@XmlRootElement(name = "EventTime")
@XmlType(name = "EventTime")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventTime {

  /**
   * "UTC". The default time zone.
   */
  private static final ZoneId ZONE_ID = ZoneId.of("UTC");

  /**
   * 10800 seconds == 3 hours. The default time period for which a Spectrum is
   * valid
   */
  private static final Duration DURATION_DEFAULT = Duration.ofHours(3);

  /**
   * The inclusive start of the event expressed using the format
   * "YYYY-MM-DDThh:mm:ssZ" in UTC.
   */
  @XmlJavaTypeAdapter(XmlZonedDateTimeAdapter.class)
  @XmlElement(required = true)
  private ZonedDateTime startTime;
  /**
   * The exclusive end of the event expressed using the format
   * "YYYY-MM-DDThh:mm:ssZ" in UTC.
   */
  @XmlJavaTypeAdapter(XmlZonedDateTimeAdapter.class)
  @XmlElement(required = true)
  private ZonedDateTime stopTime;

  /**
   * Key Bridge: The calculated event duration.
   */
  @XmlJavaTypeAdapter(XmlDurationAdapter.class)
  private Duration duration;

  /**
   * Default no-arg constructor. Sets the `startTime`.
   */
  public EventTime() {
    this.startTime = ZonedDateTime.now(ZONE_ID);

  }

  /**
   * Build a fully qualified EventTime instance having the default duration of 3
   * hours.
   *
   * @return a fully qualified EventTime instance
   */
  public static EventTime getInstance() {
    EventTime time = new EventTime();
    time.setDuration(DURATION_DEFAULT);
    return time;

  }

  /**
   * Build a fully qualified EventTime instance
   *
   * @param seconds the instance duration, in seconds
   * @return a fully qualified EventTime instance
   */
  public static EventTime getInstance(int seconds) {
    EventTime time = new EventTime();
    time.setDuration(Duration.of(seconds, ChronoUnit.SECONDS));
    return time;
  }

  /**
   * Build a fully qualified EventTime instance
   *
   * @param duration the instance duration
   * @return a fully qualified EventTime instance
   */
  public static EventTime getInstance(Duration duration) {
    EventTime time = new EventTime();
    time.setDuration(duration);
    return time;
  }

  /**
   * Get the event duration.
   *
   * @return the event duration
   */
  public Duration getDuration() {
    return duration;
  }

  /**
   * Set the event duration. Also sets the `stopTime`.
   *
   * @param duration the event duration
   */
  public void setDuration(Duration duration) {
    this.duration = duration;
    this.stopTime = startTime.plus(duration);
  }

  /**
   * Get the inclusive start of the event.
   *
   * @return the inclusive start of the event
   */
  public ZonedDateTime getStartTime() {
    return startTime;
  }

  /**
   * Set the inclusive start of the event.
   *
   * @param startTime the inclusive start of the event
   */
  public void setStartTime(ZonedDateTime startTime) {
    this.startTime = startTime;
  }

  /**
   * Get the exclusive end of the event.
   *
   * @return the exclusive end of the event
   */
  public ZonedDateTime getStopTime() {
    return stopTime;
  }

  /**
   * Set the exclusive end of the event. Also sets the `duration`.
   *
   * @param stopTime the exclusive end of the event
   */
  public void setStopTime(ZonedDateTime stopTime) {
    this.stopTime = stopTime;
    this.duration = Duration.between(startTime, stopTime);
  }

  @Override
  public String toString() {
    return "EventTime{" + "startTime=" + startTime + ", stopTime=" + stopTime + ", duration=" + duration + '}';
  }

}

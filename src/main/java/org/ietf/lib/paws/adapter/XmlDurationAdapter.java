/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ietf.lib.paws.adapter;

import java.text.ParseException;
import java.time.Duration;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter to translate between a standard java.time.Duration instance
 * and ISO-8601 seconds based representation, such as PT8H6M12.345S
 *
 * @see
 * <a href="http://docs.oracle.com/javase/7/docs/api/java/text/DateFormat.html">DateFormat</a>
 * @see <a href="http://www.w3.org/TR/NOTE-datetime">W3C Date and Time
 * Formats</a>
 * @author Key Bridge LLC
 * @since v1.1.1 added 10/22/15
 */
public class XmlDurationAdapter extends XmlAdapter<String, Duration> {

  public XmlDurationAdapter() {
  }

  /**
   * Obtains a Duration from a text string such as PnDTnHnMn.nS. This will parse
   * a textual representation of a duration, including the string produced by
   * toString(). The formats accepted are based on the ISO-8601 duration format
   * PnDTnHnMn.nS with days considered to be exactly 24 hours.
   *
   * @param v The date-time datatype string, formatted in the UTC time zone.
   * @return a Date instance, normalized to UTC, null if the input string is
   *         null or empty.
   * @throws ParseException if the datatype string fails to parse
   */
  @Override
  public Duration unmarshal(String v) throws ParseException {
    return v != null ? Duration.parse(v) : null;
  }

  /**
   * Write a string representation of this duration using ISO-8601 seconds based
   * representation, such as PT8H6M12.345S. The format of the returned string
   * will be PTnHnMnS, where n is the relevant hours, minutes or seconds part of
   * the duration. Any fractional seconds are placed after a decimal point i the
   * seconds section. If a section has a zero value, it is omitted. The hours,
   * minutes and seconds will all have the same sign.
   *
   * @param v the Date instance, formatted in the UTC time zone
   * @return a patterned date string, null if the input calendar is null
   */
  @Override
  public String marshal(Duration v) {
    return v != null ? v.toString() : null;
  }
}

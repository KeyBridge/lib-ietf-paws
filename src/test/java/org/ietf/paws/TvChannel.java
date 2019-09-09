/*
 *  Copyright (C) 2011 Caulfield IP Holdings (Caulfield) and/or its affiliates.
 *  All rights reserved. Use is subject to license terms.
 *
 *  Software Code is protected by Caulfield Copyrights. Caulfield hereby
 *  reserves all rights in and to Caulfield Copyrights and no license is
 *  granted under Caulfield Copyrights in this Software License Agreement.
 *  Caulfield generally licenses Caulfield Copyrights for commercialization
 *  pursuant to the terms of either Caulfield's Standard Software Source Code
 *  License Agreement or Caulfield's Standard Product License Agreement.
 *  A copy of Caulfield's either License Agreement can be obtained on request
 *  by email from: info@caufield.org.
 */
package org.ietf.paws;

import java.util.ArrayList;
import java.util.List;

/**
 * An enumerated list North American television frequencies for over-the-air
 * (also called terrestrial) broadcast transmission.
 * <p>
 * A television channel is a physical or virtual channel over which a television
 * station or television network is distributed. For example, in North America,
 * "channel 2" refers to the broadcast or cable band of 54 to 60 MHz, with
 * carrier frequencies of 55.25 MHz for NTSC analog video (VSB) and 59.75 MHz
 * for analog audio (FM), or 55.31 MHz for digital ATSC (8VSB). Channels may be
 * shared by many different television stations or cable-distributed channels
 * depending on the location and service provider.
 * <p>
 * Depending on the multinational band plan for a given region, analog
 * television channels are typically 6, 7, or 8 MHz in bandwidth, and therefore
 * television channel frequencies vary as well. Channel numbering is also
 * different.
 *
 * @see <a
 * href="http://en.wikipedia.org/wiki/Television_channel_frequencies">Television
 * channel frequencies</a>
 * @see <a
 * href="http://en.wikipedia.org/wiki/North_American_television_frequencies">North
 * American television frequencies</a>
 * @author Jesse Caulfield
 * @since v2.0.0 rewritten to simplify and better accommodate TVWS operation
 */
public enum TvChannel {

  /**
   * TV Channel VHF2 [54.0 - 60.0 Mhz]. [gov.fcc.broadcasting.54-72]
   */
  VHF2(54.0, 60.0, "54-72"),
  /**
   * TV Channel VHF3 [60.0 - 66.0 Mhz]. [gov.fcc.broadcasting.54-72]
   */
  VHF3(60.0, 66.0, "54-72"),
  /**
   * TV Channel VHF4 [66.0 - 72.0 Mhz]. [gov.fcc.broadcasting.54-72]
   */
  VHF4(66.0, 72.0, "54-72"),

  /**
   * TV Channel VHF5 [76.0 - 82.0 Mhz]. [gov.fcc.broadcasting.76-88]
   */
  VHF5(76.0, 82.0, "76-88"),
  /**
   * TV Channel VHF6 [82.0 - 88.0 Mhz]. [gov.fcc.broadcasting.76-88]
   */
  VHF6(82.0, 88.0, "76-88"),

  /**
   * TV Channel VHF7 [174.0 - 180.0 Mhz]. [gov.fcc.broadcasting.174-216]
   */
  VHF7(174.0, 180.0, "174-216"),
  /**
   * TV Channel VHF8 [180.0 - 186.0 Mhz]. [gov.fcc.broadcasting.174-216]
   */
  VHF8(180.0, 186.0, "174-216"),
  /**
   * TV Channel VHF9 [186.0 - 192.0 Mhz]. [gov.fcc.broadcasting.174-216]
   */
  VHF9(186.0, 192.0, "174-216"),
  /**
   * TV Channel VHF10 [192.0 - 198.0 Mhz]. [gov.fcc.broadcasting.174-216]
   */
  VHF10(192.0, 198.0, "174-216"),
  /**
   * TV Channel VHF11 [198.0 - 204.0 Mhz]. [gov.fcc.broadcasting.174-216]
   */
  VHF11(198.0, 204.0, "174-216"),
  /**
   * TV Channel VHF12 [204.0 - 210.0 Mhz]. [gov.fcc.broadcasting.174-216]
   */
  VHF12(204.0, 210.0, "174-216"),
  /**
   * TV Channel VHF13 [210.0 - 216.0 Mhz]. [gov.fcc.broadcasting.174-216]
   */
  VHF13(210.0, 216.0, "174-216"),

  /**
   * TV Channel UHF14 [470.0 - 476.0 Mhz]. [gov.fcc.broadcasting.470-512]
   */
  UHF14(470.0, 476.0, "470-512"),
  /**
   * TV Channel UHF15 [476.0 - 482.0 Mhz]. [gov.fcc.broadcasting.470-512]
   */
  UHF15(476.0, 482.0, "470-512"),
  /**
   * TV Channel UHF16 [482.0 - 488.0 Mhz]. [gov.fcc.broadcasting.470-512]
   */
  UHF16(482.0, 488.0, "470-512"),
  /**
   * TV Channel UHF17 [488.0 - 494.0 Mhz]. [gov.fcc.broadcasting.470-512]
   */
  UHF17(488.0, 494.0, "470-512"),
  /**
   * TV Channel UHF18 [494.0 - 500.0 Mhz]. [gov.fcc.broadcasting.470-512]
   */
  UHF18(494.0, 500.0, "470-512"),
  /**
   * TV Channel UHF19 [500.0 - 506.0 Mhz]. [gov.fcc.broadcasting.470-512]
   */
  UHF19(500.0, 506.0, "470-512"),
  /**
   * TV Channel UHF20 [506.0 - 512.0 Mhz]. [gov.fcc.broadcasting.470-512]
   */
  UHF20(506.0, 512.0, "470-512"),

  /**
   * TV Channel UHF21 [512.0 - 518.0 Mhz]. [gov.fcc.broadcasting.512-608]
   */
  UHF21(512.0, 518.0, "512-608"),
  /**
   * TV Channel UHF22 [518.0 - 524.0 Mhz]. [gov.fcc.broadcasting.512-608]
   */
  UHF22(518.0, 524.0, "512-608"),
  /**
   * TV Channel UHF23 [524.0 - 530.0 Mhz]. [gov.fcc.broadcasting.512-608]
   */
  UHF23(524.0, 530.0, "512-608"),
  /**
   * TV Channel UHF24 [530.0 - 536.0 Mhz]. [gov.fcc.broadcasting.512-608]
   */
  UHF24(530.0, 536.0, "512-608"),
  /**
   * TV Channel UHF25 [536.0 - 542.0 Mhz]. [gov.fcc.broadcasting.512-608]
   */
  UHF25(536.0, 542.0, "512-608"),
  /**
   * TV Channel UHF26 [542.0 - 548.0 Mhz]. [gov.fcc.broadcasting.512-608]
   */
  UHF26(542.0, 548.0, "512-608"),
  /**
   * TV Channel UHF27 [548.0 - 554.0 Mhz]. [gov.fcc.broadcasting.512-608]
   */
  UHF27(548.0, 554.0, "512-608"),
  /**
   * TV Channel UHF28 [554.0 - 560.0 Mhz]. [gov.fcc.broadcasting.512-608]
   */
  UHF28(554.0, 560.0, "512-608"),
  /**
   * TV Channel UHF29 [560.0 - 566.0 Mhz]. [gov.fcc.broadcasting.512-608]
   */
  UHF29(560.0, 566.0, "512-608"),
  /**
   * TV Channel UHF30 [566.0 - 572.0 Mhz]. [gov.fcc.broadcasting.512-608]
   */
  UHF30(566.0, 572.0, "512-608"),
  /**
   * TV Channel UHF31 [572.0 - 578.0 Mhz]. [gov.fcc.broadcasting.512-608]
   */
  UHF31(572.0, 578.0, "512-608"),
  /**
   * TV Channel UHF32 [578.0 - 584.0 Mhz]. [gov.fcc.broadcasting.512-608]
   */
  UHF32(578.0, 584.0, "512-608"),
  /**
   * TV Channel UHF33 [584.0 - 590.0 Mhz]. [gov.fcc.broadcasting.512-608]
   */
  UHF33(584.0, 590.0, "512-608"),
  /**
   * TV Channel UHF34 [590.0 - 596.0 Mhz]. [gov.fcc.broadcasting.512-608]
   */
  UHF34(590.0, 596.0, "512-608"),
  /**
   * TV Channel UHF35 [596.0 - 602.0 Mhz]. [gov.fcc.broadcasting.512-608]
   */
  UHF35(596.0, 602.0, "512-608"),
  /**
   * TV Channel UHF36 [602.0 - 608.0 Mhz]. [gov.fcc.broadcasting.512-608]
   */
  UHF36(602.0, 608.0, "512-608"),

  /**
   * TV Channel UHF37 [608.0 - 614.0 Mhz]. [gov.fcc.radio_astronomy.608-614]
   */
  UHF37(608.0, 614.0, "608-614"),

  /**
   * TV Channel UHF38 [614.0 - 620.0 Mhz]. [gov.fcc.broadcasting.614-698]
   */
  UHF38(614.0, 620.0, "614-698"),
  /**
   * TV Channel UHF39 [620.0 - 626.0 Mhz]. [gov.fcc.broadcasting.614-698]
   */
  UHF39(620.0, 626.0, "614-698"),
  /**
   * TV Channel UHF40 [626.0 - 632.0 Mhz]. [gov.fcc.broadcasting.614-698]
   */
  UHF40(626.0, 632.0, "614-698"),
  /**
   * TV Channel UHF41 [632.0 - 638.0 Mhz]. [gov.fcc.broadcasting.614-698]
   */
  UHF41(632.0, 638.0, "614-698"),
  /**
   * TV Channel UHF42 [638.0 - 644.0 Mhz]. [gov.fcc.broadcasting.614-698]
   */
  UHF42(638.0, 644.0, "614-698"),
  /**
   * TV Channel UHF43 [644.0 - 650.0 Mhz]. [gov.fcc.broadcasting.614-698]
   */
  UHF43(644.0, 650.0, "614-698"),
  /**
   * TV Channel UHF44 [650.0 - 656.0 Mhz]. [gov.fcc.broadcasting.614-698]
   */
  UHF44(650.0, 656.0, "614-698"),
  /**
   * TV Channel UHF45 [656.0 - 662.0 Mhz]. [gov.fcc.broadcasting.614-698]
   */
  UHF45(656.0, 662.0, "614-698"),
  /**
   * TV Channel UHF46 [662.0 - 668.0 Mhz]. [gov.fcc.broadcasting.614-698]
   */
  UHF46(662.0, 668.0, "614-698"),
  /**
   * TV Channel UHF47 [668.0 - 674.0 Mhz]. [gov.fcc.broadcasting.614-698]
   */
  UHF47(668.0, 674.0, "614-698"),
  /**
   * TV Channel UHF48 [674.0 - 680.0 Mhz]. [gov.fcc.broadcasting.614-698]
   */
  UHF48(674.0, 680.0, "614-698"),
  /**
   * TV Channel UHF49 [680.0 - 686.0 Mhz]. [gov.fcc.broadcasting.614-698]
   */
  UHF49(680.0, 686.0, "614-698"),
  /**
   * TV Channel UHF50 [686.0 - 692.0 Mhz]. [gov.fcc.broadcasting.614-698]
   */
  UHF50(686.0, 692.0, "614-698"),
  /**
   * TV Channel UHF51 [692.0 - 698.0 Mhz]. [gov.fcc.broadcasting.614-698]
   */
  UHF51(692.0, 698.0, "614-698");

  /**
   * The channelId start frequency (MHz)
   */
  private final double frequencyMin;
  /**
   * The channelId end frequency (MHz)
   */
  private final double frequencyMax;
  /**
   * The frequency allocation RANGE identified in the FCC table of frequency
   * allocations.
   */
  private final String frequencyBand;

  private TvChannel(double frequencyMin, double frequencyMax, String band) {
    this.frequencyMin = frequencyMin;
    this.frequencyMax = frequencyMax;
    this.frequencyBand = band;
  }

  /**
   * The frequency allocation RANGE identified in the FCC table of frequency
   * allocations.
   *
   * @return a dash-delimited string of the min and max frequency for this
   *         channel. e.g. '614-698'
   */
  public String getFrequencyBand() {
    return frequencyBand;
  }

  /**
   * The minimum (start) frequency for this channel (MHz).
   * <p>
   * This value is inclusive: channels include the minimum frequency.
   *
   * @return The minimum (start) frequency
   */
  public double getFrequencyMin() {
    return frequencyMin;
  }

  /**
   * The maximum (end) frequency for this channel (MHz).
   * <p>
   * This value is exclusive: channels do not include the maximum frequency.
   *
   * @return The maximum (end) freque
   */
  public double getFrequencyMax() {
    return frequencyMax;
  }

  /**
   * Get the channel mid-range frequency (MHz).
   * <p>
   * This is equal to (frequencyMax + frequencyMin) / 2
   *
   * @return the channel mid-range frequency
   */
  public double getFrequencyCenter() {
    return ((frequencyMax + frequencyMin) / 2d);
  }

  /**
   * Get the frequency units. For this enumerated class the units are "MHz"
   *
   * @return the frequency units
   */
  public String getUnit() {
    return "MHz";
  }

  /**
   * Get the US Channel width in MHz.
   *
   * @return 6 MHz.
   */
  public double getChannelWidth() {
    return frequencyMax - frequencyMin;
  }

  /**
   * Find a Enum_ChannelUS channel by its channel number.
   *
   * @param channel the colloquial channel number. For US channels this is
   *                simply the channel number. e.g. '2' is TV channel 2.
   * @return a channel number
   * @throws IllegalArgumentException if the channel number is not a Television
   *                                  broadcast channel
   */
  public static TvChannel fromChannelNumber(int channel) throws IllegalArgumentException {
    return TvChannel.valueOf((channel < 13 ? "UHF" : "VHF") + channel);
  }

  /**
   * Find a enumerated Channel that contains the indicated frequency.
   *
   * @param frequency the frequency of interest in MHz.
   * @return the first channel having its min frequency less than or equal to
   *         and its max frequency greater than the input frequency.
   * @throws IllegalArgumentException if the frequency does not correspond to a
   *                                  Television broadcast channel
   */
  public static TvChannel fromFrequency(double frequency) throws IllegalArgumentException {
    for (TvChannel channel : TvChannel.values()) {
      if (channel.getFrequencyMin() <= frequency && channel.getFrequencyMax() > frequency) {
        return channel;
      }
    }
    /**
     * Hook to catch the highest possible frequency.
     */
    if (TvChannel.UHF51.getFrequencyMax() == frequency) {
      return TvChannel.UHF51;
    }
    throw new IllegalArgumentException(frequency + "MHz is not in the Television broadcast bands.");
  }

  //<editor-fold defaultstate="collapsed" desc="Find First Adjacent Channels">
  /**
   * Get the next channel. Throws an exception if the next value does not exist.
   *
   * @return the next channel.
   */
  public TvChannel getNext() {
    return values()[ordinal() + 1];
  }

  /**
   * Get the previous channel. Throws an exception if the next value does not
   * exist.
   *
   * @return the next channel.
   */
  public TvChannel getPrevious() {
    return values()[ordinal() - 1];
  }

  /**
   * Returns a list of the first adjacent channels, upper and lower, if present.
   * <p>
   * This method overrides and excludes channel 37.
   *
   * @return a list of containing up to two channels
   */
  public List<TvChannel> findFirstAdjacentChannels() {
    List<TvChannel> channelListAdjacent = new ArrayList<>();
    try {
      /**
       * Calculate the lower channel. It is adjacent if the lower channel's max
       * frequency is equal to the querying channel's min frequency.
       */
      TvChannel channelLower = getPrevious();
      if (channelLower != null && channelLower.getFrequencyMax() == frequencyMin && !channelLower.equals(UHF37)) {
        channelListAdjacent.add(channelLower);
      }
      /**
       * Calculate the upper channel. It is adjacent if the upper channel's min
       * frequency is equal to the querying channel's max frequency.
       */
      TvChannel channelUpper = getNext();
      if (channelUpper != null && channelUpper.getFrequencyMin() == frequencyMax && !channelUpper.equals(UHF37)) {
        channelListAdjacent.add(channelUpper);
      }
    } catch (IllegalArgumentException exception) {
    }
    return channelListAdjacent;
  }//</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Comparator Interface Implementation">
  /**
   * Determine if the input channel ID is above this channel
   *
   * @param channel the input channel if interest
   * @return true if the input channel is above this channel
   */
  public boolean above(TvChannel channel) {
    return channel.compareTo(this) < 0;
  }

  /**
   * Determine if the input channel ID is above OR equal to this channel
   *
   * @param channel the input channel if interest
   * @return true if the input channel is above or equal to this channel
   */
  public boolean aboveEqual(TvChannel channel) {
    return channel.compareTo(this) <= 0;
  }

  /**
   * Determine if the input channel ID is below this channel
   *
   * @param channel the input channel if interest
   * @return true if the input channel is below this channel
   */
  public boolean below(TvChannel channel) {
    return channel.compareTo(this) > 0;
  }

  /**
   * Determine if the input channel ID is below OR equal to this channel
   *
   * @param channel the input channel if interest
   * @return true if the input channel is below or equal to this channel
   */
  public boolean belowEqual(TvChannel channel) {
    return channel.compareTo(this) >= 0;
  }

  /**
   * Determine if this channel is between (but not equal) to the lower and upper
   * channels.
   *
   * @param lowerChannel the lower channel
   * @param upperChannel the upper channel
   * @return TRUE if between, otherwise false
   */
  public boolean between(TvChannel lowerChannel, TvChannel upperChannel) {
    return this.above(lowerChannel) && this.below(upperChannel);
  }

  /**
   * Determine if this channel is between (or equal) to the lower and upper
   * channels.
   *
   * @param lowerChannel the lower channel
   * @param upperChannel the upper channel
   * @return TRUE if between or equal, otherwise false
   */
  public boolean betweenEqual(TvChannel lowerChannel, TvChannel upperChannel) {
    return this.aboveEqual(lowerChannel) && this.belowEqual(upperChannel);
  }//</editor-fold>

  /**
   * Return a pretty-print output of the channel formatted as NAME [MIN - MAX
   * MHz]
   *
   * @return e.g. 'VHF 2 [ 54 - 60 MHz]'
   */
  public String getLabel() {
    return String.format("%-5s [%3.0f - %3.0f MHz]", name(), frequencyMin, frequencyMax);
  }

}

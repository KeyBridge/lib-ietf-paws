/*
 * Copyright 2019 Key Bridge. All rights reserved. Use is subject to license
 * terms.
 *
 * This software code is protected by Copyrights and remains the property of
 * Key Bridge and its suppliers, if any. Key Bridge reserves all rights in and to
 * Copyrights and no license is granted under Copyrights in this Software
 * License Agreement.
 *
 * Key Bridge generally licenses Copyrights for commercialization pursuant to
 * the terms of either a Standard Software Source Code License Agreement or a
 * Standard Product License Agreement. A copy of either Agreement can be
 * obtained upon request by sending an email to info@keybridgewireless.com.
 *
 * All information contained herein is the property of Key Bridge and its
 * suppliers, if any. The intellectual and technical concepts contained herein
 * are proprietary.
 */
package org.ietf.paws;

import ch.keybridge.lib.gis.common.GisCalculator;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.ietf.lib.paws.*;
import org.ietf.lib.paws.message.AvailableSpectrumRequest;
import org.ietf.lib.paws.message.SpectrumUseNotify;
import org.ietf.lib.paws.type.AntennaPolarizationType;
import org.ietf.lib.paws.type.FccDeviceMode;
import org.ietf.lib.paws.type.PawsRulesetType;
import org.ietf.lib.paws.type.SpectrumRequestType;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

/**
 *
 * @author Key Bridge
 */
public class EntityBuilder {

  /**
   * Prepare the random test generator
   */
  private static final Lorem l = LoremIpsum.getInstance();
  private static final Random r = new Random();
  /**
   * Indicator that a random position should be generated. If FALSE then a
   * static position in Washington DC is used.
   */
  private boolean randomPosition = true;

  public void setRandomPosition(boolean randomPosition) {
    this.randomPosition = randomPosition;
  }

  /**
   * Build a random AvailableSpectrumRequest. The SpectrumRequestType will be
   * selected at random.
   *
   * @return a an AvailableSpectrumRequest for a randomly selected
   *         SpectrumRequestType
   *
   */
  public AvailableSpectrumRequest buildRandomAvailableSpectrumRequest() {
    SpectrumRequestType requestType = getRandomSpectrumRequestType();
    return buildAvailableSpectrumRequest(requestType);
  }

  /**
   * Build an AvailableSpectrumRequest for the specified type
   *
   * @param requestType a SpectrumRequestType
   * @return an AvailableSpectrumRequest for the specified SpectrumRequestType
   */
  public AvailableSpectrumRequest buildAvailableSpectrumRequest(SpectrumRequestType requestType) {
    AvailableSpectrumRequest request = new AvailableSpectrumRequest();
    request.setRequestType(requestType);

    /**
     * DeviceOwner is always required. It is used to math the transaction with a
     * registered user and account.
     */
    request.setDeviceOwner(buildDeviceOwner()); // always required

    /**
     * Build a master or slave configuration
     */
    switch (requestType) {
      /**
       * SLAVE requests are submitted by a MASTER on behalf of the SLAVE. The
       * MASTER must identify itself.
       */
      case SLAVE: {
        request.setDeviceDesc(buildDeviceDesc(FccDeviceMode.MODE_2));
        request.setAntenna(buildAntennaCharacteristics());
        request.setLocation(buildGeolocation());

        request.setMasterDeviceDesc(buildDeviceDesc(FccDeviceMode.FIXED));
        request.setMasterDeviceLocation(buildGeolocation());
        request.setCapabilities(buildDeviceCapabilities());
      }
      break;

      case MASTER: {
        request.setDeviceDesc(buildDeviceDesc(FccDeviceMode.FIXED));
        request.setAntenna(buildAntennaCharacteristics());
        request.setLocation(buildGeolocation());
      }
      break;

      case LPA: {
        request.setDeviceDesc(buildDeviceDesc(FccDeviceMode.LPA));
        request.setLocation(buildGeolocation());
      }
      break;

      case INFO:
        request.setDeviceDesc(buildDeviceDesc(FccDeviceMode.LPA));
        request.setLocation(buildGeolocation());
        break;

      default:
        throw new AssertionError(requestType);

    }

    return request;
  }

  public SpectrumUseNotify buildSpectrumUseNotify() {
    SpectrumUseNotify notify = new SpectrumUseNotify();
    notify.setDeviceDesc(buildDeviceDesc(FccDeviceMode.FIXED));
    notify.setLocation(buildGeolocation());
//    for (int i = 0; i < 1 + r.nextInt(3); i++) {
    notify.setTransmitChannel(buildSpectrumChannel(getRandomChannel()));
    notify.setReceiveChannel(buildSpectrumChannel(getRandomChannel()));
//    }
    notify.setTimeRange(buildTimeRange());

    return notify;
  }

  public TvChannel getRandomChannel() {
    return TvChannel.values()[r.nextInt(TvChannel.values().length)];
  }

  public SpectrumRequestType getRandomSpectrumRequestType() {
    return SpectrumRequestType.values()[r.nextInt(SpectrumRequestType.values().length)];
  }

  public DeviceDescriptor buildDeviceDesc(FccDeviceMode deviceMode) {
    DeviceDescriptor device = new DeviceDescriptor();
    device.setRulesetIds(PawsRulesetType.FCC_PART_15_H_2019);
    device.setSerialNumber(UUID.randomUUID().toString());
    device.setManufacturerId(l.getWords(2));
    device.setModelId(l.getWords(1));
//    device.setRulesetId(PawsRulesetType.FCC_Part15H_2019);

    device.setDeviceId(UUID.randomUUID().toString().substring(0, 18));
    device.setDeviceMode(deviceMode.name());
//    device.setFccDeviceType(FccDeviceType.FIXED);

    return device;
  }

  public GeoLocation buildGeolocation() {
    GeoLocation location = new GeoLocation();

    Point point = randomPosition ? generateRandomPoint() : generateWashingtonDCPoint();
    int uncertainty = 50;

    location.setPoint(point);
    location.setUncertainty(uncertainty);
    location.setRegion(generateRandomPolygonCircle(point, uncertainty));
    location.setConfidence(r.nextInt(100));  // not used

    return location;
  }

  /**
   * Generate a random coordinate somewhere in the world.
   *
   * @return
   */
  private Point generateRandomPoint() {
    /**
     * USA CONUS bounds <br>
     * X: -123.11 to -74.26 t: to 29.63 to 48.12
     */
//    Coordinate washingtonDc = new Coordinate(-77.16, 39.00);
//    return GisCalculator.asPoint(washingtonDc);
    int sign = r.nextBoolean() ? 1 : -1;
    return GisCalculator.asPoint(new Coordinate(-95 + sign * r.nextDouble() * 28,
                                                34 + sign * r.nextDouble() * 12)); // CONUS centered
  }

  private Point generateWashingtonDCPoint() {
    /**
     * USA CONUS bounds <br>
     * X: -123.11 to -74.26 t: to 29.63 to 48.12
     */
    Coordinate washingtonDc = new Coordinate(-77.16, 39.00);
    return GisCalculator.asPoint(washingtonDc);
  }

  /**
   * Build a circular polygon. This is useful for authorization and
   * infrastructure shapes.
   *
   * @param centroid the contour centroid
   * @return the polygon
   */
  private Polygon generateRandomPolygonCircle(Point centroid, double buffer) {
    try {
      Geometry polygon = GisCalculator.buffer(centroid, buffer + buffer * r.nextDouble());
      return (Polygon) polygon;
    } catch (Exception exception) {
      return null;
    }
  }

  public AntennaCharacteristics buildAntennaCharacteristics() {
    AntennaCharacteristics antenna = new AntennaCharacteristics();

    double hag = 10; // default meters agl

    antenna.setHeight(hag + hag * r.nextDouble());
    antenna.setHeightUncertainty(hag * r.nextDouble() / 2);

    antenna.setPolarization(r.nextBoolean()
                            ? AntennaPolarizationType.H
                            : AntennaPolarizationType.V);
    /**
     * Generate a random radiation pattern. It is OK to use a HashMap here as
     * the XmlRadiationPatternAdapter::marshal function will enforce entry
     * sorting.
     */
    Map<Double, Double> radiationPattern = new HashMap<>();
    for (double i = 0; i < 360; i += r.nextInt(30)) {
      radiationPattern.put(i, -1 * Math.abs(r.nextDouble() * 3)); // relative gain value must be negative
    }
//    XmlRadiationPatternAdapter adapter = new XmlRadiationPatternAdapter();
    antenna.setRadiationPattern(radiationPattern);
    /**
     * Note: Either set the gain + beamwidth OR provide a pattern. If both are
     * provided the gain + beamwidth is ignored by the database as a radiation
     * pattern supercedes if both are provided.
     * <p>
     * Here we set both to demonstrate this effect.
     */
    antenna.setGain(19d);
    antenna.setBeamWidthAzimuth(24d);
    antenna.setBeamWidthElevation(10d);

    antenna.setRotation((double) r.nextInt(360));

    return antenna;
  }

  public DeviceOwner buildDeviceOwner() {
    /**
     * DeviceOwner contains LDAP distinquished names, which should be assembled
     * by a proper LDAP library. Here we fake it by hand-assembling a DN.
     */
    DeviceOwner owner = new DeviceOwner();
    owner.setOwner(buildLdapDn(false));
    owner.setOperator(buildLdapDn(true));
    return owner;
  }

  private String buildLdapDn(boolean operator) {
    StringBuilder sb = new StringBuilder();
    sb.append("cn=").append(l.getName());
    sb.append(",dc=").append(l.getWords(1));
    sb.append(",dc=").append("com");
    /**
     * `mail` is the primary reference to the the user account number on the Key
     * Bridge system.
     */
    sb.append(",mail=").append(l.getEmail());
    /**
     * `keyid` is an optional backup reference to the the user account number on
     * the Key Bridge system.
     */
    if (operator) {
      sb.append(",installerId=").append(UUID.randomUUID().toString());
    }

    return sb.toString();
  }

  public DeviceCapabilities buildDeviceCapabilities() {
    DeviceCapabilities capabilities = new DeviceCapabilities();
    /**
     * Add a few random ranges. These will actually come from the device
     * certification.
     */
    for (int i = 0; i < 1 + r.nextInt(3); i++) {
      double startMHz = Math.abs(450 * r.nextDouble());
      double stopMHz = startMHz + 50 + 50 * r.nextDouble();

      FrequencyRange range = new FrequencyRange();
      range.setStartMHz(startMHz);
      range.setStopMHz(stopMHz);
      capabilities.getFrequencyRanges().add(range);
    }

    return capabilities;
  }

  private SpectrumChannel buildSpectrumChannel(TvChannel channel) {
    return new SpectrumChannel(channel.name(), channel.getFrequencyMin(), channel.getFrequencyMax());
  }

  private EventTime buildTimeRange() {
    EventTime eventTime = new EventTime();
    eventTime.setStartTime(ZonedDateTime.now());
    eventTime.setStopTime(ZonedDateTime.now().plusHours(12));
    return eventTime;
  }
}

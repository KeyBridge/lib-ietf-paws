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
package org.ietf.lib.paws.message;

import ch.keybridge.lib.xml.JaxbUtility;
import java.util.Arrays;
import java.util.Collection;
import javax.xml.bind.JAXBException;
import org.ietf.lib.paws.SpectrumChannel;
import org.ietf.lib.paws.SpectrumInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Key Bridge
 */
public class AvailableSpectrumResponseTest {

  public AvailableSpectrumResponseTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testMethod() throws JAXBException {
    AvailableSpectrumResponse response = new AvailableSpectrumResponse();

    SpectrumInfo info = new SpectrumInfo("info", 1, 2);
    SpectrumChannel channel = new SpectrumChannel("channel", 4, 5);

    Collection<SpectrumChannel> channels = Arrays.asList(channel);
    response.setSpectrumChannels(channels);

    Collection<SpectrumInfo> infos = Arrays.asList(info);
    response.setSpectrumInfos(infos);

    response.clearSpectrum();

    response.addSpectrumEntry(channel);
    response.addSpectrumEntry(info);

    System.out.println(JaxbUtility.marshal(response));

  }

}

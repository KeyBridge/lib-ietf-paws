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

import ch.keybridge.lib.json.JsonUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import javax.xml.bind.JAXBException;
import org.ietf.lib.paws.message.AvailableSpectrumRequest;
import org.ietf.lib.paws.type.SpectrumRequestType;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Key Bridge
 */
public class EntityBuilderTest {

  private static EntityBuilder entityBuilder;

  public EntityBuilderTest() {
  }

  @BeforeClass
  public static void setUpClass() {
    entityBuilder = new EntityBuilder();
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testUnmarshal() {
  }

  @Test
  public void testMarshal() throws JAXBException, JsonProcessingException {
    entityBuilder.setRandomPosition(true);
    AvailableSpectrumRequest request = entityBuilder.buildAvailableSpectrumRequest(SpectrumRequestType.INFO);
//    System.out.println(JaxbUtility.marshal(request));
    System.out.println(JsonUtility.marshal(request));
  }

}

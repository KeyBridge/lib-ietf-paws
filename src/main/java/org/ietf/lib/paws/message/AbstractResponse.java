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

import java.util.UUID;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Key Bridge LLC
 */
public class AbstractResponse {

  @XmlAttribute
  private final String id;

  public AbstractResponse() {
    this.id = UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

}

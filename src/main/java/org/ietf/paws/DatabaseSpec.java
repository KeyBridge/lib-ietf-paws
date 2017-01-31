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

import javax.xml.bind.annotation.*;

/**
 * <img src="doc-files/databaseSpec.png">
 * <p>
 * 5.8. DatabaseSpec
 * <p>
 * This element contains the name and URI of a Database.
 * <pre>
 * +--------------------------+
 * |DatabaseSpec              |
 * +---------------+----------+
 * |name:string    | REQUIRED |
 * |uri:string     | REQUIRED |
 * +---------------+----------+
 * </pre>
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "DatabaseSpec")
@XmlType(name = "DatabaseSpec")
@XmlAccessorType(XmlAccessType.FIELD)
public class DatabaseSpec {

  /**
   * The display name. Its maximum length is 64 octets.
   */
  @XmlElement(required = true)
  private String name;
  /**
   * The corresponding URI of the Database. Its maximum length is 1024 octets.
   */
  @XmlElement(required = true)
  private String uri;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }
}

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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

/**
 * <img src="doc-files/dbUpdateSpec.png">
 * <p>
 * 5.7. DbUpdateSpec
 * <p>
 * This element is provided by the Database to notify devices of an upcoming
 * change to the database URI.
 * <pre>
 * +-------------------------------+
 * |DbUpdateSpec                   |
 * +---------------------+---------+       +--------------------------+
 * |databases:list       |REQUIRED |------>|DatabaseSpec              |
 * +---------------------+---------+  1..* +---------------+----------+
 *                                         |name:string    | REQUIRED |
 *                                         |uri:string     | REQUIRED |
 *                                         +---------------+----------+
 * </pre>
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "DbUpdateSpec")
@XmlType(name = "DbUpdateSpec")
@XmlAccessorType(XmlAccessType.FIELD)
public class DbUpdateSpec {

  /**
   * List of one or more DatabaseSpec (Section 5.8) entries. A device needs to
   * update its preconfigured entry for the responding Database with the
   * alternate Databases listed in the DbUpdateSpec.
   */
  @XmlElement(required = true)
  private List<DatabaseSpec> databases;

  public List<DatabaseSpec> getDatabases() {
    if (databases == null) {
      databases = new ArrayList<>();
    }
    return databases;
  }

  public void setDatabases(List<DatabaseSpec> databases) {
    this.databases = databases;
  }
}

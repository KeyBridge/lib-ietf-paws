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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.ietf.lib.paws.DbUpdateSpec;
import org.ietf.lib.paws.Error;

/**
 * 4.5.6. SPECTRUM_USE_RESP
 * <p>
 * The spectrum-use response message simply acknowledges receipt of the
 * notification.
 * <pre>
 *   +---------------------------------------+
 *   |SPECTRUM_USE_RESP                      |
 *   +----------------------------+----------+
 *   |databaseChange:DbUpdateSpec | OPTIONAL |
 *   |.......................................|
 *   |*other:any                  | OPTIONAL |
 *   +----------------------------+----------+
 * </pre> other: Database implementations MAY return additional parameters in
 * the response. Consult the PAWS Parameters Registry (Section 9.2) for possible
 * additional parameters.
 *
 * @author Key Bridge
 */
@XmlRootElement(name = "SpectrumUseResponse")
@XmlType(name = "SpectrumUseResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpectrumUseResponse {

  /**
   * The Database MAY include a DbUpdateSpec (Section 5.7) to notify the device
   * of a change to the database URI, providing one or more alternate database
   * URIs. The device needs to update its preconfigured entry for the responding
   * Database with the alternate Databases listed in the DbUpdateSpec.
   */
  private DbUpdateSpec databaseChange;

  /**
   * Error element describing any error encountered during processing.
   */
  private Error error;

  public DbUpdateSpec getDatabaseChange() {
    return databaseChange;
  }

  public void setDatabaseChange(DbUpdateSpec databaseChange) {
    this.databaseChange = databaseChange;
  }

  public Error getError() {
    return error;
  }

  public void setError(Error error) {
    this.error = error;
  }

}

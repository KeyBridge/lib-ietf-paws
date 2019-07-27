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

import java.util.Objects;
import javax.xml.bind.annotation.*;
import org.ietf.lib.paws.type.PawsErrorCode;

/**
 * 5.17. Error Element
 * <p>
 * <img src="doc-files/error.png">
 * <p>
 * If the Database responds to a PAWS request message with an error, it MUST
 * include an Error element.
 * <pre>
 * +----------------------------------+
 * |Error                             |
 * +----------------+-----------------+
 * |code:int        | REQUIRED        |
 * |message:string  | OPTIONAL        |
 * |data:any        | see description |
 * +----------------+-----------------+
 * </pre> The following table lists predefined and reserved error codes. They
 * are loosely grouped into the following categories:
 * <p>
 * -100s: Indicates compatibility issues, e.g., version mismatch, unsupported or
 * unimplemented features.
 * <p>
 * -200s: Indicates that the device request contains an error that needs to be
 * modified before making another request.
 * <p>
 * -300s: Indicates authorization-related issues.
 * <p>
 * Values that are not defined explicitly in the Error Codes Table (Table 1)
 * below are unassigned. To define new error codes, see PAWS Error Code Registry
 * (Section 9.3).
 * <pre>
 *  Code   Name             Description and Additional Parameters
 *  ------ ---------------- ---------------------------------------------
 *  0      (reserved)
 *  -100   (reserved)
 *  -101   VERSION          The Database does not support the specified
 *                          version of the message.  This error does not
 *                          use any additional data.
 *  -102   UNSUPPORTED      The Database does not support the device.
 *                          For example, it supports none of the rulesets
 *                          specified in the request or does not support
 *                          the device, based on its device type, model,
 *                          etc.  This error does not use any additional
 *                          data.
 *  -103   UNIMPLEMENTED    The Database does not implement the optional
 *                          request or optional feature.  This error does
 *                          not use any additional data.
 *  -104   OUTSIDE_COVERAGE The specified geolocation is outside the
 *                          coverage area of the Database.  The Database
 *                          MAY include a DbUpdateSpec (Section 5.7) to
 *                          provide a list of alternate Databases that
 *                          might be appropriate for the requested
 *                          location.  See OUTSIDE_COVERAGE Error
 *                          (Section 5.17.1) for more details.
 *  -105   DATABASE_CHANGE  The Database has changed its URI.  The
 *                          Database MAY include a DbUpdateSpec (Section
 *                          5.7) in the error response to provide devices
 *                          with one or more alternate database URIs.
 *                          The device needs to update its preconfigured
 *                          entry for the responding Database with the
 *                          alternate Databases listed in the
 *                          DbUpdateSpec.  See DATABASE_CHANGE Error
 *                          (Section 5.17.2) for more details.
 *  -200   (reserved)
 *  -201   MISSING          A required parameter is missing.  The
 *                          Database MUST include a list of the required
 *                          parameter names.  The Database MAY include
 *                          only names of parameters that are missing,
 *                          but MAY include a full list. Including the
 *                          full list of missing parameters may reduce
 *                          the number of re-queries from the device.
 *                          See MISSING Error (Section 5.17.3) for more
 *                          details.
 *  -202   INVALID_VALUE    A parameter value is invalid in some way.
 *                          The Database SHOULD include a message
 *                          indicating which parameter and why its value
 *                          is invalid.  This error does not use any
 *                          additional data.
 *  -300   (reserved)
 *  -301   UNAUTHORIZED     The device is not authorized to used the
 *                          Database.   Authorization may be determined
 *                          by the ruleset or be dependent on prior
 *                          arrangement between the device and Database.
 *                          This error does not use any additional data.
 *  -302   NOT_REGISTERED   Device registration required, but the device
 *                          is not registered.  This error does not use
 *                          any additional data.
 *  -32000 (reserved)       Reserved for JSON-RPC error codes.
 *  to
 *  -32768
 * <p>
 * </pre>
 *
 * @author Key Bridge LLC
 */
@XmlRootElement(name = "Error")
@XmlType(name = "Error")
@XmlAccessorType(XmlAccessType.FIELD)
public class Error {

  /**
   * An integer code that indicates the error type is REQUIRED. Values MUST be
   * within the range -32768 to 32767, inclusive.
   */
  @XmlElement(required = true)
  private Integer code;
  /**
   * A description of the error is OPTIONAL. It MAY be in any language. Its
   * maximum length is 128 octets.
   */
  private String message;
  /**
   * The Database MAY include additional data. For some errors, additional data
   * may be required (see Table 1). The device MUST ignore any data parameters
   * it does not understand.
   */
  private Object data;

  public static Error getInstance(PawsErrorCode pawsError) {
    Error error = new Error();
    error.setCode(pawsError.getCode());
    error.setMessage(pawsError.getDescription());
    return error;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 97 * hash + Objects.hashCode(this.code);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Error other = (Error) obj;
    return Objects.equals(this.code, other.code);
  }

}

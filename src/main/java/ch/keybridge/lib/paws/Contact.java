/*
 * Copyright 2016 Key Bridge LLC.
 *
 * All rights reserved. Use is subject to license terms.
 *
 * Software Code is protected by Copyrights. Author hereby
 * reserves all rights in and to Copyrights and no license is
 * granted under Copyrights in this Software License Agreement.
 *
 * Key Bridge LLC generally licenses Copyrights for commercialization
 * pursuant to the terms of either a Standard Software Source Code
 * License Agreement or a Standard Product License Agreement.
 * A copy of either Agreement can be obtained upon request
 * from: info@keybridgewireless.com
 */
package ch.keybridge.lib.paws;

import java.util.Objects;
import javax.xml.bind.annotation.*;

/**
 * <img src="doc-files/contact.png">
 * A database entity class to manage, store and retrieve user contact
 * information.
 * <p>
 * The WSIF contact represents a generalized container for individual (person)
 * and company (organization) contact information. The WSIF contact is derived
 * from the IETF vCARD specification with minor extensions to accommodate useful
 * information provided by Government databases or information otherwise
 * required for white space implementation by Rule.
 * <p>
 * A WSIF contact object organizes information necessary to describe an
 * organization or person, the organization or person’s role, their security
 * credentials, and how they may be reached via a location object which captures
 * mailing addresses.
 * <h2>Compatibility</h2> This class is designed to hold all useful information
 * in a vCard. e.g.
 * <pre>BEGIN:VCARD
 *   VERSION:2.1
 *   N:Caulfield;Jesse
 *   FN:Jesse Caulfield
 *   ORG:Key Bridge Global LLC
 *   TITLE:President
 *   NOTE;ENCODING=QUOTED-PRINTABLE:Skype: jcaulfield=0D=0A=
 *   Google voice: (202) 596-5092
 *   TEL;WORK;VOICE:(703) 414-3500
 *   TEL;CELL;VOICE:(202) 414-3500
 *   TEL;WORK;FAX:(703) 414-3501
 *   ADR;WORK;PREF:;;1650 Tysons Blvd., Suite 1100;Vienna;VA;22182
 *   LABEL;WORK;PREF;ENCODING=QUOTED-PRINTABLE:1650 Tysons Blvd., \
 *     Suite 1100=0D=0A=Vienna, VA  22182
 *   X-MS-OL-DEFAULT-POSTAL-ADDRESS:2
 *   BDAY:19711020
 *   EMAIL;PREF;INTERNET:jesse.caulfield@keybridgeglobal.com
 *   REV:20110602T042855Z
 * END:VCARD
 * </pre> <h2>Validity</h2> If the contact is a person then the firstName and
 * lastName attributes are required. If the contact is an organization or
 * company then the organization attribute is required. The following contact
 * attributes and elements are required by white space Rule:
 * <p>
 * For Fixed TVBD registration: <ul> <li>Either (firstName and lastName) or
 * organization</li> <li>email</li> <li>address</li> <li>phone</li> </ul>
 * <p>
 * For Licensed and Unlicensed low-power auxiliary devices: <ul> <li>Either
 * (firstName and lastName) or organization</li> <li>email</li> <li>address</li>
 * <li>phone</li> </ul>
 * <h2>Key Bridge implementation</h2>
 * If the contact does not have an email address then the email attribute is set
 * to a calculated unique value. This assures WSIF validity while accommodating
 * the importation of records from external (i.e. Government) databases that may
 * not consistently provide a valid email address.
 * <p>
 * 11/17/15 - fax is demoted to an extension
 *
 * @see <a href="http://tools.ietf.org/html/rfc6350">IETF RFC 6350: vCard Format
 * Specification</a>
 * @author Jesse Caulfield
 * @since v1.0.0 created 06/19/12
 */
@XmlRootElement(name = "Contact")
@XmlType(name = "Contact")
@XmlAccessorType(XmlAccessType.FIELD)
public class Contact {

  @XmlElement(required = true)
  private String email;
  /**
   * The first name.
   */
  private String nameFirst;
  /**
   * The last name
   */
  private String nameLast;
  /**
   * The business Title or Additional Names, Honorific Prefixes, and Honorific
   * Suffixes. For organizations this may be used as the ATTN field.
   * <p>
   * e.g. Dr.;Jr.,M.D.,A.C.P.
   */
  private String title;

  /**
   * The main or direct telephone number.
   * <p>
   * If country code is omitted (e.g. 10 digits) then 1 (USA) shall be assumed.
   * <p>
   * The persisted value is a simple (undecorated) digit sequence. The digits
   * should be formatted for presentation using a phone number formatter, such
   * as {@code libphonenumber}, for example.
   *
   * @see
   * <a href="https://github.com/googlei18n/libphonenumber">libphonenumber</a>
   */
  private String phone;
  /**
   * The user mobile phone number.
   * <p>
   * If country code is omitted (e.g. 10 digits) then 1 (USA) shall be assumed.
   * <p>
   * This should able to send and receive SMS messages.
   * <p>
   * The persisted value is a simple (undecorated) digit sequence. The digits
   * should be formatted for presentation using a phone number formatter, such
   * as {@code libphonenumber}, for example.
   */
  private String mobile;

  /**
   * The Contact address.
   */
  private Address address;

  /**
   * The organization to which this Contact record is assigned.
   */
  private String organization;

  /**
   * Construct a new empty Contact entity class. A serialized database record ID
   * is automatically assigned.
   */
  public Contact() {
  }

  /**
   * Construct a new empty Contact entity class with the assigned Email address.
   *
   * @param email the contact email address
   */
  public Contact(String email) {
    this.email = email;
  }

  /**
   * Construct a new Contact entity class with the assigned Email address.
   *
   * @param email the contact email address
   * @return a contact record
   */
  public static Contact getInstance(String email) {
    return new Contact(email);
  }

  /**
   * Construct a new empty Contact entity class with the indicated email and
   * first/last name.
   *
   * @param email     (required) the email address
   * @param firstName the first name
   * @param lastName  the last name
   * @return a contact record
   */
  public static Contact getInstance(String email, String firstName, String lastName) {
    Contact c = new Contact(email);
    c.setNameFirst(firstName);
    c.setNameLast(lastName);
    return c;
  }

  // <editor-fold defaultstate="collapsed" desc="Getter and Setter">
  /**
   * Get the first name.
   * <p>
   * For convenience this always returns a non-null string to help with
   * comparison and string-builders.
   *
   * @return a non-null string; empty if the field is not set.
   */
  public String getNameFirst() {
    return nameFirst != null ? nameFirst : "";
  }

  /**
   * Set the first name.
   *
   * @param nameFirst the first name.
   */
  public void setNameFirst(String nameFirst) {
    this.nameFirst = nameFirst;
  }

  /**
   * Get the last name.
   * <p>
   * For convenience this always returns a non-null string to help with
   * comparison and string-builders.
   *
   * @return a non-null string; empty if the field is not set.
   */
  public String getNameLast() {
    return nameLast != null ? nameLast : "";
  }

  /**
   * Set the last name.
   *
   * @param nameLast the last name
   */
  public void setNameLast(String nameLast) {
    this.nameLast = nameLast;
  }

  /**
   * Determine if the FIRST and LAST name fields are configured and not empty;
   *
   * @return TRUE if the FIRST and LAST name fields are configured
   */
  public boolean isSetName() {
    return !getNameFirst().isEmpty() && !getNameLast().isEmpty();
  }

  /**
   * This method will attempt to build a name as follows: First it will try to
   * concatenate the firstName + lastName fields. If either firstName or
   * lastName are null then the organization field is used.
   *
   * @return a String containing the firstName + lastName or organization. NULL
   *         if none are set.
   */
  public String getNameFull() {
    if (isSetName()) {
      return nameFirst + " " + nameLast;
    } else if (!isSetOrganization()) {
      return organization;
    }
    return null;
  }

  /**
   * Set the nameFirst and nameLast fields from a concatenated string value.
   * <p>
   * This method splits the input fullName string on the first available space.
   * <p>
   * This method includes a null check and will ignore null or empty inputs.
   *
   * @param fullName the contact full name. e.g. 'John Doe'. This expects two
   *                 words separated by a space " " character.
   * @throws IllegalArgumentException if the fullName does not contain a space
   *                                  character between two words.
   */
  public void setNameFull(String fullName) throws IllegalArgumentException {
    if (fullName == null || fullName.isEmpty()) {
      return;
    }
    String name = fullName.trim();
    if (name.contains(" ")) {
      /**
       * First try to intercept the pattern of "FIRST M. LAST".
       */
      String[] tokens = fullName.split(" ");
      if (tokens.length == 3) {
        nameFirst = tokens[0];
        nameLast = tokens[2];
      } else {
        /**
         * If the name doesn't parse cleanly then just grab the first string
         * token name as a first name and stuff the balance into the last name
         * field.
         * <p>
         * Make sure the names do not exceed 64 characters.
         */
        int firstSpace = name.indexOf(' ', 0);
        nameFirst = name.substring(0, firstSpace < 64 ? firstSpace : 64);
        nameLast = name.substring(firstSpace, name.length() < 64 ? name.length() : 64).trim();
      }
    } else {
      throw new IllegalArgumentException("\"" + fullName + "\" does not contain a space and cannot be parsed into First + Last parts");
    }
  }

  /**
   * Get the email address
   * <p>
   * Note that even though the syntax of the address may be correct, there is no
   * guarantee that a mailbox of that name exists.
   *
   * @return the email address
   */
  public String getEmail() {
    return email;
  }

  /**
   * Set the email field.
   * <p>
   * Recommend using {@code javax.mail.internet.InternetAddress} to validate
   * that this address conforms to the syntax rules of RFC 822.
   *
   * @param email an email address.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Returns a complete email address formatted as 'first last &lt;email&gt;'
   *
   * @return A non-null formatted email address string
   */
  public String getEmailFormatted() {
    return getNameFull() + "&nbsp;&lt;" + email + "&gt;";
  }

  /**
   * If the email address is set this method will attempt to return the
   * domain-name portion. e.g. the portion of the email address after the
   * '&#64;' character.
   *
   * @return the email domain name portion, null if email is not set or the
   *         email cannot be parsed.
   */
  public String getEmailDomain() {
    if (email != null) {
      try {
        return email.split("@")[1];
      } catch (Exception e) {
      }
    }
    return null;
  }

  /**
   * Get the business Title. For organizations this may be used as the ATTN
   * field.
   *
   * @return the business Title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Set the business Title. For organizations this may be used as the ATTN
   * field.
   *
   * @param title the business Title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Get the business organization (e.g. company)
   *
   * @return The business organization name.
   */
  public String getOrganization() {
    return organization;
  }

  /**
   * Set business organization (e.g. company)
   *
   * @param organization The business organization name.
   */
  public void setOrganization(String organization) {
    this.organization = organization;
  }

  /**
   * Determine if the Organization is configured.
   *
   * @return TRUE if the organization is not null.
   */
  public boolean isSetOrganization() {
    return organization != null;
  }

  /**
   * Get the main phone number.
   *
   * @return The main phone number.
   */
  public String getPhone() {
    return phone;
  }

  /**
   * Set the main phone number.
   *
   * @param phone The main phone number.
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * Get the user mobile phone number.
   *
   * @return The user mobile phone number
   */
  public String getMobile() {
    return mobile;
  }

  /**
   * Set user mobile phone number.
   *
   * @param mobile The user mobile phone number
   */
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  /**
   * Get the contact address record. If no address is set then a new address is
   * created with the country set to UNITED_STATES_OF_AMERICA by default.
   *
   * @return a non-null Address entity class
   */
  public Address getAddress() {
    if (address == null) {
      address = new Address();
    }
    return address;
  }

  /**
   * Set the contact Address
   *
   * @param address the contact mailing or physical address
   */
  public void setAddress(Address address) {
    this.address = address;
  }

  /**
   * Indicator that the address is set (e.g. not null) and configured (i.e.
   * complete).
   *
   * @return TRUE if the address is not null and completely configured.
   */
  public boolean isSetAddress() {
    return address != null && address.isComplete();
  }// </editor-fold>

  /**
   * Simple method to validate the contact configuration. This method inspects
   * and determines if the following fields are set: email, nameFirst, nameLast,
   * phone
   * <p>
   * Note that the address is NOT examined and must be inspected separately.
   *
   * @return TRUE if the contact configuration is complete and valid
   */
  public boolean isComplete() {
    return notEmpty(email)
            && notEmpty(nameFirst)
            && notEmpty(nameLast)
            && notEmpty(phone);
  }

  /**
   * Internal helper method to determine if a string is not null and not empty.
   *
   * @param string the string value
   * @return TRUE if the string is not null AND not empty
   */
  private boolean notEmpty(String string) {
    return string != null && !string.isEmpty();
  }

  /**
   * Hash code and equals are based on EMAIL.
   *
   * @return the object hash code
   */
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 71 * hash + Objects.hashCode(this.email);
    return hash;
  }

  /**
   * Hash code and equals are based on EMAIL.
   *
   * @param obj the other object to compare
   * @return TRUE if equal
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Contact other = (Contact) obj;
    return Objects.equals(this.email, other.getEmail());
  }

  /**
   * Get the full name plus email in a pretty print label.
   *
   * @return a pretty print label formatted as {@code fullName + [email]}
   */
  public String getLabel() {
    return getNameFull() + " [" + email + "]";
  }

  /**
   * Get the contact email address.
   *
   * @return the email field
   */
  @Override
  public String toString() {
    return email;
  }

}

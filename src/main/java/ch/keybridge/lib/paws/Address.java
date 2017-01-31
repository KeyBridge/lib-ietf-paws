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

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.*;

/**
 * <img src="doc-files/address.png">
 * <p>
 * Address is a standardized container for physical (e.g. mailing) street
 * information. This is a simplified implementation similar to the IETF ‘civic
 * street’ concept.
 * <p>
 * A valid street requires at least that the city, state and country are
 * identified.
 * <p>
 * WSIF street attributes and names are chosen to closely match existing US
 * Government databases (e.g. Postal Service, FCC, NOAA, NASA) and to also
 * support international street content requirements.
 * <h2>Validation </h2> The city, state and country attributes are required.
 *
 * @author Jesse Caulfield
 * @since 1.0.0 created 06/06/2012
 */
@XmlRootElement(name = "Address")
@XmlType(name = "Address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * The address street component. This includes the facility number plus street
   * name.
   */
  @XmlElement(required = true)
  private String street;
  /**
   * The city.
   */
  @XmlElement(required = true)
  private String city;
  /**
   * The city's political incorporation.
   */
  private String county;
  /**
   * The state or administrative area.
   */
  @XmlElement(required = true)
  private String state;
  /**
   * The alphanumeric postal code (i.e. for US destinations this is the zip
   * code).
   */
  @XmlElement(required = true)
  private String postalCode;
  /**
   * The 2-character ISO 1366 alpha-2 country code. e.g. "US" for United States.
   * <p>
   * ISO 3166-1 alpha-2 codes are two-letter country codes defined in ISO
   * 3166-1, part of the ISO 3166 standard published by the International
   * Organization for Standardization (ISO), to represent countries, dependent
   * territories, and special areas of geographical interest. They are the most
   * widely used of the country codes published by ISO (the others being alpha-3
   * and numeric), and are used most prominently for the Internet's country code
   * top-level domains (with a few exceptions).[1] They were first included as
   * part of the ISO 3166 standard in its first edition in 1974.
   */
  private String country;

  /**
   * Construct a new Address entity class with a time-based record ID number
   * automatically assigned.
   */
  public Address() {
  }

  /**
   * Construct a new fully qualified street entity.
   *
   * @param street     the street street
   * @param city       the city
   * @param state      the state (or region)
   * @param postalCode the postal code (zip)
   * @param country    the ISO-2 country code
   * @return a Address instance
   */
  public static Address getInstance(String street, String city, String state, String postalCode, String country) {
    Address address = new Address();
    address.setStreet(street);
    address.setCity(city);
    address.setState(state);// set the state - this detects and encodes U.S. States
    address.setPostalCode(postalCode);
    address.setCounty(country);// set the country - this detects and validates an ISO2 country code.
    return address;
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  /**
   * Get the street field.
   *
   * @return the street street
   */
  public String getStreet() {
    return street;
  }

  /**
   * Set the the street field.
   *
   * @param street the street field.
   */
  public void setStreet(String street) {
    this.street = street;
  }

  /**
   * Get the city
   *
   * @return the street city
   */
  public String getCity() {
    return city;
  }

  /**
   * @param city the city
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Get the city's political incorporation.
   *
   * @return the county name
   */
  public String getCounty() {
    return county;
  }

  /**
   * Set the city's political incorporation.
   *
   * @param county the county name
   */
  public void setCounty(String county) {
    this.county = county;
  }

  /**
   * Get the state. If in the United States this will be an enumerated state
   * name (2-character abbreviation)
   *
   * @return the state name.
   */
  public String getState() {
    return state;
  }

  /**
   * Set the state.
   * <p>
   * For US states this method attempts to match either a 2-character
   * abbreviation or a full state name. If a U.S. state is matched then the
   * country is also set to United States..
   *
   * @param state the political state name or abbreviation
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Get the postal code (e.g. U.S. Zip code)
   *
   * @return the postal code
   */
  public String getPostalCode() {
    return postalCode;
  }

  /**
   * Set the postal code (e.g. U.S. Zip code)
   *
   * @param postalCode the postal code
   */
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  /**
   * Set the country.
   * <p>
   * This method tries (very hard) to match the provided country string with an
   * enumerated, known country. Match attempts include by name, partial name,
   * ISO2 and ISO3 codes.
   * <p>
   * Developer note: throws {@code IllegalArgumentException} if the input
   * country is not recognized. If possible use a enumerated {@code ECountry}
   * name.
   *
   * @param country a country ISO2 code
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * Get the country and an enumerated ISO2 country object.
   *
   * @return the country ISO2 code.
   */
  public String getCountry() {
    return country;
  }//</editor-fold>

  /**
   * Perform a quick check to determine if this street record is completely
   * configured or if any fields are missing.
   * <p>
   * This is helpful to determine if this street should be cleaned up or
   * geocoded to build a more complete instance.
   * <p>
   * Address records may be parsed, cleaned up or geocoded to add missing
   * information . For example, the city and state may be inferred if the
   * postalCode and Country are known.
   * <p>
   * An street is not complete if any of its fields are empty: street , city,
   * state, postalCode and country.
   *
   * @return true if the Address record fields are all populated
   */
  public boolean isComplete() {
    return street != null && !street.isEmpty()
            && city != null && !city.isEmpty()
            && state != null && !state.isEmpty()
            && postalCode != null && !postalCode.isEmpty();
  }

  /**
   * @return a hash code of the street, city, state, postalCode and country.
   */
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + Objects.hashCode(this.street);
    hash = 97 * hash + Objects.hashCode(this.city);
    hash = 97 * hash + Objects.hashCode(this.state);
    hash = 97 * hash + Objects.hashCode(this.postalCode);
    return Math.abs(hash);
  }

  /**
   * Compares the object hash codes, which is built from street, city, state,
   * postalCode and country fields.
   *
   * @param object the other object
   * @return TRUE if the street fields match, FALSE if not.
   */
  @Override
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    }
    if (getClass() != object.getClass()) {
      return false;
    }
    return this.hashCode() == object.hashCode();
  }

  /**
   * Get a single line output of this street
   *
   * @return a single like street string
   */
  @Override
  public String toString() {
    return new StringBuilder()
            .append(street != null && !street.isEmpty() && !street.trim().equalsIgnoreCase("null")
                    ? street
                    : "")
            .append(city != null && !city.isEmpty() && !city.trim().equalsIgnoreCase("null")
                    ? " " + city
                    : "")
            .append(state != null && !state.isEmpty() && !state.trim().equalsIgnoreCase("null")
                    ? ", " + state.toUpperCase() + " "
                    : "")
            .append(postalCode != null && !postalCode.isEmpty() && !postalCode.trim().equalsIgnoreCase("null")
                    ? postalCode
                    : "")
            .toString();
  }

}

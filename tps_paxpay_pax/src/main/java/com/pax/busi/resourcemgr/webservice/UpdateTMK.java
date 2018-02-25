package com.pax.busi.resourcemgr.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="req" type="{http://tempuri.org/KMP.xsd}STUPDATETMKREQ"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "req" })
@XmlRootElement(name = "UpdateTMK")
public class UpdateTMK {

	@XmlElement(required = true, nillable = true)
	protected STUPDATETMKREQ req;

	/**
	 * Gets the value of the req property.
	 * 
	 * @return possible object is {@link STUPDATETMKREQ }
	 * 
	 */
	public STUPDATETMKREQ getReq() {
		return req;
	}

	/**
	 * Sets the value of the req property.
	 * 
	 * @param value
	 *            allowed object is {@link STUPDATETMKREQ }
	 * 
	 */
	public void setReq(STUPDATETMKREQ value) {
		this.req = value;
	}

}

package com.pax.busi.resourcemgr.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element name="res" type="{http://tempuri.org/KMP.xsd}STUPDATETMKRES" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "res" })
@XmlRootElement(name = "UpdateTMKResponse")
public class UpdateTMKResponse {

	@XmlElementRef(name = "res", type = JAXBElement.class)
	protected JAXBElement<STUPDATETMKRES> res;

	/**
	 * Gets the value of the res property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}
	 *         {@link STUPDATETMKRES }{@code >}
	 * 
	 */
	public JAXBElement<STUPDATETMKRES> getRes() {
		return res;
	}

	/**
	 * Sets the value of the res property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}
	 *            {@link STUPDATETMKRES }{@code >}
	 * 
	 */
	public void setRes(JAXBElement<STUPDATETMKRES> value) {
		this.res = ((JAXBElement<STUPDATETMKRES>) value);
	}

}

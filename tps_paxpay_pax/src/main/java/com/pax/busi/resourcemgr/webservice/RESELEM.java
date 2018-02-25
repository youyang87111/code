package com.pax.busi.resourcemgr.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for RESELEM complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="RESELEM">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="iResCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="szResInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RESELEM", propOrder = { "iResCode", "szResInfo" })
public class RESELEM {

	protected int iResCode;
	@XmlElement(required = true, nillable = true)
	protected String szResInfo;

	/**
	 * Gets the value of the iResCode property.
	 * 
	 */
	public int getIResCode() {
		return iResCode;
	}

	/**
	 * Sets the value of the iResCode property.
	 * 
	 */
	public void setIResCode(int value) {
		this.iResCode = value;
	}

	/**
	 * Gets the value of the szResInfo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSzResInfo() {
		return szResInfo;
	}

	/**
	 * Sets the value of the szResInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSzResInfo(String value) {
		this.szResInfo = value;
	}

}

package com.pax.busi.resourcemgr.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for REQELEM complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="REQELEM">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="szTid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="szMcr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="szMid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="szSerialNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "REQELEM", propOrder = { "szTid", "szMcr", "szMid",
		"szSerialNo" })
public class REQELEM {

	@XmlElement(required = true, nillable = true)
	protected String szTid;
	@XmlElement(required = true, nillable = true)
	protected String szMcr;
	@XmlElement(required = true, nillable = true)
	protected String szMid;
	@XmlElement(required = true, nillable = true)
	protected String szSerialNo;

	/**
	 * Gets the value of the szTid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSzTid() {
		return szTid;
	}

	/**
	 * Sets the value of the szTid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSzTid(String value) {
		this.szTid = value;
	}

	/**
	 * Gets the value of the szMcr property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSzMcr() {
		return szMcr;
	}

	/**
	 * Sets the value of the szMcr property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSzMcr(String value) {
		this.szMcr = value;
	}

	/**
	 * Gets the value of the szMid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSzMid() {
		return szMid;
	}

	/**
	 * Sets the value of the szMid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSzMid(String value) {
		this.szMid = value;
	}

	/**
	 * Gets the value of the szSerialNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSzSerialNo() {
		return szSerialNo;
	}

	/**
	 * Sets the value of the szSerialNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSzSerialNo(String value) {
		this.szSerialNo = value;
	}

}

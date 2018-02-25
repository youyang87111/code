package com.pax.busi.resourcemgr.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.pax.busi.webservice package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _UpdateTMKResponseRes_QNAME = new QName("",
			"res");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.pax.busi.webservice
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link UpdateTMKResponse }
	 * 
	 */
	public UpdateTMKResponse createUpdateTMKResponse() {
		return new UpdateTMKResponse();
	}

	/**
	 * Create an instance of {@link RESELEM }
	 * 
	 */
	public RESELEM createRESELEM() {
		return new RESELEM();
	}

	/**
	 * Create an instance of {@link REQELEM }
	 * 
	 */
	public REQELEM createREQELEM() {
		return new REQELEM();
	}

	/**
	 * Create an instance of {@link STUPDATETMKREQ }
	 * 
	 */
	public STUPDATETMKREQ createSTUPDATETMKREQ() {
		return new STUPDATETMKREQ();
	}

	/**
	 * Create an instance of {@link UpdateTMK }
	 * 
	 */
	public UpdateTMK createUpdateTMK() {
		return new UpdateTMK();
	}

	/**
	 * Create an instance of {@link STUPDATETMKRES }
	 * 
	 */
	public STUPDATETMKRES createSTUPDATETMKRES() {
		return new STUPDATETMKRES();
	}

	/**
	 * Create an instance of {@link TmkCvt }
	 * 
	 */
	public TmkCvt createTmkCvt() {
		return new TmkCvt();
	}

	/**
	 * Create an instance of {@link STCVTRES }
	 * 
	 */
	public STCVTRES createSTCVTRES() {
		return new STCVTRES();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link STUPDATETMKRES }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "", name = "res", scope = UpdateTMKResponse.class)
	public JAXBElement<STUPDATETMKRES> createUpdateTMKResponseRes(
			STUPDATETMKRES value) {
		return new JAXBElement<STUPDATETMKRES>(_UpdateTMKResponseRes_QNAME,
				STUPDATETMKRES.class, UpdateTMKResponse.class, value);
	}

}

package com.pax.core.util;

import gnu.gettext.GettextResource;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.ResourceBundle;

/*
 * ============================================================================		
 * = COPYRIGHT		
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION		
 *   This software is supplied under the terms of a license agreement or		
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied		
 *   or disclosed except in accordance with the terms in that agreement.		
 *      Copyright (C) 2017-? PAX Technology, Inc. All rights reserved.		
 * Description: // Detail description about the function of this module,		
 *             // interfaces with the other modules, and dependencies. 		
 * Revision History:		
 * Date	                 Author	                  Action
 * 2017/3/7  	         PAX        Create/Add/Modify/Delete
 * ============================================================================		
 */
public class TranslationUtils {
    private ResourceBundle catalog;

    private TranslationUtils() {
    }

    public static TranslationUtils getInstance(Locale locale) {
        if (locale == null) {
            locale = new Locale("zh", "CN");
        }
        TranslationUtils t = new TranslationUtils();
        t.setCatalog(ResourceBundle.getBundle("Messages", locale));
        return t;
    }

    public String __(String msgid) {
        if (StringUtils.isBlank(msgid)) {
            return "";
        }
        
        String value = GettextResource.gettext(catalog, msgid);
        
        if(StringUtils.isNotBlank(value)){
        	return value;
        }else{
        	return "";
        }
    }

    public String _x(String msgid, String msgctxt) {
        return GettextResource.pgettext(catalog, msgctxt, msgid);
    }

    public String _n(String msgid, String msgid_plural, long n) {
        return GettextResource.ngettext(catalog, msgid, msgid_plural, n);
    }

    public String _nx(String msgid, String plural, long n, String context) {
        return GettextResource.npgettext(catalog, context, msgid, plural, n);
    }

    public ResourceBundle getCatalog() {
        return catalog;
    }

    public void setCatalog(ResourceBundle catalog) {
        this.catalog = catalog;
    }
}

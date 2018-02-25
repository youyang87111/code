package com.pax.core.common.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.spi.FilterReply;

public class AuthLogFilter extends LogFilter {
	@Override
	public FilterReply decide(ILoggingEvent event) {
		super.decide(event);
		
		String msg = event.getMessage();
		if (msg.contains("COM.PAX:SQL")) {
			if (msg.contains("com.pax.auth")) {
				return FilterReply.ACCEPT;
			} else {
				return FilterReply.DENY;
			}
		} else {
			return FilterReply.ACCEPT;
		}
	}
}

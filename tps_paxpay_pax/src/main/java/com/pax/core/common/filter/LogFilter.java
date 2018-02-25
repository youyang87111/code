package com.pax.core.common.filter;

import org.apache.commons.lang3.StringUtils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class LogFilter extends Filter<ILoggingEvent> {
	@Override
	public FilterReply decide(ILoggingEvent event) {
		String msg = event.getMessage();
		if (StringUtils.isBlank(msg)) {
			return FilterReply.DENY;
		}
		if (msg.length() > 4096 && msg.indexOf("Caused by") == -1) {
			return FilterReply.DENY;
		} else {
			return FilterReply.ACCEPT;
		}
	}
}

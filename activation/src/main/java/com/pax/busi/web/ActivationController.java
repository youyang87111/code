package com.pax.busi.web;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pax.busi.facade.ActivationReq;
import com.pax.busi.facade.ActivationRsp;
import com.pax.busi.facade.BaseRsp;
import com.pax.busi.facade.ResponseCode;

@RestController
public class ActivationController {

	private static final Logger log = LoggerFactory.getLogger(ActivationController.class);

	
	@RequestMapping("/activation")
	public BaseRsp activation(ActivationReq req) {
		log.info("收到激活请求:"+ToStringBuilder.reflectionToString(req, ToStringStyle.SHORT_PREFIX_STYLE));
		ActivationRsp rsp = new ActivationRsp();
		rsp.setResponse(ResponseCode.SUCCESS);
		return rsp;
	}
}

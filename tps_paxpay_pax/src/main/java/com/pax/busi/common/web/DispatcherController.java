package com.pax.busi.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pax.core.web.BaseAjaxController;

@Controller
@RequestMapping("/dispatcher")
public class DispatcherController extends BaseAjaxController {
	
	@RequestMapping(value = "/selCposMer")
	public String selCposMer() {

		return "busi/resourceMgr/selCposMer";

	}

	@RequestMapping(value = "/selRMer")
	public String selRMer() {

		return "busi/resourceMgr/selRMer";

	}

	@RequestMapping(value = "/selCposMer2")
	public String selCposMer2() {

		return "busi/resourceMgr/selCposMer2";

	}

	@RequestMapping(value = "/selRMer2")
	public String selRMer2() {

		return "busi/resourceMgr/selRMer2";

	}

	@RequestMapping(value = "/selLterm")
	public String selLterm() {

		return "busi/resourceMgr/selLterm";

	}

	@RequestMapping(value = "/selRTerm")
	public String selRTerm() {
		
		return "busi/resourceMgr/selRTerm";
		
	}
	
}

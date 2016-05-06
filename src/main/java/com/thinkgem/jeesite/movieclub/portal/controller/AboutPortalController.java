/*
 * Copyright (C) 2015 SEC BFO, Inc. All Rights Reserved.
 */
package com.thinkgem.jeesite.movieclub.portal.controller;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.movieclub.consumer.entity.Consumer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "${frontPath}/about")
public class AboutPortalController
		extends BaseController
{
	@RequestMapping(value = "/overView", method = RequestMethod.GET)
	public String showOverViewPage(HttpServletRequest request, HttpServletResponse response, Model model) {

		return "modules/about/overView";
	}
}

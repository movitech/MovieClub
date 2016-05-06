/*
 * Copyright (C) 2015 SEC BFO, Inc. All Rights Reserved.
 */
package com.thinkgem.jeesite.test.api;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.test.entity.TestData;
import com.thinkgem.jeesite.test.entity.TestDataMain;
import com.thinkgem.jeesite.test.service.TestDataMainService;
import com.thinkgem.jeesite.test.service.TestDataService;
import com.thinkgem.jeesite.test.vo.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/api/testMobile")
public class TestDataMainMobileController extends BaseController
{

	@Autowired
	private TestDataMainService testDataMainService;

	@Autowired
	private TestDataService testDataService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public TestDataMain get(@RequestParam(required=false) String id) {
		TestDataMain entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testDataMainService.get(id);
		}
		if (entity == null){
			entity = new TestDataMain();
		}
		return entity;
	}

	@ResponseBody
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public List<Content> getContent(@RequestParam(required=false) String id){
		Content content =new Content();
		List<Content> list=new ArrayList<Content>();
		TestData entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testDataService.get(id);
			content.setAndroid_update_id(entity.getAndroidUpdateId());
			content.setVersion_code(entity.getVersionCode());
			content.setVersion_name(entity.getVersionName());
			content.setFile(entity.getFile());

			content.setDate_created(DateUtils.formatDateTime(entity.getDateCreated()));
		}
		list.add(content);
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/demoMovie", method = RequestMethod.GET)
	public String getDemoMovie(@RequestParam(required=false) String id){
		TestData entity = null;
		String returnValue=null;
		if (StringUtils.isNotBlank(id)){
			entity = testDataService.get(id);
			returnValue=entity.getRemarks();
		}
		return returnValue;
	}

}

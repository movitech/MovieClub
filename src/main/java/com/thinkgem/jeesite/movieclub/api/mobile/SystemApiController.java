package com.thinkgem.jeesite.movieclub.api.mobile;


import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.movieclub.vo.SysConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "${mobileApiPath}/sys")
public class SystemApiController {

	@RequestMapping(value = "/config", method = RequestMethod.GET)
	@ResponseBody
	public SysConfig register() {
		SysConfig conf = new SysConfig();
		conf.setMediaServerProtocol(Global.getConfig("media.server.protocol"));
		conf.setMediaServerIP(Global.getConfig("media.server.ip"));
		conf.setMediaServerPort(Global.getConfig("media.server.port"));
		return conf;
	}
}

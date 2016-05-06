package com.thinkgem.jeesite.movieclub.sysconfig.service;


import com.thinkgem.jeesite.movieclub.sysconfig.dao.SysConfDao;
import com.thinkgem.jeesite.movieclub.sysconfig.entity.SysConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

@Service
@Transactional(readOnly = true)
public class SysConfigService {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private SysConfDao sysConfDao;


	private static final Properties properties = new Properties();

	private static Map<String, String> configs;

	public SysConfigService() {
		/**
		 * 加载数据库数据,遍历properties中的数据 没有的初始化到数据库
		 * 将数据写入到一个map里面.
		 */
		configs = loadConfigMap();

		try {
			properties.load(ClassLoader.getSystemResourceAsStream("sys.config.properties"));
		} catch (FileNotFoundException e) {
			logger.error("Can't not load the file 'sys.config.properties' :" + e.getMessage());
		} catch (IOException e) {
			logger.error("IOException when loading  'sys.config.properties'" + e.getCause());
		}
		Iterator it = properties.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			//配置不存在时将配置写入数据库并添加到缓存.
			if (!configs.containsKey(key)) {
				SysConf conf = new SysConf();
				conf.setKey(key);
				conf.setValue(value);
				conf.setDelFlag("0");
				sysConfDao.insert(conf);
				configs.put(key, value);
			}
			configs.put(key, value);
		}
	}

	public Map<String, String> loadConfigMap() {
		Map<String, String> configs = new HashMap<String, String>();
		Map<String, String> regionMap = sysConfDao.loadConfigMap();
		String key = null;
		String value = null;
		for (Map.Entry<String, String> entry : regionMap.entrySet()) {
			if ("key".equals(entry.getKey())) {
				key = entry.getValue();
			} else if ("value".equals(entry.getKey())) {
				value = entry.getValue();
			}
		}
		configs.put(key, value);
		return configs;
	}

	@Transactional(readOnly = false)
	public void update(SysConf sysConf) {
		sysConfDao.update(sysConf);
	}

//	public MailConfig getMailConfig() {
//		MailConfig mailConfig = new MailConfig();
//		mailConfig.setMailServer(configs.get(Const.SysConfigKey.MAIL_SERVER_KEY));
//		mailConfig.setMailUsername(configs.get(Const.SysConfigKey.MAIL_USER_KEY));
//		mailConfig.setPassword(configs.get(Const.SysConfigKey.MAIL_PASSWORD_KEY));
//		return mailConfig;
//	}
}

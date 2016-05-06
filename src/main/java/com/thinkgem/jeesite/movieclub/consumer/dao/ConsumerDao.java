/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.consumer.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.movieclub.consumer.entity.Consumer;

/**
 * consumerDAO接口
 *
 * @author eric.wang
 * @version 2015-10-12
 */
@MyBatisDao
public interface ConsumerDao extends CrudDao<Consumer> {

	/**
	 * 根据邮箱查询用户
	 *
	 * @param param
	 * @return
	 */
	public Consumer getByEmail(Consumer param);

	/**
	 * 根据邮箱和密码查询用户
	 *
	 * @param param
	 * @return
	 */
	public Consumer getByEmailAndPassword(Consumer param);

	public Consumer getByToken(Consumer param);

	public void updateMobileAccessToken(Consumer param);

	public void updatePassword(Consumer param);

	void updateResetPasswordToken(Consumer param);

	void activeRegisterUser(Consumer param);

	public Consumer getByThirdId(Consumer param);
}
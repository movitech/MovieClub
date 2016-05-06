/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.consumer.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.movieclub.consumer.dao.ConsumerDao;
import com.thinkgem.jeesite.movieclub.consumer.entity.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * consumerService
 *
 * @author eric.wang
 * @version 2015-10-12
 */
@Service
@Transactional(readOnly = true)
public class ConsumerService extends CrudService<ConsumerDao, Consumer> {

	@Autowired
	private ConsumerDao consumerDao;

	public Consumer get(String id) {
		return super.get(id);
	}

	public List<Consumer> findList(Consumer consumer) {
		return super.findList(consumer);
	}

	public Page<Consumer> findPage(Page<Consumer> page, Consumer consumer) {
		return super.findPage(page, consumer);
	}

	@Transactional(readOnly = false)
	public void save(Consumer consumer) {
		super.save(consumer);
	}

	@Transactional(readOnly = false)
	public void delete(Consumer consumer) {
		super.delete(consumer);
	}

	public Consumer getByEmail(String email) {
		Consumer param = new Consumer();
		param.setEmail(email);
		Consumer user = consumerDao.getByEmail(param);
		return user;
	}

	public Consumer getByToken(String token,String tokenType) {
		Consumer param = new Consumer();
		if("mt".equalsIgnoreCase(tokenType)){
			param.setMobileAccessToken(token);
		}else if("wt".equalsIgnoreCase(tokenType)){
			param.setWebAccessToken(token);
		}else{
			param.setResetPasswordToken(token);
		}
		Consumer user = consumerDao.getByToken(param);
		return user;
	}

	public Consumer getUserByFacebookId(String id){
		Consumer param = new Consumer();
		param.setFacebookId(id);
		Consumer user = consumerDao.getByThirdId(param);
		return user;
	}
	public Consumer getUserByTwitterId(String id){
		Consumer param = new Consumer();
		param.setTwitterId(id);
		Consumer user = consumerDao.getByThirdId(param);
		return user;
	}
	public Consumer getUserByGoogleId(String id){
		Consumer param = new Consumer();
		param.setGoogleId(id);
		Consumer user = consumerDao.getByThirdId(param);
		return user;
	}

	public Consumer validate(String email, String password) {
		Consumer param = new Consumer();
		param.setEmail(email);
		param.setPassword(password);
		Consumer user = consumerDao.getByEmailAndPassword(param);
		return user;
	}

	@Transactional(readOnly = false)
	public void updateMobileAccessToken(String id, String newToken, Date expireDate) {
		Consumer param = new Consumer();
		param.setId(id);
		param.setMobileTokenExpireDate(expireDate);
		param.setMobileAccessToken(newToken);
		consumerDao.updateMobileAccessToken(param);
	}

	@Transactional(readOnly = false)
	public void updatePassword(String id, String newPassword) {
		Consumer param = new Consumer();
		param.setId(id);
		param.setPassword(newPassword);
		consumerDao.updatePassword(param);
	}

	@Transactional(readOnly = false)
	public void updateResetPasswordToken(String id, String resetPasswordToken, Date expireDate) {
		Consumer param = new Consumer();
		param.setResetPasswordToken(resetPasswordToken);
		param.setResetPasswordTokenExpireDate(expireDate);
		param.setId(id);
		consumerDao.updateResetPasswordToken(param);
	}

	@Transactional(readOnly = false)
	public void activeRegisterUser(String id){
		Consumer param = new Consumer();
		param.setId(id);
		param.setActive(true);
		consumerDao.activeRegisterUser(param);
	}
}
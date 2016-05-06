/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.contact.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.movieclub.contact.entity.Contact;
import com.thinkgem.jeesite.movieclub.contact.dao.ContactDao;

/**
 * ContactService
 * @author eric.wang
 * @version 2015-10-15
 */
@Service
@Transactional(readOnly = true)
public class ContactService extends CrudService<ContactDao, Contact> {

	public Contact get(String id) {
		return super.get(id);
	}
	
	public List<Contact> findList(Contact contact) {
		return super.findList(contact);
	}
	
	public Page<Contact> findPage(Page<Contact> page, Contact contact) {
		return super.findPage(page, contact);
	}
	
	@Transactional(readOnly = false)
	public void save(Contact contact) {
		super.save(contact);
	}
	
	@Transactional(readOnly = false)
	public void delete(Contact contact) {
		super.delete(contact);
	}
	
}
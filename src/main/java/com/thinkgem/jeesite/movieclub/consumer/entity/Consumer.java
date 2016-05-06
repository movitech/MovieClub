/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.movieclub.consumer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * consumerEntity
 *
 * @author eric.wang
 * @version 2015-10-12
 */
public class Consumer extends DataEntity<Consumer> {

	private static final long serialVersionUID = 1L;
	private String email;        // email
	private String firstName;        // first_name
	private String lastName;        // last_name
	private String nickName;       //nick_name
	private String gender;        // gender
	private Date birthday;        // birthday
	private String password;        // password
	private String userRoleId;        // user_role_id
    private String roleName;        // roleName
	private String webAccessToken;        // access_token_web
	private Date webTokenExpireDate;        // token_expire_date_web
	private String mobileAccessToken;        // access_token_mobile
	private Date mobileTokenExpireDate;        // token_expire_date_mobile
	private String resetPasswordToken;        // reset_password_token
	private Date resetPasswordTokenExpireDate;        // reset_password_token_expire_date
	private String thirdAccessToken;        // access_token_third
	private Date thirdTokenExpireDate;        // token_expire_date_third
	private boolean active;        // active
	private String userImage;        // user_image

	private String facebookId;
	private String twitterId;
	private String googleId;

	public Consumer() {
		super();
	}

	public Consumer(String id) {
		super(id);
	}

	@Length(min = 1, max = 200, message = "email长度必须介于 1 和 200 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Length(min = 0, max = 40, message = "first_name长度必须介于 0 和 40 之间")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Length(min = 0, max = 40, message = "last_name长度必须介于 0 和 40 之间")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Length(min = 0, max = 10, message = "gender长度必须介于 0 和 10 之间")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Length(min = 1, max = 100, message = "password长度必须介于 1 和 100 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Length(min = 0, max = 64, message = "user_role_id长度必须介于 0 和 64 之间")
	public String getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

    @Length(min = 0, max = 64, message = "user_role_id长度必须介于 0 和 64 之间")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

	@Length(min = 0, max = 255, message = "access_token长度必须介于 0 和 255 之间")
	public String getWebAccessToken() {
		return webAccessToken;
	}

	public void setWebAccessToken(String accessToken) {
		this.webAccessToken = accessToken;
	}

	@Length(min = 0, max = 255, message = "access_token长度必须介于 0 和 255 之间")
	public String getMobileAccessToken() {
		return mobileAccessToken;
	}

	public void setMobileAccessToken(String accessToken) {
		this.mobileAccessToken = accessToken;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWebTokenExpireDate() {
		return webTokenExpireDate;
	}

	public void setWebTokenExpireDate(Date webTokenExpireDate) {
		this.webTokenExpireDate = webTokenExpireDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMobileTokenExpireDate() {
		return mobileTokenExpireDate;
	}

	public void setMobileTokenExpireDate(Date mobileTokenExpireDate) {
		this.mobileTokenExpireDate = mobileTokenExpireDate;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	@Length(min = 0, max = 255, message = "user_image长度必须介于 0 和 255 之间")
	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getResetPasswordToken()
	{
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken)
	{
		this.resetPasswordToken = resetPasswordToken;
	}

	public Date getResetPasswordTokenExpireDate()
	{
		return resetPasswordTokenExpireDate;
	}

	public void setResetPasswordTokenExpireDate(Date resetPasswordTokenExpireDate)
	{
		this.resetPasswordTokenExpireDate = resetPasswordTokenExpireDate;
	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public String getThirdAccessToken()
	{
		return thirdAccessToken;
	}

	public void setThirdAccessToken(String thirdAccessToken)
	{
		this.thirdAccessToken = thirdAccessToken;
	}

	public Date getThirdTokenExpireDate()
	{
		return thirdTokenExpireDate;
	}

	public void setThirdTokenExpireDate(Date thirdTokenExpireDate)
	{
		this.thirdTokenExpireDate = thirdTokenExpireDate;
	}

	public String getFacebookId()
	{
		return facebookId;
	}

	public void setFacebookId(String facebookId)
	{
		this.facebookId = facebookId;
	}

	public String getTwitterId()
	{
		return twitterId;
	}

	public void setTwitterId(String twitterId)
	{
		this.twitterId = twitterId;
	}

	public String getGoogleId()
	{
		return googleId;
	}

	public void setGoogleId(String googleId)
	{
		this.googleId = googleId;
	}
}
package com.boot.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.boot.common.domain.AbstractEntity;

@Entity
@Table(name = "tb_user")
public class User extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String openID;								//openID
	private String xcxOpenID;							//福星进宝的openID
	private String userIconUrl;							//头像url
	private String nickName;							//昵称
	private Date updateTime;							//更新时间
	private Integer userSex;							//性别1：男2：女
	private String cityName;							//城市名称
	private Integer userType = 0;						//用户类型：0普通用户：1福星  2金牌福星
	private String userMobbile;							//用户手机号
	private Integer userAge;							//年龄
	private String realName;							//姓名
	private String occupation;							//职业
	private String education;							//学历
	private Date birthday;								//生日
	private String customIconUrl;						//自定义头像
	private Date createTime;							//创建时间
	private Integer level;								//等级
	private Integer subscribeState= 0;					//是否关注公众号了  0 未关注   1关注了
	private String wxCenterId;							//微信中心粉丝的ID（支付用）
	private String unionid;								//微信中心unionid
	private String coins;								//去区块联获取值
	private String memberCard;							//一卡易会员卡号
	private	Integer status = 0 ;						// 0正常  1封号处理  
	private	String adminId;	
	private	Integer qklState = 0;							//是否开通了区块链 0未开通  1 开通	
	
	
 
	public Integer getQklState() {
		return qklState;
	}
	public void setQklState(Integer qklState) {
		this.qklState = qklState;
	}
	public String getXcxOpenID() {
		return xcxOpenID;
	}
	public void setXcxOpenID(String xcxOpenID) {
		this.xcxOpenID = xcxOpenID;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getWxCenterId() {
		return wxCenterId;
	}
	public void setWxCenterId(String wxCenterId) {
		this.wxCenterId = wxCenterId;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	
	public Integer getSubscribeState() {
		return subscribeState;
	}
	public void setSubscribeState(Integer subscribeState) {
		this.subscribeState = subscribeState;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	public String getOpenID() {
		return openID;
	}
	public void setOpenID(String openID) {
		this.openID = openID;
	}
	public String getUserIconUrl() {
		return userIconUrl;
	}
	public void setUserIconUrl(String userIconUrl) {
		this.userIconUrl = userIconUrl;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public Integer getUserSex() {
		return userSex;
	}
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getUserMobbile() {
		return userMobbile;
	}
	public void setUserMobbile(String userMobbile) {
		this.userMobbile = userMobbile;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCustomIconUrl() {
		return customIconUrl;
	}
	public void setCustomIconUrl(String customIconUrl) {
		this.customIconUrl = customIconUrl;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Transient
	public String getCoins() {
		return coins;
	}
	public void setCoins(String coins) {
		this.coins = coins;
	}
	public String getMemberCard() {
		return memberCard;
	}
	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}
}

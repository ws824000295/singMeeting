package com.boot.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.boot.common.domain.AbstractEntity;

/**
 * 初始用户：用户名：admin 密码：admin    
 * @author pangqing
 *
 */
@Entity
@Table(name = "admin_user")
public class AdminUser extends AbstractEntity implements UserDetails{
  
	private static final long serialVersionUID = -1309416218452359969L;

	private String username;
	
	private String password;
	
	private Integer type;									// 主社：0  其他副社1
	
	private String wxQrcode;								//公众号二维码

	private String qrcodeBackGround;						//二维码背景图	
	
	private Integer model;									//0--默认样式		1--新样式
   
	private String customerQrcode;							//客服二维码
	
	private String advertImage;								//广告位图片
	
	private String advertLink;								//广告位链接
	
	private String customerName;							//分社名称
	
	private String customerLogo;							//分社logo
	
	private String servicePhone;							//客服电话
	
	private String fufenBanner;								//我的-福粉banner
	
	private String email;									//商户收件箱
	
	private String walletName;								//钱包名
	
	private String serverName;								//分社微信中心的地址
	
	private String manageServerName;						//后台管理系统的地址
	
	private String partnerName;   							//分社合作伙伴名字
	
	private String partnerRegion;   						//分社合作区域
	
	private Date partnerTimeBegin; 							//分社合作时间开始
	
	private Date partnerTimeEnd;  							//分社合作时间结束

	private String hkKey;  									//惠客的key
	
	private Integer wxCenterState = 0 ;  					//是否有开放平台 0没有  1有   没有的话进平台用openid+adminid 查用户  否则用 unniod+adminId

	private String agentNo ;  								// 代理号
	
	private String signName = "福星社";					    //短信签名

	private String fsName ;					    			//童星  福星
	
	public String getFsName() {
		return fsName;
	}

	public void setFsName(String fsName) {
		this.fsName = fsName;
	}

	private Date createTime = new Date();		 			 
	
	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getManageServerName() {
		return manageServerName;
	}

	public void setManageServerName(String manageServerName) {
		this.manageServerName = manageServerName;
	}

	public Integer getWxCenterState() {
		return wxCenterState;
	}

	public void setWxCenterState(Integer wxCenterState) {
		this.wxCenterState = wxCenterState;
	}

	public String getHkKey() {
		return hkKey;
	}

	public void setHkKey(String hkKey) {
		this.hkKey = hkKey;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getWalletName() {
		return walletName;
	}

	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}

	public String getQrcodeBackGround() {
		return qrcodeBackGround;
	}

	public void setQrcodeBackGround(String qrcodeBackGround) {
		this.qrcodeBackGround = qrcodeBackGround;
	}

	public String getWxQrcode() {
		return wxQrcode;
	}

	public void setWxQrcode(String wxQrcode) {
		this.wxQrcode = wxQrcode;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getModel() {
		return model;
	}

	public void setModel(Integer model) {
		this.model = model;
	}
	
	public String getCustomerQrcode() {
		return customerQrcode;
	}

	public void setCustomerQrcode(String customerQrcode) {
		this.customerQrcode = customerQrcode;
	}

	public String getAdvertImage() {
		return advertImage;
	}

	public void setAdvertImage(String advertImage) {
		this.advertImage = advertImage;
	}

	public String getAdvertLink() {
		return advertLink;
	}

	public void setAdvertLink(String advertLink) {
		this.advertLink = advertLink;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerLogo() {
		return customerLogo;
	}

	public void setCustomerLogo(String customerLogo) {
		this.customerLogo = customerLogo;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}
	
	public String getFufenBanner() {
		return fufenBanner;
	}

	public void setFufenBanner(String fufenBanner) {
		this.fufenBanner = fufenBanner;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        //在这里可以根据用户的type区分用户角色
       // authorities.add(new SimpleGrantedAuthority(user.isAdmin() ? "ROLE_ADMIN" : "ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPartnerRegion() {
		return partnerRegion;
	}

	public void setPartnerRegion(String partnerRegion) {
		this.partnerRegion = partnerRegion;
	}

	public Date getPartnerTimeBegin() {
		return partnerTimeBegin;
	}

	public void setPartnerTimeBegin(Date partnerTimeBegin) {
		this.partnerTimeBegin = partnerTimeBegin;
	}

	public Date getPartnerTimeEnd() {
		return partnerTimeEnd;
	}

	public void setPartnerTimeEnd(Date partnerTimeEnd) {
		this.partnerTimeEnd = partnerTimeEnd;
	}


	
}

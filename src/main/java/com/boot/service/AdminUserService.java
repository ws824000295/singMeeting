package com.boot.service;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.common.service.AbstractService;
import com.boot.config.Constants;
import com.boot.domain.AdminUser;
import com.boot.repository.AdminUserRepository;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

@Service
@Transactional(readOnly = true)
public class AdminUserService extends AbstractService {

	@Autowired
	private AdminUserRepository adminUserRepository;

	@Transactional(rollbackFor = Exception.class)
	public AdminUser updatePassword(String id, String password) {
		AdminUser user = adminUserRepository.findOne(id);
		user.setPassword(password);
		adminUserRepository.save(user);
		return user;
	}

	public AdminUser findAdminById(String id) {
		AdminUser user = adminUserRepository.findOne(id);
		return user;
	}
	public AdminUser findAdminByUserName(String userName) {
		AdminUser user = adminUserRepository.findByUserName(userName);
		return user;
	}

	/**
	 * 
	 * @param wxQrcode				公众号二维码
	 * @param customerQrcode		客服二维码
	 * @param advertImage			广告位图片
	 * @param advertLink			广告位链接
	 * @param customerName			分社名称
	 * @param servicePhone			客服电话
	 * @param email					邮箱
	 * @param fufenBanner			福粉banner
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateAdminUser(String id, String wxQrcode, String customerQrcode,
			String advertImage, String advertLink, String customerName,String servicePhone, String email, String fufenBanner) {
		
		AdminUser user = adminUserRepository.findOne(id);
		user.setWxQrcode(wxQrcode);
		user.setCustomerQrcode(customerQrcode);
		user.setAdvertImage(advertImage);
		user.setAdvertLink(advertLink);
		user.setCustomerName(customerName);
		user.setServicePhone(servicePhone);
		user.setEmail(email);
		user.setFufenBanner(fufenBanner);
		adminUserRepository.save(user);
	}
	/**@date   2018-07-20
	 * @author huyuhang
	 * 
	 * @description 根据分社合作伙伴名字 查询分社信息
	 */
	public Map<String, Object> findAdminByParentName(String parentName) {
		List<Map<String, Object>> adminUserList= adminUserRepository.findAdminByParentName(parentName);
		Map<String,Object>map=null;
		System.out.println(adminUserList.toString());
		if(adminUserList!=null && adminUserList.size()!=0){
		        map=adminUserList.get(0);	  
		}
		return map;
	}
	
	
	public List<AdminUser> findAll(){
		return adminUserRepository.findAll();
	}

	public Integer findCount() {
		return adminUserRepository.findCount();
	}

	@Transactional(rollbackFor = Exception.class)
	public String saveNewAdminUser(AdminUser adminUser) {
		AdminUser user = adminUserRepository.save(adminUser);
		return user.getId();
		
	}
	/**@date   2018-12-20
	 * @author huyuhang
	 * 判断该分社是那种支付方式
	 * 
	 *        福星社(也就是总社)                          //e宝支付    
	 *        还是单开福星社分社(也就是川川地球,儿童教育汇，瑜伽团)  //微信支付
	 *        
	 *        还是惠客单独用的分社(和福星社没有关系)  //e宝支付
	 *           
	 * @description 根据分社合作伙伴名字 查询分社信息
	 */
	public String checkPayType(String adminId) {		
		String hkAdminId=Constants.HK_ADMIN_ID;
		if(adminId.equals(hkAdminId)){
			return "yopPay";			
		}
		List<String> fulishareEbAdminId=Arrays.asList(Constants.FULISHARE_EB_ADMIN_ID);//福星社 e宝 支付的分社id
		if(fulishareEbAdminId.contains(adminId)){
			return "yopPay";
		}	
		List<String> fulishareWxAdminId=Arrays.asList(Constants.FULISHARE_WX_ADMIN_ID);//福星社 微信 支付的分社id		 
		if(fulishareWxAdminId.contains(adminId)){
			return "wxPay";
		}
		return "";
	}
}

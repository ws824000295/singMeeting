package com.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.boot.domain.Device;

public interface DeviceRepository extends JpaRepository<Device, String>, JpaSpecificationExecutor<Device>{
 
	
	/** 
	* @Title: queryDeviceByState 
	* @Description: 查询未使用的设备
	* @param @param state
	* @param @return设定文件 
	* @author 王朔
	* @return List<Device>返回类型 
	* @throws 
	*/
	@Query(value = " from Device where 1 = 1 ")
	List<Device> queryDevice();

	/** 
	* @Title: queryDeviceByIds 
	* @Description: 根据设备ids查询多个设备
	* @param @param ids
	* @param @return设定文件 
	* @author 王朔
	* @return List<Device>返回类型 
	* @throws 
	*/
	@Query(value = " from Device where id in ?1 ")
	List<Device> queryDeviceByIds(List<String> ids);

	/** 
	* @Title: updateStateById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param deviceId设定文件 
	* @author 王朔
	* @return void返回类型 
	* @throws 
	*/
	@Modifying
	@Query(value = " update tb_device set state = '0' where id = ?1 ", nativeQuery = true)
	void updateStateById(String deviceId);
	
}

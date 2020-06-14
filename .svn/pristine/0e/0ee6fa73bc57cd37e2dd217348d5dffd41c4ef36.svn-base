package com.boot.web.manage.meeting;

import java.io.IOException;

import com.boot.common.util.Server;



/**  
 * @ClassName: InitSocketControl
 * @Description: 初始化 socket
 * @author 王朔
 * @date 2019年12月23日 
 */
public class InitSocketControl  implements Runnable  {

	/**   
	* 此方法描述的是：   
	* @author: wangs   
	* @version: 2020年1月10日 下午2:39:13   
	*/   
	@Override
	public void run() {
		try {
			Server.scoketServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}

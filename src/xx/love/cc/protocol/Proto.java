package xx.love.cc.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuehangyu   
 * @Description 
 * @date 2015-5-4下午5:28:05  
 */
public class Proto {
	public static final Map<Short, Class> map = new HashMap<Short, Class>();
	
	/**
	 * 根据协议号获取对应的协议信息
	 * @param code
	 * @return
	 */
	public static Class getProto(short code){
		if(map.containsKey(code)){
			return map.get(code);
		}
		return null;
	}
	
	//初始化
	static{
		map.put(Protocol.TEST_1, Proto.class);
	}
	
}

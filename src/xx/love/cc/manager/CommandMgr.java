package xx.love.cc.manager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xx.love.cc.BaseServer;
import xx.love.cc.ClassUtil;
import xx.love.cc.Log;
import xx.love.cc.common.Cmd;
import xx.love.cc.common.Command;

public class CommandMgr {
	private static final Map<Short, Command> cmdCache = new HashMap<Short, Command>();

	public static boolean init() {
		if (!check()) {
			return false;
		}
		if (!load()) {
			return false;
		}
		return true;
	}

	/**
	 * 检查协议的合法性
	 * 
	 * @return
	 */
	private static boolean check() {
		try {
			Class<?> cla = Class.forName("xx.love.cc.protocol.Protocol");
			List<Short> codes = new ArrayList<Short>();
			Field[] fields = cla.getFields();//获得所有属性
			for (Field field : fields) {
				short code = field.getShort(field.getType());
				if (codes.contains(code)) {
					Log.error("重复的协议号:0x" + Integer.toHexString(code));
					return false;
				}
				codes.add(code);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 加载协议命令到缓存
	 * 
	 * @return
	 */
	private static boolean load() {
		Package pack = BaseServer.class.getPackage();
		//获得pack下所有类的Class对象
		List<Class<?>> clazzList = ClassUtil.getClasses(pack);
		
		for (Class<?> clazz : clazzList) {
			Cmd cmd = clazz.getAnnotation(Cmd.class);
			if (cmd != null) {
				if (cmdCache.get(cmd.code()) != null) {
					return false;
				}
				try {
					cmdCache.put(cmd.code(), (Command) clazz.newInstance());
				} catch (InstantiationException e) {
					e.printStackTrace();
					return false;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					return false;
				}
				continue;
			}
		}
		Log.info("协议加载完成：cmdCache.size = " + cmdCache.size());
		return true;
	}

	public static Map<Short, Command> getCmdcache() {
		return cmdCache;
	}

	
}

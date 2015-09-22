package xx.love.cc.mina;

import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import xx.love.cc.Log;
import xx.love.cc.common.Command;
import xx.love.cc.manager.CommandMgr;

public class ServerIoHandler extends IoHandlerAdapter {

	@Override
	public void messageReceived(IoSession session, Object message) {
		try{
			String str = message.toString();
			Log.info("接收到的内容：" + str);
			//此处检测消息体
			short code = Short.parseShort(str);
			Map<Short, Command> Cmdcache = CommandMgr.getCmdcache();
			if(Cmdcache.containsKey(code)){
				Cmdcache.get(code).execute();
				return;
			}
			Log.error("不存在的协议号:0x" + Integer.toHexString(code));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
}

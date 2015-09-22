package xx.love.cc.test;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * 
 * 客户端 iohandler
 * 
 * @author liudd
 * 
 * 
 */

public class MyClientIoHandler extends IoHandlerAdapter {

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("我连上了");
	}
	
	
	
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println(message.toString());
	}

	
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("我退出了");
		session.close(true);
	}
	
}
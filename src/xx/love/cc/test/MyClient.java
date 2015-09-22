package xx.love.cc.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;


/**
 * 
 * mina框架中客户端与服务器端的执行流程一致，
 * 
 * 唯一的区别是:
 * 
 * ioservice的client实现是ioconnetor,server端是ioacceptor
 * 
 * @author liudd
 * 
 * 
 */

public class MyClient {

	public static void main(String[] args) {

		// 1.创建ioservice

		SocketConnector connector = new NioSocketConnector();

		connector.setConnectTimeoutMillis(30000);

		// 2.注册过滤器

		connector.getFilterChain().addLast(

		"codec",

		new ProtocolCodecFilter(new TextLineCodecFactory(Charset

		.forName("UTF-8"), LineDelimiter.WINDOWS.getValue(),

		LineDelimiter.WINDOWS.getValue())));

		// 3注册iohandler,到ioserivce

		connector.setHandler(new MyClientIoHandler());

		// 4.绑定套接字,建立连接
		//返回XXXFuture的方法都是异步的，所以下面等待成功后才能取得session
		ConnectFuture cf = connector.connect(new InetSocketAddress("localhost", 3000));
		
		//等待连接成功  
		cf.awaitUninterruptibly();

		IoSession session = cf.getSession();
		while(session.isConnected()){
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
				String str = br.readLine();
				session.write(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}

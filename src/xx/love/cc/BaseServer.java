package xx.love.cc;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.log4j.PropertyConfigurator;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import xx.love.cc.common.ServerType;
import xx.love.cc.manager.CommandMgr;
import xx.love.cc.mina.MessageProtocolCodecFactory;
import xx.love.cc.mina.ServerIoHandler;

public class BaseServer {

	protected static short serverType;//服务器类型
	protected static String configPath;//入口配置文件路径，通过启动参数赋值的好处是可以随意修改配置文件的地址
	
	/**
	 * 初始化
	 */
	protected boolean init(){
		if(!initComponent(initConfigs(), "配置文件")){
			return false;
		}
		PropertyConfigurator.configure(Config.getValue("log4j.path"));
		Log.info("日志准备就绪……");
		if(!initComponent(initCommand(), "命令集合")){
			return false;
		}
		return true;
	}
	
	/**
	 * 初始化相关模块
	 */
	protected boolean initComponent(boolean initResult, String componentName){
		if(!initResult){
			System.out.println("初始化" + componentName + "失败！");
		}
		return initResult;
	}
	
	/**
	 * 初始化配置文件
	 * @param path 
	 */
	protected boolean initConfigs(){
		return Config.init(configPath);
	}
	
	/**
	 * 初始化命令集合
	 */
	protected boolean initCommand(){
		return CommandMgr.init();
	}
	
	/**
	 * 初始化mina
	 */
	protected boolean initMina(int port){
		try {
			// 1.监听器
			SocketAcceptor acceptor = new NioSocketAcceptor();
			// 2.编写过滤器,
			// 自定义的消息编解码工厂，内部封装的有自定义的编解码器
			acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MessageProtocolCodecFactory()));
			
			SocketSessionConfig config = acceptor.getSessionConfig();
			config.setSendBufferSize(2048);
			config.setReadBufferSize(2048);
			config.setIdleTime(IdleStatus.BOTH_IDLE, 10);// 读写通道在10秒内无任何操作进入空闲状态
			// 3.注册iohandler到acceptor
			acceptor.setHandler(new ServerIoHandler());
			// 4绑定套接字
			acceptor.bind(new InetSocketAddress(port));
//			System.out.println(port);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	/**
	 * 启动
	 */
	protected void start(){
		long start = TimeUtil.getSysteCurTimeForMill();
		StringBuilder msg = new StringBuilder();
		switch(serverType){
		case ServerType.GAME_SERVER:
			msg.append("GameServer");
			break;
		case ServerType.CHAT_SERVER:
			msg.append("ChatServer");
			break;
		case ServerType.BATTLE_SERVER:
			msg.append("BattleServer");
			break;
		}
		if(!init()){
			System.out.println(msg + "服务器初始化失败……");
			return;
		}
		long end = TimeUtil.getSysteCurTimeForMill();
		Log.info(msg + "服务器启动成功");
		Log.info("启动耗时：" + (end - start) + "毫秒");
	}
	
	/**
	 * 关闭
	 */
	protected void stop(){
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BaseServer().start();
	}

}

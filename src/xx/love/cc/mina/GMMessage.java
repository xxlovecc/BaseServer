package xx.love.cc.mina;

import com.google.protobuf.Message;

/**
 * 自定义消息封装体，服务器与客户端，以及服务器与服务器之间传递的消息封装体
 * 在网络编程中使用 protobuf 需要解决两个问题：
 * 长度，protobuf 打包的数据没有自带长度信息或终结符，需要由应用程序自己在发生和接收的时候做正确的切分；
 * 类型，protobuf 打包的数据没有自带类型信息，需要由发送方把类型信息传给给接收方，接收方创建具体的Protobuf Message对象，再做的反序列化。
 *
 * @author xuehangyu
 */
public class GMMessage {

	public static final int HEAD_LENGTH = 1024;
	
	private int length;//消息体长度
	
	private byte[] body;//消息体

	private Message message;//消息体
	
	
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	
}

package xx.love.cc.mina;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import xx.love.cc.Log;

import com.google.protobuf.Message;

/**
 * 编码器
 * @author xuehangyu
 *
 */
public class MessageEncoder implements ProtocolEncoder {

	private Charset charset;
	
	public MessageEncoder(Charset charset){
		this.charset = charset;
	}
	
	
	
	@Override
	public void dispose(IoSession session) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		IoBuffer buffer = null;
		if(message != null && message instanceof GMMessage){
			GMMessage gmsg = (GMMessage) message;
			Message msg = gmsg.getMessage();
			
			
			if (buffer == null) {// 丢弃这个数据包
				return;
			}

			buffer.flip();
			out.write(buffer);
		}
	}

}

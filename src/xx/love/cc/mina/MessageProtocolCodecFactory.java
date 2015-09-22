package xx.love.cc.mina;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * 自定义消息编解码器工厂
 * @author xuehangyu
 */
public class MessageProtocolCodecFactory implements ProtocolCodecFactory {

	private ProtocolEncoder encoder;//编码器
    private ProtocolDecoder decoder;//解码器
    
	public MessageProtocolCodecFactory() {
		this(Charset.forName("UTF-8"));
	}

	public MessageProtocolCodecFactory(Charset charset) {
		encoder = new MessageEncoder(charset);
		decoder = new MessageDecoder(charset);
	}
	
	@Override
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return encoder;
	}

}

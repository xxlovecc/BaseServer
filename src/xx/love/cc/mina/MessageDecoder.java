package xx.love.cc.mina;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * 解码器
 * @author xuehangyu
 *
 */
public class MessageDecoder implements ProtocolDecoder {

	private Charset charset;
	
	public MessageDecoder(Charset charset){
		this.charset = charset;
	}
	
	
	@Override
	public void decode(IoSession arg0, IoBuffer arg1, ProtocolDecoderOutput arg2)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1)
			throws Exception {
		// TODO Auto-generated method stub

	}

}

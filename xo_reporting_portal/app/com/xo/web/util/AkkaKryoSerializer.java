package com.xo.web.util;

import java.io.ByteArrayOutputStream;

import play.Logger;
import play.api.Play;
import akka.serialization.JSerializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.xo.web.viewdtos.MessageDto;

public class AkkaKryoSerializer extends JSerializer {

	protected final Kryo KRYO = new Kryo();

	public AkkaKryoSerializer() {
		super();
		KRYO.setClassLoader(Play.current().classloader());
	}

	private final byte[] serialize(Object object) {
		byte[] byteObjects = null;
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(65536);
	    	Output output = new Output(byteArrayOutputStream);
	        KRYO.writeObject(output, object);
	        byteObjects = output.toBytes();
		} catch(Exception e) {
			Logger.error("Error while serializing the object.", e);
		}
		return byteObjects;
    }

	private final Object deserialize(byte[] bytes, Class<?> clazz) {
		try {
			if(bytes != null && bytes.length > 0 && clazz != null) {
				return KRYO.readObject(new Input(bytes), clazz);
			}
		} catch(Exception e) {
			Logger.error("Error while deserializing the bytes.", e);
		}
		return null;
    }

	@Override
	public int identifier() {
		return 11111;
	}

	@Override
	public boolean includeManifest() {
		return false;
	}

	@Override
	public byte[] toBinary(Object arg0) {
		return this.serialize(arg0);
	}

	@Override
	public Object fromBinaryJava(byte[] arg0, Class<?> arg1) {
		return this.deserialize(arg0, arg1 == null ? MessageDto.class : arg1);
	}
}

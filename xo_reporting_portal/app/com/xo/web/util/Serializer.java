package com.xo.web.util;

import java.io.ByteArrayOutputStream;

import play.Logger;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.xo.web.core.XOException;

public class Serializer<T> {

	protected final Kryo KRYO = new Kryo();
	private Class<T> persistentClass;

    public Serializer(Class<T> persistentClass) {
    	this.persistentClass = persistentClass;
	}

	public byte[] serialize(T object) throws XOException {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(65536);
	    	Output output = new Output(byteArrayOutputStream);
	        KRYO.writeObject(output, object);
	        return output.toBytes();
		} catch(Exception e) {
			Logger.error("Error while serializing the object.", e);
			throw new XOException("Error while serializing the object.", e);
		}
    }

	public T deserialize(byte[] bytes) throws XOException {
		try {
			if(bytes != null && bytes.length > 0 && this.persistentClass != null) {
				return (T) KRYO.readObject(new Input(bytes), this.persistentClass);
			}
		} catch(Exception e) {
			Logger.error("Error while deserializing the bytes.", e);
			throw new XOException("Error while serializing the object.", e);
		}
		return null;
    }
}

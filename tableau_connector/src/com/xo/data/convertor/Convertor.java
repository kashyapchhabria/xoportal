package com.xo.data.convertor;

import java.io.InputStream;
import java.io.Reader;

public interface Convertor<T> {

	T convert(InputStream dataToBeConverted, T clazz);

	T convert(Reader dataToBeConverted, T clazz);

	String getConvertorType();
	
}

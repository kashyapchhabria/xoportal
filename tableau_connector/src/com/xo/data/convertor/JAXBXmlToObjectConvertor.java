package com.xo.data.convertor;

import java.io.InputStream;
import java.io.Reader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@SuppressWarnings("unchecked")
public class JAXBXmlToObjectConvertor<T> implements Convertor<T> {

	@Override
	public T convert(final Reader xmlReader, final T clazz) {
		T object = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz.getClass());
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			object = (T) jaxbUnmarshaller.unmarshal(xmlReader);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public String getConvertorType() {
		return "Xml To Object convertor";
	}
	
	@Override
	public T convert(final InputStream dataToBeConverted, final T clazz) {
		T object = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz.getClass());
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			object = (T) jaxbUnmarshaller.unmarshal(dataToBeConverted);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

}

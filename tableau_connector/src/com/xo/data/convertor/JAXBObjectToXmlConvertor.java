package com.xo.data.convertor;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JAXBObjectToXmlConvertor<T> {

	public String convert(final T instance) {
		StringWriter stringWriter = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(instance.getClass());
			Marshaller jaxbUnmarshaller = jaxbContext.createMarshaller();
			stringWriter = new StringWriter();
			jaxbUnmarshaller.marshal(instance, stringWriter);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringWriter.getBuffer().toString();
	}

	public String getConvertorType() {
		return "Object to Xml convertor";
	}

}

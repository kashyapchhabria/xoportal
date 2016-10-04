package com.xo.web.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.xo.web.core.XOException;

import play.Logger;
import play.i18n.Lang;
import play.mvc.Http.Context;

public class XoMailContentProvider {

	private static final String SYMPOL_HYPHEN = "_";
	private static final String TEMPLATE_METHOD_RENDER = "render";
	private static final String FALLBACK_LANGUAGE = "en";

	public String getLangCode() {
		Lang lang = null; 
		try {
			lang = Lang.preferred(Context.current().request().acceptLanguages());
		} catch(RuntimeException e) {
			Logger.error(e.getMessage());
		}
		return lang != null ? lang.code() : FALLBACK_LANGUAGE;
	}

	public Method getEmailTemplateRenderer(final String template, Class<?>...methodArgs) throws XOException{
		Class<?> cls = null;
		Method htmlRender = null;
		String langCode = getLangCode();
		StringBuilder errorMsg = new StringBuilder();
		try {
			cls = Class.forName(template + SYMPOL_HYPHEN + langCode);
		} catch (ClassNotFoundException e) {
			errorMsg.append("Template: '");
			errorMsg.append(template);
			errorMsg.append(SYMPOL_HYPHEN);
			errorMsg.append(langCode);
			errorMsg.append("' was not found! Trying to use English fallback template instead.");
			Logger.warn(errorMsg.toString());
		}
		if (cls == null) {
			try {
				cls = Class.forName(template + SYMPOL_HYPHEN + FALLBACK_LANGUAGE);
			} catch (ClassNotFoundException e) {
				errorMsg.append("Fallback template: '");
				errorMsg.append(template);
				errorMsg.append(SYMPOL_HYPHEN);
				errorMsg.append(FALLBACK_LANGUAGE);
				errorMsg.append("' was not found either!");
				Logger.warn(errorMsg.toString());
			}
		}
		if (cls != null) {
			try {
				if(XoUtil.hasData(methodArgs)) {
					htmlRender = cls.getMethod(TEMPLATE_METHOD_RENDER, methodArgs);
				} else {
					htmlRender = cls.getMethod(TEMPLATE_METHOD_RENDER);
				}
			} catch (NoSuchMethodException e) {
				throw new XOException(e);
			}
		}
		return htmlRender;
	}

	public String getEmailTemplate(Method htmlRender, Object...args) throws XOException {
		String ret = null;
		if (htmlRender != null) {
			try {
				ret = htmlRender.invoke(null, args).toString();
			} catch (IllegalAccessException e) {
				throw new XOException(e);
			} catch (InvocationTargetException e) {
				throw new XOException(e);
			}
		}
		return ret;
	}
	
}

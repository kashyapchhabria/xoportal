package com.xo.web.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import play.Logger;
import play.Play;
import play.api.Plugin;
import play.mvc.Http.Context;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feth.play.module.mail.Mailer.Mail.Body;
import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.persistence.XODataSourcePlugin;

public class XoUtil implements XoAppConstant {

	public static final boolean hasData(Collection<?>... collections) {
		boolean status = false;
		for(Collection<?> collection : collections) {
			if(collection == null || collection.size() == 0) {
				status = false;
				break;
			}
			status = true;
		}
		return status;
	}

	public static final boolean hasData(Collection<?> collection) {
		return collection != null && collection.size() > 0;
	}
	
	public static final boolean hasData(List<?> collection) {
		return collection != null && collection.size() > 0;
	}
	
	public static final boolean isNotNull(Object... objects) {
		boolean status = true;
		for(Object tempObject : objects) {
			if(tempObject == null) {
				status = false;
				break;
			} else if(tempObject instanceof CharSequence && tempObject.toString().trim().length() == 0 ) {
				status = false;
				break;
			}
		}
		return status;
	}

	public static final boolean hasData(Map<?, ?> map) {
		return map != null && map.size() > 0;
	}

	public static final String getConfig(String configKey) {
		return Play.application().configuration().getString(configKey);
	}
	
	public static final List<?> getConfigsAsList(String configKey) {
		return Play.application().configuration().getList(configKey);
	}
	
	public static final boolean getBoolConfig(String configKey) {
		return Play.application().configuration().getBoolean(configKey);
	}
	
	public static final int getIntConfig(String configKey) {
		return Play.application().configuration().getInt(configKey);
	}
	
	public static final boolean hasData(Object[] dataHolder) {
		return dataHolder != null && dataHolder.length > 0;
	}
	
	public static final Plugin getPluginByClass(Class<? extends Plugin> pluginClazz) {
		Plugin pluginObject = null;
		if(pluginClazz == XODataSourcePlugin.class) {
			pluginObject = Play.application().plugin(XODataSourcePlugin.class);
		}
		return pluginObject;
	}

	/*public static final Plugin getPluginByClass(String configName) {
		Plugin pluginObject = null;
			return pluginObject;
	}*/

	public static final boolean isValidJSON(final String json) {
		boolean valid = false;
		try {
			final JsonParser parser = new ObjectMapper().getFactory().createParser(json);
			/*while (parser.nextToken() != null) {
			}*/
			valid = parser.nextToken() != null;
		} catch (JsonParseException jpe) {
			jpe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return valid;
	}

	public static final boolean jsonValidate(JsonNode json, String jsonNodeName)throws XODAOException{
		JsonNode selectedAns=json.findPath(jsonNodeName);
		return !selectedAns.isNull();
	}

	public static final boolean jsonValidate(JsonNode json, String...jsonNodeNames)throws XODAOException{
		boolean validationStatus = false;
		if(XoUtil.hasData(jsonNodeNames)) {
			validationStatus = true;
			for(String jsonNodeName : jsonNodeNames) {
				JsonNode selectedAns=json.findPath(jsonNodeName);
				if(selectedAns.isNull()) {
					validationStatus = false;
					break;
				}
			}
		}
		return validationStatus;
	}

	public static final String formatDate(final Date date) {
		return formatDate(date, DATE_FORMAT_DD_MM_YYYY);
	}

	public static final String formatDate(final Date date, String dateFormat) {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(dateFormat);
        String dateString = DATE_FORMAT.format(date);
        return dateString;
	}

	public static final Date convertToDate(String dateStr) {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY);
        Date convertedDate = null;
		try {
			convertedDate = DATE_FORMAT.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return convertedDate != null ? convertedDate : new Date();
	}

	public static void sendMail(final String content, final String subject, final String receiptient) throws XOException {
		try{
			final Body body = new Body("", content);
			XO_MAILER.sendMail(subject, body, receiptient);
		} catch(Exception e) {
			Logger.error("Error while sending mail.", e);
			throw new XOException("Mail sending error.", e);
		}
	}

	public static final String getApplicationEndUser() {
		String appContext = null;
		try {
			appContext = getConfig(XoAppConfigKeys.XO_END_USER);
		} catch(Exception e) {
			Logger.error("Error while sending mail.", e);
		}
		return isNotNull(appContext) ? appContext : "";
	}

	public static final String getApplicationContext() {
		String appContext = null;
		try {
			appContext = getConfig(XoAppConfigKeys.APPLICATION_CONTEXT);
		} catch(Exception e) {
			Logger.error("Error while sending mail.", e);
		}
		return isNotNull(appContext) ? appContext : "";
	}

	/**
	 * Convert the current request maker's ip to hexadecimal.
	 * @return
	 */
	public static final String ipToHex() {
		return ipToHex(getAndSetRemoteAddress());
	}

	public static final String getAndSetRemoteAddress() {
		String requestingHostAddress = "";
		InetAddress requestHostMachine = null;
		try{
			String actualHost = Context.current().request().getHeader(HEADER_X_FORWARDED_FOR);
			requestingHostAddress = Context.current().request().remoteAddress();
			if(!XoUtil.isNotNull(actualHost)) {
				requestHostMachine = requestingHostAddress.equalsIgnoreCase("127.0.0.1") || requestingHostAddress.equalsIgnoreCase("0:0:0:0:0:0:0:1") ? 
						InetAddress.getLocalHost() : InetAddress.getByAddress(requestingHostAddress.getBytes());
			} else {
				String forwardedHosts[] = actualHost.split(",");
				if(forwardedHosts.length > 0) {
					requestHostMachine = InetAddress.getByName(forwardedHosts[0]);
				}
			}
			if(requestHostMachine != null) {
				requestingHostAddress = requestHostMachine.getHostAddress();
				Context.current().response().setHeader(HEADER_X_FORWARDED_FOR, requestingHostAddress);
			}
		} catch(UnknownHostException e) {
			Logger.error("Unknown host found.", e);
		}
		return requestingHostAddress;
	}
	
	/**
	 * Converting IP address to its equavelent Hexadecimal form.
	 * @param ipAddress to be converted to hexadecimal
	 * @return the hexadecimal form of given ip address.
	 */
	public static final String ipToHex(String ipAddress) {
		if(XoUtil.isNotNull(ipAddress)) {
			String[] ipAddressInArray = ipAddress.split("\\.");
			long ipLongValue = 0;
			for (int i = 0; i < ipAddressInArray.length; i++) {
				
				int power = 3 - i;
				int ip = Integer.parseInt(ipAddressInArray[i]);
				ipLongValue += ip * Math.pow(256, power);
				
			}
			return Long.toHexString(ipLongValue);
		} else {
			return "";
		}
	}
}

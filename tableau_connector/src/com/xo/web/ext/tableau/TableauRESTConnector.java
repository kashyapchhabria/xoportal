package com.xo.web.ext.tableau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.xo.data.convertor.JAXBObjectToXmlConvertor;
import com.xo.data.convertor.JAXBXmlToObjectConvertor;
import com.xo.web.ext.tableau.models.Credential;
import com.xo.web.ext.tableau.models.Site;
import com.xo.web.ext.tableau.models.TableauServerRequest;
import com.xo.web.ext.tableau.models.TableauServerResponse;
import com.xo.web.ext.tableau.models.User;
import com.xo.web.ext.tableau.models.View;
import com.xo.web.ext.tableau.models.WorkBook;

@SuppressWarnings("serial")
public class TableauRESTConnector {

	private static final JAXBObjectToXmlConvertor<TableauServerRequest> TABEALU_REQUEST_CONVERTOR = 
			new JAXBObjectToXmlConvertor<TableauServerRequest>();

	private static final JAXBXmlToObjectConvertor<TableauServerResponse> TABEALU_RESPONSE_CONVERTOR = 
			new JAXBXmlToObjectConvertor<TableauServerResponse>();
	
	// Initialize apache HttpClient
	public static final HttpClient REST_CLIENT = HttpClientBuilder.create().build();

	public static final String AUTH_TOKEN_HEADER = "X-Tableau-Auth";

	public static final String URL_AUTH_XML = "/auth.xml";
	public static final String URL_AUTH_LOGIN_XML = "/auth/login.xml";
	public static final String URL_LOGOUT = "/api/2.3/auth/signout";
	public static final String URL_LOGIN = "/api/2.3/auth/signin";
	public static final String URL_QUERY_SITE_BY_NAME = "/api/2.3/sites/{siteName}?key=name";
	public static final String URL_QUERY_SITE_BY_ID = "/api/2.3/sites/{siteId}";
	public static final String URL_QUERY_USERS_BY_SITE = "/api/2.3/sites/{siteId}/users/";
	public static final String URL_QUERY_WORKBOOKS_BY_USER = "/api/2.3/sites/{siteId}/users/{userId}/workbooks";
	public static final String URL_QUERY_VIEWS_BY_WORKBOOK = "/api/2.3/sites/{siteId}/workbooks/{workbookId}/views?includeUsageStatistics=false";
	public static final String URL_QUERY_TRUSTED_IP_TICKET = "/trusted";
	public static String authToken;
	public enum HttpMethodType {
		GET,
		POST,
		DELETE,
		PUT,
		OPTIONS
	}
	
	public static CharSequence supplant(CharSequence message, Map<String, String> params) {

	    Matcher m = Pattern.compile("\\{(\\w+)\\}").matcher(message);
	    StringBuffer sb = new StringBuffer();
	    while (m.find()) {
	    	m.appendReplacement(sb, "");
	    	String key = m.group(1);
	    	sb.append(params.get(key).toString());
	    }
	    m.appendTail(sb);
	    return sb.toString();

	}
	
	/**
	 * Fetches The HttpResponse into a StringBuffer
	 * 
	 * @param httpresp
	 *            HttpResponse
	 * @return StringBuffer with contents of the HttpResponse
	 * @throws IOException
	 */
	private static final StringBuffer fetchResponse(HttpResponse httpresp)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(httpresp.getEntity().getContent()));

		StringBuffer strbuffer = new StringBuffer();
		String currentline = "";
		while ((currentline = bufferedReader.readLine()) != null) {
			strbuffer.append(currentline);
		}
		return strbuffer;
	}
	
	/**
	 * 
	 * @param url to connect.
	 * @param payloadEntity payload to be sent.
	 * @param methodType the url is supports.
	 * @return response as string.
	 * @throws IOException
	 */
	public static final StringBuffer connectTableau(String url, HttpEntity payloadEntity,  
			HttpMethodType methodType, String authToken) throws IOException {
		
		// Create HTTP request
		HttpRequestBase request = null;
		switch(methodType) {
			case GET :
				request = new HttpGet(url);
				break;
			case DELETE:
				request = new HttpDelete(url);
				break;
			case POST:
				request = new HttpPost(url);
				break;
			case OPTIONS:
				request = new HttpOptions(url);
				break;
			case PUT:
				request = new HttpPut(url);
				break;
		}

		if(authToken != null && !url.contains(URL_LOGIN)) {
			request.setHeader(AUTH_TOKEN_HEADER, authToken);
		}

		// bind payload to the request
		if(payloadEntity != null) {
			((HttpPost)request).setEntity(payloadEntity);
		}
		HttpResponse response = url.endsWith(".xml") ? REST_CLIENT.execute(request) : HttpClientBuilder.create().build().execute(request);
		StringBuffer responseStrBuffer = fetchResponse(response);
		return responseStrBuffer;
	}
	
	/**
	 * 
	 * @param url to connect.
	 * @param tsRequest payload to be sent.
	 * @param methodType the url is supports.
	 * @return TableauServerResponse object.
	 * @throws IOException
	 */
	public static final TableauServerResponse connectTableau(String url, TableauServerRequest tsRequest, 
		HttpMethodType methodType, String authToken) throws IOException {
		TableauServerResponse tsResponse = null;
		StringBuffer resultBuffer = null;
		if(tsRequest != null) {
			String tsResponsePayload = TABEALU_REQUEST_CONVERTOR.convert(tsRequest);
			resultBuffer = connectTableau(url, new StringEntity(tsResponsePayload), methodType, authToken);
		} else {
			resultBuffer = connectTableau(url, (HttpEntity)null, methodType, authToken);
		}
		if(resultBuffer != null && resultBuffer.indexOf("<tsResponse") > -1) {
			int endoftag = resultBuffer.indexOf("\">");
			int startofns = resultBuffer.indexOf("xmlns=");
			resultBuffer = resultBuffer.delete(startofns, endoftag+1);
//			System.out.println(resultBuffer);
			
			// Parse XML FROM the result
			StringReader reader = new StringReader(resultBuffer.toString());

			// Get Required data for creating the authentication request, such as
			// modulus and exponent of the RSA public key and the authencity_token

			tsResponse = TABEALU_RESPONSE_CONVERTOR.convert(reader, new TableauServerResponse());
		}
		return tsResponse;
	}
	
	public static final List<View> getViewsForWorkbook(String tableauHost, Site site, WorkBook workbook, String authToken2) throws Exception {
		Map<String, String> urlArgs = new HashMap<String, String>()
	    {{
	      put("siteId", site.id);
	      put("workbookId", workbook.id);
	    }};
		String url = (String) supplant(tableauHost + URL_QUERY_VIEWS_BY_WORKBOOK, urlArgs);
		
		TableauServerResponse tsResponseForWorkbookViews = connectTableau(url, (TableauServerRequest) null, HttpMethodType.GET, authToken);
		// update / add the Views to the database
		/*for(View view : tsResponseForWorkbookViews.views) {
			System.out.println("View Id" + view.id);
			System.out.println("View name" + view.name);
		}*/
		return tsResponseForWorkbookViews.views;
	}

	public static final List<WorkBook> getWorkBooksForUser(String tableauHost, Site site, User user, String authToken2) throws Exception {
		Map<String, String> urlArgs = new HashMap<String, String>()
	    {{
	      put("siteId", site.id);
	      put("userId", user.id);
	    }};
	    String url = (String) supplant(tableauHost + URL_QUERY_WORKBOOKS_BY_USER, urlArgs);

		TableauServerResponse tsResponseForWorkbook = connectTableau(url, (TableauServerRequest) null, HttpMethodType.GET, authToken);
		// update / add the workbooks to the database
		/*for(WorkBook workbook : tsResponseForWorkbook.workbooks) {
			System.out.println("Workbook Id" + workbook.id);
			System.out.println("Workbook name" + workbook.name);
			System.out.println("Project Id" + workbook.project.id);
			System.out.println("Project name" + workbook.project.name);
		}*/
		return tsResponseForWorkbook.workbooks;
	}

	public static final List<User> getUsersDetailsBySite(String tableauHost, Site site, String authToken2) throws Exception {
		Map<String, String> urlArgs = new HashMap<String, String>()
	    {{
	      put("siteId", site.id);
	    }};
		String url = (String) supplant(tableauHost + URL_QUERY_USERS_BY_SITE, urlArgs);
		
		TableauServerResponse tsResponseForSiteUsers = connectTableau(url, (TableauServerRequest) null, HttpMethodType.GET, authToken);
		/*for(User user : tsResponseForSiteUsers.users) {
			System.out.println("User Id" + user.id);
			System.out.println("User name" + user.name);
		}*/
		// update / add the users to the database
		return tsResponseForSiteUsers.users;
	}

	public static final Site getSiteDetailsByName(String tableauHost, Site site, String authToken) throws Exception {
		Map<String, String> urlArgs = new HashMap<String, String>()
	    {{
//	      put("siteName", site.siteName);
	      put("siteId", site.id);
	    }};
	    String url = (String) supplant(tableauHost + URL_QUERY_SITE_BY_ID, urlArgs);

	    TableauServerResponse tsResponseForSite = connectTableau(url, (TableauServerRequest) null, HttpMethodType.GET, authToken);
	    site.id = tsResponseForSite.site.id;
	    site.state = tsResponseForSite.site.state;
	    site.statusReason = tsResponseForSite.site.statusReason;
		// update / add the site to the database
		return site;
	}
	
	public static final String getTicket(String tableauHost, String userName, String clientIP, String siteContentUrl) {
		StringBuffer result = new StringBuffer();
		try {

			// Fill in parameters
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("username", userName));
			nvps.add(new BasicNameValuePair("client_ip", clientIP));
			nvps.add(new BasicNameValuePair("target_site", siteContentUrl));

			// bind parameters to the request
			result = connectTableau(tableauHost + URL_QUERY_TRUSTED_IP_TICKET, new UrlEncodedFormEntity(nvps), HttpMethodType.POST, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	public static final void updatePortalTableauReports(String tableauHost, String username, String password,
			String siteName) throws Exception {
		signIn(tableauHost, username, password, siteName);
	}
	
	public static final TableauServerResponse signIn(String tableauHost, String username, String password, String siteName) throws Exception {
		
		String hostURL = tableauHost + URL_LOGIN;
		TableauServerRequest tsRequest = new TableauServerRequest();
		Credential credentials = new Credential();
		credentials.name = username;
		credentials.password = password;
		credentials.site = new Site();
		credentials.site.contentUrl = siteName;
		tsRequest.credentials = credentials;
		TableauServerResponse tsResponse = connectTableau(hostURL, tsRequest, HttpMethodType.POST, null);
		authToken = tsResponse.credentials.token;
		getSiteDetailsByName(tableauHost, tsResponse.credentials.site, authToken);
		List<User> users = getUsersDetailsBySite(tableauHost, tsResponse.credentials.site, authToken);
		for(User user : users) {
			List<WorkBook> workbooks = getWorkBooksForUser(tableauHost, tsResponse.credentials.site, user, authToken);
			for(WorkBook workbook : workbooks){
				List<View> views = getViewsForWorkbook(tableauHost, tsResponse.credentials.site, workbook, authToken);
				System.out.println("Views in "+ workbook.name +":");
				for (View view: views) {
					if ( ! view.name.equalsIgnoreCase(views.get(views.size() - 1).name))
						System.out.print(view.name+",");
					else
						System.out.print(view.name+".\n");
				}
				// update / add the workbooks to the database
			}
		}
		return tsResponse;
	}

	public static void main(String[] args) {
		final String USER_NAME = "Etisalat";
		final String PASSWORD = "XoaNon2016";
		final String TABLEAU_HOST = "http://192.175.112.82";
		final String siteName = "Xo_etisalat";
		try {
			updatePortalTableauReports(TABLEAU_HOST, USER_NAME, PASSWORD, siteName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

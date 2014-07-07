package com.android.meteorid.manager;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.util.Log;

public class ApiManager {

	public static final int TIMEOUT = 10000;
	private static final String TAG = "ApiManager";
	private static CookieStore listCookies;

	private static HttpParams createHttpParams(){
		HttpParams myhttpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(myhttpParams, ApiManager.TIMEOUT);   
		HttpConnectionParams.setSoTimeout(myhttpParams, ApiManager.TIMEOUT); 
		return myhttpParams;
	}

	public static JSONObject callAPI(String urlApi){
		return callAPI(urlApi, false, false);
	}

	public static JSONObject callAPI(String urlApi, boolean useSessionCookie , boolean saveSessionCookie){
		ByteArrayOutputStream os = null;
		HttpClient httpclient = new DefaultHttpClient( createHttpParams() );
		if(listCookies != null && useSessionCookie){ 
			((DefaultHttpClient)httpclient).setCookieStore(getCookieForUrl(urlApi));
		}
		HttpGet httpget = new HttpGet( urlApi); 
		JSONObject oJsonResult = null;

		try {
			HttpResponse response = httpclient.execute(httpget);
			os = new ByteArrayOutputStream();
			response.getEntity().writeTo(os);
			oJsonResult = new JSONObject( os.toString() );
			if(saveSessionCookie){
				saveCookieStore(httpclient);
			}

		} catch (IOException e) {
			Log.e(TAG, "Failed : Bad request IOException " + urlApi + " : " + e.getMessage() );
			e.printStackTrace();
		} catch (JSONException e) {
			Log.e(TAG, "Failed : Bad request JSONException " + urlApi + " : " + e.getMessage() );
			e.printStackTrace();
		} catch (OutOfMemoryError e) {
			Log.e(TAG, "Failed : Bad request OutOfMemoryError " + urlApi + " : " + e.getMessage() );
		} finally { 
			closeStream(os); 
		}
		return oJsonResult;
	}

	private static void saveCookieStore(HttpClient client) {
		listCookies = ((DefaultHttpClient) client).getCookieStore();
	}

	private static void closeStream(Closeable stream) { 
		if (stream != null) { 
			try { 
				stream.close(); 
			} catch (Exception e) { 
				Log.e(TAG, "Exception " + e.getMessage());
			} 
		} 
	}
	
	private static CookieStore getCookieForUrl(String url){
		BasicCookieStore store = new BasicCookieStore();
		try{
			for (int i = 0; i < listCookies.getCookies().size(); i++) {
				BasicClientCookie cookie = new BasicClientCookie(listCookies.getCookies().get(i).getName(),listCookies.getCookies().get(i).getValue());
				cookie.setDomain(Uri.parse(url).getHost());
				store.addCookie(cookie);
			}
		}catch(Exception e){
			e.printStackTrace();
			return listCookies;
		}
		return store;
	}

	public static void resetCookies(){
		listCookies = null;
	}

}

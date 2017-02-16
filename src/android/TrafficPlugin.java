/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
*/

package com.maneskul.cordova.traffic;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.LOG;
import org.apache.cordova.Whitelist;
import org.xmlpull.v1.XmlPullParser;

import android.content.Context;

public class TrafficPlugin extends CordovaPlugin {
    private static final String LOG_TAG = "TrafficPlugin";

    // Used when instantiated via reflection by PluginManager
    public TrafficPlugin() {
    }
   	
    @Override
    public void pluginInitialize() {
        if (allowedNavigations == null) {
            allowedNavigations = new Whitelist();
            allowedIntents = new Whitelist();
            allowedRequests = new Whitelist();
            new CustomConfigXmlParser().parse(webView.getContext());
        }
    }

    public String getTimeZoneID() {
		TimeZone tz = TimeZone.getDefault();
        return (tz.getID());
    }
	
	public PluginResult listReceived(CallbackContext callbackContext) {
		ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> runningApps = manager.getRunningAppProcesses();
		JSONObject json = new JSONArray();
		
		for (RunningAppProcessInfo runningApp : runningApps) {
		  JSONObject json = new JSONObject();
		  
		  // Get UID of the selected process
		  int uid = ((RunningAppProcessInfo)getListAdapter().getItem(position)).uid;
		  // Get traffic data
		  long received = TrafficStats.getUidRxBytes(uid);
		  long send   = TrafficStats.getUidTxBytes(uid);
		  json.put("uid", uid);
		  json.put("received", received);
		  json.put("send", send);
		  
		  jsons.put((Object)json);
		}
		
		callbackContext.success(jsons);
	}
	
	
}

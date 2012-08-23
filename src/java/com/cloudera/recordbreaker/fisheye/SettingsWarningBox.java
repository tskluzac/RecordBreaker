/*
 * Copyright (c) 2012, Cloudera, Inc. All Rights Reserved.
 *
 * Cloudera, Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"). You may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the
 * License.
 */
package com.cloudera.recordbreaker.fisheye;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.PropertyModel;

/************************************************
 * Application-wide warning box that tells user
 * when settings need immediate attention.
 *
 * @author "Michael Cafarella"
 * @version 1.0
 * @since 1.0
 *************************************************/
public class SettingsWarningBox extends WebMarkupContainer {
  final class ErrorMsgHandler {
    public ErrorMsgHandler() {
    }
    public String getErrorMsg() {
      FishEye fe = FishEye.getInstance();
      String fsUrl = fe.getFSUrl();
      String user = fe.getUsername();

      String errorMsg = null;      
      if (fsUrl == null && user == null) {
        errorMsg = "FishEye currently has no filesystem and no logged-in user.  Fix these in Settings before continuing.";
      } else if (fsUrl == null) {
        errorMsg = "FishEye currently has no filesystem.  Fix this in Settings before continuing.";      
      } else if (errorMsg == null) {
        errorMsg = "FishEye currently has no logged-in user.  Fix this in Settings before continuing.";      
      }
      return errorMsg;
    }
  }
  public SettingsWarningBox() {
    super("settingsWarningMsgContainer");
    add(new Label("settingsErrorLabel", new PropertyModel(new ErrorMsgHandler(), "errorMsg")));    
    setOutputMarkupPlaceholderTag(true);
    setVisibilityAllowed(false);
  }
  public void onConfigure() {
    FishEye fe = FishEye.getInstance();    
    setVisibilityAllowed((fe.getFSUrl() == null) || (fe.getUsername() == null));
  }
}
/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package webrisk;

import com.google.cloud.webrisk.v1.WebRiskServiceClient;
import com.google.webrisk.v1.SearchUrisRequest;
import com.google.webrisk.v1.SearchUrisResponse;
import com.google.webrisk.v1.ThreatType;
import java.io.IOException;

public class SearchUriExample {

  public static void main(String[] args) {
    //The URL to be searched
    String uri = "http://testsafebrowsing.appspot.com/s/malware.html";
    SearchUrisResponse response = searchUriExample(uri);
  }

  public static SearchUrisResponse searchUriExample(String uri) {
    SearchUrisRequest searchUrisRequest;
    SearchUrisResponse searchUrisResponse = null;

    try (WebRiskServiceClient webRiskServiceClient = WebRiskServiceClient.create()) {
      //Query the url for a specific threat type
      searchUrisRequest = SearchUrisRequest.newBuilder().addThreatTypes(ThreatType.MALWARE)
          .setUri(uri).build();
      searchUrisResponse = webRiskServiceClient.searchUris(searchUrisRequest);
      webRiskServiceClient.shutdownNow();
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
    return searchUrisResponse;
  }
}

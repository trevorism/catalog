package com.trevorism.gcloud

import com.google.gson.Gson
import com.trevorism.data.model.DataCatalog
import com.trevorism.https.AppClientSecureHttpClient
import com.trevorism.https.SecureHttpClient

this.metaClass.mixin(io.cucumber.groovy.Hooks)
this.metaClass.mixin(io.cucumber.groovy.EN)

SecureHttpClient client = new AppClientSecureHttpClient()
String json
Gson gson = new Gson()
String baseUrl = System.getenv("ACCEPTANCE_BASE_URL") ?: "https://catalog.data.trevorism.com"

When(/the search for id or name {string} is requested/) { String string ->
    json = client.post("${baseUrl}/search", "{\"query\":\"${string}\"}")
}

Then(/the app dataset is returned/) {  ->
    List<DataCatalog> catalogList = gson.fromJson(json, List)
    DataCatalog catalog = catalogList[0]
    assert catalog.datasetId == 1
    assert catalog.datasetName == "answer"
    assert catalog.lookup == "datastore:answer"

}

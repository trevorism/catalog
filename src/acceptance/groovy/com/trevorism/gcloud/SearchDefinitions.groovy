package com.trevorism.gcloud

import com.google.gson.Gson
import com.trevorism.gcloud.webapi.model.DataCatalog
import com.trevorism.https.DefaultSecureHttpClient
import com.trevorism.https.SecureHttpClient

/**
 * @author tbrooks
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

SecureHttpClient client = new DefaultSecureHttpClient()
String json
Gson gson = new Gson()

When(/the search for id or name {string} is requested/) { String string ->
    // Write code here that turns the phrase above into concrete actions
    json = client.post("https://catalog.data.trevorism.com/api/result", "{\"query\":\"${string}\"}")
}

Then(/the app dataset is returned/) {  ->
    DataCatalog catalog = gson.fromJson(json, DataCatalog)
    assert catalog.id == "6194655276302336"
    assert catalog.datasetId == "2"
    assert catalog.datasetName == "app"
    assert catalog.url == "https://datastore.trevorism.com/api/app"

}

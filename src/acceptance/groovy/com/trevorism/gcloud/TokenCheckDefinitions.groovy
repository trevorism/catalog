package com.trevorism.gcloud

import com.trevorism.http.headers.HeadersHttpClient
import com.trevorism.http.headers.HeadersJsonHttpClient
import com.trevorism.http.util.ResponseUtils
import com.trevorism.https.DefaultSecureHttpClient
import com.trevorism.https.SecureHttpClient
import com.trevorism.secure.ClasspathBasedPropertiesProvider
import com.trevorism.secure.PropertiesProvider

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

SecureHttpClient client = new DefaultSecureHttpClient()
def response = ""

When(/the endpoint tester internal endpoint is invoked/) { ->
    response = client.get("https://endpoint-tester.testing.trevorism.com/secure/json")
}

Then(/a response is returned successfully/) { ->
    assert response == "secure hello json"
}

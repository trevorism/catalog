package com.trevorism.gcloud


import com.trevorism.https.DefaultSecureHttpClient
import com.trevorism.https.SecureHttpClient

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

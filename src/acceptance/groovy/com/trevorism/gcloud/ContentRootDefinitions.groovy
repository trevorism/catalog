package com.trevorism.gcloud

import com.trevorism.http.JsonHttpClient

/**
 * @author tbrooks
 */

this.metaClass.mixin(io.cucumber.groovy.Hooks)
this.metaClass.mixin(io.cucumber.groovy.EN)

String baseUrl = System.getenv("ACCEPTANCE_BASE_URL") ?: "https://catalog.data.trevorism.com"

def contextRootContent
def pingContent
def httpClient = new JsonHttpClient()

Given(/the catalog application is alive/) { ->
    try{
        httpClient.get("${baseUrl}/ping")
    }
    catch (Exception ignored){
        Thread.sleep(10000)
        httpClient.get("${baseUrl}/ping")
    }
}

When(/I navigate to {string}/) { String string ->
    contextRootContent = httpClient.get(baseUrl)
}

Then(/then a link to the help page is displayed/) {  ->
    assert contextRootContent
    assert contextRootContent.contains("/help")
}

When(/I ping the application deployed to {string}/) { String string ->
    pingContent = httpClient.get("${baseUrl}/ping")
}

Then(/pong is returned, to indicate the service is alive/) {  ->
    assert pingContent == "pong"
}
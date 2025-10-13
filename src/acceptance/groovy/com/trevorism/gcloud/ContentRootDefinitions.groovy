package com.trevorism.gcloud

import com.trevorism.http.JsonHttpClient

/**
 * @author tbrooks
 */

this.metaClass.mixin(io.cucumber.groovy.Hooks)
this.metaClass.mixin(io.cucumber.groovy.EN)

def contextRootContent
def pingContent
def httpClient = new JsonHttpClient()

Given(/the catalog application is alive/) { ->
    try{
        httpClient.get("https://catalog.data.trevorism.com/ping")
    }
    catch (Exception ignored){
        Thread.sleep(10000)
        httpClient.get("https://catalog.data.trevorism.com/ping")
    }
}

When(/I navigate to {string}/) { String string ->
    contextRootContent = httpClient.get(string)
}

Then(/then a link to the help page is displayed/) {  ->
    assert contextRootContent
    assert contextRootContent.contains("/help")
}

When(/I ping the application deployed to {string}/) { String string ->
    pingContent = httpClient.get("${string}/ping")
}

Then(/pong is returned, to indicate the service is alive/) {  ->
    assert pingContent == "pong"
}
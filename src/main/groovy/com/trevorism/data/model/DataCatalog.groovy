package com.trevorism.data.model

import groovy.transform.ToString

@ToString
class DataCatalog {

    String id
    long datasetId
    String datasetName
    String datastore
    String lookup
    Map metadata

}

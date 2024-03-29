package com.trevorism.data.model

import groovy.transform.ToString

@ToString
class DataCatalog {

    String id
    String datasetId
    String datasetName
    String url
    Map metadata

}

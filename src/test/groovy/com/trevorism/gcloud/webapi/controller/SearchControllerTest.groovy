package com.trevorism.gcloud.webapi.controller

import com.trevorism.data.Repository
import com.trevorism.gcloud.webapi.model.DataCatalog
import com.trevorism.gcloud.webapi.model.Search
import org.junit.Test

class SearchControllerTest {

    @Test
    void testCreate() {
        Search search = new Search(query: "domainmodel")
        SearchController searchController = new SearchController()
        searchController.repo = [list: {-> [new DataCatalog(datasetName: "DomainModel") ]}] as Repository<DataCatalog>
        assert searchController.search(search)

    }
}

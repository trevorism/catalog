package com.trevorism.data.controller

import com.trevorism.data.model.DataCatalog
import com.trevorism.data.model.Search
import com.trevorism.data.service.CatalogService
import org.junit.jupiter.api.Test

class SearchControllerTest {

    @Test
    void testSearch() {
        Search search = new Search(query: "domainmodel")
        SearchController searchController = new SearchController()
        searchController.catalogService = [search: {String value -> [new DataCatalog(datasetName: "DomainModel")] }] as CatalogService
        assert searchController.search(search)
    }
}

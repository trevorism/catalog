package com.trevorism.data.controller

import com.trevorism.data.Repository
import com.trevorism.data.model.DataCatalog
import com.trevorism.data.model.Search
import org.junit.jupiter.api.Test

class SearchControllerTest {

    @Test
    void testCreate() {
        Search search = new Search(query: "domainmodel")
        SearchController searchController = new SearchController()
        searchController.repo = [list: { -> [new DataCatalog(datasetName: "DomainModel")] }] as Repository<DataCatalog>
        assert searchController.search(search)

    }
}

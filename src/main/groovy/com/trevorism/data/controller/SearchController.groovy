package com.trevorism.data.controller

import com.trevorism.data.PingingDatastoreRepository
import com.trevorism.data.Repository
import com.trevorism.data.model.DataCatalog
import com.trevorism.data.model.Search
import com.trevorism.secure.Roles
import com.trevorism.secure.Secure
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("api/result")
class SearchController {

    private Repository<DataCatalog> repo = new PingingDatastoreRepository<>(DataCatalog)

    @Tag(name = "Catalog Operations")
    @Operation(summary = "Search for a DataCatalog **Secure")
    @Post(value = "/", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @Secure(value = Roles.SYSTEM, allowInternal = true)
    DataCatalog search(Search search){
        try{
            long value = Long.parseLong(search.query)
            return repo.list().find{it.datasetId == value.toString()}
        }
        catch(Exception ignored){}

        return repo.list().find{it.datasetName.toLowerCase() == search.query.toLowerCase()}
    }
}

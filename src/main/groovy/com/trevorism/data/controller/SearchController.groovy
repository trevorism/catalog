package com.trevorism.data.controller

import com.trevorism.data.PingingDatastoreRepository
import com.trevorism.data.Repository
import com.trevorism.data.model.DataCatalog
import com.trevorism.data.model.Search
import com.trevorism.data.service.CatalogService
import com.trevorism.secure.Permissions
import com.trevorism.secure.Roles
import com.trevorism.secure.Secure
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.inject.Inject

@Controller("/search")
class SearchController {

    @Inject
    private CatalogService catalogService

    @Tag(name = "Search Operations")
    @Operation(summary = "Search for a DataCatalog **Secure")
    @Post(value = "/", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @Secure(value = Roles.USER, allowInternal = true, permissions = Permissions.READ)
    List<DataCatalog> search(@Body Search search){
        catalogService.search(search.query)
    }
}

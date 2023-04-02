package com.trevorism.data.controller

import com.trevorism.data.PingingDatastoreRepository
import com.trevorism.data.Repository
import com.trevorism.data.model.DataCatalog
import com.trevorism.secure.Roles
import com.trevorism.secure.Secure
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.exceptions.HttpStatusException
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

import java.util.logging.Logger

@Controller("/api")
class CatalogController {

    private Repository<DataCatalog> service = new PingingDatastoreRepository<>(DataCatalog)
    private static final Logger log = Logger.getLogger(CatalogController.class.name)

    @Tag(name = "Catalog Operations")
    @Operation(summary = "Get a data catalog with id {id} **Secure")
    @Get(value = "{id}", produces = MediaType.APPLICATION_JSON)
    @Secure(value = Roles.SYSTEM, allowInternal = true)
    DataCatalog read(String id) {
        service.get(id)
    }

    @Tag(name = "Catalog Operations")
    @Operation(summary = "Get all data catalogs **Secure")
    @Secure(value = Roles.SYSTEM, allowInternal = true)
    @Get(value = "/", produces = MediaType.APPLICATION_JSON)
    List<com.trevorism.data.model.DataCatalog> readAll() {
        service.list()
    }

    @Tag(name = "Catalog Operations")
    @Operation(summary = "Create a data catalog **Secure")
    @Post(value = "/", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @Secure(value = Roles.SYSTEM, allowInternal = true)
    DataCatalog create(@Body DataCatalog catalog) {
        try {
            service.create(catalog)
        } catch (Exception e) {
            log.severe("Unable to create List object: ${catalog} :: ${e.getMessage()}")
            throw new HttpStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @Tag(name = "Catalog Operations")
    @Operation(summary = "Update a data catalog with id {id} **Secure")
    @Put(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @Secure(value = Roles.SYSTEM, allowInternal = true)
    DataCatalog update(String id, @Body DataCatalog catalog) {
        service.update(id, catalog)
    }

    @Tag(name = "Catalog Operations")
    @Operation(summary = "Delete a data catalog with id {id} **Secure")
    @Delete(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    @Secure(value = Roles.SYSTEM, allowInternal = true)

    DataCatalog delete(String id) {
        service.delete(id)
    }
}

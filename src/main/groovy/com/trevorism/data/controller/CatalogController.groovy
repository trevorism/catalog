package com.trevorism.data.controller


import com.trevorism.data.model.DataCatalog
import com.trevorism.data.service.CatalogService
import com.trevorism.secure.Permissions
import com.trevorism.secure.Roles
import com.trevorism.secure.Secure
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.exceptions.HttpStatusException
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.inject.Inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller("/object")
class CatalogController {

    @Inject
    private CatalogService catalogService
    private static final Logger log = LoggerFactory.getLogger(CatalogController.class.name)

    @Tag(name = "Catalog Operations")
    @Operation(summary = "Get all data catalogs **Secure")
    @Secure(value = Roles.USER, allowInternal = true, permissions = Permissions.READ)
    @Get(value = "/", produces = MediaType.APPLICATION_JSON)
    List<DataCatalog> list() {
        catalogService.list()
    }

    @Tag(name = "Catalog Operations")
    @Operation(summary = "Get a data catalog with id {id} **Secure")
    @Get(value = "{id}", produces = MediaType.APPLICATION_JSON)
    @Secure(value = Roles.USER, allowInternal = true, permissions = Permissions.READ)
    DataCatalog read(String id) {
        catalogService.read(id)
    }

    @Tag(name = "Catalog Operations")
    @Operation(summary = "Create a data catalog **Secure")
    @Post(value = "/", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @Secure(value = Roles.USER, allowInternal = true, permissions = Permissions.CREATE)
    @Status(HttpStatus.CREATED)
    DataCatalog create(@Body DataCatalog catalog) {
        try {
            catalogService.create(catalog)
        } catch (Exception e) {
            log.error("Unable to create DataCatalog object: ${catalog}", e)
            throw new HttpStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @Tag(name = "Catalog Operations")
    @Operation(summary = "Update a data catalog with id {id} **Secure")
    @Put(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @Secure(value = Roles.USER, allowInternal = true, permissions = Permissions.UPDATE)
    DataCatalog update(String id, @Body DataCatalog catalog) {
        catalogService.update(id, catalog)
    }

    @Tag(name = "Catalog Operations")
    @Operation(summary = "Delete a data catalog with id {id} **Secure")
    @Delete(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    @Secure(value = Roles.USER, allowInternal = true, permissions = Permissions.DELETE)
    DataCatalog delete(String id) {
        catalogService.delete(id)
    }
}
 
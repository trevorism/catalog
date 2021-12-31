package com.trevorism.gcloud.webapi.controller

import com.trevorism.data.PingingDatastoreRepository
import com.trevorism.data.Repository
import com.trevorism.gcloud.webapi.filter.Created
import com.trevorism.gcloud.webapi.model.DataCatalog
import com.trevorism.secure.Roles
import com.trevorism.secure.Secure
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

import javax.ws.rs.BadRequestException
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import java.util.logging.Logger

@Api("Catalog Operations")
@Path("api")
class CatalogController {

    private Repository<DataCatalog> service = new PingingDatastoreRepository<>(DataCatalog)
    private static final Logger log = Logger.getLogger(CatalogController.class.name)

    @ApiOperation(value = "Get a data catalog with id {id} **Secure")
    @GET
    @Secure(value = Roles.SYSTEM, allowInternal = true)
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    DataCatalog read(@PathParam("id") String id){
        service.get(id)
    }

    @ApiOperation(value = "Get all data catalogs")
    @Secure(value = Roles.SYSTEM, allowInternal = true)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<DataCatalog> readAll(){
        service.list()
    }

    @ApiOperation(value = "Create a data catalog **Secure")
    @POST
    @Secure(value = Roles.SYSTEM, allowInternal = true)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Created
    DataCatalog create(DataCatalog catalog){
        try {
            service.create(catalog)
        }catch (Exception e){
            log.severe("Unable to create List object: ${catalog} :: ${e.getMessage()}")
            throw new BadRequestException(e)
        }
    }

    @ApiOperation(value = "Update a data catalog with id {id} **Secure")
    @PUT
    @Secure(value = Roles.SYSTEM, allowInternal = true)
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    DataCatalog update(@PathParam("id") String id, DataCatalog catalog){
        service.update(id, catalog)
    }

    @ApiOperation(value = "Delete a data catalog with id {id} **Secure")
    @DELETE
    @Secure(value = Roles.SYSTEM, allowInternal = true)
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    DataCatalog delete(@PathParam("id") String id){
        service.delete(id)
    }
}

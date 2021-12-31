package com.trevorism.gcloud.webapi.controller

import com.trevorism.data.PingingDatastoreRepository
import com.trevorism.data.Repository
import com.trevorism.gcloud.webapi.filter.Created
import com.trevorism.gcloud.webapi.model.DataCatalog
import com.trevorism.gcloud.webapi.model.Search
import com.trevorism.secure.Roles
import com.trevorism.secure.Secure
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

import javax.ws.rs.BadRequestException
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Api("Catalog Operations")
@Path("api/result")
class SearchController {

    private Repository<DataCatalog> service = new PingingDatastoreRepository<>(DataCatalog)

    @ApiOperation(value = "Search for a DataCatalog **Secure")
    @POST
    @Secure(value = Roles.SYSTEM, allowInternal = true)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    DataCatalog create(Search search){
        try{
            long value = Long.parseLong(search.query)
            return service.list().find{it.datasetId == value.toString()}

        }
        catch(Exception ignored){}

        return service.list().find{it.datasetName == search.query}
    }
}
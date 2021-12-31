package com.trevorism.gcloud.webapi.controller

import com.trevorism.data.Repository
import com.trevorism.gcloud.webapi.model.DataCatalog
import org.junit.Test

class CatalogControllerTest {

    @Test
    void testCreate(){
        CatalogController controller = new CatalogController()
        controller.service = [create: {new DataCatalog(id: "1")}] as Repository

        DataCatalog dc = new DataCatalog()
        assert "1" == controller.create(dc).id

    }

    @Test
    void testRead(){
        CatalogController controller = new CatalogController()
        controller.service = [get: {new DataCatalog(id: "1")}] as Repository

        assert "1" == controller.read("414124212").id

    }

    @Test
    void testReadAll(){
        CatalogController controller = new CatalogController()
        controller.service = [list: {[new DataCatalog(id: "1")]}] as Repository

        assert controller.readAll()
    }

    @Test
    void testUpdate() {
        CatalogController controller = new CatalogController()
        controller.service = [update: { id, list -> list}] as Repository

        assert controller.update("5202267682", new DataCatalog(id: "1"))
    }

    @Test
    void testDelete() {
        CatalogController controller = new CatalogController()
        controller.service = [delete: { new DataCatalog(id: "1")}] as Repository
        assert controller.delete("5202267682")
    }
}

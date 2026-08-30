package com.trevorism.data.controller


import com.trevorism.data.model.DataCatalog
import com.trevorism.data.service.CatalogService
import io.micronaut.http.exceptions.HttpStatusException
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertThrows

class CatalogControllerTest {

    @Test
    void testCreate() {
        CatalogController controller = new CatalogController()
        controller.catalogService = [create: { new DataCatalog(id: "1", datasetName: "sample") }] as CatalogService

        DataCatalog dc = new DataCatalog()
        assert "1" == controller.create(dc).id
    }

    @Test
    void testRead() {
        CatalogController controller = new CatalogController()
        controller.catalogService = [read: { id -> new DataCatalog(id: "1") }] as CatalogService

        assert "1" == controller.read("414124212").id
    }

    @Test
    void testReadAll() {
        CatalogController controller = new CatalogController()
        controller.catalogService = [list: { [new DataCatalog(id: "1")] }] as CatalogService

        assert controller.list()
    }

    @Test
    void testUpdate() {
        CatalogController controller = new CatalogController()
        controller.catalogService = [update: { id, list -> list }] as CatalogService

        assert controller.update("5202267682", new DataCatalog(id: "1"))
    }

    @Test
    void testDelete() {
        CatalogController controller = new CatalogController()
        controller.catalogService = [delete: { id -> new DataCatalog(id: "1") }] as CatalogService
        assert controller.delete("5202267682")
    }

    @Test
    void testCreateWithException() {
        CatalogController controller = new CatalogController()
        controller.catalogService = [create: { throw new RuntimeException() }] as CatalogService

        DataCatalog dc = new DataCatalog()
        assertThrows(HttpStatusException, () -> controller.create(dc))
    }
}

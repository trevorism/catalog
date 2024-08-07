package com.trevorism.data.controller

import com.trevorism.data.Repository
import com.trevorism.data.model.DataCatalog
import io.micronaut.http.exceptions.HttpStatusException
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertThrows

class CatalogControllerTest {

    @Test
    void testCreate() {
        CatalogController controller = new CatalogController()
        controller.service = [create: { new DataCatalog(id: "1") }] as Repository

        DataCatalog dc = new DataCatalog()
        assert "1" == controller.create(dc).id
    }

    @Test
    void testRead() {
        CatalogController controller = new CatalogController()
        controller.service = [get: { new DataCatalog(id: "1") }] as Repository

        assert "1" == controller.read("414124212").id
    }

    @Test
    void testReadAll() {
        CatalogController controller = new CatalogController()
        controller.service = [list: { [new DataCatalog(id: "1")] }] as Repository

        assert controller.readAll()
    }

    @Test
    void testUpdate() {
        CatalogController controller = new CatalogController()
        controller.service = [update: { id, list -> list }] as Repository

        assert controller.update("5202267682", new DataCatalog(id: "1"))
    }

    @Test
    void testDelete() {
        CatalogController controller = new CatalogController()
        controller.service = [delete: { new DataCatalog(id: "1") }] as Repository
        assert controller.delete("5202267682")
    }

    @Test
    void testCreateWithException() {
        CatalogController controller = new CatalogController()
        controller.service = [create: { throw new RuntimeException() }] as Repository

        DataCatalog dc = new DataCatalog()
        assertThrows(HttpStatusException, () -> controller.create(dc))
    }
}

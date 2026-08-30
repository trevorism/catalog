package com.trevorism.data.service

import com.trevorism.data.FastDatastoreRepository
import com.trevorism.data.Repository
import com.trevorism.data.model.DataCatalog
import com.trevorism.https.SecureHttpClient
import jakarta.inject.Singleton

@Singleton
class DefaultCatalogService implements CatalogService {

    private Repository<DataCatalog> repository

    DefaultCatalogService(SecureHttpClient passThruSecureHttpClient) {
        repository = new FastDatastoreRepository<>(DataCatalog, passThruSecureHttpClient)
    }

    @Override
    List<DataCatalog> list() {
        repository.list()
    }

    @Override
    DataCatalog read(String id) {
        repository.get(id)
    }

    @Override
    DataCatalog create(DataCatalog catalog) {
        if (!catalog.datasetName) {
            throw new IllegalArgumentException("Dataset name is required")
        }
        if (!catalog.datastore) {
            catalog.datastore = "datastore"
        }
        catalog.lookup = "${catalog.datastore}:${catalog.datasetName}"
        List<DataCatalog> allExisting = repository.list()
        catalog.datasetId = (allExisting.max({ it.datasetId })?.datasetId ?: 0) + 1
        repository.create(catalog)
    }

    @Override
    DataCatalog update(String id, DataCatalog catalog) {
        repository.update(id, catalog)
    }

    @Override
    DataCatalog delete(String id) {
        repository.delete(id)
    }

    @Override
    List<DataCatalog> search(String query) {
        List<DataCatalog> allExisting = repository.list()
        try {
            Long.parseLong(query)
            return allExisting.findAll { it.datasetId == Long.parseLong(query) }
        }
        catch (Exception ignored) {
        }
        return allExisting.findAll { it.datasetName?.toLowerCase()?.contains(query.toLowerCase()) || it.datastore?.toLowerCase()?.contains(query.toLowerCase()) }
    }
}

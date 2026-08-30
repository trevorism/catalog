package com.trevorism.data.service

import com.trevorism.data.model.DataCatalog

interface CatalogService {

    List<DataCatalog> list()
    DataCatalog read(String id)
    DataCatalog create(DataCatalog catalog)
    DataCatalog update(String id, DataCatalog catalog)
    DataCatalog delete(String id)
    List<DataCatalog> search(String query)
}

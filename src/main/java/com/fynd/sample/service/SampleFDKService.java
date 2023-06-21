package com.fynd.sample.service;

import com.fynd.extension.service.ExtensionService;
import com.sdk.common.model.FDKException;
import com.sdk.common.model.FDKServerResponseError;
import com.sdk.platform.PlatformClient;
import com.sdk.platform.catalog.CatalogPlatformModels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class SampleFDKService {

    private static final Logger log = LoggerFactory.getLogger(SampleFDKService.class);

    @Autowired
    ExtensionService extensionService;

    public void sampleFunction(String companyId)
            throws IOException, FDKException, FDKServerResponseError {
        PlatformClient platformClient = extensionService.getPlatformClient(companyId);
        /**
         * Code to call the specific Fynd platform API using Platform Client
         */
        CatalogPlatformModels.ProductListingResponseV2 productListingResponse = platformClient.catalog.getProducts(
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), "", new ArrayList<>(),
                1, 10);
        log.info("Items received from Catalog API : {}", productListingResponse.getItems()
                .size());
    }
}

package com.fynd.sample.service;

import com.fynd.extension.service.ExtensionService;
import com.sdk.platform.PlatformClient;
import com.sdk.platform.PlatformModels;
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

    public void sampleFunction(String companyId) throws IOException {
        PlatformClient platformClient = extensionService.getPlatformClient(companyId);
        /**
         * Code to call the specific Fynd platform API using Platform Client
         */
        PlatformModels.ProductListingResponse productListingResponse = platformClient.catalog.getProducts(
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), "", new ArrayList<>(),
                1, 10);
        log.info("Items received from Catalog API : {}", productListingResponse.getItems()
                                                                               .size());
    }
}

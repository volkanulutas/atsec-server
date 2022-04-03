package com.vem.atsecserver.converter;

import com.vem.atsecserver.entity.rawproduct.DonorInstitute;
import com.vem.atsecserver.payload.rawproduct.DonorInstituteRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author volkanulutas
 * @since 25.12.2020
 */
@Slf4j
@Component
public class DonorInstituteConverter {
    @Autowired
    private RawProductConverter rawProductConverter;

    public DonorInstitute toEntity(DonorInstituteRequest request) {
        if (request == null) {
            log.error("Error is occurred while converting donor institute.");
            return null;
        }
        DonorInstitute result = new DonorInstitute();
        result.setId(request.getId());
        result.setName(request.getName());
        result.setCode(request.getCode());

        /*
        List<RawProduct> resultList = new ArrayList<>();
        List<RawProductRequest> rawProducts = request.getRawProducts();
        for (RawProductRequest req : rawProducts) {
            resultList.add(rawProductConverter.toEntity(req));
        }
        result.setRawProducts(resultList);
         */
        return result;
    }

    public DonorInstituteRequest toRequest(DonorInstitute entity) {
        if (entity == null) {
            log.error("Error is occurred while converting  donor institute.");
            return null;
        }
        DonorInstituteRequest result = new DonorInstituteRequest();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setCode(entity.getCode());

        /*
        List<RawProductRequest> resultList = new ArrayList<>();
        for (RawProduct req : entity.getRawProducts()) {
            resultList.add(rawProductConverter.toRequest(req));
        }
        result.setRawProducts(resultList);

         */
        result.setDeleted(entity.getDeleted());
        return result;
    }
}
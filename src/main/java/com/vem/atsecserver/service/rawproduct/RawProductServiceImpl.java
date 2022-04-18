package com.vem.atsecserver.service.rawproduct;

import com.vem.atsecserver.entity.rawproduct.EnumRawProductStatus;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.repository.rawproduct.RawProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Service
public class RawProductServiceImpl implements RawProductService {
    @Autowired
    private RawProductRepository rawProductRepository;

    @Override
    public RawProduct create(RawProduct parameter) {
        RawProduct entity = new RawProduct();
        entity.setIssueTissueDate(parameter.getIssueTissueDate());
        entity.setArrivalDate(parameter.getArrivalDate());
        entity.setLocation(parameter.getLocation());
        entity.setInformation(parameter.getInformation());
        entity.setStatus(parameter.getStatus());
        entity.setTissueType(parameter.getTissueType());
        entity.setDonor(parameter.getDonor());
        entity.setResponsibleSigner(parameter.getResponsibleSigner());
        entity.setFiles(parameter.getFiles());
        // entity.setCheckedOutBy(parameter.getCheckedOutBy()); // TODO:
        entity.setDefinition(parameter.getDefinition());
        entity.setDoctorName(parameter.getDoctorName());
        entity.setDeleted(false);
        entity.setFiles(parameter.getFiles());
        entity.setTissueCarryCase(parameter.getTissueCarryCase());
        entity.setSterialBag(parameter.getSterialBag());
        entity.setDataLogger(parameter.getDataLogger());
        entity.setTemperature(parameter.getTemperature());

        return rawProductRepository.save(entity);
    }

    @Override
    public RawProduct update(RawProduct parameter) {
        Optional<RawProduct> byId = rawProductRepository.findById(parameter.getId());
        if (byId.isPresent()) {
            RawProduct entity = byId.get();
            entity.setIssueTissueDate(parameter.getIssueTissueDate());
            entity.setArrivalDate(parameter.getArrivalDate());
            entity.setLocation(parameter.getLocation());
            entity.setInformation(parameter.getInformation());
            entity.setStatus(parameter.getStatus());
            entity.setTissueType(parameter.getTissueType());
            entity.setFiles(parameter.getFiles());
            entity.setDeleted(false);

            entity.setTissueCarryCase(parameter.getTissueCarryCase());
            entity.setSterialBag(parameter.getSterialBag());
            entity.setDataLogger(parameter.getDataLogger());
            entity.setTemperature(parameter.getTemperature());
            return rawProductRepository.save(entity);
        }
        return null;
    }

    @Override
    public List<RawProduct> getAllRawProducts() {
        return rawProductRepository.findAll().stream()
                .filter(e -> !e.getDeleted()
                        && (e.getStatus() != null && !e.getStatus().equals(EnumRawProductStatus.PRE_PROCESSING))
                        && (e.getStatus() != null && !e.getStatus().equals(EnumRawProductStatus.MEDICAL_WASTE))
                        && (e.getStatus() != null && !e.getStatus().equals(EnumRawProductStatus.MEDICAL_WASTE)))
                        .collect(Collectors.toList());
    }

    @Override
    public RawProduct delete(Long id) {
        Optional<RawProduct> byId = rawProductRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setDeleted(true);
            rawProductRepository.save(byId.get());
            return byId.get();
        }
        return null;
    }

    @Override
    public RawProduct findRawProductById(Long id) {
        return rawProductRepository.findById(id).get(); // TODO: get()
    }

    @Override
    public List<RawProduct> getRawProductsByStatus(EnumRawProductStatus status) {
        return rawProductRepository.findByStatus(status);
    }

    @Override
    public RawProduct getRawProductById(long rawProductId) {
        return rawProductRepository.getById(rawProductId);
    }
}
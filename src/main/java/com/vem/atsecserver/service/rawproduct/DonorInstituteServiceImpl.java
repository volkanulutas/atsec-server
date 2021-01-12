package com.vem.atsecserver.service.rawproduct;

import com.vem.atsecserver.entity.rawproduct.DonorInstitute;
import com.vem.atsecserver.repository.rawproduct.DonorInstituteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class DonorInstituteServiceImpl implements DonorInstituteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DonorInstituteServiceImpl.class);

    @Autowired
    private DonorInstituteRepository donorInstituteRepository;

    @Override
    public DonorInstitute create(DonorInstitute request) {
        DonorInstitute donorInstitute = new DonorInstitute();
        donorInstitute.setName(request.getName());
        donorInstitute.setCode(request.getCode());
        donorInstitute.setDeleted(false);
        // donorInstitute.setRawProducts(); // TODO:
        return donorInstituteRepository.save(donorInstitute);
    }

    @Override
    public DonorInstitute update(DonorInstitute request) {
        Optional<DonorInstitute> byId = donorInstituteRepository.findById(request.getId());
        if (byId.isPresent()) {
            byId.get().setName(request.getName());
            byId.get().setCode(request.getCode());
            byId.get().setDeleted(false);
            // byId.get().setRawProducts(); // TODO:
            return donorInstituteRepository.save(byId.get());
        }
        return null;
    }

    @Override
    public List<DonorInstitute> getAllDonorInstitutes() {
        return donorInstituteRepository.findAll().stream().filter(e -> !e.getDeleted()).collect(Collectors.toList());
    }

    @Override
    public DonorInstitute delete(Long id) {
        Optional<DonorInstitute> byId = donorInstituteRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setDeleted(true);
            donorInstituteRepository.save(byId.get());
            return byId.get();
        } else {
            LOGGER.error("Role does not found with id: {}", id);
        }
        return null;
    }

    @Override
    public DonorInstitute findDonorInstituteById(Long id) {
        Optional<DonorInstitute> dIOpt = donorInstituteRepository.findById(id);
        if (dIOpt.isPresent() && !dIOpt.get().getDeleted()) {
            return dIOpt.get();
        }
        return null;
    }

    @Override
    public Boolean existsByCode(String code) {
        return donorInstituteRepository.findByCode(code) != null;
    }

    @Override
    public DonorInstitute findDonorById(Long id) {
        return donorInstituteRepository.findById(id).get();
    }
}
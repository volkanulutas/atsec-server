package com.vem.atsecserver.service.rawproduct;

import com.vem.atsecserver.entity.rawproduct.TissueType;
import com.vem.atsecserver.repository.rawproduct.TissueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
@Service
public class TissueServiceImpl implements TissueService {
    @Autowired
    private TissueRepository tissueRepository;

    @Override
    public TissueType create(TissueType tissueTypePar) {
        TissueType tissueType = new TissueType(tissueTypePar.getName(), tissueTypePar.getDefinition());
        tissueType.setDeleted(false);
        return tissueRepository.save(tissueType);
    }

    @Override
    public TissueType update(TissueType tissueTypePar) {
        Optional<TissueType> byId = tissueRepository.findById(tissueTypePar.getId());
        if (byId.isPresent()) {
            byId.get().setName(tissueTypePar.getName());
            byId.get().setDefinition(tissueTypePar.getDefinition());
            byId.get().setDeleted(false);
            return tissueRepository.save(byId.get());
        }
        return null;
    }

    @Override
    public List<TissueType> getAllTissueTypes() {
        return tissueRepository.findAll().stream().filter(e -> !e.getDeleted()).collect(Collectors.toList());
    }

    @Override
    public TissueType delete(Long id) {
        Optional<TissueType> byId = tissueRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setDeleted(true);
            return tissueRepository.save(byId.get());
        }
        return null;
    }

    @Override
    public TissueType findTissueTypeById(Long id) {
        return tissueRepository.findById(id).get(); // TODO: get()
    }
}
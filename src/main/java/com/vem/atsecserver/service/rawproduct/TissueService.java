package com.vem.atsecserver.service.rawproduct;

import com.vem.atsecserver.entity.rawproduct.TissueType;
import com.vem.atsecserver.entity.user.Permission;

import java.util.List;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
public interface TissueService {
    TissueType create(TissueType permission);

    TissueType update(TissueType permission);

    List<TissueType> getAllTissueTypes();

    TissueType delete(Long id);

    TissueType findTissueTypeById(Long id);
}

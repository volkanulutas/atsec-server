package com.vem.atsecserver.payload.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductStatusDateRequest implements Serializable {

    private Long id;

    private String productStatus;

    private long processDate;
}
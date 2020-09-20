package com.troy.qiuli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author caoqiang
 * @date 2020-09-20 17:42
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDto implements Serializable {

    private Long id;

    private String name;

    private BigDecimal price;

}

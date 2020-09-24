package com.troy.qiuli.service;

import com.troy.qiuli.dao.entity.GoodsOrder;

/**
 * @author caoqiang
 * @date 2020-09-01 17:53
 * @desc
 */
public interface OrderBizService {

    int saveGoodsOrder(GoodsOrder goodsOrder);

    int updateGoodsOrder(GoodsOrder goodsOrder);
}

package com.troy.qiuli.dao.mapper.autogenerated;

import com.troy.qiuli.dao.entity.GoodsOrder;
import com.troy.qiuli.dao.entity.GoodsOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* Created by Mybatis Generator 2020-10-16 11:59:02
*/
public interface GoodsOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_order
     *
     * @mbggenerated Fri Oct 16 11:59:02 CST 2020
     */
    int countByExample(GoodsOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_order
     *
     * @mbggenerated Fri Oct 16 11:59:02 CST 2020
     */
    int deleteByExample(GoodsOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_order
     *
     * @mbggenerated Fri Oct 16 11:59:02 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_order
     *
     * @mbggenerated Fri Oct 16 11:59:02 CST 2020
     */
    int insert(GoodsOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_order
     *
     * @mbggenerated Fri Oct 16 11:59:02 CST 2020
     */
    int insertSelective(GoodsOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_order
     *
     * @mbggenerated Fri Oct 16 11:59:02 CST 2020
     */
    List<GoodsOrder> selectByExample(GoodsOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_order
     *
     * @mbggenerated Fri Oct 16 11:59:02 CST 2020
     */
    GoodsOrder selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_order
     *
     * @mbggenerated Fri Oct 16 11:59:02 CST 2020
     */
    int updateByExampleSelective(@Param("record") GoodsOrder record, @Param("example") GoodsOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_order
     *
     * @mbggenerated Fri Oct 16 11:59:02 CST 2020
     */
    int updateByExample(@Param("record") GoodsOrder record, @Param("example") GoodsOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_order
     *
     * @mbggenerated Fri Oct 16 11:59:02 CST 2020
     */
    int updateByPrimaryKeySelective(GoodsOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_order
     *
     * @mbggenerated Fri Oct 16 11:59:02 CST 2020
     */
    int updateByPrimaryKey(GoodsOrder record);
}
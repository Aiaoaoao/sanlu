package com.yc.education.mapper.basic;

import com.yc.education.model.basic.ProductBasic;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductBasicMapper extends MyMapper<ProductBasic> {


    /**
     * 账款 -- 库存业务查询
     * @param productno 产品编号
     * @param productnoEnd 产品编号
     * @param productname 产品名称
     * @param productnameEnd 产品名称
     * @param unit 单位
     * @param unitEnd 单位
     * @return
     */
    List<ProductBasic> listQueryInventoryCost(@Param("productno") String productno ,@Param("productnoEnd") String productnoEnd ,@Param("productname") String productname ,@Param("productnameEnd") String productnameEnd ,@Param("unit") String unit ,@Param("unitEnd") String unitEnd);

    /**
     * 查询最大编号
     * @return
     */
    String  selectMaxIdnum();


    /**
     * 查询所有产品
     * @return
     */
    List<ProductBasic> selectProductBasic();

    /**
     * 查询所有产品
     * @return
     */
    List<ProductBasic> selectProductBasicByIdnumOrName(@Param("idnumOrName")String idnumOrName);


    /**
     * 查询所有产品
     * @return
     */
    List<ProductBasic> selectProductBasicByIsstop(@Param("isstop")int isstop);


    /**
     * 根据编号查询产品
     * @param idnum
     * @return
     */
    ProductBasic selectProductBasicByIsnum(@Param("idnum") String idnum);


    /**
     * 产品价格设定搜索产品
     * @param isnum  产品编号
     * @param proname 产品名称
     * @param basicunit //基本单位
     * @param protype //产品大类
     * @return 855273
     */
    List<ProductBasic> selectProductBasicSearch(@Param("isnum")String isnum,
                                                @Param("proname")String proname,
                                                @Param("basicunit")long basicunit,
                                                @Param("protype")long protype);






}
package com.yc.education.service.basic;

import com.yc.education.model.basic.RegionBasic;
import com.yc.education.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName RegionBasicService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/6 19:58
 * @Version 1.0
 */
public interface RegionBasicService extends IService<RegionBasic> {


    /**
     * 查询所有区域设定
     * @return
     */
    List<RegionBasic> selectProductBasic(int pageNum,int pageSize);


    List<RegionBasic> selectProductBasic();


    /**
     * 查询所有区域设定
     * @return
     */
    List<RegionBasic> selectProductBasic(String orderAndName,int pageNum,int pageSize);


    List<RegionBasic> selectProductBasic(String orderAndName);

    /**
     * 根据编号查询区域设定
     * @param isnum
     * @return
     */
    RegionBasic selectProductBasicByIsnum(@Param("isnum") String isnum);

}

package com.yc.education.service.basic;

import com.yc.education.model.basic.TransportBasic;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName TransportBasicService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/3/18 14:38
 * @Version 1.0
 */
public interface TransportBasicService extends IService<TransportBasic> {

    /**
     * 查询最大编号
     * @return
     */
    String selectMaxIdnum();

    /**
     * 查询所有供应商 不包括暂停使用供应商
     * @return
     */
    List<TransportBasic> selectTransportBasic(int pageNum, int pageSize);

    /**
     * 查询所有供应商 不包括暂停使用供应商
     * @return
     */
    List<TransportBasic> selectTransportBasic();

    /**
     * 根据编号查询供应商
     * @param idnum
     * @return
     */
    TransportBasic selectTransportBasicByIsnum(String idnum);

    /**
     * 查询所有供应商  包括暂停使用供应商
     * @return
     */
    List<TransportBasic> selectTransportBasicNotSotp(int types);

    /**
     * 查询所有供应商  包括暂停使用供应商
     * @return
     */
    List<TransportBasic> selectTransportBasicNotSotp(int types,int pageNum,int pageSize);

}

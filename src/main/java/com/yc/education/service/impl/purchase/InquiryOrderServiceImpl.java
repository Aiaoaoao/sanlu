package com.yc.education.service.impl.purchase;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.purchase.InquiryOrderMapper;
import com.yc.education.model.purchase.InquiryOrder;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.purchase.InquiryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName InquiryOrderServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/27 17:34
 * @Version 1.0
 */
@Service("InquiryOrderService")
public class InquiryOrderServiceImpl extends BaseService<InquiryOrder> implements InquiryOrderService {

    @Autowired
    private InquiryOrderMapper inquiryOrderMapper;


    @Override
    public String selectMaxIdnum(String currentDate) {
        try {
            return inquiryOrderMapper.selectMaxIdnum(currentDate);
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public List<InquiryOrder> findInquiryOrder() {
        try {
            return inquiryOrderMapper.findInquiryOrder();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<InquiryOrder> findInquiryOrder(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return inquiryOrderMapper.findInquiryOrder();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<InquiryOrder> findInquiryOrder(String order) {
        return inquiryOrderMapper.findInquiryOrderByOrder(order);
    }

    @Override
    public List<InquiryOrder> findInquiryOrder(String order, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return inquiryOrderMapper.findInquiryOrderByOrder(order);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<InquiryOrder> findInquiryOrder(int status, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return inquiryOrderMapper.findInquiryOrderByStatus(status);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<InquiryOrder> findInquiryOrderBySupplier(String supplier, String startTime, String endTime) {
        try {
            return inquiryOrderMapper.findInquiryOrderBySupplier(supplier, startTime, endTime);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<InquiryOrder> findInquiryOrderBySupplier(String supplier, String startTime, String endTime, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return inquiryOrderMapper.findInquiryOrderBySupplier(supplier, startTime, endTime);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<InquiryOrder> findInquiryOrderNotSh() {
        try {
            return inquiryOrderMapper.findInquiryOrderNotSh();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public InquiryOrder findInquiryOrderByOrders(String orders) {
        try {
            return inquiryOrderMapper.findInquiryOrderByOrders(orders);
        } catch (Exception e) {
            return null;
        }
    }


}

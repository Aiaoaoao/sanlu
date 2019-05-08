package com.yc.education.service.impl.account;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.account.AccountInputInvoiceMapper;
import com.yc.education.model.account.AccountInputInvoice;
import com.yc.education.service.account.IAccountInputInvoiceService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author BlueSky
 * @Date 2019-01-09 11:52
 */
@Service
public class AccountInputInvoiceServiceImpl extends BaseService<AccountInputInvoice> implements IAccountInputInvoiceService {

    @Autowired
    AccountInputInvoiceMapper mapper;

    @Override
    public List<AccountInputInvoice> listAccountInputInvoiceByDate(String dateBen, String dateEnd, String supplierBen, String supplierEnd, String invoiceNoBen, String invoiceNoEnd,int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return mapper.listAccountInputInvoiceByDate(dateBen,dateEnd,supplierBen,supplierEnd,invoiceNoBen,invoiceNoEnd);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccountInputInvoice> listAccountInputInvoiceByDate(String dateBen, String dateEnd) {
        try {
            return mapper.listAccountInputInvoiceByDate(dateBen,dateEnd,"","","","");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getMaxOrderNo() {
        try {
            return mapper.getMaxOrderNo();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccountInputInvoice> listOrderNoLike(String orderNo) {
        try {
            return mapper.listOrderNoLike(orderNo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccountInputInvoice> listAccountInputInvoice() {
        try {
            return mapper.listAccountInputInvoice("");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AccountInputInvoice getByOrderNo(String orderno) {
        try {
            return mapper.getByOrderNo(orderno);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccountInputInvoice> listAccountInputInvoice(String text,int page, int rows) {
        try {
            PageHelper.startPage(page, rows);
            return mapper.listAccountInputInvoice(text);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccountInputInvoice> listAccountInputInvoiceNotSh() {
        try {
            return mapper.listAccountInputInvoiceNotSh();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

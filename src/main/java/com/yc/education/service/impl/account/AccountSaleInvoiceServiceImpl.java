package com.yc.education.service.impl.account;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.account.AccountReceiptMapper;
import com.yc.education.mapper.account.AccountSaleInvoiceMapper;
import com.yc.education.model.account.AccountReceipt;
import com.yc.education.model.account.AccountSaleInvoice;
import com.yc.education.service.account.IAccountReceiptService;
import com.yc.education.service.account.IAccountSaleInvoiceService;
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
public class AccountSaleInvoiceServiceImpl extends BaseService<AccountSaleInvoice> implements IAccountSaleInvoiceService {

    @Autowired
    AccountSaleInvoiceMapper mapper;

    @Override
    public List<AccountSaleInvoice> listAccountSaleInvoiceByCustomer(String cusotmerNo) {
        try {
            return mapper.listAccountSaleInvoiceByCustomer(cusotmerNo);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<AccountSaleInvoice> listNotRushAccountSaleInvoice(String customerNo) {
        try {
            return mapper.listNotRushAccountSaleInvoice(customerNo);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public String getMaxOrderNo() {
        try {
            return mapper.getMaxOrderNo();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<AccountSaleInvoice> listOrderNoLike(String orderNo) {
        try {
            return mapper.listOrderNoLike(orderNo);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<AccountSaleInvoice> listAccountSaleInvoice() {
        try {
            return mapper.listAccountSaleInvoice("");
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public AccountSaleInvoice getByOrderNo(String orderno) {
        try {
            return mapper.getByOrderNo(orderno);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<AccountSaleInvoice> listAccountSaleInvoice(String text,int page, int rows) {
        try {
            PageHelper.startPage(page, rows);
            return mapper.listAccountSaleInvoice(text);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<AccountSaleInvoice> listAccountSaleInvoiceNotSh() {
        try {
            return mapper.listAccountSaleInvoiceNotSh();
        }catch (Exception e){
            return null;
        }
    }
}

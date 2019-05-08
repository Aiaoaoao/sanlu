package com.yc.education.service.impl.account;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.account.AccountReceiptMapper;
import com.yc.education.model.account.AccountReceipt;
import com.yc.education.service.account.IAccountReceiptService;
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
public class AccountReceiptServiceImpl extends BaseService<AccountReceipt> implements IAccountReceiptService {

    @Autowired
    AccountReceiptMapper mapper;

    @Override
    public List<AccountReceipt> listAccountReceiptNotShiroOrBalance(String customerNo, String dateBen, String dateEnd) {
        try {
            return mapper.listAccountReceiptNotShiroOrBalance(customerNo,dateBen,dateEnd);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<AccountReceipt> listAccountReceiptByWhere(String customerNo, String customerNoEnd, String dateBen, String dateEnd) {
        try {
            return mapper.listAccountReceiptByWhere(customerNo,customerNoEnd,dateBen,dateEnd);
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
    public List<AccountReceipt> listOrderNoLike(String orderNo) {
        try {
            return mapper.listOrderNoLike(orderNo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccountReceipt> listAccountReceipt() {
        try {
            return mapper.listAccountReceipt("");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AccountReceipt getByOrderNo(String orderno) {
        try {
            return mapper.getByOrderNo(orderno);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccountReceipt> listAccountReceipt(String text,int page, int rows) {
        try {
            PageHelper.startPage(page, rows);
            return mapper.listAccountReceipt(text);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccountReceipt> listAccountReceiptNotSh() {
        try {
            return mapper.listAccountReceiptNotSh();
        }catch (Exception e){
            return null;
        }
    }
}

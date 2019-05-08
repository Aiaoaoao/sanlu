package com.yc.education.service.impl.account;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.account.AccountPrepaymentMapper;
import com.yc.education.mapper.account.AccountPrepaymentMapper;
import com.yc.education.model.account.AccountPrepayment;
import com.yc.education.model.account.AccountPrepayment;
import com.yc.education.service.account.IAccountPrepaymentService;
import com.yc.education.service.account.IAccountPrepaymentService;
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
public class AccountPrepaymentServiceImpl extends BaseService<AccountPrepayment> implements IAccountPrepaymentService {

    @Autowired
    AccountPrepaymentMapper mapper;

    @Override
    public List<AccountPrepayment> listAccountPrepaymentByRecently(int num) {
        try {
            return mapper.listAccountPrepaymentByRecently(num);
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
    public List<AccountPrepayment> listOrderNoLike(String orderNo) {
        try {
            return mapper.listOrderNoLike(orderNo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccountPrepayment> listAccountPrepayment() {
        try {
            return mapper.listAccountPrepayment("");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AccountPrepayment getByOrderNo(String orderno) {
        try {
            return mapper.getByOrderNo(orderno);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccountPrepayment> listAccountPrepayment(String text,int page, int rows) {
        try {
            PageHelper.startPage(page, rows);
            return mapper.listAccountPrepayment(text);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccountPrepayment> listAccountPrepaymentNotSh() {
        try {
            return mapper.listAccountPrepaymentNotSh();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

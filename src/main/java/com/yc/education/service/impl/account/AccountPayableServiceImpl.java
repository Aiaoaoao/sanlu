package com.yc.education.service.impl.account;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.account.AccountPayableMapper;
import com.yc.education.model.account.AccountPayable;
import com.yc.education.service.account.IAccountPayableService;
import com.yc.education.service.impl.BaseService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author BlueSky
 * @Date 2018-12-04 16:51
 */
@Service
public class AccountPayableServiceImpl extends BaseService<AccountPayable> implements IAccountPayableService {

    @Autowired
    AccountPayableMapper mapper;

    @Override
    @SneakyThrows
    public List<AccountPayable> listAccountsPayableByNum(Integer num) {
        return mapper.listAccountsPayableByNum(num);
    }

    @Override
    @SneakyThrows
    public String getMaxOrderNo() {
        return mapper.getMaxOrderNo();
    }

    @Override
    @SneakyThrows
    public List<AccountPayable> listAccountsPayable() {
        return mapper.listAccountsPayable("");
    }

    @Override
    @SneakyThrows
    public List<AccountPayable> listAccountsPayable(String text,int page, int size) {
        PageHelper.startPage(page, size);
        return mapper.listAccountsPayable(text);
    }

    @Override
    public List<AccountPayable> listAccountPayableNotSh() {
        try {
            return mapper.listAccountPayableNotSh();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @SneakyThrows
    public List<AccountPayable> listOrderNoLike(String orderNo) {
        return mapper.listOrderNoLike(orderNo);
    }

    @Override
    @SneakyThrows
    public AccountPayable getByOrderNo(String orderno) {
        return mapper.getByOrderNo(orderno);
    }

}

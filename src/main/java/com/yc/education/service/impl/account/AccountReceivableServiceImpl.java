package com.yc.education.service.impl.account;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.account.AccountReceivableMapper;
import com.yc.education.model.account.AccountReceivable;

import com.yc.education.service.account.IAccountReceivableService;
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
public class AccountReceivableServiceImpl extends BaseService<AccountReceivable> implements IAccountReceivableService {

    @Autowired
    AccountReceivableMapper mapper;

    @Override
    @SneakyThrows
    public List<AccountReceivable> listAccountReceivableByTime(String benTime, String endTime) {
        return mapper.listAccountReceivableByTime(benTime,endTime);
    }

    @Override
    @SneakyThrows
    public List<AccountReceivable> listAccountReceivableByNum(Integer num) {
        return mapper.listAccountReceivableByNum(num);
    }

    @Override
    @SneakyThrows
    public String getMaxOrderNo() {
        return mapper.getMaxOrderNo();
    }

    @Override
    @SneakyThrows
    public List<AccountReceivable> listAccountReceivable() {
        return mapper.listAccountReceivable("");
    }

    @Override
    public List<AccountReceivable> listAccountReceivable(String text,int page, int size) {
        try {
            PageHelper.startPage(page, size);
            return mapper.listAccountReceivable(text);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<AccountReceivable> listAccountReceivableNotSh() {
        try {
            return mapper.listAccountReceivableNotSh();
        }catch (Exception e){
            return null;
        }
    }


    @Override
    @SneakyThrows
    public AccountReceivable getByOrderNo(String orderno) {
        return mapper.getByOrderNo(orderno);
    }

}

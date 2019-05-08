package com.yc.education.service.impl.account;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.account.AccountCoastAccountingMapper;
import com.yc.education.model.account.AccountCoastAccounting;
import com.yc.education.service.account.IAccountCoastAccountingService;
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
public class AccountCoastAccountingServiceImpl extends BaseService<AccountCoastAccounting> implements IAccountCoastAccountingService {

    @Autowired
    AccountCoastAccountingMapper mapper;

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
    @SneakyThrows
    public List<AccountCoastAccounting> listAccountCoastAccounting() {
        return mapper.listAccountCoastAccounting("");
    }

    @Override
    @SneakyThrows
    public AccountCoastAccounting getByOrderNo(String orderno) {
        return mapper.getByOrderNo(orderno);
    }

    @Override
    @SneakyThrows
    public List<AccountCoastAccounting> listAccountCoastAccounting(String text,int page, int size) {
        PageHelper.startPage(page, size);
        return mapper.listAccountCoastAccounting(text);
    }
}

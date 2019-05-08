package com.yc.education.service.impl.account;

import com.yc.education.mapper.account.AccountReceivableRushMapper;
import com.yc.education.model.account.AccountReceivableRush;
import com.yc.education.service.account.IAccountReceivableRushService;
import com.yc.education.service.impl.BaseService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author BlueSky
 * @Date 2018-12-07 11:44
 */
@Service
public class AccountReceivableRushServiceImpl extends BaseService<AccountReceivableRush> implements IAccountReceivableRushService {

    @Autowired
    AccountReceivableRushMapper mapper;

    @Override
    @SneakyThrows
    public List<AccountReceivableRush> listAccountReceivableRush(String otherid) {
        return mapper.listAccountReceivableRush(otherid);
    }

    @Override
    public int deleteAccountReceivableRushByParentId(String otherid) {
        try {
            return mapper.deleteAccountReceivableRushByParentId(otherid);
        }catch (Exception e){
            e.printStackTrace();
            return  0;
        }
    }
}

package com.yc.education.service.impl.account;

import com.yc.education.mapper.account.AccountReceivableFeeMapper;
import com.yc.education.model.account.AccountReceivableFee;
import com.yc.education.service.account.IAccountReceivableFeeService;
import com.yc.education.service.impl.BaseService;
import lombok.SneakyThrows;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author BlueSky
 * @Date 2018-12-07 11:44
 */
@Service
public class AccountReceivableFeeServiceImpl extends BaseService<AccountReceivableFee> implements IAccountReceivableFeeService {

    @Autowired
    AccountReceivableFeeMapper mapper;

    @Override
    @SneakyThrows
    public List<AccountReceivableFee> listAccountReceivableFee(String otherid) {
        return mapper.listAccountReceivableFee(otherid);
    }

    @Override
    public int deleteAccountReceivableFeeByParentId(String otherid) {
        try {
            return mapper.deleteAccountReceivableFeeByParentId(otherid);
        }   catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}

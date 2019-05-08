package com.yc.education.service.impl.account;

import com.yc.education.mapper.account.AccountPayableInfoMapper;
import com.yc.education.mapper.account.AccountReceiptInfoMapper;
import com.yc.education.model.account.AccountPayableInfo;
import com.yc.education.model.account.AccountReceiptInfo;
import com.yc.education.service.account.IAccountPayableInfoService;
import com.yc.education.service.account.IAccountReceiptInfoService;
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
public class AccountPayableInfoServiceImpl extends BaseService<AccountPayableInfo> implements IAccountPayableInfoService {

    @Autowired
    AccountPayableInfoMapper mapper;

    @Override
    public List<AccountPayableInfo> listAccountPayableInfo(String otherid) {
        try {
            return mapper.listAccountPayableInfo(otherid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteAccountPayableInfoByParentId(String otherid) {
        try {
            return mapper.deleteAccountPayableInfoByParentId(otherid);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}

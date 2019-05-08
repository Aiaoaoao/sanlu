package com.yc.education.service.impl.account;

import com.yc.education.mapper.account.AccountPrepaymentInfoMapper;
import com.yc.education.model.account.AccountPrepaymentInfo;
import com.yc.education.service.account.IAccountPrepaymentInfoService;
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
public class AccountPrepaymentInfoServiceImpl extends BaseService<AccountPrepaymentInfo> implements IAccountPrepaymentInfoService {

    @Autowired
    AccountPrepaymentInfoMapper mapper;


    @Override
    public List<AccountPrepaymentInfo> listAccountPrepaymentInfo(String otherid) {
        try {
            return mapper.listAccountPrepaymentInfo(otherid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteAccountPrepaymentInfoByParentId(String otherid) {
        try{
            return mapper.deleteAccountPrepaymentInfoByParentId(otherid);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}

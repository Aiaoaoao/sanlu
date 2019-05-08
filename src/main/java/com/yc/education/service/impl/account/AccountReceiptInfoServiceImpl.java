package com.yc.education.service.impl.account;

import com.yc.education.mapper.account.AccountReceiptInfoMapper;
import com.yc.education.model.account.AccountReceiptInfo;
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
public class AccountReceiptInfoServiceImpl extends BaseService<AccountReceiptInfo> implements IAccountReceiptInfoService {

    @Autowired
    AccountReceiptInfoMapper mapper;


    @Override
    public List<AccountReceiptInfo> listAccountReceiptInfo(String otherid) {
        try {
            return mapper.listAccountReceiptInfo(otherid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteAccountReceiptInfoByParentId(String otherid) {
        try {
            return mapper.deleteAccountReceiptInfoByParentId(otherid);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}

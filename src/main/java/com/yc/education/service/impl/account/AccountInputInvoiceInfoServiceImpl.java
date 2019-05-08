package com.yc.education.service.impl.account;

import com.yc.education.mapper.account.AccountInputInvoiceInfoMapper;
import com.yc.education.model.account.AccountInputInvoiceInfo;
import com.yc.education.service.account.IAccountInputInvoiceInfoService;
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
public class AccountInputInvoiceInfoServiceImpl extends BaseService<AccountInputInvoiceInfo> implements IAccountInputInvoiceInfoService {

    @Autowired
    AccountInputInvoiceInfoMapper mapper;


    @Override
    public List<AccountInputInvoiceInfo> listAccountInputInvoiceInfo(String otherid) {
        try {
            return mapper.listAccountInputInvoiceInfo(otherid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteAccountInputInvoiceInfoByParentId(String otherid) {
        try{
            return mapper.deleteAccountInputInvoiceInfoByParentId(otherid);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}

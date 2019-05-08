package com.yc.education.service.impl.account;

import com.yc.education.mapper.account.AccountCoastPurchaseMapper;
import com.yc.education.model.account.AccountCoastPurchase;
import com.yc.education.service.account.IAccountCoastPurchaseService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author BlueSky
 * @Date 2018-12-04 16:51
 */
@Service
public class AccountCoastPurchaseServiceImpl extends BaseService<AccountCoastPurchase> implements IAccountCoastPurchaseService {

    @Autowired
    AccountCoastPurchaseMapper mapper;

    @Override
    public List<AccountCoastPurchase> listAccountCoastPurchase(String otherid) {
        try {
            return mapper.listAccountCoastPurchase(otherid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}

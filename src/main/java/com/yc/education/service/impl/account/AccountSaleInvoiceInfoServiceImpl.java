package com.yc.education.service.impl.account;

import com.yc.education.mapper.account.AccountSaleInvoiceInfoMapper;
import com.yc.education.model.account.AccountSaleInvoiceInfo;
import com.yc.education.service.account.IAccountSaleInvoiceInfoService;
import com.yc.education.service.impl.BaseService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author BlueSky
 * @Date 2019-01-09 11:52
 */
@Service
public class AccountSaleInvoiceInfoServiceImpl extends BaseService<AccountSaleInvoiceInfo> implements IAccountSaleInvoiceInfoService {

    @Autowired
    AccountSaleInvoiceInfoMapper mapper;

    @Override
    public Integer getOrderAuditStatusByOrderNo(String orderNo) {
        try {
            return mapper.getOrderAuditStatusByOrderNo(orderNo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccountSaleInvoiceInfo> listAccountSaleInvoiceInfo(String otherid) {
        try {
            return mapper.listAccountSaleInvoiceInfo(otherid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteAccountSaleInvoiceInfoByParentId(String otherid) {
        try {
            return mapper.deleteAccountSaleInvoiceInfoByParentId(otherid);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}

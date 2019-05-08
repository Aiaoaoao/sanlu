
import com.yc.education.model.purchase.PurchaseProduct;
import com.yc.education.service.purchase.PurchaseProductService;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 *@Description TODO
 *@Author QuZhangJing
 *@Date 15:57  2019/5/5
 *@Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
//配置了@ContextConfiguration注解并使用该注解的locations属性指明spring和配置文件
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:mybatis-config.xml" })
public class ClawUtil {

    @Autowired
    private PurchaseProductService purchaseProductService;

    @Test
   public void testProduct(){
       List<PurchaseProduct> purchaseProducts =  purchaseProductService.findPurchaseProductNew("",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
               1,18
                );
        for (PurchaseProduct purchaseProduct:purchaseProducts) {
            System.out.println("==="+purchaseProduct.getProorders());
        }

   }
}

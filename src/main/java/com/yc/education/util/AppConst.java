package com.yc.education.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;

public class AppConst {

	/**
	 * 小窗口分页行数
	 */
	public static int ROWS = 30;

	/**
	 * 销货明细表 - 业务别
	 */
	public static final String[] SALE_DETAIL_BUSINESS = new String[]{"业务负责","销货日期","销货单号","客户编号","客户简称","仓库名称","币别","销售净额","税额","销售总金额","税别"};


	/**
	 * 销货明细表 - 产品别
	 */
	public static final String[] SALE_DETAIL_PRODUCT = new String[]{"产品编号","产品名称","单据日期","单据单号","客户简称","业务负责","仓库名称","币别","单价","销售金额","小计","税别"};


	/**
	 * 销货明细表 - 日期别
	 */
	public static final String[] SALE_DETAIL_DATE = new String[]{"销货日期","销货单号","客户简称","业务负责","仓库名称","币别","销售净额","税额","销货总金额","税别"};

	/**
	 * 销货明细表 - 日期别 参数
	 */
	public static final String[] SALE_DETAIL_DATE_P = new String[]{"create_date","sale_no","customer_no_str","business_leader_str","warehouse_out_str","currency","sale_money","tax_money","calc_money","calc_money","tax"};

	/**
	 * 销项-税率
	 */
	public static String RATE = "0.16";

    /**
     * sm4 加密 key
     */
	public static  final String  SM4KEY="sanlu#";

	/**
	 * 弹框--提示头
	 */
	public static  final String  ALERT_HEADER="三禄ERP提醒您";
	/**
	 * 弹框--删除
	 */
	public static  final String  ALERT_DELETE="您确认要删除吗？若确认删除后数据则无法恢复！";
	/**
	 * 弹框-- 作废
	 */
	public static  final String  ALERT_ABOLISH="您确认要作废吗？";
	/**
	 *弹框-- 审核
	 */
	public static final  String ALERT_EXAMINE = "该单据无审核，无法进行此操作!";
	/**
	 * 弹框 -- 取消审核
	 */
	public static final  String ALERT_OCCUPY = "该单据被占用,无法取消审核!";
	/**
	 * 待审核单据
	 */
	public static final String STAY_ORDER[] ={"采购入库单","销货出库单","库存异动作业","盘库作业","销货单","销售退货单","报价单","订货单","网上订单","采购订单","询价单","应收账款冲账","应付账款冲账","销项发票","进项发票","收款单","预付账款",};
	/**
	 * 关联单据类型
	 */
	public static final String RELATION[] = {"采购入库单","销货出库单","库存异动作业","盘库作业","快递收件","快递寄件","销货单","销售退货单","报价单","订货单","网上订单","采购订单","询价单","应收账款冲账","应付账款冲账"};
	/**
	 *
	 */
	public static  final  String COST_PASS = "(已核算成本)";
	/**
	 *
	 */
	public static  final  String COST_NO = "(未核算成本)";

	/**
	 * 获取JDBC连接
	 * @return
	 */
	public static Connection getConnection(){
		try {
			//加载DB驱动，并获得DB连接
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/sanlu?characterEncoding=UTF-8";
			String user = "root";
			String password = "root";
			return DriverManager.getConnection(url, user, password);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

}

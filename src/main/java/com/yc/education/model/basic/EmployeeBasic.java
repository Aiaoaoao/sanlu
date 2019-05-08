package com.yc.education.model.basic;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO 员工表
 *@Author QuZhangJing
 *@Date 15:51  2018/8/31
 *@Version 1.0
 */
@Table(name = "employee_basic")
public class EmployeeBasic {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 员工编号
     */
    private String idnum;

    /**
     * 员工工号
     */
    private String jobnum;

    /**
     * 员工姓名
     */
    private String empname;

    /**
     * 员工性别1、男  2、女
     */
    private Integer empsex;

    /**
     * 员工身份证
     */
    private String empcard;

    /**
     * 英文名称
     */
    private String englishname;

    /**
     * 国家
     */
    private Integer country;

    /**
     * 城市
     */
    private String area;

    /**
     * 护照编号
     */
    private String passportnum;

    /**
     * 出生地
     */
    private Integer birthplace;

    /**
     * 民族
     */
    private Integer nation;

    /**
     * 婚姻状态
     */
    private Integer marital;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 建档人
     */
    private String addpeople;

    /**
     * 建档日期
     */
    private String adddate;

    /**
     * 最后修改人
     */
    private String updatepeople;

    /**
     * 最后修改日期
     */
    private String updatedate;

    /**
     * 暂停使用
     */
    private Integer isstop;

    /**
     * 暂停描述
     */
    private String stopdes;

    /**
     * 部门编号
     */
    private Long department;

    /**
     * 职位
     */
    private Integer duty;

    /**
     * 使用时长
     */
    private Integer usedate;

    /**
     * 到职日期
     */
    private Date comedate;

    /**
     * 转正日期
     */
    private Date okdate;

    /**
     * 任职状态
     */
    private Integer dutystatus;

    /**
     * 停职日期
     */
    private String outdate;

    /**
     * 考勤卡号
     */
    private String checknum;

    /**
     * 职位津贴
     */
    private Double subsdiy;

    /**
     * 工资等级
     */
    private Integer grade;

    /**
     * 缴纳保险
     */
    private Integer insurance;

    /**
     * 登录密码
     */
    private String password;


    @Transient
    private int sort;


    public EmployeeBasic() {

    }

    public EmployeeBasic(Long id, String idnum, String jobnum, String empname, Integer empsex, String empcard, String englishname, Integer country, String area, String passportnum, Integer birthplace, Integer nation, Integer marital, String phone, String address, String remarks, String email, String addpeople, String adddate, String updatepeople, String updatedate, Integer isstop, String stopdes, Long department, Integer duty, Integer usedate, Date comedate, Date okdate, Integer dutystatus, String outdate, String checknum, Double subsdiy, Integer grade, Integer insurance) {
        this.id = id;
        this.idnum = idnum;
        this.jobnum = jobnum;
        this.empname = empname;
        this.empsex = empsex;
        this.empcard = empcard;
        this.englishname = englishname;
        this.country = country;
        this.area = area;
        this.passportnum = passportnum;
        this.birthplace = birthplace;
        this.nation = nation;
        this.marital = marital;
        this.phone = phone;
        this.address = address;
        this.remarks = remarks;
        this.email = email;
        this.addpeople = addpeople;
        this.adddate = adddate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.isstop = isstop;
        this.stopdes = stopdes;
        this.department = department;
        this.duty = duty;
        this.usedate = usedate;
        this.comedate = comedate;
        this.okdate = okdate;
        this.dutystatus = dutystatus;
        this.outdate = outdate;
        this.checknum = checknum;
        this.subsdiy = subsdiy;
        this.grade = grade;
        this.insurance = insurance;
    }


    public EmployeeBasic( String idnum, String jobnum, String empname, Integer empsex, String empcard, String englishname, Integer country, String area, String passportnum, Integer birthplace, Integer nation, Integer marital, String phone, String address, String remarks, String email, String addpeople, String adddate, String updatepeople, String updatedate, Integer isstop, String stopdes, Long department, Integer duty, Integer usedate, Date comedate, Date okdate, Integer dutystatus, String outdate, String checknum, Double subsdiy, Integer grade, Integer insurance) {
        this.idnum = idnum;
        this.jobnum = jobnum;
        this.empname = empname;
        this.empsex = empsex;
        this.empcard = empcard;
        this.englishname = englishname;
        this.country = country;
        this.area = area;
        this.passportnum = passportnum;
        this.birthplace = birthplace;
        this.nation = nation;
        this.marital = marital;
        this.phone = phone;
        this.address = address;
        this.remarks = remarks;
        this.email = email;
        this.addpeople = addpeople;
        this.adddate = adddate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.isstop = isstop;
        this.stopdes = stopdes;
        this.department = department;
        this.duty = duty;
        this.usedate = usedate;
        this.comedate = comedate;
        this.okdate = okdate;
        this.dutystatus = dutystatus;
        this.outdate = outdate;
        this.checknum = checknum;
        this.subsdiy = subsdiy;
        this.grade = grade;
        this.insurance = insurance;
    }


    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取员工编号
     *
     * @return idnum - 员工编号
     */
    public String getIdnum() {
        return idnum;
    }

    /**
     * 设置员工编号
     *
     * @param idnum 员工编号
     */
    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    /**
     * 获取员工工号
     *
     * @return jobnum - 员工工号
     */
    public String getJobnum() {
        return jobnum;
    }

    /**
     * 设置员工工号
     *
     * @param jobnum 员工工号
     */
    public void setJobnum(String jobnum) {
        this.jobnum = jobnum;
    }

    /**
     * 获取员工姓名
     *
     * @return empname - 员工姓名
     */
    public String getEmpname() {
        return empname;
    }

    /**
     * 设置员工姓名
     *
     * @param empname 员工姓名
     */
    public void setEmpname(String empname) {
        this.empname = empname;
    }

    /**
     * 获取员工性别1、男  2、女
     *
     * @return empsex - 员工性别1、男  2、女
     */
    public Integer getEmpsex() {
        return empsex;
    }

    /**
     * 设置员工性别1、男  2、女
     *
     * @param empsex 员工性别1、男  2、女
     */
    public void setEmpsex(Integer empsex) {
        this.empsex = empsex;
    }

    /**
     * 获取员工身份证
     *
     * @return empcard - 员工身份证
     */
    public String getEmpcard() {
        return empcard;
    }

    /**
     * 设置员工身份证
     *
     * @param empcard 员工身份证
     */
    public void setEmpcard(String empcard) {
        this.empcard = empcard;
    }

    /**
     * 获取英文名称
     *
     * @return englishname - 英文名称
     */
    public String getEnglishname() {
        return englishname;
    }

    /**
     * 设置英文名称
     *
     * @param englishname 英文名称
     */
    public void setEnglishname(String englishname) {
        this.englishname = englishname;
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public Integer getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(Integer country) {
        this.country = country;
    }

    /**
     * 获取城市
     *
     * @return area - 城市
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置城市
     *
     * @param area 城市
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取护照编号
     *
     * @return passportnum - 护照编号
     */
    public String getPassportnum() {
        return passportnum;
    }

    /**
     * 设置护照编号
     *
     * @param passportnum 护照编号
     */
    public void setPassportnum(String passportnum) {
        this.passportnum = passportnum;
    }

    /**
     * 获取出生地
     *
     * @return birthplace - 出生地
     */
    public Integer getBirthplace() {
        return birthplace;
    }

    /**
     * 设置出生地
     *
     * @param birthplace 出生地
     */
    public void setBirthplace(Integer birthplace) {
        this.birthplace = birthplace;
    }

    /**
     * 获取民族
     *
     * @return nation - 民族
     */
    public Integer getNation() {
        return nation;
    }

    /**
     * 设置民族
     *
     * @param nation 民族
     */
    public void setNation(Integer nation) {
        this.nation = nation;
    }

    /**
     * 获取婚姻状态
     *
     * @return marital - 婚姻状态
     */
    public Integer getMarital() {
        return marital;
    }

    /**
     * 设置婚姻状态
     *
     * @param marital 婚姻状态
     */
    public void setMarital(Integer marital) {
        this.marital = marital;
    }

    /**
     * 获取联系电话
     *
     * @return phone - 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取建档人
     *
     * @return addpeople - 建档人
     */
    public String getAddpeople() {
        return addpeople;
    }

    /**
     * 设置建档人
     *
     * @param addpeople 建档人
     */
    public void setAddpeople(String addpeople) {
        this.addpeople = addpeople;
    }



    /**
     * 获取最后修改人
     *
     * @return updatepeople - 最后修改人
     */
    public String getUpdatepeople() {
        return updatepeople;
    }

    /**
     * 设置最后修改人
     *
     * @param updatepeople 最后修改人
     */
    public void setUpdatepeople(String updatepeople) {
        this.updatepeople = updatepeople;
    }



    /**
     * 获取暂停使用
     *
     * @return isstop - 暂停使用
     */
    public Integer getIsstop() {
        return isstop;
    }

    /**
     * 设置暂停使用
     *
     * @param isstop 暂停使用
     */
    public void setIsstop(Integer isstop) {
        this.isstop = isstop;
    }

    /**
     * 获取暂停描述
     *
     * @return stopdes - 暂停描述
     */
    public String getStopdes() {
        return stopdes;
    }

    /**
     * 设置暂停描述
     *
     * @param stopdes 暂停描述
     */
    public void setStopdes(String stopdes) {
        this.stopdes = stopdes;
    }

    /**
     * 获取部门编号
     *
     * @return department - 部门编号
     */
    public Long getDepartment() {
        return department;
    }

    /**
     * 设置部门编号
     *
     * @param department 部门编号
     */
    public void setDepartment(Long department) {
        this.department = department;
    }

    /**
     * 获取职位
     *
     * @return duty - 职位
     */
    public Integer getDuty() {
        return duty;
    }

    /**
     * 设置职位
     *
     * @param duty 职位
     */
    public void setDuty(Integer duty) {
        this.duty = duty;
    }

    /**
     * 获取使用时长
     *
     * @return usedate - 使用时长
     */
    public Integer getUsedate() {
        return usedate;
    }

    /**
     * 设置使用时长
     *
     * @param usedate 使用时长
     */
    public void setUsedate(Integer usedate) {
        this.usedate = usedate;
    }

    /**
     * 获取到职日期
     *
     * @return comedate - 到职日期
     */
    public Date getComedate() {
        return comedate;
    }

    /**
     * 设置到职日期
     *
     * @param comedate 到职日期
     */
    public void setComedate(Date comedate) {
        this.comedate = comedate;
    }

    /**
     * 获取转正日期
     *
     * @return okdate - 转正日期
     */
    public Date getOkdate() {
        return okdate;
    }

    /**
     * 设置转正日期
     *
     * @param okdate 转正日期
     */
    public void setOkdate(Date okdate) {
        this.okdate = okdate;
    }

    /**
     * 获取任职状态
     *
     * @return dutystatus - 任职状态
     */
    public Integer getDutystatus() {
        return dutystatus;
    }

    /**
     * 设置任职状态
     *
     * @param dutystatus 任职状态
     */
    public void setDutystatus(Integer dutystatus) {
        this.dutystatus = dutystatus;
    }

    public String getOutdate() {
        return outdate;
    }

    public void setOutdate(String outdate) {
        this.outdate = outdate;
    }

    /**
     * 获取考勤卡号
     *
     * @return checknum - 考勤卡号
     */
    public String getChecknum() {
        return checknum;
    }

    /**
     * 设置考勤卡号
     *
     * @param checknum 考勤卡号
     */
    public void setChecknum(String checknum) {
        this.checknum = checknum;
    }

    /**
     * 获取职位津贴
     *
     * @return subsdiy - 职位津贴
     */
    public Double getSubsdiy() {
        return subsdiy;
    }

    /**
     * 设置职位津贴
     *
     * @param subsdiy 职位津贴
     */
    public void setSubsdiy(Double subsdiy) {
        this.subsdiy = subsdiy;
    }

    /**
     * 获取工资等级
     *
     * @return grade - 工资等级
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * 设置工资等级
     *
     * @param grade 工资等级
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * 获取缴纳保险
     *
     * @return insurance - 缴纳保险
     */
    public Integer getInsurance() {
        return insurance;
    }

    /**
     * 设置缴纳保险
     *
     * @param insurance 缴纳保险
     */
    public void setInsurance(Integer insurance) {
        this.insurance = insurance;
    }

    public String getAdddate() {
        return adddate;
    }

    public void setAdddate(String adddate) {
        this.adddate = adddate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
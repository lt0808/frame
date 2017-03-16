package com.frame.web.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import com.frame.common.basis.IdEntity;
@Entity
@Table(name = "web_bank_order")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BankOrder extends IdEntity<BankOrder> {
	private String orderType;//这里用户的考试类型吧  方便查询
	private String orderNo;//订单号
	private String orderDesc;
	private String userId;//用户Id
	private String userName;//用户名
	private String userTrueName;//用户真实姓名
	private String userSfz;//身份证
	private String userTel;//电话
	private String userPhone;//手机
	private String userHome;
	private String userBlankCard;//银行卡号
	private String userPaymentHostdate;//交易日期年月日
	private String userPaymentHosttime;//交易时间时分秒
	private String userPaymentAmount;//交易金额
	private String userBatchno;//商户号码
	private String merchanremarks;
	private String paytype;//订单对象
	private String notifytype;
	private String userPaymenttype;//订单对象
	private String userPaymentVouchno;//交易类型   001 消费交易
	private String userPaymentInfo;
	
	private Integer status;
    @Length(max=50)
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	@Length(max=1000)
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	@Length(max=1000)
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	@Length(max=50)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Length(max=50)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Length(max=50)
	public String getUserTrueName() {
		return userTrueName;
	}
	public void setUserTrueName(String userTrueName) {
		this.userTrueName = userTrueName;
	}
	@Length(max=50)
	public String getUserSfz() {
		return userSfz;
	}
	public void setUserSfz(String userSfz) {
		this.userSfz = userSfz;
	}
	@Length(max=100)
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	@Length(max=100)
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	@Length(max=200)
	public String getUserHome() {
		return userHome;
	}
	public void setUserHome(String userHome) {
		this.userHome = userHome;
	}
	@Length(max=50)
	public String getUserBlankCard() {
		return userBlankCard;
	}
	public void setUserBlankCard(String userBlankCard) {
		this.userBlankCard = userBlankCard;
	}
	@Length(max=200)
	public String getUserPaymentHostdate() {
		return userPaymentHostdate;
	}
	public void setUserPaymentHostdate(String userPaymentHostdate) {
		this.userPaymentHostdate = userPaymentHostdate;
	}
	@Length(max=200)
	public String getUserPaymentHosttime() {
		return userPaymentHosttime;
	}
	public void setUserPaymentHosttime(String userPaymentHosttime) {
		this.userPaymentHosttime = userPaymentHosttime;
	}
	@Length(max=1000)
	public String getUserPaymentAmount() {
		return userPaymentAmount;
	}
	public void setUserPaymentAmount(String userPaymentAmount) {
		this.userPaymentAmount = userPaymentAmount;
	}
	@Length(max=200)
	public String getUserBatchno() {
		return userBatchno;
	}
	public void setUserBatchno(String userBatchno) {
		this.userBatchno = userBatchno;
	}
	@Length(max=2000)
	public String getMerchanremarks() {
		return merchanremarks;
	}
	public void setMerchanremarks(String merchanremarks) {
		this.merchanremarks = merchanremarks;
	}
	@Length(max=1000)
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	@Length(max=1000)
	public String getNotifytype() {
		return notifytype;
	}
	public void setNotifytype(String notifytype) {
		this.notifytype = notifytype;
	}
	@Length(max=50)
	public String getUserPaymenttype() {
		return userPaymenttype;
	}
	public void setUserPaymenttype(String userPaymenttype) {
		this.userPaymenttype = userPaymenttype;
	}
	@Length(max=200)
	public String getUserPaymentVouchno() {
		return userPaymentVouchno;
	}
	public void setUserPaymentVouchno(String userPaymentVouchno) {
		this.userPaymentVouchno = userPaymentVouchno;
	}
	@Length(max=200)
	public String getUserPaymentInfo() {
		return userPaymentInfo;
	}
	public void setUserPaymentInfo(String userPaymentInfo) {
		this.userPaymentInfo = userPaymentInfo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}

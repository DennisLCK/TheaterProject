package com.web.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "Bulletin")
public class BulletinBean implements Serializable {
	private static final long serialVersionUID = 8705745439713457086L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bulletin_no")
	private Integer no;
	private Integer countNum = 0;// 修改次數
	private String bortingId;// 文章追蹤id
	private Boolean available = true;// 是否存在 true=存在 false=刪除
	private String title;
	private String context;
	private String startDate;
	private String endDate;
	private Date postTime;
	private Integer discount;
	private Integer discountTicketBuy;
	private Integer discountTicketFree;
	private Integer discountPriceBuy;
	private Integer discountPriceFree;
	@Transient
	private String imgUrlString;
	@Transient
	private Integer employeeId;
	private String fileName;
	private Blob coverImage;
	@Transient
	private MultipartFile bulletinImage;
	@Transient
	private String pay;
	@Transient
	private String free;

	@ManyToOne
	@JoinColumn(name = "fk_employee_id")
	private EmployeeBean employee;

	public Integer getCountNum() {
		return countNum;
	}

	public void setCountNum(Integer count) {
		this.countNum = count;
	}

	public String getBortingId() {
		return bortingId;
	}

	public void setBortingId(String bortingId) {
		this.bortingId = bortingId;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getDiscountTicketBuy() {
		return discountTicketBuy;
	}

	public void setDiscountTicketBuy(Integer discountTicketBuy) {
		this.discountTicketBuy = discountTicketBuy;
	}

	public Integer getDiscountTicketFree() {
		return discountTicketFree;
	}

	public void setDiscountTicketFree(Integer discountTicketFree) {
		this.discountTicketFree = discountTicketFree;
	}

	public Integer getDiscountPriceBuy() {
		return discountPriceBuy;
	}

	public void setDiscountPriceBuy(Integer discountPriceBuy) {
		this.discountPriceBuy = discountPriceBuy;
	}

	public Integer getDiscountPriceFree() {
		return discountPriceFree;
	}

	public void setDiscountPriceFree(Integer discountPriceFree) {
		this.discountPriceFree = discountPriceFree;
	}

	public String getImgUrlString() {
		return imgUrlString;
	}

	public void setImgUrlString(String imgUrlString) {
		this.imgUrlString = imgUrlString;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Blob getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(Blob coverImage) {
		this.coverImage = coverImage;
	}

	public MultipartFile getBulletinImage() {
		return bulletinImage;
	}

	public void setBulletinImage(MultipartFile bulletinImage) {
		this.bulletinImage = bulletinImage;
	}

	public EmployeeBean getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeBean employee) {
		this.employee = employee;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
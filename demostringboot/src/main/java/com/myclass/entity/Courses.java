package com.myclass.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity(name = "courses")
public class Courses {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "title")
	private String name;

	private String description;
	private String image;
	private long price;
	private int discount;
	private String content;

	@Column(name = "promotion_price")
	private long promotionPrice;

	@Column(name = "letures_count")
	private int leturesCount;

	@Column(name = "hour_count")
	private int hourCount;

	@Column(name = "view_count")
	private int viewCount;

	@Column(name = "category_id")
	@NotBlank(message = "Vui lòng chọn category!")
	private int categoryId;

	@ManyToOne()
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	private Category category;

	@OneToMany(mappedBy = "courses")
	private List<Users_Courses> usersCourses;

	@OneToMany(mappedBy = "courses")
	private List<Target> targets;

	@OneToMany(mappedBy = "courses")
	private List<Video> videos;
	
	public Courses() {
		// TODO Auto-generated constructor stub
	}

	public Courses(int id, String name, String description, String image, long price, int discount, String content,
			long promotionPrice, int leturesCount, int hourCount, int viewCount, int categoryId, Category category,
			List<Users_Courses> usersCourses, List<Target> targets, List<Video> videos) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
		this.discount = discount;
		this.content = content;
		this.promotionPrice = promotionPrice;
		this.leturesCount = leturesCount;
		this.hourCount = hourCount;
		this.viewCount = viewCount;
		this.categoryId = categoryId;
		this.category = category;
		this.usersCourses = usersCourses;
		this.targets = targets;
		this.videos = videos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(long promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public int getLeturesCount() {
		return leturesCount;
	}

	public void setLeturesCount(int leturesCount) {
		this.leturesCount = leturesCount;
	}

	public int getHourCount() {
		return hourCount;
	}

	public void setHourCount(int hourCount) {
		this.hourCount = hourCount;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Users_Courses> getUsersCourses() {
		return usersCourses;
	}

	public void setUsersCourses(List<Users_Courses> usersCourses) {
		this.usersCourses = usersCourses;
	}

	public List<Target> getTargets() {
		return targets;
	}

	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

}

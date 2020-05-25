package com.example.announcements.models;


import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="announcement")
public class Announcement {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	@Column(name = "announcement_id")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	private Category category_id;

	@ManyToOne(cascade = CascadeType.ALL)
	private User user_id;

	@Column(name = "announcement_name", length=50)
	private String name;

	@Column(name = "price")
	private Float price;

	@Column(name = "description", length=5000)
	private String description;

	@Column(name = "status", length=50)
	private String status;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] image;

	@Column(name = "is_hidden")
	private Boolean is_hidden = false;

	@Column(name = "phone_number", length=15)
	private String phone_number;

	@Column(name = "datetime")
	private Date datetime = Calendar.getInstance().getTime();

	@Column(name = "location")
	private String location;

	@OneToOne(cascade = CascadeType.ALL)
	private PrivateMessage private_message_id;


	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Category category_id) {
		this.category_id = category_id;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Boolean getIs_hidden() {
		return is_hidden;
	}

	public void setIs_hidden(Boolean is_hidden) {
		this.is_hidden = is_hidden;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public PrivateMessage getPrivate_message_id() {
		return private_message_id;
	}

	public void setPrivate_message_id(PrivateMessage private_message_id) {
		this.private_message_id = private_message_id;
	}
}

package com.example.announcements.models;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="private_message")
public class PrivateMessage {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	@Column(name = "message_id")
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	private Announcement announcement_id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User buyer;

	@Column(name = "message", length=2500)
	private String message;

	@Column(name = "datetime")
	private Date datetime = Calendar.getInstance().getTime();

	public PrivateMessage() {
		this(null, null, null, null, null);
	}

	public PrivateMessage(Integer id, Announcement announcement_id, User buyer, String message, Date datetime) {
		this.id = id;
		this.announcement_id = announcement_id;
		this.buyer = buyer;
		this.message = message;
		this.datetime = datetime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Announcement getAnnouncement_id() {
		return announcement_id;
	}

	public void setAnnouncement_id(Announcement announcement_id) {
		this.announcement_id = announcement_id;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
}

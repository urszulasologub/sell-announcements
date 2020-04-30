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

}

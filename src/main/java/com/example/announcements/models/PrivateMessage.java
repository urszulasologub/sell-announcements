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
	private int id;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="private_message_id")
	private Set<Announcement> announcements = new HashSet<Announcement>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User buyer;

	@Column(name = "message", length=2500)
	private String message;

	@Column(name = "datetime")
	private Date datetime = Calendar.getInstance().getTime();

}
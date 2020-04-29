package com.example.announcements.models;


import javax.persistence.*;

@Entity
@Table(name="announcement")
public class Announcement {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	@Column(name = "announcement_id")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	private Category category_id;


	/*@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "auth_user_id", nullable = false)
	private User user_id;*/

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

}

package com.example.announcements.models;

import javax.persistence.*;

@Entity
@Table(name="private_message")
public class PrivateMessage {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	@Column(name = "message_id")
	private int id;


}

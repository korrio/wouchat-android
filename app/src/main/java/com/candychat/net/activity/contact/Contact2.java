package com.candychat.net.activity.contact;

import java.util.ArrayList;

public class Contact2 {
	public int id;
	public String name;
	public String phone;


	public Contact2(){

	}

	public Contact2(int id, String name, String phone) {
		this.id = id;
		this.name = name;
		this.phone = phone;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}

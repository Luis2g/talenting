package mx.edu.utez.talenting.dto;

import java.io.Serializable;

import mx.edu.utez.talenting.entity.Employeer;
import mx.edu.utez.talenting.entity.User;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	private Employeer employeer;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Employeer getEmployeer() {
		return employeer;
	}

	public void setEmployeer(Employeer employeer) {
		this.employeer = employeer;
	}

}

package mx.edu.utez.talenting.dto;

import mx.edu.utez.talenting.entity.User;

public class AuthResponse {
	private String email;
	private String accessToken;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AuthResponse() { }
	
	public AuthResponse(String email, String accessToken) {
		this.email = email;
		this.accessToken = accessToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}

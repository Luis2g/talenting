
package mx.edu.utez.talenting.dto;

public class UserChangePasswordDTO {
	private int idUser;
	private String oldPassword;
	private String password;
	
	public UserChangePasswordDTO() {
		
	}
	

	public UserChangePasswordDTO(int idUser, String oldPassword, String password) {
		super();
		this.idUser = idUser;
		this.oldPassword = oldPassword;
		this.password = password;
	}



	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "UserChangePasswordDTO [idUser=" + idUser + ", oldPassword=" + oldPassword + ", password=" + password
				+ "]";
	}
	
	
	
	
}

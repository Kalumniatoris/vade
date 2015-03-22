package ka2.pr;

public class Account {
	private String login, password, email;

	public Account(String login, String password, String email) {
		this.login = login;
		this.password = password;
		this.email = email;
		// TODO Auto-generated constructor stub
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

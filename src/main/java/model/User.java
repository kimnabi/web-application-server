package model;

import db.DataBase;

public class User {
    private String userId;
    private String password;
    private String name;
    private String email;

    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
    }

	public boolean matchPassword(String newPass) {
		// TODO Auto-generated method stub
		return this.password.equals(newPass);
	}

	public static void login(String userId, String password) throws UserNotFoundException {

		User user= DataBase.findUserById(userId);
		if(user == null) {
			throw new UserNotFoundException();
		}
		if(user.matchPassword(password)) {
			
		}
	}
}

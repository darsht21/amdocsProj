package com.rest.pojo;

public class UserUpdateRequest {
	
	private int oldUserId;
	
	private int newUserId;
	
	private String  newPassword;

	

	public int getOldUserId() {
		return oldUserId;
	}

	public void setOldUserId(int oldUserId) {
		this.oldUserId = oldUserId;
	}

	public int getNewUserId() {
		return newUserId;
	}

	public void setNewUserId(int newUserId) {
		this.newUserId = newUserId;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
	
	

}

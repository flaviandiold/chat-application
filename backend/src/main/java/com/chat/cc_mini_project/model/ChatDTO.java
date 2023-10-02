package com.chat.cc_mini_project.model;

import java.util.List;

public class ChatDTO {
	private int chatId;
    private String firstUserName;
    private String secondUserName;
    private List<Message> messagesList;
	public ChatDTO(int chatId, String firstUserName, String secondUserName) {
		super();
		this.chatId = chatId;
		this.firstUserName = firstUserName;
		this.secondUserName = secondUserName;
	}
	
	public void setMessagesList(List<Message> messagesList) {
		this.messagesList = messagesList;
	}

	public int getChatId() {
		return chatId;
	}

	public String getFirstUserName() {
		return firstUserName;
	}

	public String getSecondUserName() {
		return secondUserName;
	}

	public List<Message> getMessagesList() {
		return messagesList;
	}
	
	
    
}

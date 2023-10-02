package com.chat.cc_mini_project.services;

import java.util.HashSet;
import java.util.List;

import com.chat.cc_mini_project.exceptions.ChatAlreadyExistException;
import com.chat.cc_mini_project.exceptions.ChatNotFoundException;
import com.chat.cc_mini_project.exceptions.NoChatExistsInTheRepository;
import com.chat.cc_mini_project.model.Chat;
import com.chat.cc_mini_project.model.ChatDTO;
import com.chat.cc_mini_project.model.Message;

public interface ChatService {

    public Chat addChat(Chat chat) throws ChatAlreadyExistException;

    List<Chat> findallchats() throws NoChatExistsInTheRepository;

    Chat getById(int id)  throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserName(String username)  throws ChatNotFoundException;

    HashSet<Chat> getChatBySecondUserName(String username)  throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserNameOrSecondUserName(String username)  throws ChatNotFoundException;

    Chat addMessage(Message add, int chatId)  throws ChatNotFoundException;

    Message addMessage2(Message message);

    List<Message> getAllMessagesInChat(int chatId) throws NoChatExistsInTheRepository;

	public List<Chat> findAllChatsOf(String username) throws NoChatExistsInTheRepository;

	public ChatDTO getChatMessageOfFirstUserNameAndSecondUserName(String firstUserName, String secondUserName) throws ChatNotFoundException;
}
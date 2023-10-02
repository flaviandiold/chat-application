package com.chat.cc_mini_project.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.cc_mini_project.exceptions.ChatNotFoundException;
import com.chat.cc_mini_project.exceptions.NoChatExistsInTheRepository;
import com.chat.cc_mini_project.model.Chat;
import com.chat.cc_mini_project.model.ChatDTO;
import com.chat.cc_mini_project.model.Message;
import com.chat.cc_mini_project.repository.ChatRepository;
import com.chat.cc_mini_project.repository.MessageRepository;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    public Chat addChat(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public Message addMessage2(Message message) {
            return messageRepository.save(message);
    }

    @Override
    public List<Message> getAllMessagesInChat(int chatId) throws NoChatExistsInTheRepository {
        List<Message> chat = messageRepository.getMessageListOf(chatId);
        if(chat.isEmpty()){
            throw new NoChatExistsInTheRepository();
        }else {
            return chat;
        }
    }

    @Override
    public List<Chat> findallchats() throws NoChatExistsInTheRepository {
        if (chatRepository.findAll().isEmpty()) {
            throw new NoChatExistsInTheRepository();
        } else {
            return chatRepository.findAll();
        }

    }

    @Override
    public Chat getById(int id) throws ChatNotFoundException {
        Optional<Chat> chatid = chatRepository.findById(id);
        if (chatid.isPresent()) {
            return chatid.get();
        } else {
            throw new ChatNotFoundException();
        }
    }

    @Override
    public HashSet<Chat> getChatByFirstUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatByFirstUserName(username);

        if (chat.isEmpty()) {
            throw new ChatNotFoundException();
        } else {
            return chat;
        }
    }

    @Override
    public HashSet<Chat> getChatBySecondUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatBySecondUserName(username);
        if (chat.isEmpty()) {
            throw new ChatNotFoundException();
        } else {
            return chat;
        }
    }

    @Override
    public HashSet<Chat> getChatByFirstUserNameOrSecondUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatByFirstUserName(username);
        HashSet<Chat> chat1 = chatRepository.getChatBySecondUserName(username);

        chat1.addAll(chat);

        if (chat.isEmpty() && chat1.isEmpty()) {
            throw new ChatNotFoundException();
        } else if (chat1.isEmpty()) {
            return chat;
        } else {
            return chat1;
        }
    }


    @Override
    public Chat addMessage(Message message, int chatId) throws ChatNotFoundException {
        Optional<Chat> chat=chatRepository.findById(chatId);
        Chat abc = chat.get();
        message.setChat(abc);
        messageRepository.save(message);
        return abc;
    }

	@Override
	public List<Chat> findAllChatsOf(String username) throws NoChatExistsInTheRepository {
		return chatRepository.findAllChatsOf(username);
	}

	@Override
	public ChatDTO getChatMessageOfFirstUserNameAndSecondUserName(String firstUserName, String secondUserName)
			throws ChatNotFoundException {
		ChatDTO chat = chatRepository.getChatMessageOfFirstUserNameAndSecondUserName(firstUserName,secondUserName);
		if(chat == null) {
			Chat entry = new Chat(new Random().nextInt(1000,10000),firstUserName,secondUserName);
			chatRepository.save(entry);
			chat = new ChatDTO(entry.getChatId(), firstUserName, secondUserName);
			chat.setMessagesList(null);
			return chat;
		}
		chat.setMessagesList(messageRepository.getMessageListOf(chat.getChatId()));
		return chat;
	}



}
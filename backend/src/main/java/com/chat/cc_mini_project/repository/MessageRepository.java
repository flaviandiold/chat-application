package com.chat.cc_mini_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chat.cc_mini_project.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{

	@Query(value="select * from message m where m.chat_id=:chatId",nativeQuery=true)
	List<Message> getMessageListOf(int chatId);

}

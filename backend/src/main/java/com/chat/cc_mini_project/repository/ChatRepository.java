package com.chat.cc_mini_project.repository;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chat.cc_mini_project.model.Chat;
import com.chat.cc_mini_project.model.ChatDTO;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

//	@Query("select new com.chat.cc_mini_project.model.ChatDTO(c.chatId,c.firstUserName,c.secondUserName) from Chat c where c.firstUserName=:username")
    HashSet<Chat> getChatByFirstUserName(String username);

//	@Query("select new com.chat.cc_mini_project.model.ChatDTO(c.chatId,c.firstUserName,c.secondUserName) from Chat c where c.secondUserName=:username")
    HashSet<Chat> getChatBySecondUserName(String username);

//	@Query("select new com.chat.cc_mini_project.model.ChatDTO(c.chatId,c.firstUserName,c.secondUserName) from Chat c where c.firstUserName=:firstUserName and c.secondUserName=:secondUserName")
    Chat getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName);

//	@Query("select new com.chat.cc_mini_project.model.ChatDTO(c.chatId,c.firstUserName,c.secondUserName) from Chat c where c.firstUserName=:firstUserName or c.secondUserName=:secondUserName")
    Chat getChatBySecondUserNameAndFirstUserName(String firstUserName, String secondUserName);

    @Query("Select c from Chat c where c.firstUserName=:username or c.secondUserName=:username")
	List<Chat> findAllChatsOf(String username);

	@Query("select new com.chat.cc_mini_project.model.ChatDTO(c.chatId,c.firstUserName,c.secondUserName) from Chat c where (c.firstUserName=:firstUserName and c.secondUserName=:secondUserName) or (c.firstUserName=:secondUserName and c.secondUserName=:firstUserName)")
	ChatDTO getChatMessageOfFirstUserNameAndSecondUserName(String firstUserName, String secondUserName);
}

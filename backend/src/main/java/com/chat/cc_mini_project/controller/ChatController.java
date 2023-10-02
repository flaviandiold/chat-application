package com.chat.cc_mini_project.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import javax.print.DocFlavor.READER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chat.cc_mini_project.exceptions.ChatAlreadyExistException;
import com.chat.cc_mini_project.exceptions.ChatNotFoundException;
import com.chat.cc_mini_project.exceptions.NoChatExistsInTheRepository;
import com.chat.cc_mini_project.model.Chat;
import com.chat.cc_mini_project.model.ChatDTO;
import com.chat.cc_mini_project.model.Message;
import com.chat.cc_mini_project.services.ChatService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

//    @PostMapping("/add")
//    public ResponseEntity<Chat> createChat(@RequestBody Chat chat) throws IOException {
//        try {
//            return new ResponseEntity<Chat>(chatService.addChat(chat), HttpStatus.CREATED);
//        } catch (ChatAlreadyExistException e) {
//            return new ResponseEntity("Chat Already Exist", HttpStatus.CONFLICT);
//        }
//    }

    @GetMapping("/all/{username}")
    public ResponseEntity<List<Chat>> getAllChats(@PathVariable("username") String username) {
        try {
            List<Chat> chats = chatService.findAllChatsOf(username);
			return new ResponseEntity<List<Chat>>(chats, HttpStatus.OK);
        } catch (NoChatExistsInTheRepository e) {
           return new ResponseEntity("List not found", HttpStatus.CONFLICT);
        }
    }

//    @GetMapping("/all/messages/{chatId}")
//    public ResponseEntity<?> getAllMessagesInChat(@PathVariable int chatId) {
//        try {
////            Chat chat = new Chat();
////            chat.setChatId(chatId);
//            List<Message> messageList = this.chatService.getAllMessagesInChat(chatId);
//            return ResponseEntity.ok(messageList);
//        } catch (NoChatExistsInTheRepository e) {
//            return new ResponseEntity("Message List not found", HttpStatus.CONFLICT);
//        }
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Chat> getChatById(@PathVariable int id) {
//        try {
//            return new ResponseEntity<Chat>(chatService.getById(id), HttpStatus.OK);
//        } catch (ChatNotFoundException e) {
//           return new ResponseEntity("Chat Not Found", HttpStatus.NOT_FOUND);
//        }
//    }


   @GetMapping("/getChatMessageOfFirstUserNameAndSecondUserName")
    public ResponseEntity<?> getChatMessageOfFirstUserNameAndSecondUserName(@RequestParam("firstUserName") String firstUserName, @RequestParam("secondUserName") String secondUserName){

        try {
            ChatDTO chatByBothEmail = this.chatService.getChatMessageOfFirstUserNameAndSecondUserName(firstUserName, secondUserName);
            return new ResponseEntity<>(chatByBothEmail, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity("Chat Not Exits", HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/all-messages-of/{chatId}")
    public ResponseEntity<List<Message>> getMessagesOf(@PathVariable("chatId") Integer chatId){
    	try {
			return new ResponseEntity<List<Message>>(chatService.getAllMessagesInChat(chatId),HttpStatus.OK);
		} catch (NoChatExistsInTheRepository e) {
			e.printStackTrace();
			return new ResponseEntity("NoChatExists",HttpStatus.OK);
		}
    }


   @PutMapping("/message/{chatId}")
    public ResponseEntity<Chat> addMessage(@RequestBody Message add , @PathVariable int chatId) throws ChatNotFoundException {
        return new ResponseEntity<Chat>(chatService.addMessage(add,chatId), HttpStatus.OK);
    }

}
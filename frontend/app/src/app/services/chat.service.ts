import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Chat } from '../models/chat';
import { Message } from '../models/message';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  
  baseUrl = "http://localhost:8080";
  
  constructor(private httpClient: HttpClient) { }
  
  loadChatByChatId(chatId: number) {
    return this.httpClient.get<Message[]>(this.baseUrl + "/chats/all-messages-of/" + chatId);
  }
  
  updateChat(message: Message, chatId: any): Observable<Object> {
    return this.httpClient.put(this.baseUrl + "/chats/message/" + `${chatId}`, message);
  }
  getChatMessageOfFirstUserNameAndSecondUserName(firstUserName: String, secondUserName: String) {
    return this.httpClient.get<Chat>(this.baseUrl + "/chats/getChatMessageOfFirstUserNameAndSecondUserName" + '?firstUserName=' + firstUserName + '&secondUserName=' + secondUserName)
  }

  getAllChatsOf(username: any) {
    return this.httpClient.get<Chat>(this.baseUrl + "/chats/all/" + username)
  }

}

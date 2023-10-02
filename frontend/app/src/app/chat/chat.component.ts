import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Chat } from '../models/chat';
import { Message } from '../models/message';
import { ChatService } from '../services/chat.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  chatForm: FormGroup;
  chatObj: Chat = new Chat();
  messageObj: Message = new Message();
  // chatId: number = 22;
  public messageList: any = [];
  public chatList: any = [];
  replymessage: String = "checking";
  public chatData: any;
  msg = "Good work";
  chatId: any = sessionStorage.getItem('chatId');
  color = "";
  secondUserName = "";
  public alluser: any = [];
  check = sessionStorage.getItem('username');
  timesRun = 0;
  timesRun2 = 0;


  firstUserName = sessionStorage.getItem('username');
  senderEmail = sessionStorage.getItem('username');
  senderCheck = sessionStorage.getItem('username');

  constructor(private chatService: ChatService, private router: Router, private userService: UserService) {
    this.chatForm = new FormGroup({
      replymessage: new FormControl()
    });
  }
  
  ngOnInit(): void {
    this.chatService.getAllChatsOf(this.senderEmail).subscribe(data => {
      console.log(sessionStorage);
      this.chatList = data;
      this.userService.getAll().subscribe(data => {
        this.alluser = data
      }); 
      this.secondUserName = sessionStorage.getItem("to");
      this.loadChatByEmail(this.senderEmail,this.secondUserName);
      console.log("in onInit of chat component");
      });
  }

  loadChatByEmail(firstUserName: string, secondUserName: string) {
    // For removing the previous chatId
    sessionStorage.removeItem("chatId");
    this.messageList = [];
    // For checking the chat room by both the emails , if there is present then it will give the chat Id in sessionStorage
    this.chatService.getChatMessageOfFirstUserNameAndSecondUserName(firstUserName,secondUserName).subscribe(data => {
      this.chatData = data;
      this.messageList = this.chatData.messagesList;
      console.log(this.chatData);
      console.log(this.chatData.messagesList);
      console.log(this.messageList);
      this.chatId = this.chatData.chatId;
      console.log(this.chatId);
      sessionStorage.setItem('chatId', this.chatId)
      sessionStorage.setItem('to', secondUserName);
      console.log(sessionStorage);

      // setInterval(() => {
      //   this.loadChatByChatId(this.chatId)
      // }, 1000)
      
    });
  }

  sendMessage() {
    console.log(this.chatForm.value);

    // This will call the update chat method when ever user send the message
    this.messageObj.replymessage = this.chatForm.value.replymessage;
    this.messageObj.senderEmail = this.senderEmail;
    
    this.chatService.updateChat(this.messageObj, this.chatId).subscribe(data => {
      console.log(data);
      this.chatForm.reset();
      this.loadChatByChatId(this.chatId);
    })
  }

  loadChatByChatId(chatId: number) {
    this.messageList = [];
    this.chatService.loadChatByChatId(chatId).subscribe(data => {
      this.chatData = data;
      this.messageList = this.chatData;
      console.log(this.chatData);
      console.log(this.chatData.messagesList);
      console.log(this.messageList);
      this.chatId = chatId;
      console.log(this.chatId);
      sessionStorage.setItem('chatId', this.chatId)
      console.log(sessionStorage);  
    });
    
  }

  routeAgain() {
   this.router.navigateByUrl('', { skipLocationChange: true }).then(() => {
          this.router.navigate(['/chat']);
        });
  }


  goToChat(username: any) {
    this.chatService.getChatMessageOfFirstUserNameAndSecondUserName(sessionStorage.getItem("username"), username).subscribe(
      (data) => {
        console.log(data);
        this.chatId = data.chatId;
        sessionStorage.setItem("chatId", this.chatId);
        sessionStorage.setItem("to", username);
        this.loadChatByEmail(sessionStorage.getItem("username"), username);
        this.router.navigateByUrl('', { skipLocationChange: true }).then(() => {
          this.router.navigate(['/chat']);
        });
      });
      }

}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Chat } from '../models/chat';
import { ChatService } from '../services/chat.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  public alluser: any = [];
  check = sessionStorage.getItem('username');
  chatId: any = 0;
  chatObj: Chat = new Chat();
  public chatData: any = [];

  constructor(private router: Router, private userService: UserService, private chatService: ChatService) { }

  ngOnInit(): void {
    let all = 
      this.userService.getAll().subscribe((data) => {
        this.alluser = data;
      });
  }


  goToChat(username: any) {
    console.log(sessionStorage);
    console.log("inGotoChat");
    if (sessionStorage.getItem("username") != null) {
      this.chatService.getChatMessageOfFirstUserNameAndSecondUserName(sessionStorage.getItem("username"), username).subscribe(
        (data) => {
          this.chatId = data.chatId;
          sessionStorage.setItem("chatId", this.chatId);
          sessionStorage.setItem("to", username);
          console.log(this.chatId);
          this.router.navigateByUrl('/chat');
        });
      }
    else {
      window.alert("Please login and try again");
    }
  }

}

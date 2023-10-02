import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Chat } from '../models/chat';
import { User } from '../models/user';
import { ChatService } from '../services/chat.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {

  chatId: any = 0;
  registerForm: FormGroup;
  loginForm: FormGroup;
  successregister: boolean = false;
  registermsg = "";
  alert = "";
  loginmsg = "";
  alert2 = "";
  successlogin: boolean = false;
  public userObj: User = new User();
  public alluser: any = [];
  secondUsername = "";
  chatObj: Chat = new Chat();
  public chatData: any = [];
  public check = "";
  loggedIn: boolean = false;
  loggedOut: boolean = true;
  chatbox: boolean = true;

  logout() {
    this.loggedIn = false;
    this.loggedOut = true;
    sessionStorage.clear();
    this.loginForm.enable();
    this.router.navigateByUrl('');
  }

  constructor(private router: Router, private userService: UserService, private chatService: ChatService) {
    this.registerForm = new FormGroup({
      username: new FormControl("", [Validators.required])
    });
    this.loginForm = new FormGroup({
      username: new FormControl("", [Validators.required])
    });
  }

  update(): void {
      this.userService.getAll().subscribe((data) => {
        console.log("Changes");
        this.alluser = data;
        console.log(data);
        this.router.navigateByUrl('/chat', { skipLocationChange: true }).then(() => {
             this.router.navigate(['']);
          }); 
      });
    
  }

  adduser() {
    if (this.registerForm.valid) {
      this.userObj.userName = this.registerForm.value.username;
      this.userService.adduser(this.userObj).subscribe(
        (data: any) => {
          console.log(data);
          this.successregister = true;
          this.alert = "success";
          this.registermsg = "Successsfully added";
          this.registerForm.reset();
          this.update();
        },
        (error) => {
          console.log(error.error);
          if (error.status == 409) {
            this.successregister = true;
            this.alert = "danger";
            this.registermsg = "Already registered"
          } else {
            this.successregister = true;
            this.alert = "danger";
            this.registermsg = "Error"
          }

        }
      )
    }

  }

  login() {
    if (this.loginForm.valid) {
      console.log(sessionStorage);
      console.log("login");
      sessionStorage.setItem('username', null);
      this.userService.getUserByUsername(this.loginForm.value.username).subscribe(
        (data: any) => {
          console.log(data);

          this.successlogin = true;
          this.alert2 = "success";
          this.loginmsg = "Successsfully LoggedIn";

          sessionStorage.setItem("username", this.loginForm.value.username);
          this.check = this.loginForm.value.username;
          this.loginForm.reset();

          this.loggedIn = true;
          this.loggedOut = false;

          this.router.navigateByUrl('/chat', { skipLocationChange: true }).then(() => {
             this.router.navigate(['']);
          }); 
          this.loginForm.disable();
        },
        (error) => {
          console.log(error.error);
          if (error.status == 404) {
            this.successlogin = true;
            this.alert2 = "danger";
            this.loginmsg = "Not a registerd user"
          } else {
            this.successlogin = true;
            this.alert2 = "danger";
            this.loginmsg = "Error"
          }

        }
      )
    }
  }
}

import { Message } from "./message";

export class Chat {

    chatId: Number;
    firstUserName: String;
    secondUserName: String;
    messageList: Message[];

    constructor() {

    }

    // constructor(private id: Number, private from: String, private to: String ) {
    //     this.chatId = id;
    //     this.firstUserName = from;
    //     this.secondUserName = to;
    // }
}

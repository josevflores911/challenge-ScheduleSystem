export class User {
    id?:number;
    password?: string;
    username?: string;

    constructor(password:string,username:string){
        this.password=password;
        this.username=username;
    }
} 
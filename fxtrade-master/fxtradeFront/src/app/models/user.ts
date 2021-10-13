import {SecurityQuestion} from './security-question.model'
export class User {

	user_id : number=0;
	first_name : string="";
	last_name : string ="";
	email : string="";
	password : string="";
	questions: SecurityQuestion[] = [new SecurityQuestion(),new SecurityQuestion(),new SecurityQuestion];


}

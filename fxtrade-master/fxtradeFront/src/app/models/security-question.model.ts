import { SecurityQuestionEnum } from './security-question-enum.model';

export class SecurityQuestion {
    
    Id: number = 0;
    question: SecurityQuestionEnum = SecurityQuestionEnum.DEFAULT ;
    answer: string ="";
    
}

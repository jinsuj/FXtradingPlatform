import { HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

const baseUrl = 'http://localhost:8080/api/getUserData';

@Injectable({
  providedIn: 'root'
})
export class UserDetailsService {

  constructor(private http: HttpClient) { }

  getUser(data:String):Observable<User> {
    return this.http.post<User>(baseUrl, data);
  }
}

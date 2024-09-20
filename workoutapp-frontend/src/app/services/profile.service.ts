import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private apiUrl = 'http://localhost:8080/profile';  // Base URL for API

  constructor(private http: HttpClient) { }

  // Fetch the profile data for the logged-in user
  getProfile(username: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/user`, {
      params: { username }
    });
  }

  // Fetch today's workout plan for the logged-in user
  getTodaysWorkout(): Observable<any> {
    return this.http.get(`${this.apiUrl}/todays-workout`);
  }

  // Update profile
  updateProfile(profile: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/update`, profile);
  }
}

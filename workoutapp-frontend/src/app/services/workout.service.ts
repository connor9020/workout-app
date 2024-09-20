import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WorkoutService {
  private apiUrl = 'http://localhost:8080/api/workout-types';

  constructor(private http: HttpClient) { }

  getWorkoutTypes(): Observable<any> {
    return this.http.get(`${this.apiUrl}/exercises?type=${type}`);
  }
}

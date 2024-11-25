import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class WorkoutService {
  private apiUrl = 'http://localhost:8080/api/exercises'; // Adjust to your backend base URL

  constructor(private http: HttpClient) {}

  getWorkoutTypes(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/types`);
  }

  getExercisesByType(type: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}`, {
      params: { type: type },
    });
  }
}


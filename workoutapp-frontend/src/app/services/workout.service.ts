import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class WorkoutService {
  private apiUrl = 'http://localhost:8080/api'; // Replace with your backend URL

  constructor(private http: HttpClient) {}

  getWorkoutTypes(): Observable<any> {
    return this.http.get(`${this.apiUrl}/workout-types`);
  }

  getExercisesByType(workoutTypeName: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/exercises?workoutType=${workoutTypeName}`);
  }
}

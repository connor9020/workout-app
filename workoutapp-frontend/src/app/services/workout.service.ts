import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WorkoutType } from '../models/workoutType.model';

@Injectable({
  providedIn: 'root',
})
export class WorkoutService {
  private apiUrl = 'http://localhost:8080/api'; // Replace with your backend URL

  constructor(private http: HttpClient) {}

  getWorkoutTypes(): Observable<WorkoutType[]> {
    return this.http.get<WorkoutType[]>(`${this.apiUrl}/workout-types`);
  }

  getExercisesByType(params: { workoutType: string }): Observable<any> {
    return this.http.get(`${this.apiUrl}/api/exercises`, { params });
  }
  
  
}

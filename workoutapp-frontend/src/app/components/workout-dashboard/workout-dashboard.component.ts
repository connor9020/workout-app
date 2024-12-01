import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-workout-dashboard',
  templateUrl: './workout-dashboard.component.html',
  styleUrls: ['./workout-dashboard.component.css'],
})
export class WorkoutDashboardComponent implements OnInit {
  workoutTypes: string[] = [];
  exercises: any[] = [];
  selectedWorkoutType: string = '';
  isLoading: boolean = true; // Tracks loading state

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    // Fetch workout types on load
    this.http.get<string[]>('http://localhost:8080/api/exercises/types')
      .subscribe(
        (data) => {
          this.workoutTypes = data;
          this.isLoading = false; // Data loaded
        },
        (error) => {
          console.error('Failed to fetch workout types:', error);
          this.isLoading = false; // Loading complete, even if failed
        }
      );
  }

  getExercises(workoutType: string): void {
    this.selectedWorkoutType = workoutType; // Update selected type
    this.exercises = []; // Clear current exercises for loading indication

    // Fetch exercises for the selected workout type
    this.http.get<any[]>(`http://localhost:8080/api/exercises/${workoutType}`)
      .subscribe(
        (data) => {
          this.exercises = data;
        },
        (error) => {
          console.error(`Failed to fetch exercises for ${workoutType}:`, error);
        }
      );
  }
  
}


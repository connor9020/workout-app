import { Component, OnInit } from '@angular/core';
import { WorkoutService } from '../../services/workout.service';

@Component({
  selector: 'app-workout-dashboard',
  templateUrl: './workout-dashboard.component.html',
})
export class WorkoutDashboardComponent implements OnInit {
  workoutTypes: string[] = [];
  exercises: any[] = [];
  selectedWorkoutType: string = '';

  constructor(private workoutService: WorkoutService) {}

  ngOnInit(): void {
    this.getWorkoutTypes();
  }

  getWorkoutTypes(): void {
    this.workoutService.getWorkoutTypes().subscribe(
      (types: string[]) => {
        console.log('Received workout types:', types); // Debugging
        this.workoutTypes = types;
      },
      (error) => {
        console.error('Failed to fetch workout types', error);
      }
    );
  }

  getExercises(type: string): void {
    this.selectedWorkoutType = type; // Display the selected type
    this.workoutService.getExercisesByType(type).subscribe(
      (data: any[]) => {
        this.exercises = data;
      },
      (error) => {
        console.error('Failed to fetch exercises', error);
      }
    );
  }
  
}


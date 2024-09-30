import { Component, OnInit } from '@angular/core';
import { WorkoutService } from '../../services/workout.service'; // Example service


interface WorkoutType {
  id: number;
  name: string;
  // Add other properties if necessary
}

interface Exercise {
  id: number;
  name: string;
}

@Component({
  selector: 'app-workout-dashboard',
  templateUrl: './workout-dashboard.component.html',
})
export class WorkoutDashboardComponent implements OnInit {
  // Declare workoutTypes as an array of WorkoutType
  workoutTypes: WorkoutType[] = [];
  exercises: any[] = [];
  selectedWorkoutType: string = '';

  constructor(private workoutService: WorkoutService) {}

  ngOnInit(): void {
    this.getWorkoutTypes();
  }

  getWorkoutTypes(): void {
    // Example service to fetch workout types
    this.workoutService.getWorkoutTypes().subscribe((types: WorkoutType[]) => {
      this.workoutTypes = types;
    });
  }

  getExercises(type: string) {
    this.workoutService.getExercisesByType(type).subscribe((data: any) => {
      this.exercises = data;
    }, error => {
      console.error('Failed to fetch exercises', error);
    });
  }
}

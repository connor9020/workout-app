import { Component, OnInit } from '@angular/core';
import { WorkoutService } from '../../services/workout.service';

@Component({
  selector: 'app-workout-type',
  templateUrl: './workout-type.component.html'
})
export class WorkoutTypeComponent implements OnInit {
  workoutTypes: any[] = [];
  exercises: any[] = [];

  constructor(private workoutService: WorkoutService) {}

  ngOnInit(): void {
    // Load workout types on component initialization
    this.loadWorkoutTypes();
  }

  // Method to fetch workout types from the back-end
  loadWorkoutTypes() {
    this.workoutService.getWorkoutTypes().subscribe((data: any) => {
      this.workoutTypes = data;
    }, error => {
      console.error('Failed to load workout types', error);
    });
  }

  // Fetch exercises for a selected workout type
  getExercises(type: string) {
    this.workoutService.getExercisesByType(type).subscribe((data: any) => {
      this.exercises = data;
    }, error => {
      console.error('Failed to fetch exercises', error);
    });
  }
}

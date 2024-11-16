import { Component, OnInit } from '@angular/core';
import { WorkoutService } from '../../services/workout.service';

@Component({
  selector: 'app-workout-type',
  templateUrl: './workout-type.component.html'
})
export class WorkoutTypeComponent implements OnInit {
  workoutTypes: any[] = [];
  exercises: any[] = [];
  selectedWorkoutType: string = ''; // Track the selected workout type

  constructor(private workoutService: WorkoutService) {}

  ngOnInit(): void {
    this.loadWorkoutTypes();
  }

  loadWorkoutTypes() {
    this.workoutService.getWorkoutTypes().subscribe(
      (data: any) => {
        this.workoutTypes = data;
      },
      error => {
        console.error('Failed to load workout types', error);
      }
    );
  }

  getExercises(type: string) {
    this.selectedWorkoutType = type; // Set the selected workout type
    this.workoutService.getExercisesByType({ workoutType: type }).subscribe(
      (data: any) => {
        this.exercises = data;
      },
      error => {
        console.error('Failed to fetch exercises', error);
      }
    );
  }
}


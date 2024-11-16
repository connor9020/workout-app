import { Component, OnInit } from '@angular/core';
import { WorkoutService } from '../../services/workout.service';
import { WorkoutType } from '../../models/workoutType.model';

@Component({
  selector: 'app-workout-dashboard',
  templateUrl: './workout-dashboard.component.html',
})
export class WorkoutDashboardComponent implements OnInit {
  workoutTypes: WorkoutType[] = [];
  exercises: any[] = [];
  selectedWorkoutType: string = '';

  constructor(private workoutService: WorkoutService) {}

  ngOnInit(): void {
    this.getWorkoutTypes();
  }

  getWorkoutTypes(): void {
    this.workoutService.getWorkoutTypes().subscribe((types: WorkoutType[]) => {
      console.log("Received workout types:", types); // Check the console for this output
      this.workoutTypes = types;
    });
  }

  getExercises(type: string) {
    this.workoutService.getExercisesByType({ workoutType: type }).subscribe(
      (data: any) => {
        this.exercises = data;
      },
      (error) => {
        console.error('Failed to fetch exercises', error);
      }
    );
  }
  
}


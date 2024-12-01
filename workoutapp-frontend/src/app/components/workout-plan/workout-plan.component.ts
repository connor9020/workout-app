import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-workout-plan',
  templateUrl: './workout-plan.component.html',
  styleUrls: ['./workout-plan.component.css'],
})
export class WorkoutPlanComponent implements OnInit {
  workoutType: string | null = null;
  exercises: any[] = [];

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit(): void {
    // Get workout type from route parameters - allows dynamic additions to the quick workout list later
    this.workoutType = this.route.snapshot.paramMap.get('type');
  
    if (this.workoutType) {
    
      // Fetch exercises for the workout type
      this.http.get<any[]>(`http://localhost:8080/api/exercises/${this.workoutType}`)
        .subscribe(
          (data) => {
            this.exercises = data;
          },
          (error) => {
            console.error(`Failed to fetch exercises for ${this.workoutType}:`, error);
          }
        );
    } else {
      console.error('Workout type is null or undefined.');
    }
  }
  
}


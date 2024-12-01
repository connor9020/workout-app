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
    // Get workout type from route parameters
    this.workoutType = this.route.snapshot.paramMap.get('type');
  
    if (this.workoutType) {
      console.log(`Fetching exercises for workout type: ${this.workoutType}`); // Debug: Log workout type
  
      // Fetch exercises for the workout type
      this.http
        .get<any[]>(`http://localhost:8080/api/exercises/${this.workoutType}`)
        .subscribe(
          (data) => {
            console.log('Fetched exercises:', data); // Debug: Log API response
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


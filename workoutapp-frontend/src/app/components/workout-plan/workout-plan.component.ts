import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

interface Exercise {
  id: number;
  exercise: string;
  type: string;
  imageUrl: string;
}

interface SetData {
  reps: number;
  weight: number;
  time: number;
}

interface ExerciseData {
  exerciseId: number;
  sets: SetData[];
}

@Component({
  selector: 'app-workout-plan',
  templateUrl: './workout-plan.component.html',
  styleUrls: ['./workout-plan.component.css'],
})
export class WorkoutPlanComponent implements OnInit {
  workoutType: string | null = null;
  exercises: Exercise[] = [];
  exerciseData: ExerciseData[] = [];

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit(): void {
    // Get workout type from route parameters
    this.workoutType = this.route.snapshot.paramMap.get('type');
  
    if (this.workoutType) {
      // Fetch exercises for the workout type
      this.http
        .get<Exercise[]>(`http://localhost:8080/api/exercises/${this.workoutType}`)
        .subscribe(
          (data) => {
            this.exercises = data;
  
            // Initialize exerciseData array with three default sets per exercise
            this.exerciseData = this.exercises.map((exercise) => ({
              exerciseId: exercise.id,
              sets: Array(3).fill(null).map(() => ({
                reps: 0,
                weight: 0,
                time: 0,
              })),
            }));
          },
          (error) => {
            console.error(`Failed to fetch exercises for ${this.workoutType}:`, error);
          }
        );
    } else {
      console.error('Workout type is null or undefined.');
    }
  }
  

  addSet(exerciseIndex: number): void {
    // Add a new empty set for the exercise at the given index
    this.exerciseData[exerciseIndex].sets.push({
      reps: 0,
      weight: 0,
      time: 0,
    });
  }

  removeSet(exerciseIndex: number, setIndex: number): void {
    // Remove the set at setIndex for the exercise at exerciseIndex
    this.exerciseData[exerciseIndex].sets.splice(setIndex, 1);
  }

  onSubmit(): void {
    // Process the workout data
    console.log('Workout data submitted:', this.exerciseData);

    // Send the data to your server or process it as needed
    this.http
      .post('http://localhost:8080/api/workout-data', this.exerciseData)
      .subscribe(
        (response) => {
          console.log('Workout data saved successfully:', response);
          // Optionally, navigate to another page or provide feedback to the user
        },
        (error) => {
          console.error('Error saving workout data:', error);
        }
      );
  }
}



import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../../services/profile.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profile: any = {};
  todaysWorkout: any = {};

  constructor(private profileService: ProfileService, private authService: AuthService) {}

  ngOnInit(): void {
    this.loadProfile();
    this.loadTodaysWorkout();
  }

  loadProfile() {
    const username = this.authService.getLoggedInUsername();
    
    if (username) {  // Check if username is not null
      this.profileService.getProfile(username).subscribe(data => {
        this.profile = data;
      });
    } else {
      console.error('No valid username found');  // Handle the case where username is null
    }
  }
  

  loadTodaysWorkout() {
    this.profileService.getTodaysWorkout().subscribe(workout => {
      this.todaysWorkout = workout;
    });
  }

  updateProfile() {
    this.profileService.updateProfile(this.profile).subscribe(response => {
      alert('Profile updated successfully');
    });
  }
}

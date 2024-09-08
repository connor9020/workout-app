import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';  // Ensure correct path to service

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService) {}

  login() {
    this.authService.login(this.username, this.password).subscribe(
      response => {
        console.log('Login successful!', response);
        // Handle success (e.g., store JWT, redirect, etc.)
      },
      error => {
        console.error('Login failed', error);
        // Handle login failure (e.g., display error message)
      }
    );
  }
}

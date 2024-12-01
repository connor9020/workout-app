import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';  // Ensure correct path to service
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    this.authService.login(this.username, this.password).subscribe(
      (response: string) => {
        console.log('Login successful!', response);
        const token = response.split(' ')[1];  // Extract the JWT from the "Bearer <token>" response
        localStorage.setItem('jwtToken', token); // Store the token

        this.router.navigate(['/dashboard']);  
      },
      error => {
        console.error('Login failed', error);
        // Handle login failure (e.g., display error message)
      }
    );
  }
  
}

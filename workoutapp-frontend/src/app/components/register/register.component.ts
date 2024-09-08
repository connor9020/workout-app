import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  username: string = '';
  name: string = '';
  email: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  register() {
    const newUser = {
      username: this.username,
      name: this.name,
      email: this.email,
      password: this.password
    };

    this.authService.register(newUser).subscribe(response => {
      console.log('User registered:', response);
      this.router.navigate(['/login']);  // Redirect to login after successful registration
    });
  }
}


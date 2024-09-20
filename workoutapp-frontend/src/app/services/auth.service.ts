import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';  // Import JwtHelperService

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/auth'; // Base API URL

  private tokenKey = 'auth-token';  // Key to store the JWT in localStorage

  constructor(private http: HttpClient, private jwtHelper: JwtHelperService) { }

  // Get the logged-in user's username from the JWT token
  getLoggedInUsername(): string | null {
    const token = localStorage.getItem(this.tokenKey);  // Retrieve the JWT from localStorage
    if (token && !this.jwtHelper.isTokenExpired(token)) {
      const decodedToken = this.jwtHelper.decodeToken(token);
      return decodedToken ? decodedToken.sub : null;  // 'sub' contains the username in JWTs
    }
    return null;  // Return null if no valid token is found
  }

  // Login request
  login(username: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, { username, password }, {
      headers: { 'Content-Type': 'application/json' },
      responseType: 'text'
    });
  }
  
  
  // Register request
  register(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user); // API endpoint for registration
  }
}


import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('jwtToken');
    console.log('JWT Token from localStorage:', token); // Debug log
    if (token) {
      const cloned = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${token}`)
      });
      console.log('Request with Authorization header:', cloned); // Debug log
      return next.handle(cloned);
    }
    console.log('No token found. Proceeding without Authorization header.');
    return next.handle(req);
  }
}


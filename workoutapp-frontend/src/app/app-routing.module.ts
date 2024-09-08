import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ProfileComponent } from './components/profile/profile.component';
import { WorkoutDashboardComponent } from './components/workout-dashboard/workout-dashboard.component';
import { WorkoutPlanComponent } from './components/workout-plan/workout-plan.component';
import { WorkoutTypeComponent } from './components/workout-type/workout-type.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'dashboard', component: WorkoutDashboardComponent },
  { path: 'workout-plan', component: WorkoutPlanComponent },
  { path: 'workout-type', component: WorkoutTypeComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },  // Redirect to login by default
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

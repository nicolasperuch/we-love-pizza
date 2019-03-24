import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { RequestOrderComponent } from './request-order/request-order.component';
import { TrackOrderComponent } from './track-order/track-order.component';
import { FeedbackComponent } from './feedback/feedback.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'order', component: RequestOrderComponent },
  { path: 'track', component: TrackOrderComponent },
  { path: 'feedback', component: FeedbackComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [ 
    RouterModule.forRoot(routes) 
  ],
  exports: [ RouterModule ]
})

export class AppRoutingModule {}
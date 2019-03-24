import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { HomeComponent } from './home/home.component';
import { RequestOrderComponent } from './request-order/request-order.component';
import { TrackOrderComponent } from './track-order/track-order.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RequestOrderComponent,
    TrackOrderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

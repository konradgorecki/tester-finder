import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from './app-routing.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatStepperModule} from "@angular/material/stepper";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatChipsModule} from "@angular/material/chips";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatCardModule} from "@angular/material/card";

import {AppComponent} from './app.component';
import {MainPageComponent} from './components/main-page/main-page.component';
import {FooterComponent} from './components/footer/footer.component';
import {AppHeaderComponent} from './components/header/app-header/app-header.component';
import {SearchFormComponent} from './components/search-form/search-form.component';
import {SelectCountriesFormComponent} from './components/select-countries-form/select-countries-form.component';
import { SelectDevicesFormComponent } from './components/select-devices-form/select-devices-form.component';
import { SearchExecutorComponent } from './components/search-executor/search-executor.component';

@NgModule({
  declarations: [
    AppComponent,
    AppHeaderComponent,
    MainPageComponent,
    FooterComponent,
    SearchFormComponent,
    SelectCountriesFormComponent,
    SelectDevicesFormComponent,
    SearchExecutorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
    MatStepperModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatChipsModule,
    MatAutocompleteModule,
    MatExpansionModule,
    MatCardModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

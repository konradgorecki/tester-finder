import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from "@angular/common/http";
import {AppHeaderComponent} from './modules/header/app-header/app-header.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MainPageComponent} from './modules/main-page/main-page.component';
import {FooterComponent} from './modules/footer/footer.component';
import {SearchFormComponent} from './modules/search-form/search-form.component';
import {ReactiveFormsModule} from "@angular/forms";
import {MatStepperModule} from "@angular/material/stepper";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatChipsModule} from "@angular/material/chips";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {SelectCountriesFormComponent} from './modules/select-countries-form/select-countries-form.component';
import { SelectDevicesFormComponent } from './modules/select-devices-form/select-devices-form.component';
import { SearchExecutorComponent } from './modules/search-executor/search-executor.component';
import {MatExpansionModule} from "@angular/material/expansion";
import {MatDividerModule} from "@angular/material/divider";
import {MatCardModule} from "@angular/material/card";

//todo pack all angular meterial dependencies into single modle - consider this - https://stackoverflow.com/a/46480745
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
    MatDividerModule,
    MatCardModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchExecutorComponent } from './search-executor.component';

describe('SearchExecutorComponent', () => {
  let component: SearchExecutorComponent;
  let fixture: ComponentFixture<SearchExecutorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchExecutorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchExecutorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

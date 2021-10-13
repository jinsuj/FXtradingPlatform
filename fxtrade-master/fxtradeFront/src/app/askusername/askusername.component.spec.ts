import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { AskusernameComponent } from './askusername.component';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('AskusernameComponent', () => {
  let component: AskusernameComponent;
  let fixture: ComponentFixture<AskusernameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AskusernameComponent ],
      imports: [FormsModule, RouterTestingModule.withRoutes([]), HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AskusernameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

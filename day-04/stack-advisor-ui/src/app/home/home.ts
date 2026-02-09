import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputText, InputTextModule } from 'primeng/inputtext';
import { AiService } from '../service/ai.service';
import { Project, StackReport } from '../model/models';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    InputTextModule,
    InputText,
    ButtonModule
  ],
  templateUrl: './home.html',
  styleUrls: ['./home.css'] 
})
export class HomeComponent {
  loading = false;
  report = false;
  reportData: StackReport | null = null;
  projectForm: FormGroup;

  constructor(private fb: FormBuilder, private service: AiService) {
    this.projectForm = this.fb.group({
      projectName: ['', Validators.required],
      projectType: ['', Validators.required],
      teamSkills: [''],
      mainRequirements: ['', Validators.required]
    });
  }


  onSubmit() {
    if (this.projectForm.valid) {
      this.loading = true;
      const projectData = this.projectForm.value;

      this.service.getRecommendation(projectData).subscribe({
        next: (response: any) => {
          try {
            this.reportData = typeof response === 'string' ? JSON.parse(response) : response;
            this.report = true;

          } catch (e) {
            console.error("Error parseando JSON", e);
            this.reportData = { backend: 'Error', frontend: 'Error', database: 'Error', reasoning: 'Could not parse response', estimatedCost: '-', estimatedTime: '-' };
          }
          this.loading = false;
        },
        error: (err) => {
          console.error(err);
          this.loading = false;
        }
      });
    }
  }

  reset() {
    this.report = false;
    this.reportData = null;
    this.projectForm.reset();
  }
}

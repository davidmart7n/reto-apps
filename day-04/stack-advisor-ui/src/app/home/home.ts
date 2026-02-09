import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms';
import { Button } from 'primeng/button';
import { Checkbox } from 'primeng/checkbox';
import { InputText } from 'primeng/inputtext';
import { AiService } from '../service/ai.service';
import { Project } from '../model/models'

interface User {
  name: string;
  terms: boolean;
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ReactiveFormsModule, InputText, Checkbox, Button],
  templateUrl: './home.html'
})
export class HomeComponent {
  loading = false;
  report = false;
  projectForm!: FormGroup;
  project:Project={ projectName: '', projectType:'', teamSkills:'', mainRequirements:''};

  constructor(private fb: FormBuilder, private service: AiService) {
    this.projectForm = this.fb.group({
      projectName: [''],
      projectType: [''],
      teamSkills: [''],
      mainRequirements: ['']
    })
  }


  onSubmit() {
    if (this.projectForm.valid) {
      this.loading = true;
      console.log('Enviando:', this.project);
      this.project=this.projectForm.value;
      // Tu API call aquí
      this.service.getRecommendation(this.project)
      .subscribe()
    }
  }
}
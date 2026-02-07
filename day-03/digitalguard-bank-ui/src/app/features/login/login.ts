import { Component, inject, signal } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../core/services/auth.service';

// UI Modules
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TabsModule } from 'primeng/tabs';
import { CardModule } from 'primeng/card';
import { DialogModule } from 'primeng/dialog';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    ButtonModule,
    InputTextModule,
    TabsModule,
    CardModule,
    DialogModule,
    ToastModule
  ],
  providers: [MessageService],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);
  private messageService = inject(MessageService);

  // --- CAMBIO CLAVE AQUÍ ---
  // Usamos un booleano normal para el [(visible)] del Dialog
  visiblePinModal: boolean = false;

  activeTab = signal<number>(0);

  loginForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
    pin: ['', [Validators.required, Validators.pattern('^[0-9]+$')]]
  });

  registerForm = this.fb.group({
    name: ['', Validators.required],
    secondName: ['', Validators.required],
    username: ['', [Validators.required, Validators.minLength(3)]],
    password: ['', [Validators.required, Validators.minLength(6)]],
    secretPin: ['', [Validators.required, Validators.pattern('^[0-9]{4,6}$')]]
  });

  onLogin() {
    const { username, password } = this.loginForm.value;

    this.authService.login({ username: username!, password: password! }).subscribe({
      next: (res) => {
        console.log('Respuesta del Login:', res);

        // Verificamos si mfaRequired es true O si el mensaje indica que falta el PIN
        if (res.mfaRequired || res.message?.includes('PIN')) {
          console.log('MFA es requerido detectado por mensaje, abriendo modal...');
          this.visiblePinModal = true;
        } else if (res.token) {
          console.log('Token recibido directamente, yendo al dashboard');
          this.router.navigate(['/dashboard']);
        }
      },
      error: (err) => {
        this.showError('Usuario o contraseña incorrectos');
      }
    });
  }

  onVerifyPin() {
    const { username, pin } = this.loginForm.value;
    if (!pin) return;

    // Nota: verifyPin usa signal internamente en el service, no afecta al componente
    this.authService.verifyPin({ username: username!, secretPin: pin! }).subscribe({
      next: (res) => {
        if (res.token) {
          this.visiblePinModal = false; // Cerramos modal
          this.showSuccess('Acceso concedido');
          setTimeout(() => this.router.navigate(['/dashboard']), 500);
        }
      },
      error: () => {
        this.showError('PIN Incorrecto');
        this.loginForm.controls.pin.reset();
      }
    });
  }

  onRegister() {
    if (this.registerForm.invalid) return;

    this.authService.register(this.registerForm.value as any).subscribe({
      next: () => {
        this.showSuccess('Cuenta creada. Inicia sesión.');
        this.activeTab.set(0);
        this.registerForm.reset();
      },
      error: () => this.showError('El usuario ya existe o error en servidor.')
    });
  }

  private showSuccess(msg: string) {
    this.messageService.add({ severity: 'success', summary: 'Éxito', detail: msg });
  }

  private showError(msg: string) {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: msg });
  }
}
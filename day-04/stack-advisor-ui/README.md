
***

### Frontend (`stack-advisor-ui`)


# 💎 Stack Advisor - Frontend

Modern **Angular 18+** web application for AI-powered tech stack recommendations. Built with **PrimeNG v20** (Standalone) and **Reactive Forms**.

## 🚀 Features
- **Standalone Architecture**: No NgModules, self-contained components
- **Reactive Forms**: Robust validation and strong typing
- **Modern UI**: PrimeNG v20 components + PrimeFlex utilities
- **UX**: Loading spinners and structured results visualization

## 🛠️ Tech Stack
- **Angular 18+** (Standalone Components)
- **TypeScript 5+**
- **PrimeNG 20** (UI Components)
- **PrimeFlex** (CSS Utilities)
- **RxJS** (Async handling)

## ⚙️ Installation
```bash
# 1. Install dependencies
npm install

# 2. Install UI libraries
npm install primeng primeicons primeflex

# 3. Run dev server
ng serve
Access at: http://localhost:4200
```
## 🏗️ Key Structure
- **`home.component.ts`**: Main logic. Uses `FormBuilder` for forms and `HttpClient` for backend connection
- **`home.html`**: Template with modern control flow (`@if`, `@for`)
- **`ai.service.ts`**: Injectable service managing API communication (`/api/ai`)
- **`models.ts`**: TypeScript interfaces (`Project`, `StackReport`) for strict typing

## 🎨 Styling
Uses `primeflex` for rapid layout:
- `flex`, `align-items-center`, `justify-content-center` for layout
- `surface-card`, `shadow-2`, `border-round` for cards



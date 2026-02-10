import { Component } from '@angular/core';
import { MediaService } from '../../core/media.service';
import { Media } from '../../shared/models';
import { Observable } from 'rxjs';
import { TableModule } from 'primeng/table'
import { Paginator } from "primeng/paginator";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  imports: [TableModule, Paginator, CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {
  cards: Media[] = [];
  totalRecords = 0;
  first = 0;
  rows = 6;
  loading=false;
  
  constructor(private mediaService:MediaService){}

  ngOnInit(){
    this.loadCards();
    };
  

  onPageChange(event: any) {
    this.first = event.first;
    this.rows = event.rows;
    this.loadCards();
  }

  loadCards() {
    this.loading = true;
    const page = this.first / this.rows;  
    this.mediaService.getMediaPage(page, this.rows).subscribe({
      next: (res) => {
        this.cards = res.content;          
        this.totalRecords = res.totalElements; 
        this.loading = false;
      }
    });
  }

  getTypeClass(type: string): string {
  const colors: { [key: string]: string } = {
    'movie': 'bg-red-500 text-white',
    'serie': 'bg-blue-500 text-white',
    'documental': 'bg-green-500 text-white',
    'video': 'bg-yellow-500 text-white',
    'default': 'bg-gray-500 text-white'
  };
  return colors[type.toLowerCase()] || colors['default'];
}

}

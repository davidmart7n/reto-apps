import { Component } from '@angular/core';
import { MediaService } from '../../core/media.service';
import { Media } from '../../shared/models';
import { Observable } from 'rxjs';
import { TableModule } from 'primeng/table'
import { Paginator } from "primeng/paginator";

@Component({
  selector: 'app-dashboard',
  imports: [TableModule, Paginator],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {
  allMedias: Media[] = [];  // Todos los datos
  cards: Media[] = [];
  totalRecords = 0;
  first = 0;
  rows = 6;
  
  constructor(private mediaService:MediaService){}

  ngOnInit() {
    this.mediaService.getAllMedia().subscribe(medias => {
      this.allMedias = medias;
      this.totalRecords = medias.length;
      this.updateCards();  // ← Pagina inicial
    });
  }

  onPageChange(event: any) {
    this.first = event.first;
    this.rows = event.rows;
    this.updateCards();
  }

  private updateCards() {
    const start = this.first;
    const end = start + this.rows;
    this.cards = this.allMedias.slice(start, end);
  }
}

import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent {
  selectedFile: File | null = null;
  uploadSuccess: boolean = false;

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef) {}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  uploadFile() {
    if (this.selectedFile) {
      const formData = new FormData();
      formData.append('file', this.selectedFile);

      this.http.post('/api/customers/upload', formData, {
        responseType: 'text'
      })
      .subscribe({
        next: (data) => {
          this.uploadSuccess = true;
          this.cdr.detectChanges();
          window.location.reload();
        },
        error: (error) => {
          console.error('Error:', error);
        }
      });
    }
  }
}

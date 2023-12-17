// file-upload.component.ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent {
  selectedFile: File | null = null;

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  uploadFile() {
    if (this.selectedFile) {
      // Implement file upload logic here
      console.log('Uploading file:', this.selectedFile);
      // You can make an HTTP request to upload the file to your server here
    }
  }
}

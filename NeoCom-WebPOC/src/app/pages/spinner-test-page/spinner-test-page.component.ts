import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-spinner-test-page',
  templateUrl: './spinner-test-page.component.html',
  styleUrls: ['./spinner-test-page.component.css']
})
export class SpinnerTestPageComponent implements OnInit {
  public downloading: boolean = true;

  constructor() { }

  ngOnInit() {
    this.downloading = true;
  }

}

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'neocom-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  public getVersion(): string {
    return "v 0.1.0 stable";
  }
}
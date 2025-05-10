import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ModelService } from '../../model.service';

@Component({
  selector: 'app-wrong-access',
  imports: [],
  templateUrl: './wrong-access.component.html',
  styleUrl: './wrong-access.component.css'
})
export class WrongAccessComponent implements OnInit {

  constructor(private model : ModelService, private router:Router) {}

  ngOnInit(): void {
    // if (this.model.defaultUrl != null) {
    //   window.location.href = this.model.defaultUrl
    // }
  }
}

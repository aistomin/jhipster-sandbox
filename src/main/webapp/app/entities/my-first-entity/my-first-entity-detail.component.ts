import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMyFirstEntity } from 'app/shared/model/my-first-entity.model';

@Component({
  selector: 'jhi-my-first-entity-detail',
  templateUrl: './my-first-entity-detail.component.html'
})
export class MyFirstEntityDetailComponent implements OnInit {
  myFirstEntity: IMyFirstEntity | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ myFirstEntity }) => (this.myFirstEntity = myFirstEntity));
  }

  previousState(): void {
    window.history.back();
  }
}

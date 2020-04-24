import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMyFirstEntity, MyFirstEntity } from 'app/shared/model/my-first-entity.model';
import { MyFirstEntityService } from './my-first-entity.service';
import { MyFirstEntityComponent } from './my-first-entity.component';
import { MyFirstEntityDetailComponent } from './my-first-entity-detail.component';

@Injectable({ providedIn: 'root' })
export class MyFirstEntityResolve implements Resolve<IMyFirstEntity> {
  constructor(private service: MyFirstEntityService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMyFirstEntity> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((myFirstEntity: HttpResponse<MyFirstEntity>) => {
          if (myFirstEntity.body) {
            return of(myFirstEntity.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MyFirstEntity());
  }
}

export const myFirstEntityRoute: Routes = [
  {
    path: '',
    component: MyFirstEntityComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'jhipsterApp.myFirstEntity.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MyFirstEntityDetailComponent,
    resolve: {
      myFirstEntity: MyFirstEntityResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterApp.myFirstEntity.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

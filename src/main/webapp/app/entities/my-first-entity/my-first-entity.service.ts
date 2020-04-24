import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMyFirstEntity } from 'app/shared/model/my-first-entity.model';

type EntityResponseType = HttpResponse<IMyFirstEntity>;
type EntityArrayResponseType = HttpResponse<IMyFirstEntity[]>;

@Injectable({ providedIn: 'root' })
export class MyFirstEntityService {
  public resourceUrl = SERVER_API_URL + 'api/my-first-entities';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMyFirstEntity>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMyFirstEntity[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(myFirstEntity: IMyFirstEntity): IMyFirstEntity {
    const copy: IMyFirstEntity = Object.assign({}, myFirstEntity, {
      dateCreated:
        myFirstEntity.dateCreated && myFirstEntity.dateCreated.isValid() ? myFirstEntity.dateCreated.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateCreated = res.body.dateCreated ? moment(res.body.dateCreated) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((myFirstEntity: IMyFirstEntity) => {
        myFirstEntity.dateCreated = myFirstEntity.dateCreated ? moment(myFirstEntity.dateCreated) : undefined;
      });
    }
    return res;
  }
}

import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { MyFirstEntityService } from 'app/entities/my-first-entity/my-first-entity.service';
import { IMyFirstEntity, MyFirstEntity } from 'app/shared/model/my-first-entity.model';

describe('Service Tests', () => {
  describe('MyFirstEntity Service', () => {
    let injector: TestBed;
    let service: MyFirstEntityService;
    let httpMock: HttpTestingController;
    let elemDefault: IMyFirstEntity;
    let expectedResult: IMyFirstEntity | IMyFirstEntity[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MyFirstEntityService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new MyFirstEntity(0, 'AAAAAAA', currentDate, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateCreated: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should return a list of MyFirstEntity', () => {
        const returnedFromService = Object.assign(
          {
            stringField: 'BBBBBB',
            dateCreated: currentDate.format(DATE_FORMAT),
            someNumber: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateCreated: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});

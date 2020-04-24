import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { MyFirstEntityDetailComponent } from 'app/entities/my-first-entity/my-first-entity-detail.component';
import { MyFirstEntity } from 'app/shared/model/my-first-entity.model';

describe('Component Tests', () => {
  describe('MyFirstEntity Management Detail Component', () => {
    let comp: MyFirstEntityDetailComponent;
    let fixture: ComponentFixture<MyFirstEntityDetailComponent>;
    const route = ({ data: of({ myFirstEntity: new MyFirstEntity(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [MyFirstEntityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MyFirstEntityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MyFirstEntityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load myFirstEntity on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.myFirstEntity).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

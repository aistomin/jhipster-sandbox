import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from 'app/shared/shared.module';
import { MyFirstEntityComponent } from './my-first-entity.component';
import { MyFirstEntityDetailComponent } from './my-first-entity-detail.component';
import { myFirstEntityRoute } from './my-first-entity.route';

@NgModule({
  imports: [JhipsterSharedModule, RouterModule.forChild(myFirstEntityRoute)],
  declarations: [MyFirstEntityComponent, MyFirstEntityDetailComponent]
})
export class JhipsterMyFirstEntityModule {}

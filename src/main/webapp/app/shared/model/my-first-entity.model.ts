import { Moment } from 'moment';

export interface IMyFirstEntity {
  id?: number;
  stringField?: string;
  dateCreated?: Moment;
  someNumber?: number;
}

export class MyFirstEntity implements IMyFirstEntity {
  constructor(public id?: number, public stringField?: string, public dateCreated?: Moment, public someNumber?: number) {}
}

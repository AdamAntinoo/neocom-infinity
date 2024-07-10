import { Entity, Schema } from 'redis-om';

export class Blueprint extends Entity {
  public name: string;
  public quantity: number;

  constructor(fields: object = {}) {
    super();
    Object.assign(this, fields);
  }
}

export const schema = new Schema(Blueprint, {
  name: { type: 'string' },
  quantity: { type: 'number' },
});

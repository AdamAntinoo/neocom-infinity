import { Observable } from 'rxjs/Rx';
//--- CLASSES
import { DataSourceLocator } from './DataSourceLocator';
import { EVariant } from './EVariant.enumerated';
//--- MODELS
import { Render } from '../models/Render.model';
import { NeoComNode } from '../models/NeoComNode.model';

export interface IDataSource {
  getLocator(): DataSourceLocator;
  getVariant(): EVariant;
  getVariantName(): string;
  setLocator(locator: DataSourceLocator): void;
  setVariant(variant: EVariant): void;
  collaborate2View(): Observable<Render[]>;
}
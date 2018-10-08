//  PROJECT:     CitaMed.common(CITM.CORE)
//  AUTHORS:     Adam Antinoo - adamantinoo.git@gmail.com
//  COPYRIGHT:   (c) 2018 by Dimensinfin Industries, all rights reserved.
//  ENVIRONMENT: Angular 6.1
//  DESCRIPTION: CitaMed. Componente core. Este projecto contiene gran parte del código Typescript que puede
//               ser reutilizado en otros aplicativos del mismo sistema (CitaMed) o inclusive en otros
//               desarrollos por ser parte de la plataforma MVC de despliegue de nodos extensibles y
//               interacciones con elementos seleccionables.
//--- SERVICES
// import { AppStoreService } from '../../services/appstore.service';
//--- INTERFACES
import { EVariant } from '../EPack.enumerated';
import { INode } from './INode.interface';

export interface ICollaboration {
  collaborate2View(variant?: EVariant): INode[];
}

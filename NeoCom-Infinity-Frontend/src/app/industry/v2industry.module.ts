import { NgModule } from '@angular/core';
import { RendersModule } from '@app/modules/renders/renders.module';
import { V1MiningOperationRenderComponent } from './renders/v1-mining-operation-render/v1-mining-operation-render.component';

@NgModule({
    imports: [
        RendersModule,
    ],
    declarations: [
        V1MiningOperationRenderComponent
    ],
    providers: [
    ],
    exports: [
        V1MiningOperationRenderComponent
    ]
})
export class InfrastructureModule { }

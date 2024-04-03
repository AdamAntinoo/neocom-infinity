import { Controller, Get } from '@nestjs/common';
import { AppService } from 'src/app.service';

@Controller()
export class AssetsController {
  constructor(private readonly appService: AppService) {}

  @Get('assets')
  getAssets(): AssetResponse[] {
    return this.appService.getHello();
  }
}

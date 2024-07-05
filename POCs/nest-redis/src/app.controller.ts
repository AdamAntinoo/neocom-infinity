import { Controller, Get } from '@nestjs/common';
import { AppService } from './app.service';
import { createClient } from 'redis';
import { Blueprint, schema } from './Blueprint.domain';
import { Entity, Schema, Client, Repository } from 'redis-om';

@Controller()
export class AppController {
  private readonly COST_INDEX_BLUEPRINTS_CACHE_NAME: string = 'BCI';
  private readonly REDIS_SEPARATOR: string = ':';
  private client;
  constructor(private readonly appService: AppService) {}

  @Get()
  getHello(): string {
    return this.appService.getHello();
  }
  @Get('redisdata')
  async getRedisData(): Promise<string> {
    const clientConnected = this.createRedisClient();
    console.log(clientConnected);
    const blueprintData = await this.client.get('TYP:17464');
    console.log(blueprintData);
    console.log(JSON.parse(blueprintData));
    return blueprintData;
  }
  private async createRedisClient(): Promise<any> {
    this.client = createClient({
      url: 'redis://localhost:5245',
    });
    this.client.on('error', (err) => console.log('Redis Client Error', err));
    return await this.client.connect();
  }
  public generateBlueprintCostIndexUniqueId(pilotId: number): string {
    return (
      this.COST_INDEX_BLUEPRINTS_CACHE_NAME + this.REDIS_SEPARATOR + pilotId
    );
  }
  // @Get('deserialize/{data}')
  // public deserializeBlueprint(data: string): Blueprint {
  //   // const target = new Blueprint(data['ProcessedBlueprint']);
  // }
  @Get('omdata')
  async writeOmDocument(): Promise<string> {
    // const blue = new Blueprint();
    // blue.name = '-NAME-';
    // blue.quantity = 200;

    const client = new Client();
    await client.open('redis://localhost:5245');
    const rep = client.fetchRepository(schema);
    // const repository = new Repository(schema, client);

    let blue: Blueprint = {
      name: '-NAME-',
      quantity: 300,
    };
    // rep.save(blue)
    // blue.name = '-NAME-';
    // blue.quantity = 200;

    const id = await rep.save(blue); // '01FJYWEYRHYFT8YTEGQBABJ43J'

    console.log(id);

    return id;
  }
}

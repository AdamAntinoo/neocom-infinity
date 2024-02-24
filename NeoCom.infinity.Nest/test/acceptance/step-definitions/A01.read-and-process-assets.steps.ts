import { binding, then, when, before} from 'cucumber-tsflow';
import { assert } from 'chai';
import { Test, TestingModule } from '@nestjs/testing';
import * as request from 'supertest';
import { AppModule } from '../../../src/app.module';

class Context {
  public app;
  public response;
}

// tslint:disable-next-line:max-classes-per-file
@binding([Context])
export class HelloWorldSteps {
  constructor(protected context: Context) {}

  @before()
  public async before(): Promise<void> {
    const moduleFixture: TestingModule = await Test.createTestingModule({
      imports: [AppModule],
    }).compile();

    this.context.app = moduleFixture.createNestApplication();
    await this.context.app.init();
  }
  
  
  @when(/retrieve "([^"]*)" for character "([^"]*)"/)
  public async retrieveCharacterAssets (endpoint:string, characterId:string) {
    // Write code here that turns the phrase above into concrete actions
    return 'pending';
  }

  @then(/the response status code is "([^"]*)"/)
  public statusResponse (status:string) {
    // Write code here that turns the phrase above into concrete actions
    return 'pending';
  }

  @then(/the number of assets should be ([^"]*)/)
  public getAssetCount (assetCount:number) {
    // Write code here that turns the phrase above into concrete actions
    return 'pending';
  }

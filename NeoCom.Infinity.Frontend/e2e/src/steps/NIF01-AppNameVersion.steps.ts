import { expect } from 'chai';
import { Before, Given, Then, When } from 'cucumber';
import { browser } from 'protractor';
import { LoginValidationPage } from '../pages/loginValidation.po';


let page: LoginValidationPage;

Before(() => {
  page = new LoginValidationPage();
});

Given('I am on the login landing page', async () => {
  await page.navigateTo();
});
When('I do nothing', () => { });
Then('I should see a {string} panel', function (panelColor: string) {
  // Write code here that turns the phrase above into concrete actions
  return 'completed';
});
Then('The panel should render the application name', async () => {
  browser.debugger();
  expect(await page.getAppName()).to.equal('NeoCom');
});
Then('The panel should render the application version', async () => {
  expect(await page.getAppVersion()).to.equal('v0.16.1');
});
Then('The panel should render the copyright notice', async () =>{
  expect(await page.getCopyright()).to.equal('© 2019,2020 Dimensinfin Industries');
});

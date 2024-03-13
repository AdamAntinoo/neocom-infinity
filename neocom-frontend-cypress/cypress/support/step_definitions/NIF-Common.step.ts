import { Given } from "@badeball/cypress-cucumber-preprocessor"
import { When, Then } from "@badeball/cypress-cucumber-preprocessor";
import { SupportService } from "./SupportService.support";
import { PlatformConstants } from "./PlatformConstants";
import { NeoComCredential } from "./NeoComCredential.domain";

const BROWSER_ROOT = 'http://localhost:5200/app'
const TITLE_VALIDATION = 'NeoCom.Infinity'
const supportService = new SupportService()

// - A U T H E N T I C A T I O N
Given('a clean cookie repository', function () {
  // - Clear all the application cookies
  cy.clearCookies()
})
Given('a valid Credential on the session storage', function () {
  // - Set a valid Credential on the session storage.
  const credential = new NeoComCredential({
    "uniqueId": "tranquility/92223647",
    "accountId": 92223647,
    "accountName": "Beth Ripley",
    "corporationId": 98384726,
    "assetsCount": 1476,
    "walletBalance": 6.309543632E8,
    "raceName": "Amarr",
    "dataSource": "tranquility"
  })
  supportService.setToSession(PlatformConstants.CREDENTIAL_KEY, JSON.stringify(credential))
})
Given('a valid JWT Token on the session storage', function () {
  // - Set a valid Credential on the session storage.
  const jwtToken: string = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6MTQyNzY2MTU3MywiYWNjb3VudE5hbWUiOiJBZGFtIEFudGlub28iLCJpc3MiOiJOZW9Db20uSW5maW5pdHkuQmFja2VuZCIsInVuaXF1ZUlkIjoidHJhbnF1aWxpdHkvOTIwMDIwNjciLCJwaWxvdElkIjo5MjAwMjA2N30.6JgBvtHyhvD8aY8-I4075tb433mYMpn9sNeYCkIO28LbhqVR4CZ-x1t_sk4IOLLtzSN07bF4c7ZceWw_ta4Brw"
  supportService.setToSession(PlatformConstants.JWTTOKEN_KEY, jwtToken)
})
Given('a valid NEOCOM-INFINITY cookie', function () {
  cy.setCookie('NEOCOM-INFINITY', "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6MTQyNzY2MTU3MywiYWNjb3VudE5hbWUiOiJBZGFtIEFudGlub28iLCJpc3MiOiJOZW9Db20uSW5maW5pdHkuQmFja2VuZCIsInVuaXF1ZUlkIjoidHJhbnF1aWxpdHkvOTIwMDIwNjciLCJwaWxvdElkIjo5MjAwMjA2N30.6JgBvtHyhvD8aY8-I4075tb433mYMpn9sNeYCkIO28LbhqVR4CZ-x1t_sk4IOLLtzSN07bF4c7ZceWw_ta4Brw")
})

Given('the application NeoCom-Infinity-Frontend', () => {
  cy.viewport(1400, 900)
  cy.visit('/')
  cy.title().should('eq', TITLE_VALIDATION)
  cy.get('app-root').as('target-page').as('target')
})

Given('the page {string} is activated', (pageName: string) => {
  switch (pageName) {
    case "Dashboard": {
      const urlRequest = '/industry/mining/miningoperations'
       cy.visit('/app/dashboard')
      break
    }
    case "Industry Dashboard": {
      cy.visit('/app/industry/dashboard')
      break
    }
    case "Mining Operations": {
      cy.visit('/app/industry/mining/miningoperations')
      break
    }
  }
})

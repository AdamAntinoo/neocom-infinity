import { expect } from 'expect';
import { Before, Then, When, setWorldConstructor } from "@cucumber/cucumber";
import { Option } from "@sniptt/monads";
import { V2MiningOperation } from "@Domain/entities/V2.MiningOperation";
import { lastValueFrom, map } from "rxjs";
import { AxiosHeaders, AxiosRequestConfig, AxiosResponse } from "axios";
import { NIN01World } from "../worlds/NIN01World";

// setWorldConstructor(NIN01World)

// Before(async function (scenario) {
//     await this.init(scenario)
// })

When('the endpoint {string} is activated from request', async function (this: NIN01World, endpoint: string) {
    expect(this.httpService).toBeDefined
    expect(this.characterId).toBeDefined
    const request: string = 'http://localhost:3000/nin/v1/character/' + this.characterId + '/miningoperations'
    const headers: AxiosHeaders = new AxiosHeaders()
    headers.set('Content-Type', 'application/json')
    headers.set('x-Neocom-check', 'check header')
    headers.set('Cookie', 'Authentication=Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVC1TaWduYXR1cmUtS2V5IiwidHlwIjoiSldUIn0.eyJzY3AiOlsiZXNpLWFzc2V0cy5yZWFkX2Fzc2V0cy52MSIsImVzaS1pbmR1c3RyeS5yZWFkX2NoYXJhY3Rlcl9taW5pbmcudjEiXSwianRpIjoiY2NhZWFmNjctNjM2ZS00ZDhhLThiNDQtZThlNzViM2ExMjE2Iiwia2lkIjoiSldULVNpZ25hdHVyZS1LZXkiLCJzdWIiOiJDSEFSQUNURVI6RVZFOjkzODEzMzEwIiwiYXpwIjoiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJ0ZW5hbnQiOiJ0cmFucXVpbGl0eSIsInRpZXIiOiJsaXZlIiwicmVnaW9uIjoid29ybGQiLCJhdWQiOlsiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJFVkUgT25saW5lIl0sIm5hbWUiOiJQZXJpY28gVHVlcnRvIiwib3duZXIiOiJRc2lrT2pXUFFERnAzM1hucEl1VzhnM0Z5eFE9IiwiZXhwIjoxNzExNTMwMjExLCJpYXQiOjE3MTE1MjkwMTEsImlzcyI6Imh0dHBzOi8vbG9naW4uZXZlb25saW5lLmNvbSJ9.cVkBkCgLir-kzahfqxjhjNMmQoks1xbt0zthSvWt0Ynuv-rJhI25m4SGReMJSvnjyseh9bmyblJbXOYEJeF_zdbuyP-KRwshWj4hre-VZ4jJPf4Rl-QcdRxPJ-2hPk-w06ltuDCwWUmaCedQauXg9tHKnM8KGapZ64OENaEKbY4A4ilAS0Iukaz9HqqXEuW7rcGAKXvN27yguF2U_hoN3QzCzGcOB0sLyiW1lpjpOC-vO-1X9nc-RUJGK4bYxoVMtxUL1bBrbBmpp2Rb1A43bpsgNGWQQc-PSzasWw2sNX90oYFHKCGQ7_dRCsk2cT7xgcdaAGjVZe9yaIHTg4Ogzg')
    headers.set('Cache-Control', 'max-age: 600, public')
    const config: AxiosRequestConfig = {
        headers: headers
    }
    console.log('step.01')
    const sut = lastValueFrom(this.httpService.get<V2MiningOperation[]>(request, config)
        .pipe(map((axiosResponse: AxiosResponse) => {
            console.log('step.02')
            this.response = axiosResponse
            return axiosResponse.data
        }))
    )
    expect(sut).toBeDefined
    // console.log(sut)
    console.log('step.03')
    return sut.then(data => {
        console.log('step.04')
        this.miningActionsResponse = data
    })
})
Then('there is a Token cookie', function (this: NIN01World) {
    console.log('step.05')
    const tokenFuture: Option<any> = this.authenticationService.authentication.getCurrentToken()
    expect(tokenFuture).toBeDefined
    expect(tokenFuture.isSome).toBeTruthy
    this.token = tokenFuture.unwrap()
    expect(this.token).toBeDefined
})
Then('the received token is valid', function (this: NIN01World) {
    expect(this.authenticationService.authentication.validateToken(this.token)).toBeTruthy
})

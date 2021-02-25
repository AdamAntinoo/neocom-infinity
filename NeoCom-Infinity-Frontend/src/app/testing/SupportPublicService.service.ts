// - CORE
import { Injectable } from '@angular/core';
import { ServerStatus } from '@domain/esi/ServerStatus.domain';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class SupportPublicService {
    public apiV1_GetServerStatus(): Observable<ServerStatus> {
        console.log(">[SupportPublicService.apiV1_GetServerStatus]")
        return new Observable((observer) => {
            observer.next(new ServerStatus(
                {
                    "server": "tranquility",
                    "players": 26172,
                    "backendVersion": "0.20.0",
                    "SDEVersion": "20201231-APP",
                    "start_time": "2021-02-23T11:04:11.000Z",
                    "startAgo": "3 hours, 28 minutes and 33 seconds ago",
                    "nextDowntime": "Downtime in 19 hours, 27 minutes and 15 seconds"
                }
            ))
            observer.complete()
        })
    }
}

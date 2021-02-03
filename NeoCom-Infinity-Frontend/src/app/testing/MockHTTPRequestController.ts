// - CORE
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class MockHTTPRequestController {
    private responseGETQueue: Subject<any> = new Subject()

    constructor() {
        console.log('>Constructing controller')
    }

    public check(): string {
        return 'validated'
    }
    public get(request: string): Observable<any> {
        console.log('step 04')
        return this.responseGETQueue
    }
    public service(method: string, response: any): void {
        console.log('step 06')
        if (method == 'GET') {
            this.responseGETQueue.next(response)
        }
    }
}

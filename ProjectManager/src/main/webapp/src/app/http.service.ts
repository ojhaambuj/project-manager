import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class HttpService {

    constructor(private httpClient: HttpClient) {}

    /**
     * POST method call
     */
    post(body: any, url: string) {
        return this.httpClient.post(url, body, {responseType: 'text'});
    }

    /**
     * GET method call
     */
    get(url: string) {
        return this.httpClient.get(url);
    }

}
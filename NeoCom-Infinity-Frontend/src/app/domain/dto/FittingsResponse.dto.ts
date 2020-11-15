// - DOMAIN
import { NeoComResponse } from './NeoComResponse.dto';
import { Fitting } from '../Fitting.domain';

export class ServerInfoResponse_depre extends NeoComResponse {
    public fittings: Fitting[];

    constructor(values: Object = {}) {
        super(values);
        Object.assign(this, values);
    }
}

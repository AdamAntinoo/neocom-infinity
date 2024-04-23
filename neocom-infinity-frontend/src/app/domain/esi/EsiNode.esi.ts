/**
 * This class encapsulates the construction flow for all Esi class objects. All them should define a decodification method to convert plain JSON objects to the Typescript classed objects.
 */
export abstract class EsiNode {
    constructor(fields: Object = {}) {
        Object.assign(this, fields);
        this.decode()
    }

    protected abstract decode(): void
}

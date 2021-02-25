/**
 * This class encapsulates the construction flow for all Esi class objects. All them should define a decodification method to convert plain JSON objects to the Typescript classed objects.
 */
export abstract class EsiNode {
    constructor(values: Object = {}) {
        Object.assign(this, values);
        this.decode()
    }

    protected abstract decode(): void
}

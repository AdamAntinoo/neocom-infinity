/**
 * Helper class to transform raw json data to an specific type with a provided transformation function.
 */
export class ResponseTransformer {
    public description: string = 'No description.';
    public transform: any;

    public setDescription(newdescription: string): ResponseTransformer {
        this.description = newdescription;
        return this;
    }
    public setTransformation(_function: any): ResponseTransformer {
        this.transform = _function;
        return this;
    }
}

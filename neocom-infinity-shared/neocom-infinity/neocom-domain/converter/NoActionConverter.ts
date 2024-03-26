import { IConverter } from "./IConverter.interface.js";

export class NoActionConverter implements IConverter<any, any>{
    convert(source: any): any {
        return source
    }
}

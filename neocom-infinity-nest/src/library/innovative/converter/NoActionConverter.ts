import { IConverter } from "@Inno/converter/IConverter.interface";

export class NoActionConverter implements IConverter<any, any>{
    convert(source: any): any {
        return source
    }
}

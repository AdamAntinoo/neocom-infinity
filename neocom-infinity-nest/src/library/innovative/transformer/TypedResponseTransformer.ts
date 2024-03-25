import { IConverter } from "@Inno/converter/IConverter.interface"
import { ITransformable } from "@Domain/interfaces/ITransformable.interface"
import { NoActionConverter } from "@Inno/converter/NoActionConverter"

export class TypedResponseTransformer<T extends ITransformable<T>> {
    private converter: IConverter<any, T> = new NoActionConverter()

    constructor(private typedType) { }

    public setConverter(converter: IConverter<any, T>): TypedResponseTransformer<T> {
        this.converter = converter
        return this
    }
    public transform(entrydata: any): T[] {
        let results: T[] = []
        console.log('TypedResponseTransformer.entryData->' + JSON.stringify(entrydata))
        if (entrydata instanceof Array) {
            for (let key in entrydata)
                results.push(this.converter.convert(entrydata[key]).transform())
        } else
            results.push(this.converter.convert(entrydata).transform())

        return results
    }
}

import { Pipe } from '@angular/core'
import { PipeTransform } from '@angular/core'
import { DecimalPipe, formatNumber } from '@angular/common'

@Pipe({
    name: 'm3volume'
})
export class M3VolumePipe implements PipeTransform {
    transform(value: number, decimals?: number): string {
        if (value == null) return '0.0 M3'
        let optimizedValue = value
        const result = formatNumber(Number(optimizedValue), 'en-US', '1.2-2')
        return result + ' M3'
    }
}

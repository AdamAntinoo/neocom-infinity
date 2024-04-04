import { Pipe } from '@angular/core'
import { PipeTransform } from '@angular/core'
import { DecimalPipe, formatNumber } from '@angular/common'

@Pipe({
    name: 'iskscaled'
})
export class IskScaledPipe implements PipeTransform {
    transform(value: number, decimals?: number): string {
        if (value == null) return '0.0 ISK'
        let optimizedValue = value
        let scale = ' ISK'
        if (value > 2000000) {
            optimizedValue = value / 1000.0
            scale = ' kISK'
        }
        if (value > 130000000) {
            optimizedValue = value / 1000000.0
            scale = ' MISK'
        }
        const result = formatNumber(Number(optimizedValue), 'en-US', '1.0-0')
        return result + scale
    }
} 

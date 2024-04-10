import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'capitalizeLetter'
})
export class CapitalizeLetterPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    if (value == null) return "";
    else return value.charAt(0).toUpperCase() + value.slice(1);
  }
}

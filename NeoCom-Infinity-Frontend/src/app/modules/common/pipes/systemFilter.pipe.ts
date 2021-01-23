import { Pipe, PipeTransform } from '@angular/core';
import { LookupSolarSystem } from '@app/modules/planetary/domain/LookupSolarSystem.domain';
import { ICollaboration } from '@innovative/domain/interfaces/ICollaboration.interface';
import { INamed } from '@innovative/domain/interfaces/INamed.interface';

@Pipe({ name: 'systemFilter' })
export class SystemFilterPipe implements PipeTransform {
    /**
     * Filter all the solar systems that contain the parameter string.
     *
     * @param {any[]} items the list of elements to render
     * @param {string} searchText
     * @returns {any[]}
     */
    transform(items: ICollaboration[], searchText: string): any[] {
        if (!items) {
            return [];
        }
        if (!searchText) {
            return items;
        }
        searchText = searchText.toLocaleLowerCase();

        return items.filter(it => {
            // const item = it as INamed
            return it['name'].toLocaleLowerCase().includes(searchText);
        });
    }
}

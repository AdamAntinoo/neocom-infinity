import { Blueprint } from './Blueprint.domain';
import { readFileSync } from 'fs';

describe('Blueprint', () => {
  describe('deserialization', () => {
    it('when receiving a complete blueprint serailization recreate the original', () => {
      const filename: string = './src/source-deserialized.json';
      const fullData = readFileSync(filename, 'utf-8');
      const cleanData = fullData
        .replace('\n', '')
        .replace('\t', '')
        .replace('\\', '');
      const blueprintData = cleanData['ProcessedBlueprint'];
      // const blueprintDataObject = cleanData.ProcessedBlueprint;

      const bluObject = JSON.parse(fullData);
      const processedBlue = bluObject['ProcessedBlueprint'];
      const blueprint = new Blueprint(processedBlue);
      expect(blueprint).toBeDefined();
    });
    // it('when receiving a complete blueprint serailization recreate the original read file', () => {
    //   const filename: string = './src/source-deserialized.json';
    //   const file = readFileSync(filename, 'utf-8');
    //   expect(file).toBeDefined();
    // });
  });
});

import { neocomDomain } from './neocom-domain.js';

it('renders with the correct text', () => {
    expect(neocomDomain()).toEqual('NeoCom Domain available.');
});
